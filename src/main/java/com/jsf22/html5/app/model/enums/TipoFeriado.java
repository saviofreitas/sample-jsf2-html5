package com.jsf22.html5.app.model.enums;

import java.util.Arrays;
import java.util.List;

public enum TipoFeriado {

	INTERNACIONAL("Internacional"),
	NACIONAL("Nacional"),
	ESTADUAL("Estadual"),
	MUNICIPAL("Municipal"),
	PORTARIA("Portaria");
	
	String descricao;
	
	private static List<TipoFeriado> tipos = Arrays.asList(TipoFeriado.values());

	private TipoFeriado(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static List<TipoFeriado> getTipos() {
		return tipos;
	}
	
}
