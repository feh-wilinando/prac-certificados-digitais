package br.com.fws.certificado_digital.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fws.certificado_digital.dao.CertifiedDao;
import br.com.fws.certificado_digital.models.Certified;

@ViewScoped
@Named
public class CertifiedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Certified> certifieds = new ArrayList<>();
	private CertifiedDao dao;
	
	@Inject
	public CertifiedBean(CertifiedDao dao) {
		this.dao = dao;
	}

	@PostConstruct
	public void loadViewObjects(){
		certifieds = dao.all();
	}
	
	public List<Certified> getCertificados() {
		return certifieds;
	}
	
	public String generate(){		
		return "/certificados/formulario.xhtml?faces-redirect=true";		
	}
	
}
