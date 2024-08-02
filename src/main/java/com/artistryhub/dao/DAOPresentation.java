package com.artistryhub.dao;

import java.util.List;

import com.artistryhub.model.Artist;
import com.artistryhub.model.City;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import com.artistryhub.model.Presentation;

public class DAOPresentation extends DAO<Presentation> {

	@Override
	public Presentation read(Object key) {
		Query query = manager.query();
		query.constrain(Presentation.class);
		query.descend("id").constrain(key);
		List<Presentation> result = query.execute();
		if (!result.isEmpty()) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void create(Presentation newPresentation) {
		if (newPresentation.getId() == 0) {
			newPresentation.setId(super.generateId());
		}
		manager.store(newPresentation);
	}

	public Presentation getCombination(Artist artist, City city, String date) {
		Query query = manager.query();
		query.constrain(Presentation.class);
		query.constrain(new DifferentDatePresentationEvaluation(artist, city, date));
		List<Presentation> result = query.execute();
		if (result.isEmpty())
			return null;
		else
			return result.get(0);

	}

	public List<Presentation> getByDate(String date) {
		Query query = manager.query();
		query.constrain(Presentation.class);
		query.constrain(new PresentationByDate(date));
		return query.execute();
	}

	public List<Presentation> getByArtist(Artist artist) {
		Query query = manager.query();
		query.constrain(Presentation.class);
		query.constrain(new PresentationByArtist(artist));
		return query.execute();
	}

	public List<Presentation> getByCityAndDate(City city, String date) {
		Query query = manager.query();
		query.constrain(Presentation.class);
		query.constrain(new PresentationByCityAndDate(city, date));
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
		private final Artist artist;

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

	private static class PresentationByDate implements Evaluation {
		private static final long serialVersionUID = 1L;
		private final String date;

		public PresentationByDate(String date) {
			this.date = date;
		}

		@Override
		public void evaluate(Candidate candidate) {
			Presentation presentation = (Presentation) candidate.getObject();
			if (presentation.getDate().equals(date)) {
				candidate.include(true);
			} else {
				candidate.include(false);
			}
		}
	}

	public final class DifferentDatePresentationEvaluation implements Evaluation {
		private static final long serialVersionUID = 1L;
		private final Artist artist;
		private final City city;
		private final String presentationDate;

		public DifferentDatePresentationEvaluation(Artist artist, City city, String presentationDate) {
			this.artist = artist;
			this.city = city;
			this.presentationDate = presentationDate;
		}

		@Override
		public void evaluate(Candidate candidate) {
			Presentation presentation = (Presentation) candidate.getObject();
			if (presentation.getArtist().equals(artist) && presentation.getCity().equals(city)
					&& presentation.getDate().equals(presentationDate)) {
				candidate.include(true);
			} else {
				candidate.include(false);
			}
		}
	}

	private static class PresentationByCityAndDate implements Evaluation {
		private static final long serialVersionUID = 1L;
		private final City city;
		private final String date;

		public PresentationByCityAndDate(City city, String date) {
			this.city = city;
			this.date = date;
		}

		@Override
		public void evaluate(Candidate candidate) {
			Presentation presentation = (Presentation) candidate.getObject();
			if (presentation.getCity().equals(city) && presentation.getDate().equals(date))
				candidate.include(true);
			else
				candidate.include(false);
		}
	}

	private static class PresentationByCityAndArtist implements Evaluation {
		private static final long serialVersionUID = 1L;
		private final City city;
		private final Artist artist;

		public PresentationByCityAndArtist(City city, Artist artist) {
			this.city = city;
			this.artist = artist;
		}

		@Override
		public void evaluate(Candidate candidate) {
			Presentation presentation = (Presentation) candidate.getObject();
			if (presentation.getCity().equals(city) && presentation.getArtist().equals(artist)) {
				candidate.include(true);
			} else {
				candidate.include(false);
			}
		}
	}

}
