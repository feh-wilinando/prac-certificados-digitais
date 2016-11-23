package br.com.fws.certificado_digital.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.fws.certificado_digital.models.users.Role;

public class RoleDao {

	@PersistenceContext
	private EntityManager manager;
	
	
	public Role loadByName(String nome){
		return manager.find(Role.class, nome);
	}
	
}
