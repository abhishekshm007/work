package com.o9paathshala.batches.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.o9paathshala.constants.ErrorMessageConstants;
import com.o9paathshala.constants.MailConstants;
import com.o9paathshala.dao.ConfiguredQuery;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.dto.UserDTO;
import com.o9paathshala.security.mail.SendMail;
import com.o9paathshala.util.CapitalizeString;
import com.o9paathshala.util.GenerateRandomString;
import com.o9paathshala.util.ProcessExcel;

/**
 * Servlet implementation class AddBatch
 */
@WebServlet("/AddBatch")
public class AddBatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TMP_DIR_PATH=System.getenv("OPENSHIFT_DATA_DIR");
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH=System.getenv("OPENSHIFT_DATA_DIR");
	private File destinationDir; 
	private static final Logger LOGGER = LoggerFactory.getLogger(AddBatch.class);
	public AddBatch() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		return;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		tmpDir=new File(TMP_DIR_PATH+File.separator+"users"+File.separator+((SessionDTO)session.getAttribute("user")).getCurrentInstituteId()+File.separator+"student"+File.separator+"tempfiles");
		destinationDir=new File(DESTINATION_DIR_PATH+File.separator+"users"+File.separator+((SessionDTO)session.getAttribute("user")).getCurrentInstituteId()+File.separator+"student"+File.separator+"savedfiles");
		String name=null;
		boolean message=false;
		String newFileName=GenerateRandomString.randomString();
		int result=0;
		PrintWriter out=response.getWriter();
		DAO dao=DAOFactory.getDAOObject();
		List<ConfiguredQuery> cqList=null;
		ConfiguredQuery cq=null;
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		PreparedStatementDTO psDTO=new PreparedStatementDTO();
		List<Map<String, Object>> resultList = null;
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
		String jsonString=null;
		try {
			List<?> items = uploadHandler.parseRequest(request);
			Iterator<?> iterator = items.iterator();
			while(iterator.hasNext()) {
				FileItem item = (FileItem) iterator.next();
				if(item.isFormField()) {
					name=item.getString();
					psDTO.setDataType(DBConstants.STRING);
					psDTO.setPosition(1);
					psDTO.setValue(name);
					psList.add(psDTO);
					resultList=dao.getAll(SQLConstants.CHECK_BATCH_NAME.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
					if(null!=resultList&&1==Integer.parseInt(resultList.get(0).get("count").toString())){
						jsonString = gson.toJson(ErrorMessageConstants.ALREADY_EXIST_NAME_ERROR);
						
						return;
					}
				}else {
					if(item.getSize()>0){
						fullName = item.getName().trim();
						String extension = FilenameUtils.getExtension(fullName);
						file = new File(destinationDir,newFileName+"."+extension);
						item.write(file);
						String path=null;
						if(("xlsx").equalsIgnoreCase(extension.trim())){
							users=ProcessExcel.convertStudentXLSXToCSV(file);
							path=file.getAbsolutePath().replace("xlsx", "csv").replace("\\", "//");
						}else if(("xls").equalsIgnoreCase(extension.trim())){
							users=ProcessExcel.convertStudentXLSToCSV(file);
							path=file.getAbsolutePath().replace("xls", "csv").replace("\\", "//");

						}
						cqList=new ArrayList<ConfiguredQuery>();
						cq=new ConfiguredQuery();
						cq.setPsList(psList);
						cq.setQuery(SQLConstants.INSERT_BATCH.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()));
						cqList.add(cq);
						cq=new ConfiguredQuery();
						cq.setPsList(null);
						cq.setQuery(SQLConstants.UPLOAD_STUDENT_CSV.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()).replace("?", path));
						cqList.add(cq);
						result=dao.cud(cqList);
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
										String body  = contents.toString().replace("PUT_LOGIN_LINK_HERE", ApplicationContants.LOGIN_HOST);
										for(UserDTO user:s){
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
					}else{
						result=dao.cud(SQLConstants.INSERT_BATCH.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
						if(result>0){
							message=true;
						}
					}
				}
			}
			jsonString = gson.toJson(message);
		}catch(Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			jsonString = gson.toJson(message);
		}finally{
			
			out.println(jsonString);
			out.close();
		}

	}
}

