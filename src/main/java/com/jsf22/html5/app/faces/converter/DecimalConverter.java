package com.jsf22.html5.app.faces.converter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="decimalConverter")
public class DecimalConverter implements Converter {
	
	private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
	
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		
		BigDecimal decimalValue = BigDecimal.ZERO;
		
		if (value == null || value.isEmpty()) {
			value = "0,00";
		}
		
		try {
			decimalValue = new BigDecimal(decimalFormat.parse(value).doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
		} catch (ParseException e) {
			return BigDecimal.ZERO;
		}
		return decimalValue;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object obj) {
		
		if (obj == null) return "0,00";
		
		BigDecimal value = (BigDecimal) obj;
		String decimal = decimalFormat.format(value); 
		return decimal;
		
	}

}
