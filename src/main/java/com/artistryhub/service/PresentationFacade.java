package com.artistryhub.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.artistryhub.dao.DAO;
import com.artistryhub.exception.ArtistNotFoundException;
import com.artistryhub.exception.InvalidArtistException;
import com.artistryhub.exception.InvalidTicketException;
import com.artistryhub.exception.UniquePresentationException;
import com.artistryhub.model.Artist;
import com.artistryhub.model.City;
import com.artistryhub.model.Presentation;

public class PresentationFacade extends AbstractFacade<Presentation> {

   

	public List<Presentation> readAll() {
		return DAOPresentation.readAll();
	}

	public void clear() {
		DAOPresentation.clear();
	}

	public Presentation create(Presentation newPresentation) {

		return null;
	}

	public Presentation create(String dateString, Artist artist, City city, double priceTicket, int duration,
			int ticketsSold, int ticketsTotal) {
		DAO.begin();
		validateArtist(artist);
		validateCity(city);
		validateTicket(ticketsSold, ticketsTotal);
		validateTicketPrice(ticketsTotal);
		
		validateDuration(duration);
		validateArtistPresence(artist, city, dateString);

		Presentation newPresentation = new Presentation(dateString, artist, city, priceTicket, duration, ticketsSold,
				ticketsTotal);
		city.addPresentation(newPresentation);
		artist.addPresentation(newPresentation);
		System.out.println(newPresentation);
		DAOPresentation.create(newPresentation);
		DAOArtist.update(artist);
		DAOCity.update(city);
		DAO.commit();
		return newPresentation;
	}

	public Presentation search(Object key) {
		return DAOPresentation.read(key);
	}

	public Presentation delete(Object key) {
		return null;
	}

	public Presentation update(Presentation params) {
		return null;
	}

	public List<Presentation> getPresentationsByArtist() {
		return null;

	}

	public List<Presentation> getPresentationsByCity() {
		return null;

	}

	public List<Presentation> getPresentationsByCityAndArtist() {
		return null;

	}

	private void validateArtistPresence(Artist artist, City city, String date) {
		if (DAOPresentation.getCombination(artist, city, date) != null) {
			throw new UniquePresentationException("An artist can only perform once in a city ​​on the same date");
		}
	}

	public LocalDate parseDateSafely(String dateString) {
	    try {
	        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    } catch (DateTimeParseException e) {
	        throw new IllegalArgumentException("Invalid date format: " + e.getMessage());
	    }
	}

	private void validateArtist(Object verifyArtist)
			throws InvalidArtistException, ArtistNotFoundException, NullPointerException {
		if (verifyArtist == null) {
			throw new NullPointerException("Artist is null");
		}
		if (!(verifyArtist instanceof Artist || verifyArtist instanceof String)) {
			throw new InvalidArtistException(
					"Invalid artist format. " + "Expected Artist object or artist name as String.");
		}

		if (verifyArtist instanceof Artist) {
			Artist artistObj = (Artist) verifyArtist;
			if (DAOArtist.read(artistObj.getName()) == null) {
				throw new ArtistNotFoundException("Artist with name '" + artistObj.getName() + "' not found.");
			}
		} else if (verifyArtist instanceof String) {
			String artistName = (String) verifyArtist;
			if (DAOArtist.read(artistName) == null) {
				throw new ArtistNotFoundException("Artist with name '" + artistName + "' not found.");
			}
		}
	}

	private void validateCity(Object verifyCity) {
		if (!(verifyCity instanceof City || verifyCity instanceof String)) {
			throw new InvalidArtistException(
					"Invalid artist format. " + "Expected Artist object or artist name as String.");
		}
		if (verifyCity instanceof City) {
			City artistObj = (City) verifyCity;
			if (DAOCity.read(artistObj.getName()) == null) {
				throw new ArtistNotFoundException("City with name '" + artistObj.getName() + "' not found.");
			}
		} else if (verifyCity instanceof String) {
			String artistName = (String) verifyCity;
			if (DAOArtist.read(artistName) == null) {
				throw new ArtistNotFoundException("City with name '" + artistName + "' not found.");
			}
		}
	}

	private void validateTicket(int verifyTickets, int verifyTotalTickets) {
		if (verifyTickets < 0 || verifyTotalTickets < 0) {
			throw new InvalidTicketException("Both ticket counts must be non-negative.");
		}
		if (verifyTickets < 0) {
			throw new InvalidTicketException("the total number of tickets sold cannot be less than 0");
		}
		if (verifyTotalTickets < 0) {
			throw new InvalidTicketException("the total number of tickets cannot be less than 0");
		}
		if (verifyTickets > verifyTotalTickets) {
			throw new InvalidTicketException("The number of tickets sold is greater than the total number");
		}
	}

	private void validateTicketPrice(double ticketPrice) {
		if (ticketPrice < 0) {
			throw new InvalidTicketException("ticket price cannot be less than 0");
		}
	}

	public void validateDate(LocalDate date) {
		if (date.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("the date happens in the past.");
		}
	}

	public void validateDuration(int duration) {
		if (duration < 30) {
			throw new IllegalArgumentException("the show time is very short");
		}
		// 24*60 = 1440
		if (duration > 1140) {
			throw new IllegalArgumentException("the show time is too long");
		}
	}
}
