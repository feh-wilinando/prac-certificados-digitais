package br.com.fws.certificado_digital.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.fws.certificado_digital.models.customer.Certified;
import br.com.fws.certificado_digital.models.customer.Customer;

public class CertifiedDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;
	
	public List<Certified> allOf(Customer customer){
		return manager.createQuery("select c from Certified c where c.customer = :customer",Certified.class)
				.setParameter("customer", customer)
				.getResultList();
	}

	public Certified findById(Long id) {
		return manager.find(Certified.class, id);
	}

	public void save(Certified certified) {
		manager.persist(certified);
	}

	public void update(Certified certified) {
		manager.merge(certified);
	}
}
