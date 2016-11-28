package br.com.fws.certificado_digital.beans.customer;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.fws.certificado_digital.dao.CertifiedDao;
import br.com.fws.certificado_digital.dao.CustomerDao;
import br.com.fws.certificado_digital.models.customer.Certified;
import br.com.fws.certificado_digital.models.customer.Customer;
import br.com.fws.certificado_digital.security.CurrentCustomer;
import br.com.fws.certificado_digital.services.CertifiedService;

@Model
public class CertifiedBean {
	
	private Long cliend_id;
	private List<Certified> certifieds = new ArrayList<>();
	
	@Inject
	private CurrentCustomer currentCustomer;
	
	@Inject
	private CertifiedDao certifiedDao;
	
	@Inject
	private CustomerDao customerDao;

	@Inject
	private CertifiedService certifiedService;
	
	
	public void loadViewParam(){
		
		
		if (currentCustomer.isLogged()) {
			cliend_id = currentCustomer.get().getId();
		}
		
		if (cliend_id != null) {
			Customer cuscomer = customerDao.findById(cliend_id);
			this.certifieds = certifiedDao.allOf(cuscomer);
		}
				
	}
	
	public List<Certified> getCertifieds() {
		return certifieds;
	}
	
	public Long getCliend_id() {
		return cliend_id;
	}
	public void setCliend_id(Long cliend_id) {
		this.cliend_id = cliend_id;
	}

	public String generate(Certified certified) {

		certifiedService.generate(certified);

		return "";
	}
}
