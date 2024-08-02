package com.artistryhub.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.artistryhub.model.City;
import com.artistryhub.model.Presentation;

import com.artistryhub.dao.DAO;

import com.artistryhub.exception.ArtistException;

import com.artistryhub.model.Artist;

public class ArtistFacade extends AbstractFacade<Artist> {
	private static final int MIN_NAME = 3;
	private static final int MAX_NAME = 32;
	private static final int MIN_BIOGRAPHY = 16;
	private static final int MAX_BIOGRAPHY = 255;
	private static final int MIN_GENRE = 3;
	private static final int MAX_GENRE = 16;

	@Override
	public List<Artist> getAll() {
		return this.DAOArtist.readAll();
	}

	public Artist create(String name, String genre, String biography) {
		DAO.begin();
		validateName(name);
		validateGenre(genre);
		validateBiography(biography);

		if (DAOArtist.read(name) != null) {
			throw new ArtistException("Uniqueness violated, the id or name must be unique");
		}

		Artist newArtist = new Artist(name, genre, biography);
		DAOArtist.create(newArtist);
		DAO.commit();
		return newArtist;
	}

	public Artist delete(String key) {
		DAO.begin();
		Artist deletedArtist = DAOArtist.read(key);

		if (deletedArtist == null) {
			DAO.rollback();
			throw new ArtistException("Artist not found.");
		}

		List<Presentation> artistPresentations = deletedArtist.getPresentations();
		if (artistPresentations != null) {
			List<Presentation> toRemove = new ArrayList<>();

			for (Presentation artistPresentation : artistPresentations) {
				if (artistPresentation != null) {
					City cityRemovedPresentation = artistPresentation.getCity();

					if (cityRemovedPresentation != null) {
						cityRemovedPresentation.removePresentation(artistPresentation);
						DAOCity.update(cityRemovedPresentation);
					}

					toRemove.add(artistPresentation);
					DAOPresentation.delete(artistPresentation);
				}
			}

			artistPresentations.removeAll(toRemove);
		}

		DAOArtist.delete(deletedArtist);

		DAO.commit();

		return deletedArtist;
	}

	public Artist update(String name, String genre, String biography) {
		DAO.begin();
		Artist oldArtist = DAOArtist.read(name);
		if (oldArtist == null) {
			throw new ArtistException("No Artist found to updated");
		}
		if (!oldArtist.getBiography().equals(biography)) {
			updateBiography(oldArtist, biography);
		}
		if (!oldArtist.getGenre().equals(genre)) {
			updateGenre(oldArtist, genre);
		}
		DAOArtist.update(oldArtist);
		return oldArtist;
	}

	public Artist search(String key) {
		if (key.matches("^[a-zA-Z ]+$")) {
			return DAOArtist.read(key);
		} else {
			throw new ArtistException("Invalid key. The name must contain only letters and spaces.");
		}
	}

	public void clear() {
		DAOArtist.clear();
	}

	public List<Artist> getArtistByPresentationCount(int limit) {
		if (limit < 0) {
			throw new ArtistException("Limit invalid!");
		}
		if (limit == 0) {
			return this.getAll();
		}
		return this.DAOArtist.getArtistByPresentationCount(limit);

	}

	public void validateName(String name) {

		if (name == null) {
			throw new ArtistException("Name cannot be null.");
		}

		if (name.isEmpty() || !Pattern.matches("^[a-zA-Z ]+$", name)) {
			throw new ArtistException("Name must contain only letters and spaces and cannot be empty.");
		}

		int length = name.length();
		if (length < ArtistFacade.MIN_NAME || length > ArtistFacade.MAX_NAME) {
			throw new ArtistException(
					"Name must be between " + ArtistFacade.MIN_NAME + " and " + ArtistFacade.MAX_NAME + " characters.");
		}
	}

	public void validateGenre(String genre) {
		if (genre == null) {
			throw new NullPointerException("Genre cannot be null.");
		}

		if (!Pattern.matches("^[a-zA-Z ]+$", genre)) {
			throw new ArtistException("The genre must contain only letters and spaces.");
		}

		if (genre.length() < ArtistFacade.MIN_GENRE) {
			throw new ArtistException("Genre must be longer than " + ArtistFacade.MIN_GENRE + " characters.");
		}

		if (genre.length() > ArtistFacade.MAX_GENRE) {
			throw new ArtistException("Genre cannot have more than " + ArtistFacade.MAX_GENRE + " characters.");
		}
	}

	public void validateBiography(String biography) {
		if (biography == null) {
			throw new ArtistException("Biography cannot be null.");
		}

		int length = biography.length();
		if (length < ArtistFacade.MIN_BIOGRAPHY) {
			throw new ArtistException("Biography must be at least " + ArtistFacade.MIN_BIOGRAPHY + " characters long.");
		}

		if (length > ArtistFacade.MAX_BIOGRAPHY) {
			throw new ArtistException("Biography cannot exceed " + ArtistFacade.MAX_BIOGRAPHY + " characters.");
		}
	}

	@SuppressWarnings("unused")
	private void updateName(Artist updateArtist, String newName) {
		if (!updateArtist.getName().equals(newName)) {
			validateName(newName);

			if (DAOArtist.read(newName) != null) {
				throw new ArtistException("There is already an artist with this name.");
			}

			updateArtist.setName(newName);
		}
	}

	private void updateBiography(Artist updateArtist, String newBiography) {
		if (!updateArtist.getBiography().equals(newBiography)) {
			validateBiography(newBiography);

			// Atualiza a biografia do artista
			updateArtist.setBiography(newBiography);
		}
	}

	private void updateGenre(Artist updateArtist, String newGenre) {
		if (!updateArtist.getGenre().equals(newGenre)) {
			validateGenre(newGenre);

			// Atualiza o gênero do artista
			updateArtist.setGenre(newGenre);
		}
	}

}
