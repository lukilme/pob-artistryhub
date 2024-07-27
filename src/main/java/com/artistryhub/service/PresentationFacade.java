package com.artistryhub.service;

import java.util.List;

import com.artistryhub.model.Presentation;

public class PresentationFacade extends AbstractFacade<Presentation> {

	public List<Presentation> readAll() {
		return DAOPresentation.readAll();
	}

	public void clear() {
		DAOPresentation.clear();
	}
	

	public Presentation create(Presentation params) {
		
		return null;
	}

	public Presentation update(Presentation params, String attribute, Object newValue) {
		return null;
	}
	
	public List<Presentation> getPresentationsByArtist(){
		return null;
		
	}
	
	public List<Presentation> getPresentationsByCity(){
		return null;
		
	}
	
	public List<Presentation> getPresentationsByCityAndArtist(){
		return null;
		
	}
	

	public Presentation search(Object key) {
		return DAOPresentation.read(key);
	}

	public Presentation delete(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

}
