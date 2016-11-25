package br.com.fws.certificado_digital.helper;

import javax.inject.Inject;

import br.com.fws.certificado_digital.mail.MailSender;
import br.com.fws.certificado_digital.mail.template.TemplateEmail;

public class MailerHelper {

	@Inject
	private MailSender mailSender;

	private String from = "Prac Certificado Digital<cadastro@prac.com.br>";
	
	
	public void sendFromTemplate(TemplateEmail template){		
		mailSender.send(from, template.getTo(), template.getSubject(), template.getBody() );
	}
}
