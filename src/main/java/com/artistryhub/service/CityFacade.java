package com.artistryhub.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.artistryhub.model.Presentation;

import com.artistryhub.dao.DAO;

import com.artistryhub.exception.CityException;
import com.artistryhub.model.City;

public class CityFacade extends AbstractFacade<City> {
	private static final int MIN_NAME = 4;
	private static final int MAX_NAME = 32;

	@Override
	public List<City> getAll() {
		return DAOCity.readAll();
	}

	public City create(String name) {
		DAO.begin();
		this.validateName(name);

		if (DAOCity.read(name) != null) {
			throw new CityException("Uniqueness violated, the id or name must be unique");

		}

		City newCity = new City(name);
		DAOCity.create(newCity);
		DAO.commit();
		return newCity;
	}

	public City update(int index, String newName) {
		DAO.begin();

		List<City> allCities = DAOCity.readAll();

		if (index < 0 || index >= allCities.size()) {

			throw new CityException("City not found.");
		}

		City cityFound = allCities.get(index);

		if (cityFound == null) {
			throw new CityException("City not found.");
		}

		cityFound.setName(newName);
		DAOCity.update(cityFound);

		List<Presentation> presentationCity = cityFound.getPresentations();

		for (Presentation presen : presentationCity) {
			if (presen != null) {

				DAOPresentation.update(presen);
			}
		}

		DAO.commit();

		return cityFound;
	}
	
	public int getIndex(City city) {
		List<City> allCities = DAOCity.readAll();
		int index = 0;
		for(int i=0; i < allCities.size(); i++) {
			if(allCities.get(i).equals(city)) {
				index = i;
			};
		}
		return index;
	}

	public City search(Object key) {
		return DAOCity.read(key);
	}

	public City delete(String key) {
		DAO.begin();

		City deletedCity = DAOCity.read(key);

		if (deletedCity == null) {
			throw new CityException("City not found.");
		}

		List<Presentation> cityPresentations = new ArrayList<>(deletedCity.getPresentations());

		if (cityPresentations != null) {
			for (Presentation cityPresentation : cityPresentations) {
				if (cityPresentation != null) {
					City cityRemovedPresentation = cityPresentation.getCity();

					if (cityRemovedPresentation != null) {
						cityRemovedPresentation.removePresentation(cityPresentation);
						DAOCity.update(cityRemovedPresentation);
					}

					deletedCity.getPresentations().remove(cityPresentation); // Remove the presentation from the city's
																				// list
					DAOPresentation.delete(cityPresentation); // Delete the presentation
				}
			}
		}

		DAOCity.delete(deletedCity);

		DAO.commit();

		return deletedCity;
	}

	public void clear() {
		DAOCity.clear();
	}

	public void validateName(String name) {

		if (name == null) {
			throw new CityException("Name cannot be null.");
		}

		if (name.isEmpty() || !Pattern.matches("^[a-zA-Z ]+$", name)) {
			throw new CityException("Name must contain only letters and spaces and cannot be empty.");
		}

		int length = name.length();
		if (length < CityFacade.MIN_NAME || length > CityFacade.MAX_NAME) {
			throw new CityException(
					"Name must be between " + CityFacade.MIN_NAME + " and " + CityFacade.MAX_NAME + " characters.");
		}
	}
}
