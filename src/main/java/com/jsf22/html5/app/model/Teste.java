package com.jsf22.html5.app.model;

import java.io.Serializable;

public class Teste implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String texto;

	private String outroTexto;

	private double valor;
	
	public Teste() {
	}
	
	public Teste(int id, String texto, String outroTexto, double valor) {
		super();
		this.id = id;
		this.texto = texto;
		this.outroTexto = outroTexto;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getOutroTexto() {
		return outroTexto;
	}

	public void setOutroTexto(String outroTexto) {
		this.outroTexto = outroTexto;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
