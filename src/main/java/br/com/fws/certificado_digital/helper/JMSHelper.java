package br.com.fws.certificado_digital.helper;

import br.com.fws.certificado_digital.mail.template.TemplateEmail;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;

/**
 * Created by nando on 28/11/16.
 */
@Stateful
public class JMSHelper {

    @Inject
    private JMSContext jmsContext;


    @Resource(name = "java:/jms/queue/MailSenderQueue")
    private Destination destination;


    public void sendTemplate(TemplateEmail template){

        JMSProducer producer = jmsContext.createProducer();


        producer.send(destination, template);
    }

}
