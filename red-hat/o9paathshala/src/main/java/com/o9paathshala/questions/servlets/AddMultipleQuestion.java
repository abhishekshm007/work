package com.o9paathshala.questions.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

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
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.util.GenerateRandomString;
import com.o9paathshala.util.ProcessExcel;

/**
 * Servlet implementation class AddMultipleStudent
 */
@WebServlet("/AddMultipleQuestion")
public class AddMultipleQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TMP_DIR_PATH=System.getenv("OPENSHIFT_DATA_DIR");
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH=System.getenv("OPENSHIFT_DATA_DIR");
	private File destinationDir; 
	private static final Logger LOGGER = LoggerFactory.getLogger(AddMultipleQuestion.class);


	public AddMultipleQuestion() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		tmpDir=new File(TMP_DIR_PATH+File.separator+"users"+File.separator+((SessionDTO)session.getAttribute("user")).getCurrentInstituteId()+File.separator+"questionbank"+File.separator+"tempfiles");
		destinationDir=new File(DESTINATION_DIR_PATH+File.separator+"users"+File.separator+((SessionDTO)session.getAttribute("user")).getCurrentInstituteId()+File.separator+"questionbank"+File.separator+"savedfiles");
		String subject=null;
		String jsonString=null;
		boolean message=false;
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
		String fullName = null;
		File file = null;
		String path=null;
		try {
			List<?> items = uploadHandler.parseRequest(request);
			Iterator<?> iterator = items.iterator();
			while(iterator.hasNext()) {

				FileItem item = (FileItem) iterator.next();
				if(item.isFormField()) {
					if(!("add").equalsIgnoreCase(item.getFieldName())){
						subject=item.getString();
					}

				}else{
					fullName = item.getName().trim();
					String extension = FilenameUtils.getExtension(fullName);
					file = new File(destinationDir,newFileName+"."+extension);
					item.write(file);
					if(("xlsx").equalsIgnoreCase(extension.trim())){
						path=ProcessExcel.convertQuestionXLSXToCSV(file);
					}else if(("xls").equalsIgnoreCase(extension.trim())){
						path=ProcessExcel.convertQuestionXLSToCSV(file);
					}
				}
			}
			result=dao.cud(SQLConstants.UPLOAD_QUESTION.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()).replace("path", path).replace("?", subject),null);
			if(result>0){
				message=true;
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
