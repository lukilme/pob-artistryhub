package com.artistryhub.service;

import com.artistryhub.model.Artist;
import com.artistryhub.model.City;
import com.artistryhub.model.Presentation;

import java.util.ArrayList;
import java.util.List;

import com.artistryhub.dao.DAOArtist;
import com.artistryhub.dao.DAOCity;
import com.artistryhub.dao.DAOPresentation;

public class Facade {
	private ArtistFacade artistFacade = new ArtistFacade();
	private CityFacade cityFacade = new CityFacade();
	private PresentationFacade presentationFacade = new PresentationFacade();
	private DAOArtist DAOArtistic = new DAOArtist();
	private DAOPresentation DAOPresentation = new DAOPresentation();
	private DAOCity DAOCity = new DAOCity();

	public Facade() {
		this.artistFacade.initialize(DAOArtistic, DAOCity, DAOPresentation);
		this.cityFacade.initialize(DAOArtistic, DAOCity, DAOPresentation);
		this.presentationFacade.initialize(DAOArtistic, DAOCity, DAOPresentation);
	}

	public void createArtist(String name, String genre, String biography) {
		this.artistFacade.create(name, genre, biography);
	}

	public Artist deleteArtist(String name) {
		return this.artistFacade.delete(name);
	}

	public Artist updateArtist(String name, String genre, String biography) {
		return this.artistFacade.update(name, genre, biography);
	}

	public Artist searchArtist(String name) {
		return this.artistFacade.search(name);
	}

	public List<Artist> getArtistByPresentationCount(int limit) {
		return this.artistFacade.getArtistByPresentationCount(limit);
	}

	public List<Artist> getArtistByDatePresentationInCity(String city, String name) {
		List<Artist> result = new ArrayList<>();
		List<Presentation> queryResult = this.presentationFacade.getPresentationByCityAndDate(city, name);

		if (queryResult != null) {
			for (Presentation aux : queryResult) {
				if (aux.getArtist() != null) {
					result.add(aux.getArtist());
				}
			}
		}

		return result;
	}

	public List<Artist> getAllArtist() {
		return this.artistFacade.getAll();
	}

	public void createCity(String name) {
		this.cityFacade.create(name);
	}

	public City deleteCity(String name) {
		return this.cityFacade.delete(name);
	}

	public City searchCity(String name) {
		return this.cityFacade.search(name);
	}

	public List<City> getAllCities() {
		return this.cityFacade.getAll();
	}
	
	public City updateCity(int index, String city) {
		return this.cityFacade.update(index, city);
	}
	
	public int getIndexCity(City city) {
		return this.cityFacade.getIndex(city);
	}

	public void createPresentation(String date, String nameArtist, String nameCity, double priceTicket, int duration,
			int ticketsSold, int ticketsTotal) {
		this.presentationFacade.create(date, nameArtist, nameCity, priceTicket, duration, ticketsSold, ticketsTotal);
	}
	
	public Presentation deletePresentation(int id) {
		return this.presentationFacade.delete(id);
	}
	
	public Presentation deletePresentation(String date, String artist, String city) {
		return this.presentationFacade.delete(date, artist, city);
	}
	
	public Presentation updatePresentation(int id,String date, String nameArtist, String nameCity, double priceTicket, int duration,
			int ticketsSold, int ticketsTotal){
		return this.presentationFacade.update(id, date, nameArtist, nameCity, priceTicket, duration, ticketsSold, ticketsTotal);
	}
	
	public Presentation searchPresentation(int id) {
		return presentationFacade.getPresentationById(id);
	}
	
	public Presentation searchPresentation(String artist, String city, String date) {
		return presentationFacade.getByArtistCityAndDate(artist, city, date);
	}
	
	public List<Presentation> getPresentationByDate(String date){
		return this.presentationFacade.getPresentationByDate(date);
	}
	
	public List<Presentation> getAllPresentation(){
		return this.presentationFacade.getAll();
	}

	public void finish() {
		this.artistFacade.finish();
		this.cityFacade.finish();
		this.presentationFacade.finish();
	}
}
