package com.artistryhub.dao;

import java.util.List;

import com.artistryhub.model.Artist;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

public class DAOArtist extends DAO<Artist> {

	@Override
	public Artist read(Object key) {
		Query query = manager.query();
		query.constrain(Artist.class);
		query.descend("name").constrain((String) key);
		List<Artist> result = query.execute();
		if (result.size() > 0)
			return result.get(0);
		else
			return null;
	}
	
	public List<Artist> getArtistByPresentationCount(int limit){
		Query query = manager.query();
		query.constrain(Artist.class);
		query.constrain(new ArtistByPresentationCount(limit));
		return query.execute();
	}
	
	private static class ArtistByPresentationCount implements Evaluation {
		private static final long serialVersionUID = 1L;
		private final int limit;

		public ArtistByPresentationCount(int limit) {
			this.limit = limit;
		}

		@Override
		public void evaluate(Candidate candidate) {
			Artist artist = (Artist) candidate.getObject();
			if (artist.getPresentations().size()==limit) {
				candidate.include(true);
			} else {
				candidate.include(false);
			}
		}
	}
}
