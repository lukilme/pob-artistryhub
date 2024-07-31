package com.artistryhub.service;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.artistryhub.dao.DAOArtist;
import com.artistryhub.dao.DAOCity;
import com.artistryhub.dao.DAOPresentation;
import com.artistryhub.model.City;

public class CityFacadeTest {
	private final DAOArtist DAOArtistic = new DAOArtist();
	private final DAOPresentation DAOPresentation = new DAOPresentation();
	private final DAOCity DAOCity = new DAOCity();
	private final CityFacade facade = new CityFacade();
	City city1 = new City(1, "Joao Pessoa");
	City city2 = new City(2, "Campina Grande");
	City city3 = new City(3, "Belo Horizonte");
	City city4 = new City(4, "Sao Paulo");

	@Test
	public void artistCreationTest() {
		facade.initialize(DAOArtistic, DAOCity, DAOPresentation);
		facade.clear();
		System.out.println("\nartistCreationTest");
		assertEquals(city1, facade.create("Joao Pessoa"));
		assertEquals(city2, facade.create("Campina Grande"));
		assertEquals(city3, facade.create("Belo Horizonte"));
		assertEquals(city4, facade.create("Sao Paulo"));
		this.showDataArtists();
		facade.finish();
	}

	private void showDataArtists() {
		List<City> cities = facade.readAll();
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
		facade.initialize(DAOArtistic, DAOCity, DAOPresentation);
		facade.clear();
		this.insertForTesting();
		System.out.println("\nartistRemovalTest");
		facade.delete(city1);
		facade.delete(city2.getName());
		facade.delete(city3.getId());
		facade.delete(city4);
		List<City> cities = facade.readAll();
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
