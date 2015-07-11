package com.jsf22.html5.app.util;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

import org.apache.log4j.Logger;

@Singleton
public class LogUtil implements Serializable {

    private static final long serialVersionUID = -4870035820353083372L;

    public static boolean isTraceHabilitado(Logger logger) {

        return logger.isTraceEnabled();
    }

    public static void trace(Logger logger, String mensagem) {

        logger.trace(mensagem);
    }

    public static void trace(Logger logger, String chave, Object... argumentos) {

        logger.trace(MensagemUtil.getMensagem(chave, argumentos));
    }

    public static boolean isDebugHabilitado(Logger logger) {

        return logger.isDebugEnabled();
    }

    public static void debug(Logger logger, String mensagem) {

        logger.debug(mensagem);
    }

    public static void debug(Logger logger, String chave, Object... argumentos) {

        logger.debug(MensagemUtil.getMensagem(chave, argumentos));
    }

    public static void debug(Logger logger, Throwable e, String mensagem, Object... argumentos) {

        if (argumentos.length > 0) {

            mensagem = String.format(mensagem, argumentos);
        }

        logger.debug(mensagem, e);
    }

    public static void info(Logger logger, String mensagem) {

        logger.info(mensagem);
    }

    public static void info(Logger logger, String chave, Object... argumentos) {

        logger.info(MensagemUtil.getMensagem(chave, argumentos));
    }

    public static void warn(Logger logger, String mensagem) {

        logger.warn(mensagem);
    }

    public static void warn(Logger logger, String chave, Object... argumentos) {

        logger.warn(MensagemUtil.getMensagem(chave, argumentos));
    }

    public static void error(Logger logger, String mensagem) {

        logger.error(mensagem);
    }

    public static void error(Logger logger, String chave, Object... argumentos) {

        logger.error(MensagemUtil.getMensagem(chave, argumentos));
    }

    public static void error(Logger logger, Throwable e, String mensagem, Object... argumentos) {

        if (argumentos.length > 0) {

            mensagem = String.format(mensagem, argumentos);
        }

        logger.error(mensagem, e);
    }

    public static void error(Logger logger, Throwable e) {

        logger.error(e.getMessage(), e);
    }

    public static void fatal(Logger logger, String mensagem) {

        logger.fatal(mensagem);
    }

    public static void fatal(Logger logger, String chave, Object... argumentos) {

        logger.fatal(MensagemUtil.getMensagem(chave, argumentos));
    }

    public static void fatal(Logger logger, Throwable e, String mensagem) {

        logger.fatal(mensagem, e);
    }

    public static void fatal(Logger logger, Throwable e, String mensagem, Object... argumentos) {

        if (argumentos.length > 0) {

            mensagem = String.format(mensagem, argumentos);
        }

        logger.fatal(mensagem, e);
    }

    public static void fatal(Logger logger, Throwable e) {

        logger.fatal(e.getMessage(), e);
    }

    @Produces
    Logger criarLogger(InjectionPoint injectionPoint) {

        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}
