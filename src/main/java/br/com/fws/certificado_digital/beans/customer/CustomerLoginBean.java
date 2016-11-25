package br.com.fws.certificado_digital.beans.customer;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.fws.certificado_digital.dao.CustomerDao;
import br.com.fws.certificado_digital.helper.MessageHelper;
import br.com.fws.certificado_digital.models.customer.Customer;
import br.com.fws.certificado_digital.security.CurrentCustomer;

@Model
public class CustomerLoginBean {

	
	private Customer customer = new Customer();
	
	@Inject
	private CustomerDao dao;
	
	@Inject
	private CurrentCustomer currentCustomer;
	
	@Inject
	private MessageHelper messageHelper;
	
	public Customer getCustomer() {
		return customer;
	}
	
	public String login(){
		String companyRegistration = customer.getCompanyRegistration();
		
		if (dao.exist(companyRegistration)) {
			
			customer = dao.loadByCompanyRegistration(companyRegistration);
			
			currentCustomer.set(customer);
			
			return "/certificados/listagem?faces-redirect=true";
		}
		
		
		messageHelper
			.onFlash()
				.addErrorMessage("Cliente não encontrado", "Favor efetuar o cadastro!");
		
		return "/clientes/formulario?faces-redirect=true";
	}
	
	
	public String logout(){
		currentCustomer.unSet();
		
		return "clientes/login?faces-redirect=true";
	}
	
}
