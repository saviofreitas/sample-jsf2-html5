package com.jsf22.html5.app.util;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class PropriedadeUtil {
	
	private static final Logger LOGGER = Logger.getLogger(PropriedadeUtil.class);

    /**
     * Retorna o valor da propriedade, no arquivo de configuração, correspondente a chave passada.
     * 
     * @param chave Chave da propriedade no arquivo de configuração.
     * @return Valor da propriedade.
     */
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

    /**
     * Retorna o {@link ResourceBundle} (<tt>Arquivo de recurso</tt>).
     * 
     * @param resourceName Nome do arquivo de configuração.
     * @return {@link ResourceBundle}.
     */
    private static ResourceBundle getResource(String resourceName) {
        return ResourceBundle.getBundle(resourceName, Constantes.LOCALE_PT_BR, Thread.currentThread().getContextClassLoader());
    }

}
