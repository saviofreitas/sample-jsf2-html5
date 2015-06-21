package com.jsf22.html5.app.persistence.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jsf22.html5.app.model.Feriado;


@Named
public class FeriadoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="SAMPLE_PU")
	private EntityManager em;
	
	public Feriado obterFeriado(Long id) {
		return em.find(Feriado.class, id);
	}
	
	public void inserirFeriado(Feriado feriado) {
		em.persist(feriado);
	}
	
	public void alterarFeriado(Feriado feriado) {
		em.merge(feriado);
	}
	
	public void removerFeriado(Feriado feriado) {
		em.remove(em.merge(feriado));
	}
	
	public List<Feriado> obterFeriados() {
		List<Feriado> feriados = new ArrayList<Feriado>();
		feriados = em.createQuery("SELECT f FROM Feriado f ", Feriado.class).getResultList();
		return feriados;
	}
}
