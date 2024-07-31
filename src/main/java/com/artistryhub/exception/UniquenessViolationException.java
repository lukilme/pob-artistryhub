package com.artistryhub.exception;

public class UniquenessViolationException extends RuntimeException  {

	private static final long serialVersionUID = 1L;

	public UniquenessViolationException(String message) {
		super(message);
	}
}