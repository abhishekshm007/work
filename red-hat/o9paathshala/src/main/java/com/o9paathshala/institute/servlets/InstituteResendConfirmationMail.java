package com.o9paathshala.institute.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.o9paathshala.constants.ApplicationContants;
import com.o9paathshala.security.EncoderDecoder;
import com.o9paathshala.security.Encryptor;
import com.o9paathshala.security.mail.SendMail;
import com.o9paathshala.util.CapitalizeString;

/**
 * Servlet implementation class InstituteResendConfirmationMail
 */
@WebServlet("/InstituteResendConfirmationMail")
public class InstituteResendConfirmationMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(InstituteResendConfirmationMail.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstituteResendConfirmationMail() {
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
		PrintWriter out = response.getWriter();
		try{
			
			final String  email = EncoderDecoder.decode(request.getParameter("req").trim());
			final String name = request.getParameter("lk").trim();
			new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						StringBuilder contents = new StringBuilder();
						BufferedReader input =  new BufferedReader(new FileReader(getServletContext().getRealPath("instituteConfirmMailTemplate.html")));
						String   line = null; //not declared within while loop
					        while (( line = input.readLine()) != null){
					          contents.append(line);
					          contents.append(System.getProperty("line.separator"));
					        }
						String body  = contents.toString().replace("PUT_CONFIRMATION_LINK_HERE", ApplicationContants.HOST+"/setPassword.jsp?id="+Encryptor.encryptSHA512(email))
								.replace("PUT_HOME_LINK_HERE", ApplicationContants.HOST);
						body = body.replace("PUT_INSTITUTE_NAME_HERE", CapitalizeString.get(name));
						new SendMail().send(email, ApplicationContants.MAIL_SUBJECT, body);
								input.close();
					} catch ( MessagingException | IOException | NoSuchAlgorithmException e) {
						LOGGER.error(e.getMessage(),e);
					}
				}
			}).start();

			response.setStatus(HttpServletResponse.SC_OK);
			out.print("Email has been resend, please check your inbox...");
			return;
		}catch(Exception e){
			LOGGER.error("Resend Institute Confirmation Link error",e);
			out.print("Some error occured, please try again!!!");
		}
	}

}
