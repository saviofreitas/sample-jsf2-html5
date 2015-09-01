package com.jsf22.html5.app.persistence.dao;

import static com.jsf22.html5.app.persistence.dao.generic.QueryParameter.with;

import javax.inject.Named;

import com.jsf22.html5.app.model.auth.Usuario;
import com.jsf22.html5.app.persistence.dao.generic.GenericDAO;

@Named
public class UsuarioDAO extends GenericDAO<Usuario> {

	private static final long serialVersionUID = 1L;
	
	public Usuario obterUsuario(String login) {
		StringBuilder sql = new StringBuilder("SELECT u FROM Usuario u WHERE u.ativo = true ");
		sql.append("AND u.login = :login ");
		
		return findOneResult(sql.toString(), with("login", login).parameters());
	}

	public Usuario obterUsuario(String login, String senha) {
		StringBuilder sql = new StringBuilder("SELECT u FROM Usuario u WHERE u.ativo = true ");
		sql.append("AND u.login = :login ");
		sql.append("AND u.senha = :senha ");
		
		return findOneResult(sql.toString(), with("login", login).and("senha", senha).parameters());
	}
}
