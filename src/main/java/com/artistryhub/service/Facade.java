package com.artistryhub.service;

import java.util.List;

public class Facade {
	private ArtistFacade artistFacade = new ArtistFacade();
	private CityFacade cityFacade = new CityFacade(); 
	private PresentationFacade presentationFacade = new PresentationFacade(); 
	
	public void createArtist(String name,List<String> types, String biography ) {
		this.artistFacade.create(null, null, null);
	}
	
	public void createCity(String name) {
		this.cityFacade.create(name);
	}
	
	public void createPresentation() {
		//this.presentationFacade.create();
	}

}
