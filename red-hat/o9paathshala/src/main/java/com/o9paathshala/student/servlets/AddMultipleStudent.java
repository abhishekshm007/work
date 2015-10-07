package com.o9paathshala.student.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.o9paathshala.constants.ApplicationContants;
import com.o9paathshala.constants.MailConstants;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.dto.UserDTO;
import com.o9paathshala.security.mail.SendMail;
import com.o9paathshala.util.CapitalizeString;
import com.o9paathshala.util.GenerateRandomString;
import com.o9paathshala.util.ProcessExcel;

/**
 * Servlet implementation class AddMultipleStudent
 */
@WebServlet("/AddMultipleStudent")
public class AddMultipleStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TMP_DIR_PATH=System.getenv("OPENSHIFT_DATA_DIR");
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH=System.getenv("OPENSHIFT_DATA_DIR");
	private File destinationDir; 


	/**
	 * Servlet implementation class GetAllStudents
	 */

	private static final Logger LOGGER = LoggerFactory.getLogger(AddMultipleStudent.class);


	public AddMultipleStudent() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();

		tmpDir=new File(TMP_DIR_PATH+File.separator+"users"+File.separator+((SessionDTO)session.getAttribute("user")).getCurrentInstituteId()+File.separator+"student"+File.separator+"tempfiles");
		destinationDir=new File(DESTINATION_DIR_PATH+File.separator+"users"+File.separator+((SessionDTO)session.getAttribute("user")).getCurrentInstituteId()+File.separator+"student"+File.separator+"savedfiles");
		String batch=null;
		Boolean message=false;
		String newFileName=GenerateRandomString.randomString();
		int result=0;
		PrintWriter out=response.getWriter();
		DAO dao=DAOFactory.getDAOObject();
		Gson gson=new Gson();
		response.setContentType("text/html");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();
		fileItemFactory.setSizeThreshold(1*1024*1024); //1 MB
		fileItemFactory.setRepository(tmpDir);
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		List<UserDTO> users = null;
		String fullName = null;
		File file = null;
		String path=null;
		String jsonString =null;
		try {
			List<?> items = uploadHandler.parseRequest(request);
			Iterator<?> iterator = items.iterator();
			while(iterator.hasNext()) {

				FileItem item = (FileItem) iterator.next();
				if(item.isFormField()) {
					if(!("add").equalsIgnoreCase(item.getFieldName())){
						batch=item.getString();
					}

				}else{
					fullName = item.getName().trim();
					String extension = FilenameUtils.getExtension(fullName);
					file = new File(destinationDir,newFileName+"."+extension);
					System.out.println(DESTINATION_DIR_PATH+File.separator+"users"+File.separator+((SessionDTO)session.getAttribute("user")).getCurrentInstituteId()+File.separator+"student"+File.separator+"savedfiles"+newFileName+"."+extension);
					item.write(file);
					if(("xlsx").equalsIgnoreCase(extension.trim())){
						users=ProcessExcel.convertStudentXLSXToCSV(file);
						path=file.getAbsolutePath().replace("xlsx", "csv").replace("\\","//");
					}else if(("xls").equalsIgnoreCase(extension.trim())){
						users=ProcessExcel.convertStudentXLSToCSV(file);
						
						path=file.getAbsolutePath().replace("xls", "csv").replace("\\","//");
				
					}
				}
			}
			result=dao.cud(SQLConstants.UPLOAD_STUDENTS.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()).replace("path", path).replace("?", batch),null);
			if(result>0){
				message=true;
				final List<UserDTO> s=users;
				new Thread(new Runnable(){
					@Override
					public void run() {
						try {
							StringBuilder contents = new StringBuilder();
							BufferedReader input =  new BufferedReader(new FileReader(getServletContext().getRealPath("LoginDataMailTemplate.html")));
							String   line = null; //not declared within while loop
						        while (( line = input.readLine()) != null){
						          contents.append(line);
						          contents.append(System.getProperty("line.separator"));
						        }
							String body  = null;
							for(UserDTO user:s){
								 body  = contents.toString().replace("PUT_LOGIN_LINK_HERE", ApplicationContants.LOGIN_HOST);
								body = body.replace("PUT_USER_NAME_HERE", CapitalizeString.get(user.getName())).replace("PUT_USER_EMAIL_HERE", user.getEmail()).replace("PUT_PASSWORD_HERE", user.getPassword());
								new SendMail().send(user.getEmail(), MailConstants.SUBJECT, body);
							}
									input.close();
						} catch ( MessagingException | IOException e) {
							LOGGER.error(e.getMessage(),e);
						}
					}
				}).start();
			}
			jsonString= gson.toJson(message);
		}catch(Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			jsonString = gson.toJson(message);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}
}
