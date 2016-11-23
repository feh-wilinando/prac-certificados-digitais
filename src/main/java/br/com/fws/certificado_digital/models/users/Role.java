package br.com.fws.certificado_digital.models.users;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {
	
	@Id
	private String name;
	
	/**
	 * @deprecated used only by frameworks
	 */
	@SuppressWarnings("unused")
	private Role(){}
	
	public Role(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
