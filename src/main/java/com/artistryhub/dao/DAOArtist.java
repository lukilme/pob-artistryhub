package com.artistryhub.dao;

import java.util.List;

import com.artistryhub.exception.CustomException;
import com.artistryhub.exception.ExceptionCode;
import com.artistryhub.model.Artist;
import com.db4o.query.Query;

public class DAOArtist extends DAO<Artist> {

	/**
	 * Reads an Artist based on the provided key. The key must be either an Integer
	 * (id) or a String (name).
	 *
	 * @param key The key used to query the artist can be an integer or a string.
	 * @return The first Artist found, or null if no artist matches the key.
	 */
	@Override
	public Artist read(Object key) {
		return queryArtist(key).stream().findFirst().orElse(null);
	}

	@Override
	public void create(Artist newArtist) {
		if (newArtist.getId() == 0) {
			newArtist.setId(super.generatObsoleteId());
		}
		manager.store(newArtist);
	}

	/**
	 * Queries for artists based on the provided key. The key must be either an
	 * Integer (id) or a String (name).
	 *
	 * @param key The key used to query the artist can be an integer or a string.
	 * @return A list of artists that match the key.
	 */
	private List<Artist> queryArtist(Object key) {
		Query query = manager.query();
		query.constrain(Artist.class);
		if (key instanceof Integer) {
			query.descend("id").constrain((Integer) key);
		} else if (key instanceof String) {
			query.descend("name").constrain((String) key);
		} else {
			throw new CustomException("Invalid key type. Expected Integer or String.", ExceptionCode.INVALID_KEY);
		}
		return query.execute();
	}
}
