package com.artistryhub.service;

import java.util.List;

import com.artistryhub.dao.DAO;
import com.artistryhub.dao.DAOArtist;
import com.artistryhub.dao.DAOCity;
import com.artistryhub.dao.DAOPresentation;

abstract class AbstractFacade<T>{
	protected DAOArtist DAOArtist;
	protected DAOPresentation DAOPresentation;
	protected DAOCity DAOCity;

	public void initialize(DAOArtist daoArtist, DAOCity daoCity, DAOPresentation daoPresentation) {
		this.DAOArtist = daoArtist;
		this.DAOPresentation = daoPresentation;
		this.DAOCity = daoCity;
		DAO.open();
	}

	public void finish() {
		DAO.close();
	}
	
	abstract public List<T> getAll();
}
