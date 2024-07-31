package com.artistryhub.exception;

public class ArtistNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ArtistNotFoundException(String message) {
		super(message);
	}
}
