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

	/**
	 * Updates the given artist instance in the database. If the artist instance
	 * already exists in the database, it will be updated with the new values. If
	 * the artist does not exist, it will be added as a new record.
	 * 
	 * @param updatedArtist The artist instance with updated information that will
	 *                      be stored in the database.
	 * 
	 * @return The updated artist instance that was stored in the database.
	 */
	@Override
	public Artist update(Artist updatedArtist) {
		manager.store(updatedArtist);
		return updatedArtist;
	}

	/**
	 * Deletes the specified artist instance from the database.
	 * 
	 * This method removes the artist object that matches the given instance from
	 * the database. If the artist does not exist in the database, no action is
	 * taken.
	 * 
	 * @param deletedArtist The artist instance to be removed from the database.
	 *                      This instance must be the same as the one stored in the
	 *                      database, including its identity and state.
	 */
	@Override
	public void delete(Artist deletedArtist) {
		manager.delete(deletedArtist);
	}

}
