package br.com.fws.certificado_digital.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.fws.certificado_digital.models.customer.Customer;

public class CustomerDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	
	
	public Customer findById(Long id){
		return manager.find(Customer.class, id);
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

	public boolean exist(String companyRegistration) {
		
		try{
			manager
			.createQuery("select c from Customer c where c.companyRegistration = :companyRegistration", Customer.class)
			.setParameter("companyRegistration", companyRegistration)
			.getSingleResult();
			
			return true;
		}catch (NoResultException e) {
			
			return false;
		}
	}

	public Customer loadByCompanyRegistration(String companyRegistration) {
		return manager
				.createQuery("select c from Customer c where c.companyRegistration = :companyRegistration", Customer.class)
				.setParameter("companyRegistration", companyRegistration)
				.getSingleResult();
	}
	
}
