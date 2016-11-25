package br.com.fws.certificado_digital.models.customer;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.com.caelum.stella.type.Estado;

@Embeddable
public class Address implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String 	street;
	
	@Min(1)
	private Integer number;
	
	private String 	complement;	
	
	@NotBlank	
	private String 	city;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Estado 	state;
	
	@NotBlank
	private String 	zipCode;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Estado getState() {
		return state;
	}

	public void setState(Estado state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}	
	
	
	
	
	
}
