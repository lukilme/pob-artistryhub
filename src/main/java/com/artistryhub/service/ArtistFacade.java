package com.artistryhub.service;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.artistryhub.dao.DAOArtist;
import com.artistryhub.exception.ExceptionCode;
import com.artistryhub.exception.FacadeException;
import com.artistryhub.model.Artist;

public class ArtistFacade{
	private static final int MIN_NAME = 4;
	private static final int MAX_NAME = 32;
	private static final int MIN_BIOGRAPHY = 4;
	private static final int MAX_BIOGRAPHY = 32;
	private static final int MAX_TYPES = 4;
	
	protected DAOArtist daomanager = new DAOArtist();
	
	
	public Artist create(String name, ArrayList<String> type, String biography) {
	
		return null;
	}

	
	public Artist delete(Object key) {
		
		return null;
	}

	
	public Artist update(Object params) {
		
		return null;
	}

	
	public Artist search(Object key) {
		
		return null;
	}
	
	public void clear() {
		
	}
	
	public void validateId(int id) {
        if (id <= 0) {
            throw new FacadeException("ID must be a positive number.", ExceptionCode.INVALID_ID);
        }
    }

    public void validateName(String name) {
        if (name == null || !Pattern.matches("^[a-zA-Z ]{4,32}$", name)) {
            throw new FacadeException("Name must contain only letters and spaces, and be between "+ArtistFacade.MIN_NAME+" and "+ArtistFacade.MAX_NAME+" characters.", ExceptionCode.INVALID_NAME);
        }
    }

    public void validateType(ArrayList<String> type) {
        if (type == null || type.size() > 4) {
            throw new FacadeException("Types must be at most "+ArtistFacade.MAX_TYPES+".", ExceptionCode.INVALID_TYPE);
        }
    }

    public void validateBiography(String biography) {
        if (biography == null || biography.length() < 16 || biography.length() > 255) {
            throw new FacadeException("Biography must be included "+ArtistFacade.MIN_BIOGRAPHY+" and "+ArtistFacade.MAX_BIOGRAPHY+" characters.", ExceptionCode.INVALID_BIOGRAPHY);
        }
    }
	
}
