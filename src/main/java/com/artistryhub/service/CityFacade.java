package com.artistryhub.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.artistryhub.dao.DAO;
import com.artistryhub.exception.CustomException;
import com.artistryhub.exception.ExceptionCode;
import com.artistryhub.model.City;
import com.artistryhub.model.Presentation;

public class CityFacade extends AbstractFacade<City> {
	private static final int MIN_NAME = 4;
	private static final int MAX_NAME = 32;

	/**
	 * This method creates a new artist instance and saves it to the database if it
	 * complies with the business rules.
	 *
	 * @param name Unique name of the city.
	 * @return The created instance of the city.
	 * @throws CustomException with ExceptionCode.INVALID_NAME if the name is
	 *                         invalid or are not unique.
	 * @throws CustomException with ExceptionCode.UNIQUENESS_VIOLATION if name or id
	 *                         are not unique.
	 */
	public City create(String name) {
		DAO.begin();
		// if something goes wrong, exceptions will be thrown
		this.validateName(name);

		if (DAOCity.read(name) != null) {
			throw new CustomException("Uniqueness violated, the id or name must be unique",
					ExceptionCode.UNIQUENESS_VIOLATION);
			// Check if the name is unique
		}
		int newId = DAOCity.generatObsoleteId();
		City newCity = new City(newId, name);
		DAOCity.create(newCity);
		DAO.commit();
		return newCity;
	}

	/**
	 * This method creates a new artist instance and saves it to the database if it
	 * complies with the business rules.
	 *
	 * @param newCity Unique name of the city.
	 * @return The created instance of the city.
	 * @throws CustomException with ExceptionCode.INVALID_NAME if the name is
	 *                         invalid or are not unique.
	 * @throws CustomException with ExceptionCode.UNIQUENESS_VIOLATION if name or id
	 *                         are not unique.
	 */
	public City create(City newCity) {
		DAO.begin();
		// if something goes wrong, exceptions will be thrown
		this.validateName(newCity.getName());
		// check if the name is unique
		if (DAOCity.read(newCity.getName()) != null) {
			throw new CustomException("Uniqueness violated, the name must be unique",
					ExceptionCode.UNIQUENESS_VIOLATION);

		}
		if (DAOCity.read(newCity.getId()) != null) {
			throw new CustomException("Uniqueness violated, the id must be unique", ExceptionCode.UNIQUENESS_VIOLATION);
		}

		DAOCity.create(newCity);
		DAO.commit();
		return newCity;
	}

	public List<City> readAll() {
		return DAOCity.readAll();
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
			throw new CustomException("Name must contain only letters and spaces, and be between " + CityFacade.MIN_NAME
					+ " and " + CityFacade.MAX_NAME + " characters.", ExceptionCode.INVALID_NAME);
		}
	}

	public City search(Object key) {
		return DAOCity.read(key);
	}

	public City update(City updatedCity) {
		DAO.open();
		DAO.begin();
		City oldCity = search(updatedCity.getId());
		if(oldCity == null) {
			
		}
		DAO.commit();
		return updatedCity;
	}

	public City delete(Object key) {
		DAO.open();
		DAO.begin();
		City deletedCity = null;
		if (key instanceof City) {
			deletedCity = DAOCity.read(((City) key).getId());
		} else {
			deletedCity = DAOCity.read(key);
		}

		if (deletedCity == null) {
			throw new CustomException("Artist not found.", ExceptionCode.ARTIST_NOT_FOUND);
		}

		ArrayList<Presentation> cityPresentations = deletedCity.getPresentations();

		if (cityPresentations != null) {
			for (Presentation cityPresentation : cityPresentations) {
				City cityRemovedPresentation = cityPresentation.getCity();

				cityRemovedPresentation.removePresentation(cityPresentation);
				deletedCity.removePresentation(cityPresentation);

				DAOCity.update(cityRemovedPresentation);
				DAOPresentation.delete(cityPresentation);
			}
		}

		DAOCity.delete(deletedCity);
		DAO.commit();

		return deletedCity;
	}

	public void clear() {
		DAOCity.clear();
	}

}
