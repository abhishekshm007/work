package com.o9paathshala.institute.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.o9paathshala.constants.ApplicationContants;
import com.o9paathshala.dao.ConfiguredQuery;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.institute.dto.InstituteRegistrationDTO;
import com.o9paathshala.security.EncoderDecoder;
import com.o9paathshala.security.Encryptor;
import com.o9paathshala.security.mail.SendMail;
import com.o9paathshala.util.CapitalizeString;
import com.o9paathshala.util.Validation;

/**
 * Servlet implementation class InstituteRegistration
 */
@WebServlet("/InstituteRegistration")
public class InstituteRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(InstituteRegistration.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstituteRegistration() {
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
	
	static String recipient = "";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try{
			/*String remoteAddr = request.getRemoteAddr();
	        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
	        reCaptcha.setPrivateKey("6Leu4AMTAAAAAIebdL40KU5PyP_41fYJvyN0wUQX");

	        String challenge = request.getParameter("recaptcha_challenge_field");
	        String uresponse = request.getParameter("recaptcha_response_field");
	        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

	        if (reCaptchaResponse.isValid()) {
	        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("Entered Captcha was wrong, please try again !!!");
				return;
	        }*/
			Gson gson = new Gson();
			InstituteRegistrationDTO data = new InstituteRegistrationDTO();
			data.setConfirmEmail(request.getParameter("confirmEmail"));
			data.setEmail(request.getParameter("email"));
			data.setName(request.getParameter("name"));
			data.setType(request.getParameter("type"));
			if(!Validation.validateEmail(data.getEmail())){
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("Your email is incorrect, please try other email...");
				return;
			}
			if(!data.getEmail().equals(data.getConfirmEmail())){
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("Your email didn't match, please try again...");
				return;
			}
			if(!Validation.validateInstituteName(data.getName())){
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("Your institute name is incorrect,no special character allowed...");
				return;
			}
			
			
			final String encryptedEmail = Encryptor.encryptSHA512(data.getEmail());
			int count = 0;
			
			boolean checkEmail = false;
			boolean checkName = false;
			DAO dao = DAOFactory.getDAOObject();
			List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
			PreparedStatementDTO ps = null;
			List<PreparedStatementDTO> psList = new ArrayList<PreparedStatementDTO>();
			String sql = null;
			ConfiguredQuery cq = null;
			List<ConfiguredQuery> cqList = new ArrayList<ConfiguredQuery>();
			
			psList = new ArrayList<PreparedStatementDTO>();
			ps = new PreparedStatementDTO();
			ps.setDataType(DBConstants.STRING);
			ps.setPosition(1);
			ps.setValue(data.getEmail());
			psList.add(ps);
			sql = SQLConstants.CHECK_INSTITUTE_REGISTRATION_BY_EMAIL;
			
			rs = dao.getAll(sql, psList);
			if(null == rs ){
				checkEmail = true;
			}
			psList = new ArrayList<PreparedStatementDTO>();
			ps = new PreparedStatementDTO();
			ps.setDataType(DBConstants.STRING);
			ps.setPosition(1);
			ps.setValue(data.getName());
			psList.add(ps);
			
			sql = SQLConstants.CHECK_INSTITUTE_REGISTRATION_BY_NAME;
			
			rs = dao.getAll(sql, psList);
			if(null == rs ){
					checkName = true;
			}
			if(checkEmail && checkName){
				psList.clear();
				ps = new PreparedStatementDTO();
				ps.setDataType(DBConstants.STRING);
				ps.setPosition(1);
				ps.setValue(data.getEmail());
				psList.add(ps);
				ps = new PreparedStatementDTO();
				ps.setDataType(DBConstants.STRING);
				ps.setPosition(2);
				ps.setValue(data.getName());
				psList.add(ps);
				ps = new PreparedStatementDTO();
				ps.setDataType(DBConstants.STRING);
				ps.setPosition(3);
				ps.setValue(data.getType());
				psList.add(ps);
				ps = new PreparedStatementDTO();
				ps.setDataType(DBConstants.STRING);
				ps.setPosition(4);
				ps.setValue(encryptedEmail);
				psList.add(ps);
				sql = SQLConstants.REGISTER_INSTITUTE;

				cq = new ConfiguredQuery();
				cq.setPsList(psList);
				cq.setQuery(sql);
				
				cqList.add(cq);
				count = dao.cud(cqList);
				if(count == 0){
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					out.print("A problem occured...please try again...");
					return;
				}
				final InstituteRegistrationDTO data2=data;
				recipient = data.getEmail();
				new Thread(new Runnable(){
					@Override
					public void run() {
						try {
							StringBuilder contents = new StringBuilder();
							BufferedReader input =  new BufferedReader(new FileReader(getServletConfig().getServletContext().getRealPath("/instituteConfirmMailTemplate.html")));
							String   line = null; //not declared within while loop
						        while (( line = input.readLine()) != null){
						          contents.append(line);
						          contents.append(System.getProperty("line.separator"));
						        }
							String body  = contents.toString().replace("PUT_CONFIRMATION_LINK_HERE", ApplicationContants.HOST+"/setPassword.jsp?id="+encryptedEmail)
									.replace("PUT_HOME_LINK_HERE", ApplicationContants.HOST);
							body = body.replace("PUT_INSTITUTE_NAME_HERE", CapitalizeString.get(data2.getName()));
							new SendMail().send(recipient, ApplicationContants.MAIL_SUBJECT, body);
									input.close();
						} catch ( Exception e) {
							LOGGER.error(e.getMessage(),e);
						}
					}
				}).start();

				data.setEmail(EncoderDecoder.encode(data.getEmail()));
				data.setName(data.getName());
				data.setConfirmEmail(null);
				data.setType(null);
				response.setStatus(HttpServletResponse.SC_OK);
				String tranferString = gson.toJson(data);
				out.print(tranferString);
				return;
			}else{
				if(!checkName){
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					out.print("This institute name already exist, please try other name...");
					return;
				}else if(!checkEmail){
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					out.print("This institute email already exist, please try other email...");
					return;
				}
			}

		}catch(Exception e){
			LOGGER.error("Error Occured", e);
			out.print("Some error occur, please try after some time!!!");
		}
		
		
	}

}
