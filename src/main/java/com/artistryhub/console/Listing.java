package com.artistryhub.console;

import java.util.List;

import com.artistryhub.model.Artist;
import com.artistryhub.model.City;
import com.artistryhub.model.Presentation;
import com.artistryhub.service.Facade;

public class Listing {
	public static void main(String[] args) {
		Facade facade = new Facade();
		List<Artist> artistList = facade.getAllArtist();
		for(Artist artist: artistList) {
			System.out.println(artist);
		}
		List<City> cityList = facade.getAllCities();

		for(City city: cityList) {
			System.out.println(city);
		}
		List<Presentation> presentationList = facade.getAllPresentation();

		for(Presentation presentation: presentationList) {
			System.out.println(presentation);
		}
		facade.finish();
		
	}
}
