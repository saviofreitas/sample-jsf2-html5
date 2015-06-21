package com.jsf22.html5.app.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DataUtil {

    /** Formato de data brasileira (dd/MM/yyyy). */
    public static final String FORMATO_DATA_BR = "dd/MM/yyyy";

    /** Formato de data norte americana (yyyy/MM/dd). */
    public static final String FORMATO_DATA_US = "yyyy/MM/dd";

    /** Formato de hora norte americana (HH:mm:ss). */
    public static final String FORMATO_HORA_BR = "HH:mm:ss";

    /** Formato de hora norte americana (HH:mm:ss). */
    public static final String FORMATO_HORA_US = "HH:mm:ss";

    /** Formato de data e hora norte americana (dd/MM/yyyy HH:mm:ss). */
    public static final String FORMATO_DATA_HORA_BR = "dd/MM/yyyy HH:mm:ss";

    /** Formato de data e hora norte americana (yyyy/MM/dd HH:mm:ss). */
    public static final String FORMATO_DATA_HORA_US = "yyyy/MM/dd HH:mm:ss";

    /**
     * Converte uma data de {@link Date} para {@link java.sql.Date}.
     * 
     * @param data Data no formato {@link Date}.
     * @return {@link java.sql.Date}.
     */
    public static java.sql.Date dateParaSqlDate(Date data) {

        return new java.sql.Date(data.getTime());
    }

    /**
     * Converte uma data de {@link Date} para {@link Calendar}.
     * 
     * @param data Data no formato {@link Date}.
     * @return {@link Calendar}.
     */
    public static Calendar dateParaCalendar(Date data) {

        Calendar c = Calendar.getInstance();
        c.setTime(data);
        return c;
    }

    /**
     * Retorna a data data com a hora, minuto, segundo e milisegundos configurados para zero.
     * 
     * @param data {@link Date} Data.
     * @return Data zerada.
     */
    public static Date zerarData(Date data) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(data);

        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        return calendar.getTime();
    }

    /**
     * Cria um {@link Date} concatenando uma data com uma hora.
     * 
     * @param data {@link Date} Data.
     * @param hora {@link Date} Hora.
     * @return {@link Date} Data e hora.
     */
    public static Date concatenarDataHora(Date data, Date hora) {

        Calendar cDataInicio = Calendar.getInstance();
        cDataInicio.setTime(data);

        Calendar cHoraInicio = Calendar.getInstance();
        cHoraInicio.setTime(hora);

        cDataInicio.set(Calendar.HOUR_OF_DAY, cHoraInicio.get(Calendar.HOUR_OF_DAY));
        cDataInicio.set(Calendar.MINUTE, cHoraInicio.get(Calendar.MINUTE));
        cDataInicio.set(Calendar.SECOND, 0);

        return cDataInicio.getTime();
    }

    /**
     * Retorna a data do dia anterior à data atual.
     * 
     * @return {@link Date} dia anterior.
     */
    public static Date obterDiaAnterior() {

        return obterDiaAnterior(new Date());
    }

    /**
     * Retorna a data do dia anterior à data informada.
     * 
     * @param data Data de referência.
     * @return {@link Date} dia anterior.
     */
    public static Date obterDiaAnterior(Date data) {

        return obterDiaAnterior(data, 1);
    }

    /**
     * Retorna a data antes dos dias informados da data de referência.
     * 
     * @param data Data de referência.
     * @param dias Quantidade de dias que serão subtraídos da data de referência.
     * @return {@link Date}.
     */
    public static Date obterDiaAnterior(Date data, Integer dias) {

        dias = dias * -1;

        Calendar hoje = new GregorianCalendar();
        hoje.setTime(zerarData(data));

        hoje.add(Calendar.DATE, dias);

        return hoje.getTime();
    }

    /**
     * Retorna a data do dia posterior à data atual.
     * 
     * @return {@link Date} dia posterior.
     */
    public static Date obterDiaPosterior() {

        return obterDiaPosterior(new Date());
    }

    /**
     * Retorna a data do dia posterior à data informada.
     * 
     * @param data Data de referência.
     * @return {@link Date} dia posterior.
     */
    public static Date obterDiaPosterior(Date data) {

        return obterDiaPosterior(data, 1);
    }

    /**
     * Retorna a data depois dos dias informados da data de referência.
     * 
     * @param data Data de referência.
     * @param dias Quantidade de dias que serão adicionados da data de referência.
     * @return {@link Date}.
     */
    public static Date obterDiaPosterior(Date data, Integer dias) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(zerarData(data));

        calendar.add(Calendar.DATE, dias);

        return calendar.getTime();
    }

    /**
     * Retorna a idade baseado na data de nascimento.
     * 
     * @param dataNascimento {@link Date} Data de nascimento.
     * @return Idade.
     */
    public static int obterIdade(Date dataNascimento) {

        return (int) (diferencaEmDias(dataNascimento, new Date()) / 365);
    }

    /**
     * Converte uma data de {@link Date} para {@link XMLGregorianCalendar}. No formato: yyyy-MM-ddTHH:mm:ss.SSS.
     * 
     * @param data {@link Date} Data.
     * @return Data em {@link XMLGregorianCalendar}.
     * @since 02/10/2012
     */
    public static XMLGregorianCalendar dateParaXMLGregorianCalendar(Date data) {

        return dateParaXMLGregorianCalendar(data, true, false);
    }

    /**
     * Converte a data no formato {@link Date} para o formato texto no padrão XMLGregorianCalendar.
     * 
     * @param data {@link Date}.
     * @param timezone <tt>TRUE</tt> exibe o timezone na data convertida, <tt>FALSE</tt> não exibe o timezone.
     * @param milisegundos <tt>TRUE</tt> exibe os milisegundos na data convertida, <tt>FALSE</tt> não exibe os
     *        milisegundos.
     * @return Data no formato XMLGregorianCalendar.
     */
    public static XMLGregorianCalendar dateParaXMLGregorianCalendar(Date data, boolean timezone, boolean milisegundos) {

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(data);
        XMLGregorianCalendar xmlGregorianCalendar;

        try {

            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

            if (!timezone) {
                xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            }

            if (!milisegundos) {
                xmlGregorianCalendar.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
            }

        } catch (DatatypeConfigurationException e) {
            xmlGregorianCalendar = null;
        }
        return xmlGregorianCalendar;
    }

    /**
     * Converte a data no formato {@link XMLGregorianCalendar} para o formato {@link Date}.
     * 
     * @param xmlGregorianCalendar {@link XMLGregorianCalendar}.
     * @return {@link Date}.
     */
    public static Date xmlGregorianCalendarParaDate(XMLGregorianCalendar xmlGregorianCalendar) {

        Date data = null;

        try {

            data = xmlGregorianCalendar.toGregorianCalendar().getTime();
        } catch (Exception e) {
            data = null;
        }
        return data;
    }

    /**
     * Retorna a diferença em dias entre duas datas.
     * 
     * @param dataInicial objeto {@link Date} representando a data inicial
     * @param dataFinal objeto {@link Date} representando a data final
     * @return Diferença em dias
     */
    public static double diferencaEmDias(Date dataInicial, Date dataFinal) {

        double resultado = 0;
        long diferenca = dataFinal.getTime() - dataInicial.getTime();

        // Calcula a diferenca em dias
        double diferencaEmDias = (diferenca / 1000) / 60 / 60 / 24;

        // Calcula as horas restantes
        long horasRestantes = (diferenca / 1000) / 60 / 60 % 24;

        // Adiciona as horas restantes como fracao de um dia
        resultado = diferencaEmDias + horasRestantes;

        return resultado;
    }

    /**
     * Verifica se a data inicial é menor que a data final.
     * 
     * @param dataInicial {@link Date} Data inicial.
     * @param dataFinal {@link Date} Data final.
     * @return <tt>TRUE</tt> se a data inicial for menor que a data final, <tt>FALSE</tt> caso contrário.
     */
    public static boolean dataInicialMenorDataFinal(Date dataInicial, Date dataFinal) {

        return dataInicial.compareTo(dataFinal) < 0;
    }

    /**
     * Verifica se a data inicial é menor ou igual que a data final.
     * 
     * @param dataInicial {@link Date} Data inicial.
     * @param dataFinal {@link Date} Data final.
     * @return <tt>TRUE</tt> se a data inicial for menor ou igual que a data final, <tt>FALSE</tt> caso contrário.
     */
    public static boolean dataInicialMenorIgualDataFinal(Date dataInicial, Date dataFinal) {

        return dataInicial.compareTo(dataFinal) <= 0;
    }

    /**
     * Verifica se a data inicial é maior que a data final.
     * 
     * @param dataInicial {@link Date} Data inicial.
     * @param dataFinal {@link Date} Data final.
     * @return <tt>TRUE</tt> se a data inicial for maior que a data final, <tt>FALSE</tt> caso contrário.
     */
    public static boolean dataInicialMaiorDataFinal(Date dataInicial, Date dataFinal) {

        return dataInicial.compareTo(dataFinal) > 0;
    }

    /**
     * Verifica se a data inicial é maior ou igual que a data final.
     * 
     * @param dataInicial {@link Date} Data inicial.
     * @param dataFinal {@link Date} Data final.
     * @return <tt>TRUE</tt> se a data inicial for maior ou igual que a data final, <tt>FALSE</tt> caso contrário.
     */
    public static boolean dataInicialMaiorIgualDataFinal(Date dataInicial, Date dataFinal) {

        return dataInicial.compareTo(dataFinal) >= 0;
    }

    /**
     * Retorna as datas compreendidas dentro do período, onde a referência são os meses 12 (dezembro).
     * 
     * @param dataInicial {@link Date} Data incial.
     * @param dataFinal {@link Date} Data final.
     * @return {@link List} Lista com as datas.
     */
    public static List<Date> obterDataPeriodoAnual(Date dataInicial, Date dataFinal) {

        List<Date> datas = new ArrayList<Date>();

        Date dataAux = zerarData(dataInicial);
        Calendar c = dateParaCalendar(dataAux);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, Calendar.DECEMBER);

        while (dataInicialMenorIgualDataFinal(c.getTime(), dataFinal)) {

            if (dataInicialMenorIgualDataFinal(dataInicial, c.getTime())) {

                datas.add(c.getTime());
                c.add(Calendar.YEAR, 1);
            } else {

                c.add(Calendar.YEAR, 1);
            }
        }
        return datas;
    }

    /**
     * Retorna as datas compreendidas dentro do período, onde a referência é o dia 1 dos meses 6 (junho) e 12
     * (dezembro).
     * 
     * @param dataInicial {@link Date} Data incial.
     * @param dataFinal {@link Date} Data final.
     * @return {@link List} Lista com as datas.
     */
    public static List<Date> obterDataPeriodoSemestral(Date dataInicial, Date dataFinal) {

        List<Date> datas = new ArrayList<Date>();

        Date dataAux = zerarData(dataInicial);
        Calendar c = dateParaCalendar(dataAux);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, Calendar.JUNE);

        while (dataInicialMenorIgualDataFinal(c.getTime(), dataFinal)) {

            if (dataInicialMenorIgualDataFinal(dataInicial, c.getTime())) {

                datas.add(c.getTime());
                c.add(Calendar.MONTH, 6);
            } else {

                c.add(Calendar.MONTH, 6);
            }
        }
        return datas;
    }

    /**
     * Retorna as datas compreendidas dentro do período, onde a referência é o dia 1 e o meses 3, 6, 12.
     * 
     * @param dataInicial {@link Date} Data incial.
     * @param dataFinal {@link Date} Data final.
     * @return {@link List} Lista com as datas.
     */
    public static List<Date> obterDataPeriodoTrimestral(Date dataInicial, Date dataFinal) {

        List<Date> datas = new ArrayList<Date>();

        Date dataAux = zerarData(dataInicial);
        Calendar c = dateParaCalendar(dataAux);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, Calendar.MARCH);

        while (dataInicialMenorIgualDataFinal(c.getTime(), dataFinal)) {

            if (dataInicialMenorIgualDataFinal(dataInicial, c.getTime())) {

                datas.add(c.getTime());
                c.add(Calendar.MONTH, 3);
            } else {

                c.add(Calendar.MONTH, 3);
            }
        }
        return datas;
    }

    /**
     * Retorna as datas compreendidas dentro do período, onde a referência são os meses 12 (dezembro).
     * 
     * @param dataInicial {@link Date} Data incial.
     * @param dataFinal {@link Date} Data final.
     * @return {@link List} Lista com as datas.
     */
    public static List<Date> obterDataPeriodoMensal(Date dataInicial, Date dataFinal) {

        List<Date> datas = new ArrayList<Date>();

        Date dataAux = zerarData(dataInicial);
        Calendar c = dateParaCalendar(dataAux);
        c.set(Calendar.DAY_OF_MONTH, 1);

        while (dataInicialMenorIgualDataFinal(c.getTime(), dataFinal)) {

            if (dataInicialMenorIgualDataFinal(dataInicial, c.getTime())) {

                datas.add(c.getTime());
                c.add(Calendar.MONTH, 1);
            } else {

                c.add(Calendar.MONTH, 1);
            }
        }
        return datas;
    }

}
