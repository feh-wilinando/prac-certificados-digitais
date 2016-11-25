package br.com.fws.certificado_digital.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.fws.certificado_digital.dao.UserDao;
import br.com.fws.certificado_digital.models.admin.User;

@Model
public class CurrentUser {

	@Inject
	private HttpServletRequest request;
	
	@Inject
	private UserDao dao;
	
	private User user;
	
	
	@PostConstruct
	public void laodUser(){
		Principal principal = request.getUserPrincipal();		
		if (principal != null) {
			String email = principal.getName();
			
			user = dao.loadByEmail(email);			
		}
	}
		
	public User get(){		
		return user;
	}
	
	public boolean isLogged(){
		return user != null;
	}
	
}
