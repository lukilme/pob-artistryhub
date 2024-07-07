package com.artistryhub.dao;

import java.util.List;

import com.artistryhub.model.Artist;
import com.artistryhub.model.Presentation;
import com.db4o.query.Query;

public class DAOPresentation extends DAO<Presentation>{

	@Override
	public Presentation read(Object key) {
		Query query = manager.query();
		query.constrain(Artist.class);
		query.descend("id").constrain(key);
		List<Presentation> result = query.execute();
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}
	

}
