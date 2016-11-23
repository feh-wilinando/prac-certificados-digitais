package br.com.fws.certificado_digital.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import br.com.caelum.stella.bean.validation.CNPJ;
import br.com.caelum.stella.bean.validation.IE;

@IE(ieField="stateRegistration", estadoField="address.state")
@Entity
public class CertifiedDetail implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String invoice;
	private String generator;
	private Address address;
	
	private String companyName;
	
	@CNPJ
	private String companyRegistration;
	
	private String stateRegistration;
	private String municipalRegistration;
	private BigDecimal weigth;
	
	/**
	 * @deprecated used only by frameworks
	 */
	@SuppressWarnings("unused")
	private CertifiedDetail(){}
	
	public CertifiedDetail(	Long id, String invoice, String companyName, String companyRegistration, String generator, 
							Address address, String stateRegistration, String municipalRegistration, BigDecimal weigth) {
		this.id = id;
		this.invoice = invoice;
		this.companyName = companyName;
		this.companyRegistration = companyRegistration;
		this.generator = generator;
		this.address = address;
		this.stateRegistration = stateRegistration;
		this.municipalRegistration = municipalRegistration;
		this.weigth = weigth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getGenerator() {
		return generator;
	}

	public void setGenerator(String generator) {
		this.generator = generator;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCompanyRegistration() {
		return companyRegistration;
	}

	public void setCompanyRegistration(String companyRegistration) {
		this.companyRegistration = companyRegistration;
	}

	public String getStateRegistration() {
		return stateRegistration;
	}

	public void setStateRegistration(String stateRegistration) {
		this.stateRegistration = stateRegistration;
	}

	public String getMunicipalRegistration() {
		return municipalRegistration;
	}

	public void setMunicipalRegistration(String municipalRegistration) {
		this.municipalRegistration = municipalRegistration;
	}

	public BigDecimal getWeigth() {
		return weigth;
	}

	public void setWeigth(BigDecimal weigth) {
		this.weigth = weigth;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
