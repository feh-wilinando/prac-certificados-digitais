package br.com.fws.certificado_digital.mail.template;

public interface TemplateEmail {

	String getTo();
	String getSubject();
	String getBody();
	
}
