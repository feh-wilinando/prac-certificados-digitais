package br.com.fws.certificado_digital.mail.template;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import br.com.fws.certificado_digital.models.customer.Certified;
import br.com.fws.certificado_digital.models.customer.Customer;
import br.com.fws.certificado_digital.qualifier.URLBase;
import br.com.fws.certificado_digital.services.VelocityService;

import javax.inject.Inject;

public class CertifiedRequestTemplateEmail implements TemplateEmail {

	private static final long serialVersionUID = 1L;
	private static final String TO = "feh.wilinando@gmail.com";

	@Inject
	private VelocityService velocityService;

	private Certified certified;

	@Inject @URLBase
	private String urlBase;


	@Override
	public String getTo() {
		return TO;
	}

	@Override
	public String getSubject() {
		return "Solicitação de Certificado: " + certified.getCustomer().getCompanyName();
	}

	@Override
	public String getBody() {
		Customer customer = certified.getCustomer();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
		
		return				
				velocityService
					.loadTemplate("certified.vm")
					.set("certified", certified)
					.set("urlBase", urlBase)
					.set("dateTimeFormatter", dateTimeFormatter)
					.set("decimalFormat", decimalFormat)
				.getContent();
	}

	@Override
	public void setTemplatable(Templatable entity) {
		this.certified = entity.unwrap(Certified.class);
	}

}
