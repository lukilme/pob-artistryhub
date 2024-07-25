package com.artistryhub.dao;

import java.util.List;

import com.artistryhub.exception.CustomException;
import com.artistryhub.exception.ExceptionCode;
import com.artistryhub.model.City;
import com.db4o.query.Query;

public class DAOCity extends DAO<City> {

	@Override
	public City read(Object key) {
		List<City> result = queryCity(key);
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	private List<City> queryCity(Object key) {
		String keyStr = key.toString();
		Query query = manager.query();
		query.constrain(City.class);

		if (keyStr.matches("\\d+")) {
			Integer id = Integer.parseInt(keyStr);
			query.descend("id").constrain(id);
		} else if (keyStr.matches("[a-zA-Z\\s]+")) {
			query.descend("name").constrain(keyStr);
		} else {
			throw new CustomException("Invalid key. The id must contain only numbers and the name only letters.",
					ExceptionCode.INVALID_KEY);
		}
		return query.execute();
	}

}
