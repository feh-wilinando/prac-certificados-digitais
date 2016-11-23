package br.com.fws.certificado_digital.mail;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@ApplicationScoped
public class MailSender {

	@Resource(name="java:jboss/mail/prac")
	private Session session;
	
	
	public void send(String from, String to, String subject, String body){
		MimeMessage message = new MimeMessage(session);
		
		try {
			message.setRecipients(RecipientType.TO, InternetAddress.parse(to));
			message.setFrom(new InternetAddress(from));
			message.setSubject(subject);
			message.setContent(body,"text/html");
						
			Transport.send(message);
			
		} catch (MessagingException e) {			
			throw new RuntimeException(e);
		}
		
	}
	
}
