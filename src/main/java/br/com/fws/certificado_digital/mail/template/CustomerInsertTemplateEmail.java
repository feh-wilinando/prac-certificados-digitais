package br.com.fws.certificado_digital.mail.template;

import br.com.fws.certificado_digital.models.customer.Customer;
import br.com.fws.certificado_digital.qualifier.URLBase;
import br.com.fws.certificado_digital.services.VelocityService;

import javax.inject.Inject;

public class CustomerInsertTemplateEmail implements TemplateEmail{

	private static final long serialVersionUID = 1L;
	private final String TO = "feh.wilinando@gmail.com";
	private Customer customer;

	@Inject
	private VelocityService velocityService;

	@Inject @URLBase
	private String urlBase;


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
		String activationURL = urlBase + "/services/customer/" + customer.getActivationHash();
		
		return 
		velocityService.loadTemplate("customer.vm")		
						.set("customer", customer)
						.set("magicHash",  activationURL )
						.getContent();		
	}

	@Override
	public void setTemplatable(Templatable entity) {
		this.customer = entity.unwrap(Customer.class);
	}

}
