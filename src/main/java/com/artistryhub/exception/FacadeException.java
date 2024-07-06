package com.artistryhub.exception;

public class FacadeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final ExceptionCode exceptionCode;

    public FacadeException(String message, ExceptionCode exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

}
