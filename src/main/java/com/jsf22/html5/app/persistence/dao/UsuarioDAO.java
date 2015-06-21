package com.jsf22.html5.app.persistence.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.jsf22.html5.app.model.auth.Usuario;


@Named
public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="SAMPLE_PU")
	private EntityManager em;
	
	public Usuario obterUsuario(Long id) {
		return em.find(Usuario.class, id);
	}
	
	public Usuario obterUsuario(String login, String senha) {
		Usuario user = null;
		StringBuilder sql = new StringBuilder("SELECT u FROM Usuario u WHERE u.ativo = true ");
		sql.append("AND u.login = :login ");
		sql.append("AND u.senha = :senha ");
		
		TypedQuery<Usuario> query = em.createQuery(sql.toString(), Usuario.class);
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		
		try {
			user = query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
		
		return user;
	}
	
	public void inserirUsuario(Usuario usuario) {
		em.persist(usuario);
	}
	
	public void alterarUsuario(Usuario usuario) {
		em.merge(usuario);
	}
	
	public void removerUsuario(Usuario usuario) {
		em.remove(em.merge(usuario));
	}
	
	public List<Usuario> obterUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		StringBuilder sql = new StringBuilder("SELECT u FROM Usuario u ORDER BY u.login ASC");
		TypedQuery<Usuario> query = em.createQuery(sql.toString(), Usuario.class);
		usuarios = query.getResultList();
		
		return usuarios;
	}
	
	public List<Usuario> obterUsuarios(Usuario usuarioLogado) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		StringBuilder sql = new StringBuilder("SELECT u FROM Usuario u WHERE u.login <> :login ORDER BY u.login ASC");
		TypedQuery<Usuario> query = em.createQuery(sql.toString(), Usuario.class);
		query.setParameter("login", usuarioLogado.getLogin());
		usuarios = query.getResultList();
		
		return usuarios;
	}
}
