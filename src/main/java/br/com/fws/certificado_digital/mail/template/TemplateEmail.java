package br.com.fws.certificado_digital.mail.template;

import java.io.Serializable;

public interface TemplateEmail extends Serializable{

	String getTo();
	String getSubject();
	String getBody();
	void setTemplatable(Templatable entity);
}
