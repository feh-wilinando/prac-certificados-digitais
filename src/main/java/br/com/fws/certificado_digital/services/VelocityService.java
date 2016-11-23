package br.com.fws.certificado_digital.services;

import java.io.StringWriter;

import javax.inject.Inject;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class VelocityService {

	private VelocityEngine velocityEngine;
	private Template template;
	private VelocityContext context;

	@Inject
	public VelocityService(VelocityEngine velocityEngine){
		this.velocityEngine = velocityEngine;
	}
	
	public VelocityService loadTemplate(String template){
		this.template = velocityEngine.getTemplate(template,"UTF-8");				
		this.context = new VelocityContext();			
		
		return this;
	}
	
	public VelocityService set(String key, Object value){
		context.put(key, value);		
		return this;
	}
	
	
	public String getContent(){
		StringWriter writer = new StringWriter();
		
		template.merge(context, writer);
		
		
		return writer.toString();
	}
	
	
	
	
}
