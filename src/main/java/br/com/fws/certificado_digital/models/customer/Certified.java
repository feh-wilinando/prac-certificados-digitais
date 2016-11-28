package br.com.fws.certificado_digital.models.customer;

import br.com.fws.certificado_digital.mail.template.Templatable;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class Certified implements Serializable, Templatable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String invoice;
	
	private Integer quantity;
	
	private String issuePoint;
	
	private Double weigth;
	
	@ManyToOne
	private Customer customer;
	
	private LocalDate issueDate;
	
	private LocalDate requestDate;
	
	@NotNull
	@Column(columnDefinition="BOOLEAN DEFAULT false")
	private boolean alreadyGenerated;

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getWeigth() {
		return weigth;
	}

	public void setWeigth(Double weigth) {
		this.weigth = weigth;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public boolean isAlreadyGenerated() {
		return alreadyGenerated;
	}

	public void setAlreadyGenerated(boolean alreadyGenerated) {
		this.alreadyGenerated = alreadyGenerated;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getIssuePoint() {
		return issuePoint;
	}

	public void setIssuePoint(String issuePoint) {
		this.issuePoint = issuePoint;
	}

	@PrePersist
	public void prePersist(){
		requestDate = LocalDate.now();
	}
	
	

}
