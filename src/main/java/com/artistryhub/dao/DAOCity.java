package com.artistryhub.dao;

import java.util.List;
import jakarta.persistence.TypedQuery;
import com.artistryhub.model.City;

public class DAOCity extends DAO<City> {

	@Override
	public City read(Object key) {
		TypedQuery<City> query = manager.createQuery("SELECT c FROM City c WHERE c.name = :name", City.class);
		query.setParameter("name", (String) key);
		List<City> result = query.getResultList();
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}
