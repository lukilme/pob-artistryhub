package com.artistryhub.service;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.artistryhub.dao.DAOArtist;
import com.artistryhub.dao.DAOCity;
import com.artistryhub.dao.DAOPresentation;
import com.artistryhub.model.Artist;
import com.artistryhub.model.City;
import com.artistryhub.model.Presentation;

public class PresentationFacadeTest {
	private final DAOArtist DAOArtistic = new DAOArtist();
	private final DAOPresentation DAOPresentation = new DAOPresentation();
	private final DAOCity DAOCity = new DAOCity();
	private final CityFacade facadeCity = new CityFacade();
	private final ArtistFacade facadeArtist = new ArtistFacade();
	private final PresentationFacade facadePresentation = new PresentationFacade();

	@Test
	public void create() {
		facadeCity.initialize(DAOArtistic, DAOCity, DAOPresentation);
		facadeArtist.initialize(DAOArtistic, DAOCity, DAOPresentation);
		facadePresentation.initialize(DAOArtistic, DAOCity, DAOPresentation);

		City city1 = facadeCity.search(1);
		City city2 = facadeCity.search(2);
		City city3 = facadeCity.search(3);
		City city4 = facadeCity.search(4);

		Artist artist1 = facadeArtist.search(1);
		Artist artist2 = facadeArtist.search(2);
		Artist artist3 = facadeArtist.search(3);
		Artist artist4 = facadeArtist.search(4);
		showData();
		
		facadePresentation.create("01/05/2026",artist1.getName(),city1.getName(),20.40,120,3000,4000);
		facadePresentation.create("01/05/2025",artist2.getName(),city2.getName(),10.50,600,3040,4000);
		facadePresentation.create("01/05/2025",artist3.getName(),city3.getName(),120.30,400,7000,8000);
		facadePresentation.create("01/05/2028",artist4.getName(),city4.getName(),620.40,300,1000,3000);
		
		showData();
		facadeCity.finish();
		facadeArtist.finish();
		facadePresentation.finish();
	}

	public void showData() {
		List<Artist> artists = facadeArtist.readAll();
		if (artists.isEmpty())
			System.out.println("Is Empty");
		else {
			for (Artist artist : artists) {
				System.out.println(artist);
			}
		}
		List<City> cities = facadeCity.readAll();
		if (cities.isEmpty())
			System.out.println("Is Empty");
		else {
			for (City city : cities) {
				System.out.println(city);
			}

		}
		List<Presentation> presentations = facadePresentation.readAll();
		if (presentations.isEmpty())
			System.out.println("Is Empty");
		else {
			for (Presentation presentation : presentations) {
				System.out.println(presentation);
			}
		}

	}
}
