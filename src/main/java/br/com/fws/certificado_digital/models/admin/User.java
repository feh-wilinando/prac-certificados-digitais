package br.com.fws.certificado_digital.models.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.fws.certificado_digital.services.EncryptorService;

@Entity
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Email
	private String email;
	
	@NotBlank
	private String password;
	
	@NotNull
	@Column(columnDefinition="BOOLEAN DEFAULT false")
	private boolean active;
			
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@NotEmpty
	private List<Role> roles = new ArrayList<>();

	
	/**
	 * @deprecated used only by frameworks
	 */
	@SuppressWarnings("unused")
	private User() {}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", roles=" + roles + "]";
	}
	
	@PrePersist
	private void prePersist(){
		EncryptorService encryptorService = new EncryptorService();
		
		String encrypted = encryptorService.encrypt(password);
		
		password = encrypted;		
	}
			
}
