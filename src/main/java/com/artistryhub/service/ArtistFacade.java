package com.artistryhub.service;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.artistryhub.dao.DAO;
import com.artistryhub.dao.DAOArtist;
import com.artistryhub.exception.ExceptionCode;
import com.artistryhub.exception.CustomException;
import com.artistryhub.model.Artist;

public class ArtistFacade {
	private static final int MIN_NAME = 4;
	private static final int MAX_NAME = 32;
	private static final int MIN_BIOGRAPHY = 16;
	private static final int MAX_BIOGRAPHY = 255;
	private static final int MAX_TYPES = 4;

	protected DAOArtist DAOManager = new DAOArtist();

	public void initialize() {
		DAO.open();
	}

	public void finish() {
		DAO.close();
	}

	/*
	 * 
	 * */
	public Artist create(String name, ArrayList<String> type, String biography) {
		DAO.begin();
		// if something goes wrong, exceptions will be thrown
		this.validateName(name);
		this.validateType(type);
		this.validateBiography(biography);

		if (DAOManager.read(name) != null) {
			throw new CustomException("Uniqueness violated, the id or name must be unique", ExceptionCode.INVALID_ID);
		}
		int newId = DAOManager.generatObsoleteId();
		Artist newArtist = new Artist(newId, name, null, type, biography);
		DAOManager.create(newArtist);
		DAO.commit();
		return newArtist;
	}

	public Artist delete(Object key) {

		return null;
	}

	public Artist update(Object params) {

		return null;
	}

	public Artist search(Object key) {

		return null;
	}

	public void clear() {
		DAOManager.clear();
	}

	public void validateId(int id) {
		if (id <= 0) {
			throw new CustomException("ID must be a positive number.", ExceptionCode.INVALID_ID);
		}
	}

	/**
	 * This method analyzes whether the name has the appropriate size according to
	 * business rules.
	 *
	 * @param name content to be checked.
	 * 
	 * @return void Ensuring that the parent method continues.
	 * 
	 * @throws CustomException with ExceptionCode.MAX_NAME preventing the parent
	 * method from continuing if the biography is null, shorter than 4 characters or
	 * longer than 32 characters.
	 */
	public void validateName(String name) {
		if (name == null || !Pattern.matches("^[a-zA-Z ]{4,32}$", name)) {
			throw new CustomException("Name must contain only letters and spaces, and be between "
					+ ArtistFacade.MIN_NAME + " and " + ArtistFacade.MAX_NAME + " characters.",
					ExceptionCode.INVALID_NAME);
		}
	}

	/**
	 * This method analyzes whether the types have the quantity adequate according
	 * to business rules
	 *
	 * @param types Content to be checked.
	 * 
	 * @return void Ensuring that the parent method continues.
	 * 
	 * @throws CustomException with ExceptionCode.MAX_TYPES preventing the parent
	 * method from continuing if there are no types, or there are more than 4
	 */
	public void validateType(ArrayList<String> types) {
		if (types == null || types.size() > 4) {
			throw new CustomException("Types must be at most " + ArtistFacade.MAX_TYPES + ".",
					ExceptionCode.INVALID_TYPE);
		}
	}

	/**
	 * This method analyzes whether the biography is the appropriate size according
	 * to the business rules.
	 *
	 * @param biography  content to be verified.
	 * 
	 * @return void  Ensuring that the parent method proceeds.
	 * 
	 * @throws CustomException with ExceptionCode.INVALID_BIOGRAPHY preventing the
	 * parent method from proceeding if the biography is null, shorter than 16
	 * characters, or longer than 255 characters.
	 */
	public void validateBiography(String biography) {
		if (biography == null || biography.length() < 16 || biography.length() > 255) {
			throw new CustomException("Biography must be between " + ArtistFacade.MIN_BIOGRAPHY + " and "
					+ ArtistFacade.MAX_BIOGRAPHY + " characters.", ExceptionCode.INVALID_BIOGRAPHY);
		}
	}

}
