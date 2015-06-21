package com.jsf22.html5.app.faces;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.jsf22.html5.app.business.UsuarioService;
import com.jsf22.html5.app.exception.ApplicationException;
import com.jsf22.html5.app.model.auth.Usuario;
import com.jsf22.html5.app.model.auth.realm.Autenticador;
import com.jsf22.html5.app.util.Constantes;
import com.jsf22.html5.app.util.JSFUtil;
import com.jsf22.html5.app.util.MensagemUtil;
import com.jsf22.html5.app.util.ValidacaoUtil;

@Named
@ViewScoped
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String HOME = "/pages/home.jsf?faces-redirect=true";
	private static final String LOGIN = "/login.jsf?faces-redirect=true";
	
	@NotNull(message="Campo obrigatório")
	@NotEmpty(message="Campo obrigatório")
	private String login;
	
	@NotNull(message="Campo obrigatório")
	@NotEmpty(message="Campo obrigatório")
	private String password;
	
	@Inject
	private Autenticador autenticador;
	
	@EJB
	private UsuarioService usuarioService;
	
	public String autenticar() {
		try {
			Usuario usuario = new Usuario(login, password);
			autenticador.autenticar(usuario);
			JSFUtil.setParametroSession(Constantes.USUARIO_SESSAO, usuario);
			return HOME;
		}
		catch(ApplicationException e) {
			JSFUtil.adicionaMensagemDeErro(MensagemUtil.getMensagem(Constantes.MSG_ERRO_USUARIO_NAO_AUTENTICADO));
			return LOGIN;
		}
	}
	
	public String logout() {
		JSFUtil.setParametroSession(Constantes.USUARIO_SESSAO, null);
		JSFUtil.getSession().invalidate();
        return LOGIN;
    }
	
	public boolean exibeMenu() {
		return ValidacaoUtil.possuiValor(getUsuarioLogado());
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario getUsuarioLogado() {
		Usuario usuario = (Usuario) JSFUtil.getParametroSession(Constantes.USUARIO_SESSAO);
		return usuario;
	}
}
