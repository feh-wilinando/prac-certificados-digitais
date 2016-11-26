package br.com.fws.certificado_digital.mail.template;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import br.com.fws.certificado_digital.models.customer.Certified;
import br.com.fws.certificado_digital.models.customer.Customer;
import br.com.fws.certificado_digital.services.VelocityService;

public class CertifiedRequestTemplateEmail implements TemplateEmail {

	private static final long serialVersionUID = 1L;
	private static final String TO = "feh.wilinando@gmail.com";	
	private VelocityService velocityService;
	private Certified certified;
	
	
	public CertifiedRequestTemplateEmail(Certified certified, VelocityService velocityService) {
		this.certified = certified;
		this.velocityService = velocityService;
	}
	
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
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
		
		return				
				velocityService
					.loadTemplate("certified.vm")
					.set("companyName", customer.getCompanyName())
					.set("companyRegistration", customer.getCompanyRegistration())
					.set("contact", customer.getContact())
					.set("jobTitle", customer.getJobTitle())
					.set("email", customer.getEmail())
					.set("telephone", customer.getTelephoneNumber())
					.set("cellPhone", customer.getCellPhoneNumber())
					.set("invoice", certified.getInvoice())
					.set("requestDate", certified.getRequestDate().format(formatter))
					.set("quantity", certified.getQuantity())
					.set("weigth", decimalFormat.format(certified.getWeigth()))
					.set("client_id", customer.getId())
					.set("certified_id", certified.getId())
				.getContent();
	}

}
