package com.jsf22.html5.app.business;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jsf22.html5.app.model.Feriado;
import com.jsf22.html5.app.persistence.dao.FeriadoDAO;

@Stateless
public class FeriadoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FeriadoDAO feriadoDAO;
	
	public Feriado obterFeriado(Long id) {
		return feriadoDAO.obterFeriado(id);
	}
	
	public void inserirFeriado(Feriado feriado) {
		feriadoDAO.inserirFeriado(feriado);
	}
	
	public void alterarFeriado(Feriado feriado) {
		feriadoDAO.alterarFeriado(feriado);
	}
	
	public void removerFeriado(Feriado feriado) {
		feriadoDAO.removerFeriado(feriado);
	}
	
	public List<Feriado> obterFeriados() {
		return feriadoDAO.obterFeriados();
	}
}
