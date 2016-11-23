package br.com.fws.certificado_digital.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.fws.certificado_digital.models.Customer;
import br.com.fws.certificado_digital.models.users.User;

public class CustomerDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	
	
	public Customer findById(Long id){
		return manager.find(Customer.class, id);
	}
	
	public Customer loadByUser(User user){
		return manager
				.createQuery("select c from Customer c join fetch c.user u where u = :user and u.active = true", Customer.class)
				.setParameter("user",user)
				.getSingleResult();
	}

	public void update(Customer customer) {
		manager.merge(customer);		
	}

	public void save(Customer customer) {
		manager.persist(customer);
	}
	
	public Customer loadByHash(String hash){
		return manager.createQuery("select c from Customer c where c.activationHash = :hash", Customer.class)
				.setParameter("hash", hash)
				.getSingleResult();
	}
	
}
