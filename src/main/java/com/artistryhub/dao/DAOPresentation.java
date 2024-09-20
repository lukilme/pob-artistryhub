package com.artistryhub.dao;

import java.util.List;


import com.artistryhub.model.Artist;
import com.artistryhub.model.City;
import com.artistryhub.model.Presentation;

import jakarta.persistence.TypedQuery;

public class DAOPresentation extends DAO<Presentation> {


    @Override
    public Presentation read(Object key) {
        TypedQuery<Presentation> query = manager.createQuery("SELECT p FROM Presentation p WHERE p.id = :id", Presentation.class);
        query.setParameter("id", key);
        List<Presentation> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void create(Presentation newPresentation) {
     
        manager.persist(newPresentation);
    }

    public Presentation getCombination(Artist artist, City city, String date) {
        TypedQuery<Presentation> query = manager.createQuery(
            "SELECT p FROM Presentation p WHERE p.artist = :artist AND p.city = :city AND p.date = :date", Presentation.class);
        query.setParameter("artist", artist);
        query.setParameter("city", city);
        query.setParameter("date", date);
        List<Presentation> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public List<Presentation> getByDate(String date) {
        TypedQuery<Presentation> query = manager.createQuery(
            "SELECT p FROM Presentation p WHERE p.date = :date", Presentation.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public List<Presentation> getByArtist(Artist artist) {
        TypedQuery<Presentation> query = manager.createQuery(
            "SELECT p FROM Presentation p WHERE p.artist = :artist", Presentation.class);
        query.setParameter("artist", artist);
        return query.getResultList();
    }

    public List<Presentation> getByCityAndDate(City city, String date) {
        TypedQuery<Presentation> query = manager.createQuery(
            "SELECT p FROM Presentation p WHERE p.city = :city AND p.date = :date", Presentation.class);
        query.setParameter("city", city);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public List<Presentation> getByCityAndArtist(City city, Artist artist) {
        TypedQuery<Presentation> query = manager.createQuery(
            "SELECT p FROM Presentation p WHERE p.city = :city AND p.artist = :artist", Presentation.class);
        query.setParameter("city", city);
        query.setParameter("artist", artist);
        return query.getResultList();
    }

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
