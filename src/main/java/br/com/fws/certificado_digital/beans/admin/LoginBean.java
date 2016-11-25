package br.com.fws.certificado_digital.beans.admin;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.com.fws.certificado_digital.dao.UserDao;
import br.com.fws.certificado_digital.models.admin.User;

@Model
public class LoginBean {
	
	private User user;
	private HttpServletRequest request;
	private UserDao dao;
	
	@Inject
	public LoginBean(User user, HttpServletRequest request, UserDao dao) {
		this.user = user;
		this.request = request;
		this.dao = dao;
	}	
	
	public User getUser() {
		return user;
	}
	
	
	public String efetuaLogin(){
		
		if (loginValidation()) {									
			return "/certificados/listagem?faces-redirect=true";		
		}
		
		return "/users/login";
		
	}

	public String logoff() throws ServletException{		
		request.logout();		
		return "/users/login?faces-redirect=true";
	}
	
	
	private boolean loginValidation() {
		
		try {
			User tempUser = dao.loadByEmail(user.getEmail());
			if (tempUser.isActive()) {
				request.login(user.getEmail(), user.getPassword());			
				return true;				
			}else{
				return false;
			}
		} catch (ServletException | NoResultException e) {			
			return false;
		}
		
	}
	
	
}
