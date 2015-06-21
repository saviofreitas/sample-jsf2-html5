package com.jsf22.html5.app.model.auth.realm;

import java.io.Serializable;

import javax.ejb.EJB;

import com.jsf22.html5.app.business.UsuarioService;
import com.jsf22.html5.app.exception.ApplicationException;
import com.jsf22.html5.app.model.auth.Usuario;
import com.jsf22.html5.app.util.ValidacaoUtil;

public class AutenticadorImpl implements Autenticador, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsuarioService usuarioService;
	
	public void autenticar(Usuario usuario) {
		usuario = usuarioService.obterUsuario(usuario.getLogin(), usuario.getSenha());
		if(!ValidacaoUtil.possuiValor(usuario)) {
			throw new ApplicationException("Acesso negado.");
		}
	}
}
