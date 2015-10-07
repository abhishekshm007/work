package com.o9paathshala.user.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.o9paathshala.constants.ApplicationContants;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.security.Encryptor;
import com.o9paathshala.security.mail.SendMail;
import com.o9paathshala.util.CapitalizeString;
import com.o9paathshala.util.GenerateRandomString;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ForgotPassword.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword() {
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
	PrintWriter out;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			out = response.getWriter();
			String email = request.getParameter("req").trim();
			String defaultPassword = GenerateRandomString.randomString();
			
			DAO dao = DAOFactory.getDAOObject();
			PreparedStatementDTO ps = null;
			List<PreparedStatementDTO> psList = new ArrayList<PreparedStatementDTO>();
			String sql = null;
			
			if(null == email){
				LOGGER.error("email is not found in request data");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("Invalid Request");
				return;
			}else{
				sql = SQLConstants.FORGOT_PASSWORD;
				ps = new PreparedStatementDTO();
				ps.setDataType(DBConstants.STRING);
				ps.setPosition(1);
				ps.setValue(Encryptor.encryptSHA512(defaultPassword));
				psList.add(ps);
				ps = new PreparedStatementDTO();
				ps.setDataType(DBConstants.STRING);
				ps.setPosition(2);
				ps.setValue(email);
				psList.add(ps);
				
				int result = dao.cud(sql, psList);
				if(result > 0){
					
					final String changedPassword = defaultPassword;
					final String recipient = email;
					new Thread(new Runnable(){
						@Override
						public void run() {
							try {
								StringBuilder contents = new StringBuilder();
								BufferedReader input =  new BufferedReader(new FileReader(getServletContext().getRealPath("resetPasswordMailTemplate.html")));
								String   line = null; //not declared within while loop
							        while (( line = input.readLine()) != null){
							          contents.append(line);
							          contents.append(System.getProperty("line.separator"));
							        }
								String body  = contents.toString().replace("PUT_LINK_HERE", ApplicationContants.HOST)
										.replace("PUT_PASSWORD_HERE", changedPassword);
								new SendMail().send(recipient, ApplicationContants.MAIL_SUBJECT, body);
										input.close();
							} catch ( MessagingException | IOException e) {
								LOGGER.error(e.getMessage(),e);
							}
						}
					}).start();
					
					response.setStatus(HttpServletResponse.SC_OK);
					out.print("Check your email for new password.");
					return; 
					
				}else{
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					out.print("This email is not registered...");
					return;
				}
			}
			
		}catch(Exception e){
			LOGGER.error("exception during forgot password", e);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("There is some error, please try later !!!");
			return;
		}finally{
			if(null != out){
				out.close();
			}
		}
	}

}
