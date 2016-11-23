package br.com.fws.certificado_digital.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import br.com.caelum.stella.type.Estado;
import br.com.fws.certificado_digital.dao.CustomerDao;
import br.com.fws.certificado_digital.mail.MailSender;
import br.com.fws.certificado_digital.models.Customer;
import br.com.fws.certificado_digital.security.CurrentCustomer;
import br.com.fws.certificado_digital.services.VelocityService;


@Model
public class CustomerBean {

	private Long id;
	private Customer customer;
	private CurrentCustomer currentCustomer;
	private MailSender mailSender;
	private CustomerDao dao;
	private VelocityService velocityService;
	private HttpServletRequest request;
	
	@Inject
	public CustomerBean(Customer customer, CurrentCustomer currentCustomer, MailSender mailSender, CustomerDao dao, VelocityService velocityService, HttpServletRequest request){		
		this.customer = customer;
		this.currentCustomer = currentCustomer;
		this.mailSender = mailSender;
		this.dao = dao;
		this.velocityService = velocityService;
		this.request = request;
	}
	
	public void loadViewParam(){
		
		Customer actualCustomer = currentCustomer.get();
		
		if (currentCustomer.isLogged()) {			
			
			if (id != null && id.equals(actualCustomer.getId())) {
				this.customer = actualCustomer;
			}
		}
				
	}
	
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Estado[] getEstados(){
		return Estado.values();
	}
	
	@Transactional
	public String alterar(){
		
		dao.update(customer);
		
		return "formulario.xhtml?faces-redirect=true";
	}

	@Transactional
	public String incluir(){
		
		String url = getUrlAtivationServices();
		
		dao.save(customer);						
		String body = velocityService
							.loadTemplate("email.vm")
							.set("customer", customer)
							.set("magicHash", url + customer.getActivationHash() )
							.getContent();
		 
		mailSender.send("Prac Certificados<prac@prac.com.br>", "feh.wilinando@gmail.com", "Cadastro de Empresa", body );
		
		return "formulario.xhtml?faces-redirect=true";
	}

	private String getUrlAtivationServices() {
		String contextPath 	= request.getContextPath();
		String requestURL 	= request.getRequestURL().toString();
		
		int contextPathPosition = requestURL.indexOf(contextPath);
		
		String host = requestURL.substring(0, contextPathPosition);
		
		
		return host + contextPath + "/services/customer/";
	}
	
}