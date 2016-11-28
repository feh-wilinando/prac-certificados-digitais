package br.com.fws.certificado_digital.beans.customer;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.fws.certificado_digital.dao.CertifiedDao;
import br.com.fws.certificado_digital.helper.JMSHelper;
import br.com.fws.certificado_digital.helper.MailerHelper;
import br.com.fws.certificado_digital.helper.MessageHelper;
import br.com.fws.certificado_digital.mail.template.CertifiedRequestTemplateEmail;
import br.com.fws.certificado_digital.models.customer.Certified;
import br.com.fws.certificado_digital.models.customer.Customer;
import br.com.fws.certificado_digital.security.CurrentCustomer;
import br.com.fws.certificado_digital.services.VelocityService;

import java.time.LocalDate;

@Model
public class CertificateRequestBean {

	private Long id;

	private Certified certified = new Certified();
	
	@Inject
	private CertifiedDao certifiedDao;
	
	@Inject
	private CurrentCustomer currentCustomer;

	@Inject
	private MessageHelper messageHelper;
	
	@Inject
	private VelocityService velocityService;


	@Inject
	private JMSHelper jmsHelper;

	@Inject
	private MailerHelper mailhelper;

	@Inject
	private CertifiedRequestTemplateEmail template;

	public void loadViewParam(){

		if (id != null){
			certified = certifiedDao.findById(id);
		}

	}


	@Transactional
	public String request(){
		
		if (currentCustomer.isLogged()) {
			Customer customer = currentCustomer.get();					

			certified.setCustomer(customer);

			if(certified.getId() != null){
				certified.setRequestDate(LocalDate.now());
				certifiedDao.update(certified);
			}else {
				certifiedDao.save(certified);
			}

			template.setTemplatable(certified);

			jmsHelper.sendTemplate(template);

			messageHelper
				.onFlash()
				.addInfoMessage("Solicitação de certificado efetuada com sucesso!", "Será enviado uma notificação ao e-mail de cadastro, ao termino do atendimento desta solicitação.");
			
		}
		
		
		return "/certificados/listagem?faces-redirect=true";
	}
	
	
	public Certified getCertified() {
		return certified;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
