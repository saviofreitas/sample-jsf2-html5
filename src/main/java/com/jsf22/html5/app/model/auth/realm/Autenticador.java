package com.jsf22.html5.app.model.auth.realm;

import javax.inject.Named;

import com.jsf22.html5.app.model.auth.Usuario;

@Named
public interface Autenticador {
	void autenticar(Usuario usuario);
}
