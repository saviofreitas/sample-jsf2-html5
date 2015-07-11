package com.jsf22.html5.app.util;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class PropriedadeUtil {
	
	private static final Logger LOGGER = Logger.getLogger(PropriedadeUtil.class);

    public static String getPropriedade(String chave) {

        String propriedade = null;

        try {

            propriedade = getResource("config").getString(chave);

        } catch (Exception e) {
            propriedade = null;
            LogUtil.error(LOGGER, MensagemUtil.getMensagem(Constantes.MSG_PROPRIEDADES_NAO_ENCONTRADA), e);
        }

        return propriedade;
    }

    private static ResourceBundle getResource(String resourceName) {
        return ResourceBundle.getBundle(resourceName, Constantes.LOCALE_PT_BR, Thread.currentThread().getContextClassLoader());
    }

}
