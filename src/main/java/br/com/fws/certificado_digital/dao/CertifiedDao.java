package br.com.fws.certificado_digital.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.fws.certificado_digital.models.Certified;
import br.com.fws.certificado_digital.security.CurrentCustomer;

public class CertifiedDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;
	
	@Inject
	private CurrentCustomer currentCustomer;
	
	public List<Certified> all(){
		return manager.createQuery("select c from Certified c join fetch c.empresa e where e.id = :id",Certified.class)
				.setParameter("id", currentCustomer.get().getId())
				.getResultList();
	}

	public Certified findById(Long id) {
		return manager.find(Certified.class, id);
	}

	public void save(Certified certified) {
		manager.persist(certified);
	}
}
