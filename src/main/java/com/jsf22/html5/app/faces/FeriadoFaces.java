package com.jsf22.html5.app.faces;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.jsf22.html5.app.business.FeriadoService;
import com.jsf22.html5.app.model.Feriado;
import com.jsf22.html5.app.model.enums.TipoFeriado;
import com.jsf22.html5.app.util.JSFUtil;

@Named
@ViewScoped
public class FeriadoFaces implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String ESTADO_DE_NOVO = "_novo";
	private static final String ESTADO_DE_EDICAO = "_edicao";
	private static final String ESTADO_DE_PESQUISA = "_pesquisa";
	private String state = ESTADO_DE_PESQUISA;
	
	@EJB
	private FeriadoService feriadoService;
	
	private Feriado feriado;
	
	private List<Feriado> feriados;
	
	@PostConstruct
	public void init() {
		feriado = new Feriado();
		carregarLista();
		setState(ESTADO_DE_PESQUISA);
	}
	
	public void gravar() {
		try {
			if(isEditando()) {
				feriadoService.alterarFeriado(feriado);
			} else if(isInserindo()) {
				feriadoService.inserirFeriado(feriado);
			}
			JSFUtil.adicionaMensagemDeSucesso("Operação realizada.");
		} catch(Exception e) {
			JSFUtil.adicionaMensagemDeErro("Operação não realizada.");
		}
		init();
	}
	
	public void cancelar() {
		init();
	}
	
	public void prepararInsert() {
		feriado = new Feriado();
		setState(ESTADO_DE_NOVO);
	}
	
	public void prepararUpdate(Feriado feriado) {
		this.feriado = feriadoService.obterFeriado(feriado.getId());
		setState(ESTADO_DE_EDICAO);
	}
	
	public void removerFeriado(Feriado feriado) {
		feriado = feriadoService.obterFeriado(feriado.getId());
		feriadoService.removerFeriado(feriado);
		JSFUtil.adicionaMensagemDeSucesso("Operação realizada.");
		init();
	}
	
	private void carregarLista() {
		feriados = feriadoService.obterFeriados();
	}
	
	public TipoFeriado[] getTiposFeriado() {
		return TipoFeriado.values();
	}
	
	public Feriado getFeriado() {
		return feriado;
	}

	public void setFeriado(Feriado feriado) {
		this.feriado = feriado;
	}

	public List<Feriado> getFeriados() {
		return feriados;
	}

	public void setFeriados(List<Feriado> feriados) {
		this.feriados = feriados;
	}

	public boolean isPesquisando() {
		return state.equalsIgnoreCase(ESTADO_DE_PESQUISA);
	}
	
	public boolean isInserindo() {
		return state.equalsIgnoreCase(ESTADO_DE_NOVO);
	}
	
	public boolean isEditando() {
		return state.equalsIgnoreCase(ESTADO_DE_EDICAO);
	}
	
	private void setState(String state) {
		this.state = state;
	}

}
