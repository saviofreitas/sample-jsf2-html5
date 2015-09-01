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
		return usuarioDAO.find(id);
	}
	
	public Usuario obterUsuario(String login, String senha) {
		return usuarioDAO.obterUsuario(login, senha);
	}
	
	public void inserir(Usuario usuario) {
		usuarioDAO.save(usuario);
	}
	
	public void alterar(Usuario usuario) {
		usuarioDAO.update(usuario);
	}
	
	public void remover(Usuario usuario) {
		usuarioDAO.delete(usuario.getId());
	}
	
	public List<Usuario> obterUsuarios(){
		return usuarioDAO.findAll();
	}

}
