package br.com.fws.certificado_digital.factory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

public class VelocityEngineFactory {

	@Produces
	@ApplicationScoped
	public VelocityEngine factory(){
		VelocityEngine velocityEngine = new VelocityEngine();
		
		String templatePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/velocity/");
		
		velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, templatePath);
		velocityEngine.init();
		
		return velocityEngine;
	}
	
}
