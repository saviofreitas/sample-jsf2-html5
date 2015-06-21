package com.jsf22.html5.app.util;

import java.math.BigInteger;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateUtils;

public class ValidacaoUtil {

    /** Padrao (expressão regular) para string com somente letras. */
    public static final String SOMENTE_NUMEROS_PATTERN = "^[0-9]*$";

    /** Padrao (expressão regular) para string com somente letras. */
    public static final String SOMENTE_LETRAS_PATTERN = "^[A-Za-z]*$";

    /** Padrao (expressão regular) para validar nome telefone - (11) 1111-1111 ou (11) 11111-1111. */
    public static final String TELEFONE_BR_PATTERN = "^\\(?[0-9]{2}\\)?[ \\-]?[0-9]{4,5}-?[0-9]{4}$";

    /** Padrao (expressão regular) para validar nome de arquivo. */
    public static final String NOME_ARQUIVO_PATTERN = "^[a-zA-Z0-9-_\\.]+\\.(pdf|txt|doc|csv)?[a-z]{3,4}$";

    /** Padrao (expressão regular) para validar e-mail. */
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /** Padrao (expressão regular) para validar IP. */
    public static final String ENDERECO_IP_PATTERN = "^([01]?\\d{1,2}|2[0-4]\\d|25[0-5])(\\.([01]?\\d{1,2}|2[0-4]\\d|25[0-5])){3}$";

    /** Padrao (expressão regular) para validar Host segundo RFC 1123. */
    public static final String NOME_HOST_RFC_1123_PATTERN = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$";

    /** Padrao (expressão regular) para validar Host segundo RFC 952. */
    public static final String NOME_HOST_RFC_952_PATTERN = "^(([a-zA-Z]|[a-zA-Z][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z]|[A-Za-z][A-Za-z0-9\\-]*[A-Za-z0-9])$";

    /**
     * Construtor da classe.
     */
    private ValidacaoUtil() {

        super();
    }

    /**
     * Verifica se o conteúdo da String contém somente números.
     * 
     * @param numero Números.
     * @return <tt>TRUE</tt> se for somente números, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isSomenteNumeros(String numero) {

        return isValorBaseadoExpressaoRegularValido(SOMENTE_NUMEROS_PATTERN, numero);
    }

    /**
     * Verifica se o conteúdo da String contém somente letras.
     * 
     * @param letras Letras.
     * @return <tt>TRUE</tt> se for somente letras, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isSomenteLetras(String letras) {

        return isValorBaseadoExpressaoRegularValido(SOMENTE_LETRAS_PATTERN, letras);
    }

    /**
     * Verifica se a data informada no formato texto é válida.
     * 
     * @param dataString Data no formato texto.
     * @param formato Formato da data (<tt>Padrão: dd/MM/yyyy</tt>).
     * @return <tt>TRUE<tt> se for válida, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isDataValida(String dataString, String formato) {

        boolean resultado = true;

        if (!possuiValor(formato)) {
            formato = DataUtil.FORMATO_DATA_BR;
        }

        final DateFormat df = new SimpleDateFormat(formato);
        df.setLenient(false);

        try {
            Date data = df.parse(dataString);
            resultado = data != null;
        } catch (Exception e) {
            resultado = false;
        }
        return resultado;
    }

    /**
     * Verifica se o CPF ou CNPJ é válido
     * 
     * @param numeroCpfCnpj Número do CPF ou CNPJ.
     * @return <tt>TRUE</tt> se for válido, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isCpfCnpjValido(String numeroCpfCnpj, boolean permitirRepeticao) {

        if (numeroCpfCnpj == null) {
            return false;
        }

        String[] cpfsInvalidos = { "00000000000", "11111111111", "22222222222", "33333333333", "44444444444",
            "55555555555", "66666666666", "77777777777", "88888888888", "99999999999" };

        String cpfCnpj = numeroCpfCnpj.replaceAll("[^0-9]*", ""); // Remove tudo que nao seja numero
        boolean isCnpj = cpfCnpj.length() == 14;
        boolean isCpf = cpfCnpj.length() == 11;

        if (!isCpf && !isCnpj) {
            return false;
        }
        if (!permitirRepeticao && Arrays.asList(cpfsInvalidos).contains(cpfCnpj)) {
            return false;
        }

        int i; // Variavel do laco interno
        int j; // Variavel do laco externo
        int digitoVerificador; // Digito verificador
        int coeficiente; // O coeficiente
        int soma; // Armazenar as somas (digitoVerificador * coeficiente)
        int[] arrayDV = { 0, 0 }; // Array para armazenar os digitos verificadores calculados (Dv1 e Dv2)
        int dv1 = Integer.parseInt(String.valueOf(cpfCnpj.charAt(cpfCnpj.length() - 2))); // Digito verificador digitado
        int dv2 = Integer.parseInt(String.valueOf(cpfCnpj.charAt(cpfCnpj.length() - 1))); // Digito verificador digitado

        for (j = 0; j < 2; j++) {
            soma = 0;
            coeficiente = 2;
            for (i = cpfCnpj.length() - 3 + j; i >= 0; i--) {
                digitoVerificador = Integer.parseInt(String.valueOf(cpfCnpj.charAt(i)));
                soma += digitoVerificador * coeficiente;
                coeficiente++;
                if (coeficiente > 9 && isCnpj) {
                    coeficiente = 2;
                }
            }
            arrayDV[j] = 11 - soma % 11;
            if (arrayDV[j] >= 10) {
                arrayDV[j] = 0;
            }
        }
        return dv1 == arrayDV[0] && dv2 == arrayDV[1];
    }

    /**
     * Verifica se o PIS/PASEP é válido
     * 
     * @param pisPasep Número do PIS/PASEP.
     * @return <tt>TRUE</tt> se for válido, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isPisPasepValido(String pisPasep) {

        int liTamanho = 0;
        StringBuffer lsAux = null;
        StringBuffer lsMultiplicador = new StringBuffer("3298765432");
        int liTotalizador = 0;
        int liResto = 0;
        int liMultiplicando = 0;
        int liMultiplicador = 0;
        boolean lbRetorno = true;
        int liDigito = 99;

        lsAux = new StringBuffer().append(pisPasep.replaceAll("[^0-9]*", "")); // Append removendo tudo que nao seja
                                                                               // numero
        liTamanho = lsAux.length();

        if (liTamanho != 11) {
            lbRetorno = false;
        }

        if (lbRetorno) {
            for (int i = 0; i < 10; i++) {

                liMultiplicando = Integer.parseInt(lsAux.substring(i, i + 1));
                liMultiplicador = Integer.parseInt(lsMultiplicador.substring(i, i + 1));
                liTotalizador += liMultiplicando * liMultiplicador;
            }

            liResto = 11 - liTotalizador % 11;
            liResto = liResto == 10 || liResto == 11 ? 0 : liResto;

            liDigito = Integer.parseInt("" + lsAux.charAt(10));
            lbRetorno = liResto == liDigito;
        }

        return lbRetorno;

    }

    // Validar URL.
    public static boolean isURI(String str) {

        if (str.indexOf(':') == -1)
            return false;
        str = str.toLowerCase().trim();

        if (!str.startsWith("http://")
            && !str.startsWith("https://")
            && !str.startsWith("file://")
            && !str.startsWith("ftp://")
            && !str.startsWith("mailto:")
            && !str.startsWith("news:")
            && !str.startsWith("urn:"))
            return false;

        try {

            URI uri = new URI(str);
            String proto = uri.getScheme();

            if (proto == null)
                return false;

            if (proto.equals("http") || proto.equals("https") || proto.equals("file") || proto.equals("ftp")) {

                if (uri.getHost() == null)
                    return false;

                String path = uri.getPath();
                if (path != null) {

                    int len = path.length();
                    for (int i = 0; i < len; i++) {

                        if ("?<>:*|\"".indexOf(path.charAt(i)) > -1)
                            return false;
                    }
                }
            }

            return true;
        } catch (Exception ex) {

            return false;
        }
    }

    // Validar Título de Eleitor
    public static boolean isTituloValido(String strTitulo) {

        int dig1;
        int dig2;
        int dig3;
        int dig4;
        int dig5;
        int dig6;
        int dig7;
        int dig8;
        int dig9;
        int dig10;
        int dig11;
        int dig12;
        int dv1;
        int dv2;
        int qDig;

        if (strTitulo.length() == 0) // Validação do preenchimento
        {
            return false; // Caso não seja informado o Título
        } else {
            if (strTitulo.length() < 12) { // Completar 12 dígitos strTitulo = "000000000000" + strTitulo; strTitulo =
                                           // strTitulo.substring(strTitulo.length() - 12); } else if
                                           // (strTitulo.length() > 12)
                {
                    return false; // Caso tenha mais que 12 dígitos
                }
            }

            qDig = strTitulo.length(); // Total de caracteres

            // Gravar posição dos caracteres
            dig1 = Integer.parseInt(Mid(strTitulo, qDig - 11, 1));
            dig2 = Integer.parseInt(Mid(strTitulo, qDig - 10, 1));
            dig3 = Integer.parseInt(Mid(strTitulo, qDig - 9, 1));
            dig4 = Integer.parseInt(Mid(strTitulo, qDig - 8, 1));
            dig5 = Integer.parseInt(Mid(strTitulo, qDig - 7, 1));
            dig6 = Integer.parseInt(Mid(strTitulo, qDig - 6, 1));
            dig7 = Integer.parseInt(Mid(strTitulo, qDig - 5, 1));
            dig8 = Integer.parseInt(Mid(strTitulo, qDig - 4, 1));
            dig9 = Integer.parseInt(Mid(strTitulo, qDig - 3, 1));
            dig10 = Integer.parseInt(Mid(strTitulo, qDig - 2, 1));
            dig11 = Integer.parseInt(Mid(strTitulo, qDig - 1, 1));
            dig12 = Integer.parseInt(Mid(strTitulo, qDig, 1));

            // Cálculo para o primeiro dígito validador
            dv1 = (dig1 * 2) + (dig2 * 3) + (dig3 * 4) + (dig4 * 5) + (dig5 * 6) + (dig6 * 7) + (dig7 * 8) + (dig8 * 9);
            dv1 = dv1 % 11;

            if (dv1 == 10) {
                dv1 = 0; // Se o resto for igual a 10, dv1 igual a zero
            }

            // Cálculo para o segundo dígito validador
            dv2 = (dig9 * 7) + (dig10 * 8) + (dv1 * 9);
            dv2 = dv2 % 11;

            if (dv2 == 10) {
                dv2 = 0; // Se o resto for igual a 10, dv1 igual a zero
            }

            // Validação dos dígitos validadores, após o cálculo realizado
            if (dig11 == dv1 && dig12 == dv2) {
                return true;
            } else {
                return false;
            }

        }

    }

    // Função Mid
    public static String Mid(String texto, int inicio, int tamanho) {

        String strMid = texto.substring(inicio - 1, inicio + (tamanho - 1));
        return strMid;
    }

    /**
     * Verifica se o endereço de e-mail é válido.
     * 
     * @param email Endereço de e-mail.
     * @return <tt>TRUE</tt> se for válido, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isEmailValido(String email) {

        return isValorBaseadoExpressaoRegularValido(EMAIL_PATTERN, email);
    }

    /**
     * Verifica se o nome do arquivo é válido.
     * 
     * @param nomeArquivo Nome do arquivo.
     * @return <tt>TRUE</tt> se for válido, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isNomeArquivoValido(String nomeArquivo) {

        return isValorBaseadoExpressaoRegularValido(NOME_ARQUIVO_PATTERN, nomeArquivo);
    }

    /**
     * Verifica se o número do telefone é válido.
     * 
     * @param telefone Número do telefone.
     * @return <tt>TRUE</tt> se for válido, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isTelefoneValido(String telefone) {

        return isValorBaseadoExpressaoRegularValido(TELEFONE_BR_PATTERN, telefone);
    }

    /**
     * Verifica se o endereço IP é válido.
     * 
     * @param ip Endereço IP.
     * @return <tt>TRUE</tt> se for válido, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isEnderecoIPValido(String ip) {

        return isValorBaseadoExpressaoRegularValido(ENDERECO_IP_PATTERN, ip);
    }

    /**
     * Verifica se o nome do Host é válido. <p><tt>Será usado a RFC 1123, que permite que nomes de Host inicie com
     * números.</tt></p>
     * 
     * @param nomeHost Nome do host.
     * @return <tt>TRUE</tt> se for válido, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isNomeHostValido(String nomeHost) {

        return isNomeHostValidoRFC1123(nomeHost);
    }

    /**
     * Verifica se o nome do Host, baseado na RFC 1123, é válido.
     * 
     * @param nomeHost Nome do host.
     * @return <tt>TRUE</tt> se for válido, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isNomeHostValidoRFC1123(String nomeHost) {

        return isValorBaseadoExpressaoRegularValido(NOME_HOST_RFC_1123_PATTERN, nomeHost);
    }

    /**
     * Verifica se o nome do Host, baseado na RFC 952, é válido.
     * 
     * @param nomeHost Nome do host.
     * @return <tt>TRUE</tt> se for válido, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isNomeHostValidoRFC952(String nomeHost) {

        return isValorBaseadoExpressaoRegularValido(NOME_HOST_RFC_952_PATTERN, nomeHost);
    }

    /**
     * Verifica se o valor é válido baseado na expressão regular.
     * 
     * @param expressaoRegular Expressão regular.
     * @param valor Valor que será verificado.
     * @return <tt>TRUE</tt> se for válido, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isValorBaseadoExpressaoRegularValido(String expressaoRegular, String valor) {

        Pattern pattern = Pattern.compile(expressaoRegular);
        Matcher matcher = pattern.matcher(valor);

        return matcher.matches();
    }

    /**
     * Verifica se a data de nascimento é válida.
     * 
     * @param dataNascimento Data de nascimento.
     * @param idade Idade máxima/mínima permitida.
     * @param maiorQue <tt>TRUE</tt> para verificar se a idade é maior que a informada, <tt>FALSE</tt> caso contrário.
     * @return <tt>TRUE</tt> se for válida, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isDataNascimentoValida(Date dataNascimento, int idade, boolean maiorQue) {

        Date data = DateUtils.addYears(dataNascimento, idade);

        if (maiorQue) {
            return DataUtil.zerarData(data).compareTo(DataUtil.zerarData(new Date())) <= 0;
        }
        return DataUtil.zerarData(data).compareTo(DataUtil.zerarData(new Date())) > 0;
    }

    /**
     * Verifica se uma expressão matemática é valida.
     * 
     * @param expressao Expressão matemática.
     * @return <tt>TRUE</tt> se for válida, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isExpressaoMatematicaValida(String expressao) {

        boolean expressaoValida = true;
        expressao = expressao.replaceAll(" ", "");

        Stack<Character> pilha = new Stack<Character>();

        for (int indice = 0; indice < expressao.length(); indice++) {

            if (expressao.charAt(indice) == '{' || expressao.charAt(indice) == '[' || expressao.charAt(indice) == '(') {

                pilha.push(expressao.charAt(indice));
            } else if (expressao.charAt(indice) == '}'
                || expressao.charAt(indice) == ']'
                || expressao.charAt(indice) == ')') {

                if (pilha.isEmpty()) {
                    expressaoValida = false;
                    break;
                } else if (expressao.charAt(indice) == ')' && pilha.peek().equals('(')) {

                    pilha.pop();
                    continue;
                } else if (expressao.charAt(indice) == ']' && pilha.peek().equals('[')) {

                    pilha.pop();
                    continue;
                } else if (expressao.charAt(indice) == '}' && pilha.peek().equals('{')) {

                    pilha.pop();
                    continue;
                }
                expressaoValida = false;
                break;
            }
        }

        if (expressaoValida) {

            expressaoValida = pilha.isEmpty();
        }
        return expressaoValida;
    }

    /**
     * Verifica se o número do processo é válido.
     * 
     * @param numero Número do processo (<tt>NNNNNNN-DD.AAAA.JTR.OOOO</tt>).
     * @return <tt>TRUE</tt> se for válido, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isNumeroProcessoValido(String numero) {

        boolean valido = false;

        if (possuiValor(numero) && numero.length() < 20) {

            valido = false;

        } else {

            StringBuilder sb = new StringBuilder(numero);
            String dv = sb.substring(7, 9);
            sb.delete(7, 9).append(dv);

            BigInteger soma1 = BigInteger.ZERO;
            BigInteger soma2 = BigInteger.ZERO;
            BigInteger resto1 = BigInteger.ZERO;
            BigInteger resto2 = BigInteger.ZERO;
            BigInteger dvResultado = BigInteger.ZERO;

            BigInteger multiplicador = BigInteger.valueOf(10000000000l);

            for (int i = 0; i < 11; i++) {

                soma1 = soma1.add(BigInteger.valueOf(Long.valueOf("" + sb.charAt(i))).multiply(multiplicador));
                multiplicador = multiplicador.divide(BigInteger.TEN);
            }

            resto1 = soma1.mod(BigInteger.valueOf(97l));

            multiplicador = BigInteger.valueOf(1000000l);

            for (int i = 11; i < 18; i++) {

                soma2 = soma2.add(BigInteger.valueOf(Long.valueOf("" + sb.charAt(i))).multiply(multiplicador));
                multiplicador = multiplicador.divide(BigInteger.TEN);
            }

            resto2 = resto1.multiply(BigInteger.valueOf(1000000000l));
            resto2 = resto2.add(soma2.multiply(BigInteger.valueOf(100l)));
            resto2 = resto2.mod(BigInteger.valueOf(97l));

            dvResultado = BigInteger.valueOf(98l).subtract(resto2);
            valido = BigInteger.valueOf(Long.valueOf(sb.substring(18))).equals(dvResultado);
        }
        return valido;
    }

    /**
     * Verifica se o valor não é nulo ou vazio.
     * 
     * @param valor Valor.
     * @return <tt>TRUE</tt> se não for <b>nulo</b> ou <b>vazio</b>, <tt>FALSE</tt> caso contrário.
     */
    public static boolean possuiValor(final Object valor) {

        boolean possuiValor = false;
        if (valor != null) {
            if (valor instanceof String || valor instanceof Character) {
                possuiValor = !valor.toString().trim().equals("");
            } else if (valor instanceof Collection<?>) {
                possuiValor = !((Collection<?>) valor).isEmpty();
            } else if (valor instanceof Map<?, ?>) {
                possuiValor = !((Map<?, ?>) valor).isEmpty();
            } else if (valor instanceof Object[]) {
                possuiValor = ((Object[]) valor).length > 0;
            } else {
                possuiValor = true;
            }
        }
        return possuiValor;
    }

}
