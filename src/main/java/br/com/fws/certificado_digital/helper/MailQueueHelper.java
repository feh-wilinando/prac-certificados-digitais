package br.com.fws.certificado_digital.helper;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

@Stateful
public class MailQueueHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private JMSContext jmsContext;
	
	@Resource(name="java:/jms/queue/MailSenderQueue")
	private Queue destination;

	private JMSProducer producer;
	
	
	@PostConstruct
	public void onLoad(){
		producer = jmsContext.createProducer();
	}
	
	public void send(Serializable object){
		producer.send(destination, object);
	}
	
}
