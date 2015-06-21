package com.jsf22.html5.app.faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.jsf22.html5.app.util.Constantes;
import com.jsf22.html5.app.util.MensagemUtil;

@FacesConverter(value = "simNaoConverter")
public class SimNaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Boolean resposta = Boolean.FALSE;
		
		if(value != null && !value.trim().isEmpty()) {
			resposta = new Boolean(value);
		}
		
		return resposta;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {		
			return (Boolean) value == true ? MensagemUtil.getMensagem(Constantes.MSG_SIM) : MensagemUtil.getMensagem(Constantes.MSG_NAO); 
		}
		
		return MensagemUtil.getMensagem(Constantes.MSG_NAO);
	}
	
}