package com.jsf22.html5.app.exception;

public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = -6870779997142357208L;

    public ApplicationException() {

        super();
    }

    /**
     * Construtor da classe.
     * 
     * @param mensagem Mensagem de erro.
     * @param causa Causa.
     */
    public ApplicationException(String mensagem, Throwable causa) {

        super(mensagem, causa);
    }

    /**
     * Construtor da classe.
     * 
     * @param mensagem Mensagem de erro.
     */
    public ApplicationException(String mensagem) {

        super(mensagem);
    }

    /**
     * Construtor da classe.
     * 
     * @param causa Causa.
     */
    public ApplicationException(Throwable causa) {

        super(causa);
    }

}
