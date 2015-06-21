package com.jsf22.html5.app.util;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

import org.apache.log4j.Logger;

@Singleton
public class LogUtil implements Serializable {

    /** */
    private static final long serialVersionUID = -4870035820353083372L;

    /**
     * Verifica se o nível de TRACE está habilitado.
     * 
     * @param logger {@link Logger}.
     * @return <tt>TRUE</tt> se estiver habilitado, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isTraceHabilitado(Logger logger) {

        return logger.isTraceEnabled();
    }

    /**
     * Realiza o log da mensagem com nível de TRACE.
     * 
     * @param logger {@link Logger}.
     * @param mensagem Mensagem de log.
     */
    public static void trace(Logger logger, String mensagem) {

        logger.trace(mensagem);
    }

    /**
     * Realiza o log da mensagem com nível de TRACE.
     * 
     * @param logger {@link Logger}.
     * @param chave Chave da mensagem no arquivo de recursos.
     * @param argumentos Argumentos que serão substituídos pelos coringas na mensagem.
     */
    public static void trace(Logger logger, String chave, Object... argumentos) {

        logger.trace(MensagemUtil.getMensagem(chave, argumentos));
    }

    /**
     * Verifica se o nível de DEBUG está habilitado.
     * 
     * @param logger {@link Logger}.
     * @return <tt>TRUE</tt> se estiver habilitado, <tt>FALSE</tt> caso contrário.
     */
    public static boolean isDebugHabilitado(Logger logger) {

        return logger.isDebugEnabled();
    }

    /**
     * Realiza o log da mensagem com nível de DEBUG.
     * 
     * @param logger {@link Logger}.
     * @param chave Chave da mensagem no arquivo de recursos.
     */
    public static void debug(Logger logger, String mensagem) {

        logger.debug(mensagem);
    }

    /**
     * Realiza o log da mensagem com nível de DEBUG.
     * 
     * @param logger {@link Logger}.
     * @param chave Chave da mensagem no arquivo de recursos.
     * @param argumentos Argumentos que serão substituídos pelos coringas na mensagem.
     */
    public static void debug(Logger logger, String chave, Object... argumentos) {

        logger.debug(MensagemUtil.getMensagem(chave, argumentos));
    }

    /**
     * Realiza o log da mensagem com nível de DEBUG.
     * 
     * @param logger {@link Logger}.
     * @param e {@link Throwable}.
     * @param mensagem Mensagem de log.
     * @param argumentos Argumentos que serão substituídos pelos coringas na mensagem.
     */
    public static void debug(Logger logger, Throwable e, String mensagem, Object... argumentos) {

        if (argumentos.length > 0) {

            mensagem = String.format(mensagem, argumentos);
        }

        logger.debug(mensagem, e);
    }

    /**
     * Realiza o log da mensagem com nível de INFO.
     * 
     * @param logger {@link Logger}.
     * @param mensagem Mensagem de log.
     */
    public static void info(Logger logger, String mensagem) {

        logger.info(mensagem);
    }

    /**
     * Realiza o log da mensagem com nível de INFO.
     * 
     * @param logger {@link Logger}.
     * @param chave Chave da mensagem no arquivo de recursos.
     * @param argumentos Argumentos que serão substituídos pelos coringas na mensagem.
     */
    public static void info(Logger logger, String chave, Object... argumentos) {

        logger.info(MensagemUtil.getMensagem(chave, argumentos));
    }

    /**
     * Realiza o log da mensagem com nível de WARN.
     * 
     * @param logger {@link Logger}.
     * @param mensagem Mensagem de log.
     */
    public static void warn(Logger logger, String mensagem) {

        logger.warn(mensagem);
    }

    /**
     * Realiza o log da mensagem com nível de WARN.
     * 
     * @param logger {@link Logger}.
     * @param chave Chave da mensagem no arquivo de recursos.
     * @param argumentos Argumentos que serão substituídos pelos coringas na mensagem.
     */
    public static void warn(Logger logger, String chave, Object... argumentos) {

        logger.warn(MensagemUtil.getMensagem(chave, argumentos));
    }

    /**
     * Realiza o log da mensagem com nível de ERROR.
     * 
     * @param logger {@link Logger}.
     * @param mensagem Mensagem de log.
     */
    public static void error(Logger logger, String mensagem) {

        logger.error(mensagem);
    }

    /**
     * Realiza o log da mensagem com nível de ERROR.
     * 
     * @param logger {@link Logger}.
     * @param chave Chave da mensagem no arquivo de recursos.
     * @param argumentos Argumentos que serão substituídos pelos coringas na mensagem.
     */
    public static void error(Logger logger, String chave, Object... argumentos) {

        logger.error(MensagemUtil.getMensagem(chave, argumentos));
    }

    /**
     * Realiza o log da mensagem com nível de ERROR.
     * 
     * @param logger {@link Logger}.
     * @param e {@link Throwable}.
     * @param mensagem Mensagem de log.
     * @param argumentos Argumentos que serão substituídos pelos coringas na mensagem.
     */
    public static void error(Logger logger, Throwable e, String mensagem, Object... argumentos) {

        if (argumentos.length > 0) {

            mensagem = String.format(mensagem, argumentos);
        }

        logger.error(mensagem, e);
    }

    /**
     * Realiza o log da mensagem com nível de ERROR.
     * 
     * @param logger {@link Logger}.
     * @param e {@link Throwable}.
     */
    public static void error(Logger logger, Throwable e) {

        logger.error(e.getMessage(), e);
    }

    /**
     * Realiza o log da mensagem com nível de FATAL.
     * 
     * @param logger {@link Logger}.
     * @param mensagem Mensagem de log.
     */
    public static void fatal(Logger logger, String mensagem) {

        logger.fatal(mensagem);
    }

    /**
     * Realiza o log da mensagem com nível de FATAL.
     * 
     * @param logger {@link Logger}.
     * @param chave Chave da mensagem no arquivo de recursos.
     * @param argumentos Argumentos que serão substituídos pelos coringas na mensagem.
     */
    public static void fatal(Logger logger, String chave, Object... argumentos) {

        logger.fatal(MensagemUtil.getMensagem(chave, argumentos));
    }

    /**
     * Realiza o log da mensagem com nível de FATAL.
     * 
     * @param logger {@link Logger}.
     * @param e {@link Throwable}.
     * @param mensagem Mensagem de log.
     */
    public static void fatal(Logger logger, Throwable e, String mensagem) {

        logger.fatal(mensagem, e);
    }

    /**
     * Realiza o log da mensagem com nível de FATAL.
     * 
     * @param logger {@link Logger}.
     * @param e {@link Throwable}.
     * @param mensagem Mensagem de log.
     * @param argumentos Argumentos que serão substituídos pelos coringas na mensagem.
     */
    public static void fatal(Logger logger, Throwable e, String mensagem, Object... argumentos) {

        if (argumentos.length > 0) {

            mensagem = String.format(mensagem, argumentos);
        }

        logger.fatal(mensagem, e);
    }

    /**
     * Realiza o log da mensagem com nível de FATAL.
     * 
     * @param logger {@link Logger}.
     * @param e {@link Throwable}.
     */
    public static void fatal(Logger logger, Throwable e) {

        logger.fatal(e.getMessage(), e);
    }

    /**
     * Cria a instância de {@link Logger} informando a classe em que a instância será atribuída.
     * 
     * @param injectionPoint {@link InjectionPoint} Ponto de injeção.
     * @return Instância de {@link Logger}.
     */
    @Produces
    Logger criarLogger(InjectionPoint injectionPoint) {

        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}
