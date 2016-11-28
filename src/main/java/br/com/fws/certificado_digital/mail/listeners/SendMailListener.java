package br.com.fws.certificado_digital.mail.listeners;

import br.com.fws.certificado_digital.dao.CertifiedDao;
import br.com.fws.certificado_digital.helper.MailerHelper;
import br.com.fws.certificado_digital.mail.template.CertifiedRequestTemplateEmail;
import br.com.fws.certificado_digital.mail.template.TemplateEmail;
import br.com.fws.certificado_digital.models.customer.Certified;
import br.com.fws.certificado_digital.services.VelocityService;
import org.apache.velocity.app.VelocityEngine;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by nando on 28/11/16.
 */
@MessageDriven(
        activationConfig =
            @ActivationConfigProperty(
                        propertyName = "destinationLookup",
                    propertyValue = "java:/jms/queue/MailSenderQueue"
            )
)
public class SendMailListener implements MessageListener{


    @Inject
    private MailerHelper mailerHelper;

    @Override
    public void onMessage(Message message) {

        try {

            TemplateEmail template = message.getBody(TemplateEmail.class);

            mailerHelper.sendFromTemplate(template);

        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

}
