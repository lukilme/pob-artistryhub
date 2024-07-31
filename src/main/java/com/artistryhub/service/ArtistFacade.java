package com.artistryhub.service;

import com.artistryhub.dao.DAO;
import com.artistryhub.exception.CustomException;
import com.artistryhub.exception.ExceptionCode;
import com.artistryhub.model.Artist;
import com.artistryhub.model.City;
import com.artistryhub.model.Presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ArtistFacade extends AbstractFacade<Artist> {
	private static final int MIN_NAME = 4;
	private static final int MAX_NAME = 32;
	private static final int MIN_BIOGRAPHY = 16;
	private static final int MAX_BIOGRAPHY = 255;
	private static final int MAX_TYPES = 4;

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
		this.validateType(type);
		this.validateBiography(biography);

		if (DAOArtist.read(name) != null) {
			throw new CustomException("Uniqueness violated, the id or name must be unique",
					ExceptionCode.UNIQUENESS_VIOLATION);
		}
		int newId = DAOArtist.generatObsoleteId();
		Artist newArtist = new Artist(newId, name, type, biography);
		DAOArtist.create(newArtist);
		DAO.commit();
		return newArtist;
	}

	public List<Artist> readAll() {
		return DAOArtist.readAll();
	}

	public ArrayList<Artist> getArtistPagination() {

		return null;
	}

	/**
	 * This method creates a new artist instance and saves it to the database if it
	 * complies with the business rules.
	 *
	 * @param newArtist The artist instance to be created.
	 * @return The created instance of the artist.
	 * @throws CustomException with ExceptionCode.INVALID_NAME if the name is
	 *                         invalid.
	 * @throws CustomException with ExceptionCode.INVALID_TYPE if the type is
	 *                         invalid.
	 * @throws CustomException with ExceptionCode.INVALID_BIOGRAPHY if the biography
	 *                         is invalid.
	 * @throws CustomException with ExceptionCode.UNIQUENESS_VIOLATION if the id or
	 *                         name are not unique.
	 */
	public Artist create(Artist newArtist) {
		DAO.begin();
		// If something goes wrong, exceptions will be thrown
		this.validateName(newArtist.getName());
		this.validateType(newArtist.getType());
		this.validateBiography(newArtist.getBiography());

		// Check if the name is unique

		if (DAOArtist.read(newArtist.getName()) != null) {
			throw new CustomException("Uniqueness violated, the name must be unique",
					ExceptionCode.UNIQUENESS_VIOLATION);
		}

		// Check if the ID is unique or generate a new one
		if (newArtist.getId() != 0) {
			if (DAOArtist.read(newArtist.getId()) != null) {
				throw new CustomException("Uniqueness violated, the id must be unique",
						ExceptionCode.UNIQUENESS_VIOLATION);
			}
		}
		DAOArtist.create(newArtist);
		DAO.commit();
		return newArtist;
	}

	public Artist delete(Object key) {

		DAO.begin();
		Artist deletedArtist = null;
		if (key instanceof Artist) {
			deletedArtist = DAOArtist.read(((Artist) key).getId());
		} else {
			deletedArtist = DAOArtist.read(key);
		}

		if (deletedArtist == null) {
			throw new CustomException("Artist not found.", ExceptionCode.ARTIST_NOT_FOUND);
		}

		ArrayList<Presentation> artistPresentations = deletedArtist.getPresentations();

		if (artistPresentations != null) {
			for (Presentation artistPresentation : artistPresentations) {
				City cityRemovedPresentation = artistPresentation.getCity();

				cityRemovedPresentation.removePresentation(artistPresentation);
				deletedArtist.removePresentation(artistPresentation);

				DAOCity.update(cityRemovedPresentation);
				DAOPresentation.delete(artistPresentation);
			}
		}

		DAOArtist.delete(deletedArtist);
		DAO.commit();

		return deletedArtist;

	}

	public Artist update(Artist updateArtist) {
		DAO.begin();
		Artist oldArtist = DAOArtist.read(updateArtist.getId());
		if (oldArtist == null) {
			throw new CustomException("No Artist found to updated", ExceptionCode.ARTIST_NOT_FOUND);
		}
		if (!oldArtist.getName().equals(updateArtist.getName())) {
			updateName(updateArtist, updateArtist.getName());
		}
		if (!oldArtist.getBiography().equals(updateArtist.getBiography())) {
			updateBiography(updateArtist, updateArtist.getBiography());
		}
		if (!oldArtist.getType().equals(updateArtist.getType())) {
			updateType(updateArtist, updateArtist.getType());
		}
		DAOArtist.update(updateArtist);
		return updateArtist;
	}

	public Artist search(Object key) {
		if (key instanceof String) {
			String keyString = (String) key;
			if (keyString.matches("\\d+")) {
				return DAOArtist.read(Integer.parseInt(keyString));
			} else if (keyString.matches("[a-zA-Z\\s]+")) {
				return DAOArtist.read(key);
			} else {
				throw new CustomException("Invalid key. The id must contain only numbers and the name only letters.",
						ExceptionCode.INVALID_KEY);
			}
		} else if (key instanceof Integer) {
			validateId((int) key);
			return DAOArtist.read(key);
		} else if (key instanceof Artist) {

			return DAOArtist.read(key);
		} else {
			throw new CustomException("Invalid key. The id must contain only numbers and the name only letters.",
					ExceptionCode.INVALID_KEY);
		}

	}

	public void clear() {
		DAOArtist.clear();
	}

	public void validateId(int id) {
		if (id <= 0) {
			throw new CustomException("ID must be a positive number.", ExceptionCode.INVALID_ID);
		}
	}

	private void updateName(Artist updateArtist, Object newValue) {
		if (!(newValue instanceof String)) {
			throw new CustomException("Invalid type for name.", ExceptionCode.INVALID_TYPE);
		}

		String newName = (String) newValue;
		if (!updateArtist.getName().equals(newName)) {
			validateName(newName);
			if (DAOArtist.read(newName) != null) {
				throw new CustomException("There is already an artist with this name.",
						ExceptionCode.UNIQUENESS_VIOLATION);
			}
			updateArtist.setName(newName);
		}
	}

	private void updateBiography(Artist updateArtist, Object newValue) {
		if (!(newValue instanceof String)) {
			throw new CustomException("Invalid type for biography.", ExceptionCode.INVALID_TYPE);
		}

		String newBiography = (String) newValue;
		if (!updateArtist.getBiography().equals(newBiography)) {
			validateBiography(newBiography);
			updateArtist.setBiography(newBiography);
		}
	}

	private void updateType(Artist updateArtist, Object newValue) {
		if (!(newValue instanceof ArrayList<?> tempList)) {
			throw new CustomException("Invalid type for type.", ExceptionCode.INVALID_TYPE);
		}

		for (Object obj : tempList) {
			if (!(obj instanceof String)) {
				throw new CustomException("Invalid type for elements in type list.", ExceptionCode.INVALID_TYPE);
			}
		}

		@SuppressWarnings("unchecked")
		ArrayList<String> newType = (ArrayList<String>) tempList;
		if (!updateArtist.getType().equals(newType)) {
			validateType(newType);
			updateArtist.setType(newType);
		}
	}

	/**
	 * This method analyzes whether the name has the appropriate size according to
	 * business rules.
	 *
	 * @param name content to be checked.
	 * @throws CustomException with ExceptionCode.MAX_NAME preventing the parent
	 *                         method from continuing if the biography is null,
	 *                         shorter than 4 characters or longer than 32
	 *                         characters.
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
	 * @throws CustomException with ExceptionCode.MAX_TYPES preventing the parent
	 *                         method from continuing if there are no types, or
	 *                         there are more than 4
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
	 * @param biography content to be verified.
	 * @throws CustomException with ExceptionCode.INVALID_BIOGRAPHY preventing the
	 *                         parent method from proceeding if the biography is
	 *                         null, shorter than 16 characters, or longer than 255
	 *                         characters.
	 */
	public void validateBiography(String biography) {
		if (biography == null || biography.length() < 16 || biography.length() > 255) {
			throw new CustomException("Biography must be between " + ArtistFacade.MIN_BIOGRAPHY + " and "
					+ ArtistFacade.MAX_BIOGRAPHY + " characters.", ExceptionCode.INVALID_BIOGRAPHY);
		}
	}

}
