package br.com.fws.certificado_digital.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.fws.certificado_digital.dao.CustomerDao;
import br.com.fws.certificado_digital.dao.UserDao;
import br.com.fws.certificado_digital.models.Customer;
import br.com.fws.certificado_digital.models.users.User;

@Model
public class CurrentCustomer {

	private Customer customer;
	
	private UserDao userDao;
	private CustomerDao customerDao;
	private HttpServletRequest request;
	
	@Inject
	public CurrentCustomer(UserDao userDao, CustomerDao customerDao, HttpServletRequest request) {
		this.userDao = userDao;
		this.customerDao = customerDao;
		this.request = request;
	}
	
	@PostConstruct
	public void loadCurrent(){
		Principal principal = request.getUserPrincipal();
		
		if (principal != null){
			User currentUser = userDao.loadByEmail(principal.getName());
			this.customer = customerDao.loadByUser(currentUser);
		}
		
	}
	
	public Customer get() {
		return customer;
	}
	
	public boolean isLogged(){
		return customer != null;
	}
	
}
