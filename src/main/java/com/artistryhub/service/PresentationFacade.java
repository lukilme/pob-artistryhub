package com.artistryhub.service;

import java.util.List;

import com.artistryhub.model.Presentation;

public class PresentationFacade extends AbstractFacade<Presentation> {

	public List<Presentation> readAll() {
		return DAOPresentation.readAll();
	}

	public void clear() {

	}

	public Presentation create(Presentation params) {
		return null;
	}

	public Presentation update(Presentation params, String attribute, Object newValue) {
		return null;
	}

	public Presentation search(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	public Presentation delete(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

}
