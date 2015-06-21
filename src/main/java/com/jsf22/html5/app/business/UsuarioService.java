package com.jsf22.html5.app.business;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jsf22.html5.app.model.auth.Usuario;
import com.jsf22.html5.app.persistence.dao.UsuarioDAO;

@Stateless
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;
	
	public Usuario obterUsuario(Long id) {
		return usuarioDAO.obterUsuario(id);
	}
	
	public Usuario obterUsuario(String login, String senha) {
		return usuarioDAO.obterUsuario(login, senha);
	}
	
	public void inserir(Usuario usuario) {
		usuarioDAO.inserirUsuario(usuario);
	}
	
	public void alterar(Usuario usuario) {
		usuarioDAO.alterarUsuario(usuario);
	}
	
	public void remover(Usuario usuario) {
		usuarioDAO.removerUsuario(usuario);
	}
	
	public List<Usuario> obterUsuarios(){
		return usuarioDAO.obterUsuarios();
	}

	/**
	 * Retorna todos os usuários, exceto o usuário logado.
	 * @param usuario
	 * @return
	 */
	public List<Usuario> obterUsuarios(Usuario usuario){
		return usuarioDAO.obterUsuarios(usuario);
	}
}
