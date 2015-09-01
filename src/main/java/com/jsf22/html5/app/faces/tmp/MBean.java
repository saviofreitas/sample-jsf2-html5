package com.jsf22.html5.app.faces.tmp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.DataModel;

import com.jsf22.html5.app.model.Teste;
import com.jsf22.html5.app.model.auth.Usuario;
import com.jsf22.html5.app.util.JSFUtil;
import com.jsf22.html5.app.util.ValidacaoUtil;

@Model
public class MBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String hello;
	
	private List<Teste> testes;
	
	/* PAGINAÇÃO SOB DEMANDA */
	private HtmlDataTable dataTable;
	private DataModel<Usuario> dataModel;
	/* PAGINAÇÃO SOB DEMANDA */
	
	private static final String ESTADO_DE_NOVO = "_novo";
	private static final String ESTADO_DE_EDICAO = "_edicao";
	private static final String ESTADO_DE_PESQUISA = "_pesquisa";
	private String state = ESTADO_DE_PESQUISA;
	
	@PostConstruct
	public void init() {
		carregarTestes();
		setState(ESTADO_DE_PESQUISA);
			
	}
	
	public void submit() {
		if(!ValidacaoUtil.possuiValor(hello)) {
			JSFUtil.adicionaMensagemDeErro("Vê se não fresca.");
		} else {
			JSFUtil.adicionaMensagemDeSucesso(hello);
		}
	}
	
	public void listar() {
		carregarTestes();
		setState(ESTADO_DE_PESQUISA);
	}
	
	public void novo() {
		setState(ESTADO_DE_NOVO);
	}
	public void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}

	public List<Teste> getTestes() {
		return testes;
	}

	public void setTestes(List<Teste> testes) {
		this.testes = testes;
	}
	
	private void carregarTestes() {
		testes = new ArrayList<Teste>();
		for(int i = 1; i <= 35; i++) {
			testes.add(new Teste(i, "Texto qualquer " + i, "Outro texto qualquer "+ i, 1 + (int) (Math.random() * 100)));
		}
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

	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public DataModel<Usuario> getDataModel() {
		return dataModel;
	}

}
