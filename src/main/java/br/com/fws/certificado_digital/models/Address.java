package br.com.fws.certificado_digital.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;

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
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private Estado 	state;
	
	@NotBlank
	private String 	zipCode;
	
	/**
	 * @deprecated used only by frameworks
	 */
	@SuppressWarnings("unused")
	private Address(){}
	
	public Address(String street, Integer number, String complement, String city, Estado state, String zipCode) {
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	public String getStreet() {
		return street;
	}
	public Integer getNumber() {
		return number;
	}
	public String getComplement() {
		return complement;
	}
	public String getCity() {
		return city;
	}
	public Estado getState() {
		return state;
	}
	public String getZipCode() {
		return zipCode;
	}
	
	
	
}
