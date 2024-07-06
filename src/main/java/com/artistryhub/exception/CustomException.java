package com.artistryhub.exception;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final ExceptionCode exceptionCode;

    public CustomException(String message, ExceptionCode exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
