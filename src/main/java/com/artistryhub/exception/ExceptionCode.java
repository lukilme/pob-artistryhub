package com.artistryhub.exception;

public enum ExceptionCode {
    INVALID_ID(1001),
    INVALID_NAME(1002),
    INVALID_TYPE(1003),
    INVALID_BIOGRAPHY(1004);

    private final int code;

    ExceptionCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}