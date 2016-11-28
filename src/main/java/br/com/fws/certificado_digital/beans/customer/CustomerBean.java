package br.com.fws.certificado_digital.beans.customer;

import java.util.UUID;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.caelum.stella.type.Estado;
import br.com.fws.certificado_digital.dao.CustomerDao;
import br.com.fws.certificado_digital.helper.JMSHelper;
import br.com.fws.certificado_digital.helper.MailerHelper;
import br.com.fws.certificado_digital.helper.MessageHelper;
import br.com.fws.certificado_digital.factory.UrlFactory;
import br.com.fws.certificado_digital.mail.template.CustomerInsertTemplateEmail;
import br.com.fws.certificado_digital.models.customer.Customer;
import br.com.fws.certificado_digital.security.CurrentCustomer;
import br.com.fws.certificado_digital.services.VelocityService;


@Model
public class CustomerBean {

	private Long id;
	private Customer customer = new Customer();
	
	@Inject
	private CustomerDao dao;
	
	@Inject
	private MessageHelper messageHelper;

	@Inject
	private CurrentCustomer currentCustomer;

	@Inject
	private CustomerInsertTemplateEmail template;

	@Inject
	private JMSHelper jmsHelper;


	public void loadViewParam(){
		if (id != null) {
			this.customer = dao.findById(id);
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
	public String sendToReview(){
		if (customer.getId() != null) {
			String activationCode = UUID.randomUUID().toString();
			
			customer.setActivationHash(activationCode);
			customer.setActive(false);
			dao.update(customer);
			
		}else{
			dao.save(customer);			
		}
		
		
		template.setTemplatable(customer);
		
		jmsHelper.sendTemplate(template);
		
		messageHelper
			.onFlash()
				.addInfoMessage("Cliente incluído para análise", "O Resultado da análise será enviado para o e-mail do cadastro!");


		if (currentCustomer.isLogged()){
			return "/certificacos/listatem?faces-redirect=true";
		}else{
			return "/clientes/formulario?faces-redirect=true";
		}
	}

	
}