package com.o9paathshala.user.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.o9paathshala.constants.HelpConstants;
import com.o9paathshala.security.mail.SendMail;


@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SendMessage() {
        super();
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(SendMessage.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("mail");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String subject=request.getParameter("subject");
		String message=request.getParameter("message");
		StringBuilder contents = new StringBuilder();
		 try {
		BufferedReader input =  new BufferedReader(new FileReader(getServletContext().getRealPath("HelpMailTemplate.html")));
		String   line = null; //not declared within while loop
	        while (( line = input.readLine()) != null){
	          contents.append(line);
	          contents.append(System.getProperty("line.separator"));
	        }
	        String body  = contents.toString().replace("NAME", name).replace("EMAIL", email).replace("SUBJECT", subject).replace("MESSAGE", message);
			
	       
	        	new SendMail().send(resourceBundle.getString("username").trim(), HelpConstants.SUBJECT, body);
	        	input.close();
	        	String jsonString = gson.toJson(true);
				out.println(jsonString);
				out.close();
				return;
			} catch (MessagingException e) {
				LOGGER.error(e.getMessage(),e);
				
			}
	}
}
