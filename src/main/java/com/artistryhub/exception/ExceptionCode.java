package com.artistryhub.exception;

public enum ExceptionCode {
    INVALID_ID(101),
    INVALID_NAME(102),
    INVALID_TYPE(103),
    INVALID_BIOGRAPHY(104),
	INVALID_KEY(105),
	UNIQUENESS_VIOLATION(106),
	ARTIST_NOT_FOUND(107),
	ATTRIBUTE_INVALID(108);

    private final int code;

    ExceptionCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}