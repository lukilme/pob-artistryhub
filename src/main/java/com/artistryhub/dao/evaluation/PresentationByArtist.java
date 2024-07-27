package com.artistryhub.dao.evaluation;

import com.artistryhub.model.Artist;
import com.artistryhub.model.Presentation;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;

public class PresentationByArtist implements Evaluation {
	private static final long serialVersionUID = 1L;
	private Artist artist;

	public PresentationByArtist(Artist artist) {
		this.artist = artist;
	}

	@Override
	public void evaluate(Candidate candidate) {
		Presentation presentation = (Presentation) candidate.getObject();
		if (presentation.getArtist().equals(artist))
			candidate.include(true);
		else
			candidate.include(false);
	}
}