package br.com.fws.certificado_digital.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.fws.certificado_digital.dao.CertifiedDao;
import br.com.fws.certificado_digital.models.Certified;

@Model
public class CertifedDetailBean {

	private Long id;
	private Certified certified;
	private CertifiedDao dao;

	@Inject
	public CertifedDetailBean(CertifiedDao dao, Certified certified){
		this.dao = dao;
		this.certified = certified;
	}
	
	public void loadViewParam(){
		if(id != null){
			this.certified = dao.findById(id);
		}
	}
	
	@Transactional
	public String save(){
		dao.save(certified);
		return "/certificados/listagem.xhtml?faces-redirect=true";
	}
	
	public Certified getCertified() {
		return certified;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
