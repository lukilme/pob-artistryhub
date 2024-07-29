package com.artistryhub.dao;

import java.util.List;

import com.artistryhub.model.Artist;
import com.artistryhub.model.City;
import com.artistryhub.model.Presentation;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

public class DAOPresentation extends DAO<Presentation> {

	@Override
	public Presentation read(Object key) {
		Query query = manager.query();
		query.constrain(Artist.class);
		query.descend("id").constrain(key);
		List<Presentation> result = query.execute();
		if (!result.isEmpty()) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void create(Presentation params) {

	}

	public Presentation update(Presentation params) {

		return params;
	}

	public void delete(Presentation params) {

	}

	public List<Presentation> readAll() {
		return null;
	}

	public void clear() {

	};

	public List<Presentation> getByArtist(Artist artist) {
		Query query = manager.query();
		query.constrain(Presentation.class);
		query.constrain(new PresentationByArtist(artist));
		return query.execute();
	}

	public List<Presentation> getByCity(City city) {
		Query query = manager.query();
		query.constrain(Presentation.class);
		query.constrain(new PresentationByCity(city));
		return query.execute();
	}

	public List<Presentation> getByCityAndArtist(City city, Artist artist) {
		Query query = manager.query();
		query.constrain(Presentation.class);
		query.constrain(new PresentationByCityAndArtist(city, artist));
		return query.execute();
	}
	// second option:

	// public List<Presentation> getByCityAndArtist(City city, Artist artist) {
	// Query query = manager.query(); query.constrain(Presentation.class);
	// query.constrain(new PresentationByCity(city));
	// query.constrain(new PresentationByCity(Artist));
	// return query.execute();
	// }

	private static class PresentationByArtist implements Evaluation {
		private static final long serialVersionUID = 1L;
		private Artist artist;

		public PresentationByArtist(Artist artist) {
			this.artist = artist;
		}

		@Override
		public void evaluate(Candidate candidate) {
			Presentation presentation = (Presentation) candidate.getObject();
			if (presentation.getArtist().equals(artist)) {
				candidate.include(true);
			} else {
				candidate.include(false);
			}
		}
	}

	private static class PresentationByCity implements Evaluation {
		private static final long serialVersionUID = 1L;
		private City city;

		public PresentationByCity(City city) {
			this.city = city;
		}

		@Override
		public void evaluate(Candidate candidate) {
			Presentation presentation = (Presentation) candidate.getObject();
			if (presentation.getCity().equals(city))
				candidate.include(true);
			else
				candidate.include(false);
		}
	}

	private static class PresentationByCityAndArtist implements Evaluation {
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

}
