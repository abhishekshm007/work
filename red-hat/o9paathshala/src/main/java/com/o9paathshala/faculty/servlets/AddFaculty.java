package com.o9paathshala.faculty.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.o9paathshala.security.Encryptor;
import com.o9paathshala.security.mail.SendMail;
import com.o9paathshala.util.CapitalizeString;
import com.o9paathshala.util.GenerateRandomString;

/**
 * Servlet implementation class AddFaculty
 */
@WebServlet("/AddFaculty")
public class AddFaculty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFaculty() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AddFaculty.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		Gson gson = new Gson();
		String jsonString=null;
		HttpSession session=request.getSession();
		DAO dao=DAOFactory.getDAOObject();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		List<ConfiguredQuery> cqList=new ArrayList<ConfiguredQuery>();
		ConfiguredQuery cq=new ConfiguredQuery();
		PreparedStatementDTO psDTO=new PreparedStatementDTO();
		final String random=GenerateRandomString.randomString();
		final String name=request.getParameter("name");
		final String email=request.getParameter("email");
		psDTO.setDataType(DBConstants.STRING);
		psDTO.setPosition(1);
		psDTO.setValue(email);
		psList.add(psDTO);
		boolean message=false;
		List<Map<String, Object>> resultList = null;
		try {
			resultList=dao.getAll(SQLConstants.CHECK_FACULTY_EMAIL.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
			if(null!=resultList&&1==Integer.parseInt(resultList.get(0).get("count").toString())){
				jsonString = gson.toJson(ErrorMessageConstants.ALREADY_EXIST_EMAIL_ERROR);
				return;
			}
			String password=Encryptor.encryptSHA512(random);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(2);
			psDTO.setValue(name);
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(3);
			psDTO.setValue(password);
			psList.add(psDTO);
			cq.setPsList(psList);
			cq.setQuery(SQLConstants.ADD_FACULTY.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()));
			cqList.add(cq);
			String[] batch = request.getParameterValues("batch");
			for (String id: batch){
				cq=new ConfiguredQuery();
				psList=new ArrayList<PreparedStatementDTO>();
				psDTO=new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(1);
				psDTO.setValue(new Integer(id));
				psList.add(psDTO);
				cq.setPsList(psList);
				cq.setQuery(SQLConstants.ASSIGN_BATCH_TO_FACULTY.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()));
				cqList.add(cq);
			}

			int result=0;
			result=dao.cud(cqList);
			if(result>0){
				message=true;
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
								body = body.replace("PUT_USER_NAME_HERE", CapitalizeString.get(name)).replace("PUT_USER_EMAIL_HERE", email).replace("PUT_PASSWORD_HERE", random);
								new SendMail().send(email, MailConstants.SUBJECT, body);
									input.close();
						} catch ( MessagingException | IOException e) {
							LOGGER.error(e.getMessage(),e);
						}
					}
				}).start();
			}
			jsonString = gson.toJson(message);
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
			jsonString = gson.toJson(message);
		}finally{
			out.println(jsonString);
			out.close();
		}


	}

}
