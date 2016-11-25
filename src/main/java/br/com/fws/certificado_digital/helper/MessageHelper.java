package br.com.fws.certificado_digital.helper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

@Model
public class MessageHelper {
	
	private FacesContext faceContext;

	@PostConstruct
	public void loadObject(){
		this.faceContext = FacesContext.getCurrentInstance();
	}
	
	
	public MessageHelper onFlash(){
		
		faceContext.getExternalContext().getFlash().setKeepMessages(true);
		
		return this;
	}
	
	public MessageHelper addMessage(FacesMessage message){
		return addMessage(null, message);		
	}	
	
	public MessageHelper addMessage(String clientId, FacesMessage message){
		this.faceContext.addMessage(clientId, message);
		
		return this;
	}
	
	public MessageHelper addInfoMessage(String summary){
		return addInfoMessage(summary, null);
	}
	
	public MessageHelper addInfoMessage(String summary, String detail){
		FacesMessage message = getMessageFrom(FacesMessage.SEVERITY_INFO, summary, detail);
		
		return addMessage(message);
	}
	
	public MessageHelper addErrorMessage(String summary){
		return addErrorMessage(summary, null);
	}
	
	public MessageHelper addErrorMessage(String summary, String detail){
		FacesMessage message = getMessageFrom(FacesMessage.SEVERITY_ERROR, summary, detail);
		
		return addMessage(message);
	}
	
	
	private FacesMessage getMessageFrom(Severity severity, String summary, String detail){
		return new FacesMessage(severity, summary, detail);
	}
	
}
