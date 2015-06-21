package com.jsf22.html5.app.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.RandomStringUtils;

public class SenhaUtil {

    /**
     * Construtor da classe.
     */
    private SenhaUtil() {

        super();
    }

    /**
     * Retorna uma senha gerada randomicamente conforme os parâmetros informados.
     * 
     * @param qtdCaracter Quantidade final de caracteres da senha gerada.
     * @return Senha.
     */
    public static String gerarSenha(int qtdCaracter) {

        return gerarSenha(qtdCaracter, true, true);
    }

    /**
     * Retorna uma senha gerada randomicamente conforme os parâmetros informados.
     * 
     * @param qtdCaracter Quantidade final de caracteres da senha gerada.
     * @param letras <tt>TRUE</tt> se é para usar letras na senha gerada, <tt>FALSE</tt> caso contrário.
     * @param numeros <tt>TRUE</tt> se é para usar números na senha gerada, <tt>FALSE</tt> caso contrário.
     * @return Senha.
     */
    public static String gerarSenha(int qtdCaracter, boolean letras, boolean numeros) {

        return RandomStringUtils.random(qtdCaracter, letras, numeros);
    }

    /**
     * Retorna uma senha gerada randomicamente conforme os parâmetros informados.
     * 
     * @param qtdCaracter Quantidade final de caracteres da senha gerada.
     * @param caracteres Array com os caraceteres válidos para geração da senha.
     * @return Senha.
     */
    public static String gerarSenha(int qtdCaracter, char[] caracteres) {
        return RandomStringUtils.random(qtdCaracter, caracteres);
    }
    
    public static String SHA1(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] sha1hash = new byte[40];
		md.update(s.getBytes());
		sha1hash = md.digest();
		return convertToHex(sha1hash);
	}
    
    private static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}
}
