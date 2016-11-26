package br.com.fws.certificado_digital.beans.customer;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.fws.certificado_digital.dao.CertifiedDao;
import br.com.fws.certificado_digital.helper.MailQueueHelper;
import br.com.fws.certificado_digital.helper.MessageHelper;
import br.com.fws.certificado_digital.mail.template.CertifiedRequestTemplateEmail;
import br.com.fws.certificado_digital.mail.template.TemplateEmail;
import br.com.fws.certificado_digital.models.customer.Certified;
import br.com.fws.certificado_digital.models.customer.Customer;
import br.com.fws.certificado_digital.security.CurrentCustomer;
import br.com.fws.certificado_digital.services.VelocityService;

@Model
public class CertificateRequestBean {

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
	private MailQueueHelper queueHelper; 
	
	@Transactional
	public String request(){
		
		if (currentCustomer.isLogged()) {
			Customer customer = currentCustomer.get();					
			
			certified.setCustomer(customer);
			certifiedDao.save(certified);
			
			TemplateEmail template = new CertifiedRequestTemplateEmail(certified, velocityService);
			
			queueHelper.send(template);
			
			messageHelper
				.onFlash()
				.addInfoMessage("Solicitação de certificado efetuada com sucesso!", "Será enviado uma notificação ao e-mail de cadastro, ao termino do atendimento desta solicitação.");
			
		}
		
		
		return "/certificados/listagem?faces-redirect=true";
	}
	
	
	public Certified getCertified() {
		return certified;
	}
		
}
