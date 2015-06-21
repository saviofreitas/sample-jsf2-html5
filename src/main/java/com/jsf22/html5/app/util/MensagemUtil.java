package com.jsf22.html5.app.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public final class MensagemUtil {

    /**
     * Retorna o texto, no arquivo de recurso, correspondente a chave passada.
     * 
     * @param chave Chave do texto no arquivo de recurso.
     * @param argumentos Argumentos que substituirão os curingas no texto.
     * @return Texto.
     */
    public static String getMensagem(String chave, Object... argumentos) {

        return getMensagem(Constantes.LOCALE_PT_BR, chave, argumentos);
    }

    /**
     * Retorna o texto, no arquivo de recurso, correspondente a chave passada em uma determinada localidade.
     * 
     * @param locale {@link Locale} (<tt>Localidade</tt>).
     * @param chave Chave do texto no arquivo de recurso.
     * @param argumentos Argumentos que substituirão os curingas no texto.
     * @return Texto.
     */
    public static String getMensagem(Locale locale, String chave, Object... argumentos) {

        return getMensagem(getResource(Constantes.ARQUIVO_RESOURCE_MSG, locale), chave, argumentos);
    }

    /**
     * Retorna o texto, no arquivo de recurso, correspondente a chave passada.
     * 
     * @param resource {@link ResourceBundle} (<tt>Arquivo de recurso</tt>).
     * @param chave Chave do texto no arquivo de recurso.
     * @param argumentos Argumentos que substituirão os curingas no texto.
     * @return Texto.
     */
    public static String getMensagem(ResourceBundle resource, String chave, Object... argumentos) {

        String mensagem = null;

        try {

            mensagem = resource.getString(chave);

            if (argumentos.length > 0) {
                mensagem = MessageFormat.format(mensagem, argumentos);
            }
        } catch (Exception e) {
            mensagem = chave;
        }
        return mensagem;
    }

    /**
     * Retorna o {@link ResourceBundle} (<tt>Arquivo de recurso</tt>) para uma determinada localidade.
     * 
     * @param resourceName Nome do arquivo de recurso.
     * @param locale {@link Locale} (<tt>Localidade</tt>).
     * @return {@link ResourceBundle}.
     */
    public static ResourceBundle getResource(String resourceName, Locale locale) {

        return ResourceBundle.getBundle(resourceName, locale, Thread.currentThread().getContextClassLoader());
    }

    /**
     * Retorna o {@link ResourceBundle} (<tt>Arquivo de recurso</tt>).
     * 
     * @param resourceName Nome do arquivo de recurso.
     * @return {@link ResourceBundle}.
     */
    public static ResourceBundle getResource(String resourceName) {

        return ResourceBundle.getBundle(resourceName);
    }

}
