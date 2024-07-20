package com.artistryhub.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.artistryhub.dao.DAO;
import com.artistryhub.dao.DAOArtist;
import com.artistryhub.dao.DAOCity;
import com.artistryhub.dao.DAOPresentation;
import com.artistryhub.exception.CustomException;
import com.artistryhub.exception.ExceptionCode;
import com.artistryhub.model.Artist;

public class CityFacade {
	private static final int MIN_NAME = 4;
	private static final int MAX_NAME = 32;
	
	protected DAOArtist DAOMainManager;
	protected DAOPresentation DAOPresentation;
	protected DAOCity DAOCity;
	
	public void initialize(DAOArtist DAOArtistic, DAOPresentation DAOPresentation, DAOCity DAOCity) {
		this.DAOMainManager = DAOArtistic;
		this.DAOPresentation = DAOPresentation;
		this.DAOCity = DAOCity;
		DAO.open();
	}
	public void finish() {
		DAO.close();
	}
	/**
	 * This method creates a new artist instance and saves it to the database if it
	 * complies with the business rules.
	 *
	 * @param name      Unique name of the artist.
	 * @param type      Category the artist falls into.
	 * @param biography Artist's story.
	 * @return The created instance of the artist.
	 * @throws CustomException with ExceptionCode.INVALID_NAME if the name is
	 *                         invalid or are not unique.
	 * @throws CustomException with ExceptionCode.INVALID_TYPE if the type is
	 *                         invalid.
	 * @throws CustomException with ExceptionCode.INVALID_BIOGRAPHY if the biography
	 *                         is invalid.
	 * @throws CustomException with ExceptionCode.UNIQUENESS_VIOLATION if name or id
	 *                         are not unique.
	 */
	public Artist create(String name, ArrayList<String> type, String biography) {
		DAO.begin();
		// if something goes wrong, exceptions will be thrown
		this.validateName(name);

		if (DAOMainManager.read(name) != null) {
			throw new CustomException("Uniqueness violated, the id or name must be unique",
					ExceptionCode.UNIQUENESS_VIOLATION);
		}
		int newId = DAOMainManager.generatObsoleteId();
		Artist newArtist = new Artist(newId, name, null, type, biography);
		DAOMainManager.create(newArtist);
		DAO.commit();
		return newArtist;
	}
	
	public List<Artist> getAll() {

		List<Artist> result = DAOMainManager.readAll();
		return result;
	}
	
	/**
	 * This method analyzes whether the name has the appropriate size according to
	 * business rules.
	 *
	 * @param name content to be checked.
	 * @return void Ensuring that the parent method continues.
	 * @throws CustomException with ExceptionCode.MAX_NAME preventing the parent
	 *                         method from continuing if the biography is null,
	 *                         shorter than 4 characters or longer than 32
	 *                         characters.
	 */
	public void validateName(String name) {
		if (name == null || !Pattern.matches("^[a-zA-Z ]{4,32}$", name)) {
			throw new CustomException("Name must contain only letters and spaces, and be between "
					+ CityFacade.MIN_NAME + " and " + CityFacade.MAX_NAME + " characters.",
					ExceptionCode.INVALID_NAME);
		}
	}
}
