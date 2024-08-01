package com.artistryhub.service;


import java.util.List;

import com.artistryhub.dao.DAOArtist;
import com.artistryhub.dao.DAOCity;
import com.artistryhub.dao.DAOPresentation;
import com.artistryhub.model.Artist;

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
	
	public void createCity(String name) {
		this.cityFacade.create(name);
	}
	
	public List<Artist> getAllArtist() {
		return this.artistFacade.readAll();
	}
	
	public void createPresentation(String datetime) {
		this.presentationFacade.create(null, null, null, 0, 0, 0, 0);
	}
	
	public Artist searchArtist(String name) {
		return this.artistFacade.search(name);
	}
	
	public void finish() {
		this.artistFacade.finish();
		this.cityFacade.finish();
		this.presentationFacade.finish();
	}

}
