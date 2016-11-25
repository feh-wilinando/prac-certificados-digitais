package br.com.fws.certificado_digital.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.fws.certificado_digital.models.customer.Customer;

@Named
@SessionScoped
public class CurrentCustomer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Customer customer;
	
	
	public Customer get(){
		return customer;
	}
	
	public void set(Customer customer){
		this.customer = customer;
	}
	
	public void unSet(){
		this.customer = null;
	}
	
	
	public boolean isLogged(){
		return customer != null;
	}

}
