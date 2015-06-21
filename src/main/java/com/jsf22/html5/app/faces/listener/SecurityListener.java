package com.jsf22.html5.app.faces.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.jsf22.html5.app.model.auth.Usuario;
import com.jsf22.html5.app.util.Constantes;
import com.jsf22.html5.app.util.JSFUtil;
import com.jsf22.html5.app.util.ValidacaoUtil;

public class SecurityListener implements PhaseListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		String viewId = JSFUtil.getViewId();
		Usuario usuarioLogado = (Usuario) JSFUtil.getParametroSession(Constantes.USUARIO_SESSAO);
		FacesContext context = JSFUtil.getFacesContext();
		if(!isLoginPage(viewId) && !ValidacaoUtil.possuiValor(usuarioLogado)) {
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			handler.handleNavigation(context, null, "login");
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
	private boolean isLoginPage(String view) {
		return view.lastIndexOf("login.xhtml") > -1;
	}

}
