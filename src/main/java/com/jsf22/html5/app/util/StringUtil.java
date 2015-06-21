package com.jsf22.html5.app.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Singleton;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;

@Singleton
public class StringUtil {

	/** Padrao (expressão regular) para validar e-mail. */
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
			+ "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*"
			+ "(\\.[A-Za-z]{2,})$";

	/**
	 * Insere o caracter à direita do valor informado, se necessário, até o
	 * mesmo atingir o tamanho mínimo esperado.
	 * 
	 * @param valor
	 *            Valor de referência.
	 * @param tamanho
	 *            Tamanho mínimo (quantidade de caracteres) que o valor de
	 *            referência deverá possuir.
	 * @param caracter
	 *            Caracter que será adicionado à direita do valor para o mesmo
	 *            atingir o tamanho mínimo.
	 * @return {@link String}.
	 */
	public static String inserirCharDireita(String valor, int tamanho,
			char caracter) {

		if (valor == null) {
			valor = StringUtils.EMPTY;
		}

		return StringUtils.rightPad(valor, tamanho, caracter);
	}

	/**
	 * Insere o caracter à esquerda do valor informado, se necessário, até o
	 * mesmo atingir o tamanho mínimo esperado.
	 * 
	 * @param valor
	 *            Valor de referência.
	 * @param tamanho
	 *            Tamanho mínimo (quantidade de caracteres) que o valor de
	 *            referência deverá possuir.
	 * @param caracter
	 *            Caracter que será adicionado à esquerda do valor para o mesmo
	 *            atingir o tamanho mínimo.
	 * @return {@link String}.
	 */
	public static String inserirCharEsquerda(String valor, int tamanho,
			char caracter) {

		if (valor == null) {
			valor = StringUtils.EMPTY;
		}

		return StringUtils.leftPad(valor, tamanho, caracter);
	}

	/**
	 * Adiciona ZEROs a esquerda do número e trunca a String para o tamanho
	 * máximo informado.
	 * 
	 * @param numero
	 *            Número.
	 * @param tamanhoMaximo
	 *            Tamanho máximo permitido para a String final.
	 * @return String final.
	 */
	public static String trucarZerosEsquerda(String numero,
			Integer tamanhoMaximo) {

		String espacos = numero;

		for (int i = espacos.length(); i < tamanhoMaximo; i++) {
			espacos = "0" + espacos;
		}

		return espacos.substring(0, tamanhoMaximo);
	}

	/**
	 * Trunca o texto na quantidade de caracteres informados acrescentando três
	 * pontos ao final (<tt>os três pontos são
	 * considerados no quantidade máxima de caracteres</tt>).
	 * 
	 * @param texto
	 *            {@link String} original.
	 * @param tamanhoMaximo
	 *            Tamanho máximo permitido para a {@link String} final.
	 * @return {@link String} final.
	 */
	public static String truncarTexto(String texto, int tamanhoMaximo) {

		return StringUtils.abbreviate(texto, tamanhoMaximo);

	}

	/**
	 * Remove os espaços desnecessários de toda a String informada.
	 * 
	 * @param valor
	 *            {@link String} de referência.
	 * @return {@link String}.
	 */
	public static String removerEspacos(String valor) {

		return StringUtils.normalizeSpace(valor);
	}

	/**
	 * Remove todos os espaços da String informada.
	 * 
	 * @param valor
	 *            {@link String} de referência.
	 * @return {@link String}.
	 */
	public static String removerTodosEspacos(String valor) {

		return valor.replaceAll(" ", "");
	}

	/**
	 * Insere a máscara de formatação no valor da String informada.<br />
	 * <tt>Ex.: inserirMascara("11111111111",
	 * "###.###.###-##")</tt>.
	 * 
	 * @param valor
	 *            {@link String} que será manipulada.
	 * @param mascara
	 *            Máscara que será aplicada.
	 * @return Valor com a máscara de formatação.
	 */
	public static String inserirMascara(String valor, String mascara) {

		String novoValor = "";
		int posicao = 0;

		for (int i = 0; mascara.length() > i; i++) {
			if (mascara.charAt(i) == '#') {
				if (valor.length() > posicao) {
					novoValor = novoValor + valor.charAt(posicao);
					posicao++;
				} else
					break;
			} else {
				if (valor.length() > posicao) {
					novoValor = novoValor + mascara.charAt(i);
				} else {
					break;
				}
			}
		}
		return novoValor;
	}

	/**
	 * Remove as máscaras ou qualquer caractere que não seja um número da String
	 * informada.
	 * 
	 * @param valor
	 *            {@link String} que será manipulada.
	 * @return String contendo apenas números.
	 */
	public static String removerMascara(String valor) {

		Pattern replace = Pattern.compile("[^0-9]");
		Matcher matcher = replace.matcher(valor);

		return matcher.replaceAll("");
	}

	/**
	 * Remove a máscara da String informada.
	 * 
	 * @param valor
	 *            {@link String} que será manipulada.
	 * @param caracteresMascara
	 *            Array com os caracteres que compõe a máscara.
	 * @return String sem a máscara.
	 */
	public static String removerMascara(String valor,
			String... caracteresMascara) {

		StringBuilder sbRegex = new StringBuilder();
		sbRegex.append("[");

		for (int i = 0; i < caracteresMascara.length; i++) {

			sbRegex.append(caracteresMascara[i]);

			if (i + 1 < caracteresMascara.length) {
				sbRegex.append("|");
			}
		}

		sbRegex.append("]");

		Pattern regex = Pattern.compile(sbRegex.toString());
		Matcher matcher = regex.matcher(valor);

		return matcher.replaceAll("");
	}

	/**
	 * Remove os caracteres especiais da {@link String} informada.
	 * 
	 * @param valor
	 *            {@link String} valor que terá os caracteres especiais
	 *            removidos.
	 * @return {@link String} final.
	 */
	public static String removerCaracteresEspeciais(String valor) {

		CharSequence cs = new StringBuilder(valor);

		return Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll(
				"\\p{InCombiningDiacriticalMarks}+", "");
	}

	/**
	 * Converte uma data no formato texto para {@link Date} com o {@link Locale}
	 * brasileiro.
	 * 
	 * @param dataString
	 *            Data no formato texto.
	 * @param formato
	 *            Formato da data.
	 * @return {@link Date}.
	 */
	public static Date textoParaData(String dataString, String formato) {

		return textoParaData(dataString, formato, new Locale("pt", "BR"));
	}

	/**
	 * Converte uma data no formato texto para {@link Date} com o {@link Locale}
	 * informado.
	 * 
	 * @param dataString
	 *            Data no formato texto.
	 * @param padrao
	 *            Padrão da data.
	 * @param locale
	 *            {@link Locale}.
	 * @return {@link Date}.
	 */
	public static Date textoParaData(String dataString, String padrao,
			Locale locale) {

		Date data = null;

		try {

			DateFormat dateFormat = new SimpleDateFormat(padrao, locale);
			data = dateFormat.parse(dataString);

		} catch (Exception e) {
			data = null;
		}
		return data;
	}

	/**
	 * Converte um valor booleano no formato texto para {@link Boolean}.
	 * 
	 * @param booleanString
	 *            Valor booleano no formato texto.
	 * @param falsePadrao
	 *            <tt>TRUE</tt> retorna <i>FALSE</i> caso ocorra erro na
	 *            conversão, <tt>FALSE</tt> retorna <i>NULL</i> caso ocorra erro
	 *            na conversão.
	 * @return {@link Boolean}.
	 */
	public static Boolean textoParaBoolean(String booleanString,
			boolean falsePadrao) {

		Boolean resultado = null;

		try {
			resultado = Boolean.valueOf(booleanString);
		} catch (Exception e) {
			if (falsePadrao) {
				resultado = Boolean.FALSE;
			}
		}

		return resultado;
	}

	/**
	 * Converte um número no formato texto para {@link Integer}.
	 * 
	 * @param numeroString
	 *            Número no formato texto.
	 * @param zeroPadrao
	 *            <tt>TRUE</tt> retorna 0 (<i>ZERO</i>) caso ocorra erro na
	 *            conversão, <tt>FALSE</tt> retorna <i>NULL</i> caso ocorra erro
	 *            na conversão.
	 * @return {@link Integer}.
	 */
	public static Integer textoParaInteger(String numeroString,
			boolean zeroPadrao) {

		Integer resultado = null;

		try {
			resultado = Integer.valueOf(numeroString);
		} catch (Exception e) {
			if (zeroPadrao) {
				resultado = 0;
			}
		}

		return resultado;
	}

	/**
	 * Converte um número no formato texto para {@link Long}.
	 * 
	 * @param numeroString
	 *            Número no formato texto.
	 * @param zeroPadrao
	 *            <tt>TRUE</tt> retorna 0 (<i>ZERO</i>) caso ocorra erro na
	 *            conversão, <tt>FALSE</tt> retorna <i>NULL</i> caso ocorra erro
	 *            na conversão.
	 * @return {@link Long}.
	 */
	public static Long textoParaLong(String numeroString, boolean zeroPadrao) {

		Long resultado = null;

		try {
			resultado = Long.valueOf(numeroString);
		} catch (Exception e) {
			if (zeroPadrao) {
				resultado = 0L;
			}
		}

		return resultado;
	}

	/**
	 * Converte um número no formato texto para {@link Float}.
	 * 
	 * @param numeroString
	 *            Número no formato texto.
	 * @param zeroPadrao
	 *            <tt>TRUE</tt> retorna 0 (<i>ZERO</i>) caso ocorra erro na
	 *            conversão, <tt>FALSE</tt> retorna <i>NULL</i> caso ocorra erro
	 *            na conversão.
	 * @return {@link Float}.
	 */
	public static Float textoParaFloat(String numeroString, boolean zeroPadrao) {

		Float resultado = null;

		try {
			resultado = Float.valueOf(numeroString);
		} catch (Exception e) {
			if (zeroPadrao) {
				resultado = 0F;
			}
		}

		return resultado;
	}

	/**
	 * Converte um número no formato texto para {@link Double}.
	 * 
	 * @param numeroString
	 *            Número no formato texto.
	 * @param zeroPadrao
	 *            <tt>TRUE</tt> retorna 0 (<i>ZERO</i>) caso ocorra erro na
	 *            conversão, <tt>FALSE</tt> retorna <i>NULL</i> caso ocorra erro
	 *            na conversão.
	 * @return {@link Double}.
	 */
	public static Double textoParaDouble(String numeroString, boolean zeroPadrao) {

		Double resultado = null;

		try {
			resultado = Double.valueOf(numeroString);
		} catch (Exception e) {
			if (zeroPadrao) {
				resultado = 0D;
			}
		}

		return resultado;
	}

	/**
	 * Converte o valor formatado no padrão brasileiro do formato texto para
	 * double.
	 * 
	 * @param valor
	 *            Valor no formato texto.
	 * @return Valor convertido para double.
	 */
	public static Double textoFormatadoParaDouble(String valor) {

		Double valorConvertido = null;

		try {

			if (valor.length() <= 6) {
				valorConvertido = Double.parseDouble(valor.replace(',', '.'));
			} else {
				Pattern replace = Pattern.compile("[.]");
				Matcher matcher = replace.matcher(valor);

				valor = matcher.replaceAll("");

				valorConvertido = Double.parseDouble(valor.replace(',', '.'));
			}
		} catch (Exception e) {
			valorConvertido = null;
		}
		return valorConvertido;
	}

	/**
	 * Converte um número no formato texto para {@link BigDecimal}.
	 * 
	 * @param numeroString
	 *            Número no formato texto.
	 * @param zeroPadrao
	 *            <tt>TRUE</tt> retorna 0 (<i>ZERO</i>) caso ocorra erro na
	 *            conversão, <tt>FALSE</tt> retorna <i>NULL</i> caso ocorra erro
	 *            na conversão.
	 * @return {@link BigDecimal}.
	 */
	public static BigDecimal textoParaBigDecimal(String numeroString,
			boolean zeroPadrao) {

		BigDecimal resultado = null;

		try {
			resultado = BigDecimal.valueOf(textoParaDouble(numeroString,
					zeroPadrao));
		} catch (Exception e) {
			if (zeroPadrao) {
				resultado = BigDecimal.ZERO;
			}
		}

		return resultado;
	}

	/**
	 * Converte uma data do formato texto para {@link Date} usando o padrão
	 * <tt>dd/MM/yyyy</tt>.
	 * 
	 * @param dataString
	 *            Data no formato texto.
	 * @return {@link Date}.
	 */
	public static Date textoParaDate(String dataString) {

		return textoParaDate(dataString, null);
	}

	/**
	 * Converte uma data do formato texto para {@link Date}.
	 * 
	 * @param dataString
	 *            Data no formato texto.
	 * @param formato
	 *            Formato usado na transformação (<tt>Padrão: dd/MM/yyyy</tt>).
	 * @return {@link Date}.
	 */
	public static Date textoParaDate(String dataString, String formato) {

		if (!ValidacaoUtil.possuiValor(formato)) {
			formato = DataUtil.FORMATO_DATA_BR;
		}

		DateFormat df = new SimpleDateFormat(formato);
		Date data = null;

		try {

			data = df.parse(dataString);
		} catch (Exception e) {
			data = null;
		}
		return data;
	}

	/**
	 * Converte o objeto {@link Date} para o formato texto com o {@link Locale}
	 * brasileiro.
	 * 
	 * @param data
	 *            {@link Date}.
	 * @param formato
	 *            Formato da data (<tt>Padrão: dd/MM/yyyy</tt>).
	 * @return Data no formato texto.
	 */
	public static String dateParaTexto(Date data, String formato) {

		return dateParaTexto(data, formato, new Locale("pt", "BR"));
	}

	/**
	 * Converte o objeto {@link Date} para o formato texto com o {@link Locale}
	 * informado.
	 * 
	 * @param data
	 *            {@link Date}.
	 * @param formato
	 *            Formato da data (<tt>Padrão: dd/MM/yyyy</tt>).
	 * @param locale
	 *            {@link Locale}.
	 * @return Data no formato texto.
	 */
	public static String dateParaTexto(Date data, String formato, Locale locale) {

		if (!ValidacaoUtil.possuiValor(formato)) {
			formato = DataUtil.FORMATO_DATA_BR;
		}

		DateFormat df = new SimpleDateFormat(formato, locale);
		String dataString = null;

		try {

			dataString = df.format(data);
		} catch (Exception e) {
			dataString = null;
		}
		return dataString;
	}

	/**
	 * Converte a data no formato {@link XMLGregorianCalendar} para o formato
	 * texto.
	 * 
	 * @param data
	 *            {@link Date}.
	 * @return Data no formato texto.
	 */
	public static String xmlGregorianCalendarParaTexto(
			XMLGregorianCalendar xmlGregorianCalendar) {

		return xmlGregorianCalendarParaTexto(xmlGregorianCalendar, true, false);
	}

	/**
	 * Converte a data no formato {@link XMLGregorianCalendar} para o formato
	 * texto.
	 * 
	 * @param xmlGregorianCalendar
	 *            {@link XMLGregorianCalendar}.
	 * @param timezone
	 *            <tt>TRUE</tt> exibe o timezone na data convertida,
	 *            <tt>FALSE</tt> não exibe o timezone.
	 * @param milisegundos
	 *            <tt>TRUE</tt> exibe os milisegundos na data convertida,
	 *            <tt>FALSE</tt> não exibe os milisegundos.
	 * @return Data no formato texto.
	 */
	public static String xmlGregorianCalendarParaTexto(
			XMLGregorianCalendar xmlGregorianCalendar, boolean timezone,
			boolean milisegundos) {

		String dataString = null;

		try {

			if (!timezone) {
				xmlGregorianCalendar
						.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			}

			if (!milisegundos) {
				xmlGregorianCalendar
						.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
			}

			dataString = xmlGregorianCalendar.toXMLFormat();
		} catch (Exception e) {
			dataString = null;
		}
		return dataString;
	}

	/**
	 * Verifica se a data informada no formato texto é válida.
	 * 
	 * <p>
	 * <tt>Usar a validação da classe ValidacaoUtil.</tt>
	 * </p>
	 * 
	 * @param dataString
	 *            Data no formato texto.
	 * @param formato
	 *            Formato da data (<tt>Padrão: dd/MM/yyyy</tt>).
	 * @return <tt>TRUE<tt> se for válida, <tt>FALSE</tt> caso contrário.
	 */
	@Deprecated
	public static boolean isDataValida(String dataString, String formato) {

		return ValidacaoUtil.isDataValida(dataString, formato);
	}

	/**
	 * Verifica se o CPF ou CNPJ é válido.
	 * 
	 * <p>
	 * <tt>Usar a validação da classe ValidacaoUtil.</tt>
	 * </p>
	 * 
	 * @param numeroCpfCnpj
	 *            Número do CPF ou CNPJ.
	 * @return <tt>TRUE</tt> se for válido, <tt>FALSE</tt> caso contrário.
	 */
	@Deprecated
	public static boolean isCpfCnpjValido(String numeroCpfCnpj,
			boolean permitirRepeticao) {

		return ValidacaoUtil.isCpfCnpjValido(numeroCpfCnpj, permitirRepeticao);
	}

	/**
	 * Verifica se o endereço de e-mail é válido.
	 * 
	 * <p>
	 * <tt>Usar a validação da classe ValidacaoUtil.</tt>
	 * </p>
	 * 
	 * @param email
	 *            Endereço de e-mail.
	 * @return <tt>TRUE</tt> se for válido, <tt>FALSE</tt> caso contrário.
	 */
	@Deprecated
	public static boolean isEmailValido(String email) {

		return ValidacaoUtil.isEmailValido(email);
	}

	/**
	 * Converte um {@link Double} para uma {@link String}
	 * 
	 * @param numeroDouble
	 *            Número no formato {@link Double}
	 * @return {@link String}
	 */
	public static String doubleParaTexto(Double numeroDouble) {

		return String.format("%.2f", numeroDouble);
	}

	public static String getCpfComZerosAEsqueda(String cpf) {
		String cpfComZerosAEsquerda;
		int tamanhoPadraoCPF = 11;			
		cpfComZerosAEsquerda = inserirCharEsquerda(cpf, tamanhoPadraoCPF, '0');
		return cpfComZerosAEsquerda;
	}

}
