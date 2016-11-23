package br.com.fws.certificado_digital.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Certified implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Customer customer;
	
	@OneToOne
	private CertifiedDetail detail;
	
	private LocalDate date;
	
	
	/**
	 * @deprecated used only by frameworks
	 */
	@SuppressWarnings("unused")
	private Certified(){}
	
	public Certified(Long id, Customer customer, CertifiedDetail detail, LocalDate date) {
		this.id = id;
		this.customer = customer;
		this.detail = detail;
		this.date = date;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public CertifiedDetail getDetail() {
		return detail;
	}


	public void setDetail(CertifiedDetail detail) {
		this.detail = detail;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}
	

}
