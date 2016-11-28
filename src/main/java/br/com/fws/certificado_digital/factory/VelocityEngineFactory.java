package br.com.fws.certificado_digital.factory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
@ApplicationScoped
public class VelocityEngineFactory {

	@Produces
	@ApplicationScoped
	public VelocityEngine factory(ServletContext context){
		VelocityEngine velocityEngine = new VelocityEngine();
		
		String templatePath = context.getRealPath("/WEB-INF/velocity/");
		velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, templatePath);
		velocityEngine.init();
		
		return velocityEngine;
	}
	
}
