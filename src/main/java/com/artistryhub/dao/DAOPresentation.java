package com.artistryhub.dao;

import java.util.List;
import com.artistryhub.dao.evaluation.PresentationByCity;
import com.artistryhub.dao.evaluation.PresentationByArtist;
import com.artistryhub.model.Artist;
import com.artistryhub.model.City;
import com.artistryhub.model.Presentation;
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
}
