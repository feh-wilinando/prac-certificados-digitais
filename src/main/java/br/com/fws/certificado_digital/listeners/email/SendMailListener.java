package br.com.fws.certificado_digital.listeners.email;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.com.fws.certificado_digital.helper.MailerHelper;
import br.com.fws.certificado_digital.mail.template.TemplateEmail;
@MessageDriven(	
			activationConfig={ 
				@ActivationConfigProperty(propertyName="destinationLookup", propertyValue="java:/jms/queue/MailSenderQueue"),
				@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue")				
			})
public class SendMailListener implements MessageListener {

	@Inject
	private MailerHelper mailerHelper;
	
	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message;
		
		try {
			TemplateEmail templateEmail = objectMessage.getBody(TemplateEmail.class);
			
			mailerHelper.sendFromTemplate(templateEmail);
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
