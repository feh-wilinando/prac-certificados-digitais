package br.com.fws.certificado_digital.security;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

public class SecurityPhaseListener implements PhaseListener{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CurrentCustomer currentCustomer;

	@Override
	public void afterPhase(PhaseEvent event) {
		
		FacesContext facesContext = event.getFacesContext();
		
		String pagina = facesContext.getViewRoot().getViewId();
		
		if (pagina.startsWith("/clientes/") || currentCustomer.isLogged()) {
			return;
		}
		
		NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
		
		navigationHandler.handleNavigation(facesContext, null, "/clientes/login?faces-redirect=true");
		
		facesContext.renderResponse();
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
