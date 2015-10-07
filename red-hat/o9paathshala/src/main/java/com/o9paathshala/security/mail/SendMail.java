package com.o9paathshala.security.mail;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	private String username;
	private String password;
	private String smtpHost;
	private Integer smtpPort;
	
	private void setProperty(){
		ResourceBundle resourceBundle = ResourceBundle.getBundle("mail");
		username = resourceBundle.getString("username").trim();
		password = resourceBundle.getString("password").trim();
		smtpHost = resourceBundle.getString("smtpHost").trim();
		smtpPort = Integer.parseInt(resourceBundle.getString("smtpPort").trim());
	}
	public boolean send(String recipient, String subject, String body) throws AddressException, MessagingException{
		setProperty();
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", smtpPort);
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getInstance(props, auth);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(recipient));
			message.setSubject(subject);
			message.setContent(body,"text/html");
			Transport.send(message);
			return true;
		
	}
}
