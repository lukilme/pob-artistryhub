package com.artistryhub.exception;

public class InvalidArtistException extends RuntimeException  {

	private static final long serialVersionUID = 1L;

	public InvalidArtistException(String message) {
		super(message);
	}
}
