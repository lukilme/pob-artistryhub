package com.artistryhub.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.artistryhub.dao.DAOArtist;
import com.artistryhub.dao.DAOCity;
import com.artistryhub.dao.DAOPresentation;
import com.artistryhub.model.City;

public class CityFacadeTest {
	private DAOArtist DAOArtistic = new DAOArtist();
	private DAOPresentation DAOPresentation = new DAOPresentation();
	private DAOCity DAOCity = new DAOCity();
	private CityFacade facade = new CityFacade();
	City city1 = new City(1, "Joao Pessoa", null);
	City city2 = new City(2, "Campina Grande", null);
	City city3 = new City(3, "Belo Horizonte", null);
	City city4 = new City(4, "Sao Paulo", null);

	@Test
	public void artistCreationTest() {
		facade.initialize(DAOArtistic, DAOPresentation, DAOCity);
		facade.clear();
		System.out.println("\nartistCreationTest");
		assertEquals(city1, facade.create("Joao Pessoa"));
		assertEquals(city2, facade.create("Campina Grande"));
		assertEquals(city3, facade.create("Belo Horizonte"));
		assertEquals(city4, facade.create("Sao Paulo"));
		this.showDataArtists();
		facade.clear();
		facade.finish();
	}

	private void showDataArtists() {
		List<City> cities = facade.getAll();
		if (cities.isEmpty())
			System.out.println("Is Empty");
		else {
			for (City artist : cities) {
				System.out.println(artist);
			}
		}
	}

	public void insertForTesting() {
		facade.create(city1);
		facade.create(city2);
		facade.create(city3);
		facade.create(city4);
	}

	@Test
	public void artistRemovalTest() {
		facade.initialize(DAOArtistic, DAOPresentation, DAOCity);
		facade.clear();
		this.insertForTesting();
		System.out.println("\nartistRemovalTest");
		facade.delete(city1);
		facade.delete(city2.getName());
		facade.delete(city3.getId());
		facade.delete(city4);
		List<City> cities = facade.getAll();
		if (cities.isEmpty())
			System.out.println("Is Empty");
		else {
			for (City city : cities) {
				System.out.println(city);
			}
		}
		facade.finish();

	}
}
