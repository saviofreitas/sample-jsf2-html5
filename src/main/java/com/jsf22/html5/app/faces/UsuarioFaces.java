package com.jsf22.html5.app.faces;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.jsf22.html5.app.business.UsuarioService;
import com.jsf22.html5.app.model.auth.Perfil;
import com.jsf22.html5.app.model.auth.Usuario;
import com.jsf22.html5.app.util.Constantes;
import com.jsf22.html5.app.util.JSFUtil;
import com.jsf22.html5.app.util.ValidacaoUtil;

@Named
@ViewScoped
public class UsuarioFaces implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsuarioService usuarioService;
	
	private Usuario usuario;
	
	private List<Usuario> usuarios;
	
	private String novaSenha;
	private String confirmacaoSenha;
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
		carregarLista();
	}
	
	public void gravar() {
		try {
			if(ValidacaoUtil.possuiValor(usuario.getId())) {
				usuarioService.alterar(usuario);
			} else {
				usuarioService.inserir(usuario);
			}
			JSFUtil.adicionaMensagemDeSucesso("Operação realizada.");
		} catch(Exception e) {
			JSFUtil.adicionaMensagemDeErro("Operação não realizada.");
		}
		init();
	}
	
	public void alterarSenha() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		if(!novaSenha.equals(confirmacaoSenha)) {
			JSFUtil.adicionaMensagemDeErro("Nova senha e confirmação não conferem.");
			return;
		}
		Usuario usuarioLogado = usuario = (Usuario) JSFUtil.getParametroSession(Constantes.USUARIO_SESSAO);
		usuario = usuarioService.obterUsuario(usuarioLogado.getLogin(), usuarioLogado.getSenha());
		usuario.setSenha(novaSenha);
		usuarioService.alterar(usuario);
		JSFUtil.adicionaMensagemDeSucesso("Senha alterada.");
	}
	
	public void prepararUpdate(Usuario usuario) {
		this.usuario = usuarioService.obterUsuario(usuario.getId());
	}
	
	public void alterarStatus(Usuario usuario) {
		usuario = usuarioService.obterUsuario(usuario.getId());
		usuario.setAtivo(!usuario.isAtivo());
		usuarioService.alterar(usuario);
		JSFUtil.adicionaMensagemDeSucesso("Operação realizada.");
		init();
	}
	
	public void removerUsuario(Usuario usuario) {
		usuario = usuarioService.obterUsuario(usuario.getId());
		usuarioService.remover(usuario);
		JSFUtil.adicionaMensagemDeSucesso("Operação realizada.");
		init();
	}
	
	private void carregarLista() {
		Usuario usuarioLogado = (Usuario) JSFUtil.getParametroSession(Constantes.USUARIO_SESSAO);
		usuarios = usuarioService.obterUsuarios(usuarioLogado);
	}
	
	public Perfil[] getPerfis() {
		return Perfil.values();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
}
