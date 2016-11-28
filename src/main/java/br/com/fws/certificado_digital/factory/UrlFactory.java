package br.com.fws.certificado_digital.factory;

import br.com.fws.certificado_digital.qualifier.URLBase;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
@ApplicationScoped
public class UrlFactory implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Produces
	@URLBase
	public String getBaseURL(HttpServletRequest request) {
		String contextPath 	= request.getContextPath();
		String requestURL 	= request.getRequestURL().toString();
		
		int contextPathPosition = requestURL.indexOf(contextPath);
		
		String host = requestURL.substring(0, contextPathPosition);
		
		
		return host + contextPath;
	}
	
}
