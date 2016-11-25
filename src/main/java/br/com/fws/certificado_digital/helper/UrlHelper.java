package br.com.fws.certificado_digital.helper;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class UrlHelper {

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
