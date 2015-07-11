package com.jsf22.html5.app.faces.listener;

import java.util.regex.Pattern;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.jsf22.html5.app.model.auth.Usuario;
import com.jsf22.html5.app.util.Constantes;
import com.jsf22.html5.app.util.JSFUtil;
import static com.jsf22.html5.app.util.ValidacaoUtil.possuiValor;

public class SecurityListener implements PhaseListener{

	private static final long serialVersionUID = 1L;

	private static final String ACESSO_RESTRITO = "^/pages/.*";
	
	@Override
	public void afterPhase(PhaseEvent event) {
		String viewId = JSFUtil.getViewId();
		Usuario usuarioLogado = (Usuario) JSFUtil.getParametroSession(Constantes.USUARIO_SESSAO);
		FacesContext context = JSFUtil.getFacesContext();
		
		if(isAcessoRestrito(viewId) && !possuiValor(usuarioLogado)) {
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
	
	private boolean isAcessoRestrito(String view) {
		return Pattern.matches(ACESSO_RESTRITO, view);
	}

}
