package com.artistryhub.dao;

import java.util.List;

import com.db4o.query.Query;

import com.artistryhub.model.City;

public class DAOCity extends DAO<City> {

	@Override
	public City read(Object key) {
		Query query = manager.query();
		query.constrain(City.class);
		query.descend("name").constrain((String) key);
		List<City> result = query.execute();
		if (result.size() > 0)
			return result.get(0);
		else
			return null;
	}

}
