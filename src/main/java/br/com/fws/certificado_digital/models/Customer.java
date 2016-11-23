package br.com.fws.certificado_digital.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.fws.certificado_digital.models.users.User;
import br.com.fws.certificado_digital.services.EncryptorService;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="companyRegistration"))
public class Customer implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String companyName;	
	
	@CNPJ @NotBlank
	private String companyRegistration;
		
	private String stateRegistration;
	private String municipalRegistration;
	
	@Embedded	
	private Address address;
	
	@OneToOne(cascade=CascadeType.ALL)
	@NotNull
	private User user;
		
	private String activationHash;
	
	
	/**
	 * @deprecated used only by frameworks
	 */
	@SuppressWarnings("unused")
	private Customer(){}
	
	public Customer(Long id, String companyName, String companyRegistration, String stateRegistration, String municipalRegistration, Address address, User user, String activationHash) {
		this.id = id;
		this.companyName = companyName;
		this.companyRegistration = companyRegistration;
		this.stateRegistration = stateRegistration;
		this.municipalRegistration = municipalRegistration;
		this.address = address;
		this.user = user;
		this.activationHash = activationHash;
	}



	public Long getId() {
		return id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompanyRegistration() {
		return companyRegistration;
	}

	public String getStateRegistration() {
		return stateRegistration;
	}

	public String getMunicipalRegistration() {
		return municipalRegistration;
	}

	public Address getAddress() {
		return address;
	}	
	public User getUser() {
		return user;
	}
	
	public String getActivationHash() {
		return activationHash;
	}

	public void setActivationHash(String activationHash) {
		this.activationHash = activationHash;
	}

	public void setId(Object object) {
		this.id = null;
	}	
	
	@PrePersist
	private void prePersist(){
		EncryptorService encryptorService = new EncryptorService();
		String encrypt = encryptorService.encrypt(companyName + companyRegistration + user.getEmail());
		activationHash = encrypt;
	}
	
}
