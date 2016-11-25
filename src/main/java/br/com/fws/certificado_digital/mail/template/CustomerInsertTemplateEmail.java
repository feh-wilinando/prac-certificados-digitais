package br.com.fws.certificado_digital.mail.template;

import br.com.fws.certificado_digital.helper.UrlHelper;
import br.com.fws.certificado_digital.models.customer.Customer;
import br.com.fws.certificado_digital.services.VelocityService;

public class CustomerInsertTemplateEmail implements TemplateEmail{

	private final String TO = "feh.wilinando@gmail.com";
	private Customer customer;
	private VelocityService velocityService;
	private UrlHelper urlHelper;
	
	public CustomerInsertTemplateEmail(Customer customer, VelocityService velocityService, UrlHelper urlHelper) {
		this.customer = customer;
		this.velocityService = velocityService;
		this.urlHelper = urlHelper;
	}
	
	@Override
	public String getTo() {
		return TO;
	}

	@Override
	public String getSubject() {
		return "Cadastro da empresa: " + customer.getCompanyName();
	}

	@Override
	public String getBody() {
		String activationURL = urlHelper.getBaseURL() + "/services/customer/" + customer.getActivationHash();
		
		return 
		velocityService.loadTemplate("customer.vm")		
						.set("customer", customer)
						.set("magicHash",  activationURL )
						.getContent();		
	}

}
