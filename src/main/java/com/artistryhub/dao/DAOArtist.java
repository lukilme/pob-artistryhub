package com.artistryhub.dao;

import java.util.List;

import com.artistryhub.model.Artist;
import jakarta.persistence.TypedQuery;

public class DAOArtist extends DAO<Artist> {

    @Override
    public Artist read(Object key) {
        TypedQuery<Artist> query = manager.createQuery("SELECT a FROM Artist a WHERE a.name = :name", Artist.class);
        query.setParameter("name", (String) key);
        List<Artist> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public List<Artist> getArtistByPresentationCount(int limit){
        TypedQuery<Artist> query;
	
		query = manager.createQuery(
		"SELECT a FROM Artist a WHERE SIZE(a.presentations) = :limit", Artist.class);
		
        query.setParameter("limit", limit);
        return query.getResultList();
    }

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
