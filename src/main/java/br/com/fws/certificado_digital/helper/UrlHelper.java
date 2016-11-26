package br.com.fws.certificado_digital.helper;

import java.io.Serializable;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class UrlHelper implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private HttpServletRequest request;
	
	
	public String getBaseURL() {
		String contextPath 	= request.getContextPath();
		String requestURL 	= request.getRequestURL().toString();
		
		int contextPathPosition = requestURL.indexOf(contextPath);
		
		String host = requestURL.substring(0, contextPathPosition);
		
		
		return host + contextPath;
	}
	
}
