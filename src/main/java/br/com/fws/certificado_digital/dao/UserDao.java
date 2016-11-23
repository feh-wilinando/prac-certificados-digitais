package br.com.fws.certificado_digital.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.fws.certificado_digital.models.users.User;

public class UserDao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="pracPU")
	private EntityManager manager;
	
	

	public User loadByEmail(String email) {
		return manager.find(User.class, email);			
	}
	
	
}
