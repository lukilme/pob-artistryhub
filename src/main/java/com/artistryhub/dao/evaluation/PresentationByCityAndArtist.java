package com.artistryhub.dao.evaluation;

import com.artistryhub.model.Artist;
import com.artistryhub.model.City;
import com.artistryhub.model.Presentation;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;

public class PresentationByCityAndArtist implements Evaluation {
	private static final long serialVersionUID = 1L;
	private City city;
	private Artist artist;

	public PresentationByCityAndArtist(City city, Artist artist) {
		this.city = city;
		this.artist = artist;
	}

	@Override
	public void evaluate(Candidate candidate) {
		Presentation presentation = (Presentation) candidate.getObject();
		if (presentation.getCity().equals(city) && presentation.getArtist().equals(artist))
			candidate.include(true);
		else
			candidate.include(false);
	}
}