package com.Hotwax;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendGmail {
	public static void sendMail(String r) throws Exception{
		System.out.println("doing");

		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myAccGmail= "vinshot75@gmail.com";
		String pswd= "irpgsdbdqaohqnsu";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(myAccGmail, pswd);
			}
		});
		
		Message message = prepareMessage(session,myAccGmail,r);
		Transport.send(message);
		System.out.println("done");		
	}

	private static Message prepareMessage(Session session, String myAccGmail, String r) throws AddressException, MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(myAccGmail));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(r));
		message.setSubject("chala ja bhai");
		message.setText("helloooooo");
		return message;
		
	}
	
}
