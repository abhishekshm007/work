package com.o9paathshala.test.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.o9paathshala.constants.ErrorMessageConstants;
import com.o9paathshala.dao.ConfiguredQuery;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.util.GenerateRandomString;

/**
 * Servlet implementation class CreateAdvanceTest
 */
@WebServlet("/CreateAdvanceTest")
public class CreateAdvanceTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TMP_DIR_PATH=System.getenv("OPENSHIFT_DATA_DIR");
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH=System.getenv("OPENSHIFT_DATA_DIR");
	private File destinationDir; 
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateAdvanceTest.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAdvanceTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
//		tmpDir=new File(TMP_DIR_PATH+File.separator+"users"+File.separator+((SessionDTO)session.getAttribute("user")).getCurrentInstituteId()+File.separator+"questionbank"+File.separator+"tempfiles");
//		destinationDir=new File(DESTINATION_DIR_PATH+File.separator+"users"+File.separator+((SessionDTO)session.getAttribute("user")).getCurrentInstituteId()+File.separator+"questionbank"+File.separator+"savedfiles");
		tmpDir=new File(getServletContext().getRealPath("/")+"users"+File.separator+((SessionDTO)session.getAttribute("user")).getCurrentInstituteId()+File.separator+"questionbank"+File.separator+"tempfiles");
		destinationDir=new File(getServletContext().getRealPath("/")+"users"+File.separator+((SessionDTO)session.getAttribute("user")).getCurrentInstituteId()+File.separator+"questionbank"+File.separator+"savedfiles");
		System.out.println(destinationDir);
		String newFileName=GenerateRandomString.randomString();
		DAO dao=DAOFactory.getDAOObject();
		List<ConfiguredQuery> cqList=new ArrayList<ConfiguredQuery>();
		ConfiguredQuery cq=null;
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		PreparedStatementDTO psDTO=new PreparedStatementDTO();
		File filePath=new File(destinationDir,newFileName+"."+"csv");
		StringBuilder data = new StringBuilder();
		FileOutputStream fos = new FileOutputStream(filePath);
		String jsonString=null;
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
		String fileName=null;
		List<?> items=null;
		File file=null;
		int result=0;
		boolean message=false;
		boolean answer=false;
		try {
			items = uploadHandler.parseRequest(request);
		
		Iterator<?> iterator = items.iterator();
		String testid=null;
		int tq=0;
		int countquestion=0;
		String temp=null;
		FileItem item = (FileItem) iterator.next();
		if(item.getFieldName().equalsIgnoreCase("testid")){
			testid=item.getString();
		}
		item = (FileItem) iterator.next();
		if(item.getFieldName().equalsIgnoreCase("tq")){
			tq=Integer.parseInt(item.getString());
		}
		String paperid=testid+"_p";
		String answerid=testid+"_r";
		while(iterator.hasNext()) {
			item = (FileItem) iterator.next();
			if(item.isFormField()) {
				String fieldName=item.getFieldName();
				if(NumberUtils.isNumber(fieldName)){
					if(fieldName.equals(temp)){
					data.append(","+item.getString());	
					continue;
					}else{
						if(temp!=null){
						data.append(";");
						}
					}
					temp=fieldName;
					data.append(testid+"/"+fieldName+"/"+item.getString());
					countquestion++;
				}
			}else{
				if(item.getSize()>0){
					if(item.getFieldName().equalsIgnoreCase("testpaper")){
						fileName=paperid;
					}else{
						if(item.getFieldName().equalsIgnoreCase("answerpaper")){
							fileName=answerid;
							answer=true;
						}
					}
					fullName = item.getName().trim();
					String extension = FilenameUtils.getExtension(fullName);
					file = new File(destinationDir,fileName+"."+extension);
					item.write(file);
				}
			}
		}
		if(countquestion!=tq){
			jsonString = gson.toJson(ErrorMessageConstants.REQUIRED_ERROR);
			return;
		}
		data.append(";");
		fos.write(data.toString().getBytes());
		fos.close();
		cq=new ConfiguredQuery();
		cq.setPsList(null);
		cq.setQuery(SQLConstants.ADD_ADVANCE_TEST_QUESTION.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()).replace("?", filePath.getAbsolutePath().replace("\\", "//")));
		cqList.add(cq);
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(1);
		psDTO.setValue(Integer.parseInt(testid));
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.STRING);
		psDTO.setPosition(2);
		psDTO.setValue(paperid);
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.STRING);
		psDTO.setPosition(3);
		if(answer){
		psDTO.setValue(answerid);
		}else{
			psDTO.setValue("");
		}
		psList.add(psDTO);
		
		cq=new ConfiguredQuery();
		cq.setPsList(psList);
		cq.setQuery(SQLConstants.ADVANCE_TEST_DOC.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()));
		cqList.add(cq);
		result=dao.cud(cqList);
		if(result>0){
			message=true;
		}jsonString = gson.toJson(message);
	} catch ( Exception e) {
		LOGGER.error(e.getMessage(), e);
		jsonString = gson.toJson(message);
	}finally{
		
		out.println(jsonString);
		out.close();
	}
	}
}
