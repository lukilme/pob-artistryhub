package com.artistryhub.exception;

public class InvalidClassException extends IllegalArgumentException  {

	private static final long serialVersionUID = 1L;

	public InvalidClassException(String message) {
		super(message);
	}
}
