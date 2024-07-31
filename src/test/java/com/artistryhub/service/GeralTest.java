package com.artistryhub.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.artistryhub.dao.DAOArtist;
import com.artistryhub.dao.DAOCity;
import com.artistryhub.dao.DAOPresentation;
import com.artistryhub.model.Artist;
import com.artistryhub.model.City;
import com.artistryhub.model.Presentation;

 

public class GeralTest {
	private final DAOArtist DAOArtistic = new DAOArtist();
	private final DAOPresentation DAOPresentation = new DAOPresentation();
	private final DAOCity DAOCity = new DAOCity();
	private final CityFacade facadeCity = new CityFacade();
	private final ArtistFacade facadeArtist = new ArtistFacade();
	private final PresentationFacade facadePresentation = new PresentationFacade();
	@Test
	public void fullDataBase() {
		
	}
}
