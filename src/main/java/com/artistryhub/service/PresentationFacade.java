package com.artistryhub.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.artistryhub.dao.DAO;
import com.artistryhub.model.Artist;
import com.artistryhub.model.City;
import com.artistryhub.exception.PresentationException;

import com.artistryhub.model.Presentation;

public class PresentationFacade extends AbstractFacade<Presentation> {
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Override
	public List<Presentation> getAll() {
		return DAOPresentation.readAll();
	}

	public Presentation create(String dateString, String artist, String city, double priceTicket, int duration,
			int ticketsSold, int ticketsTotal) {
		DAO.begin();

		Artist presentationArtist = validateArtist(artist);
		City presentationCity = validateCity(city);

		validateTicket(ticketsSold, ticketsTotal);
		validateTicketPrice(ticketsTotal);
		validateDuration(duration);
		validateDate(dateString);
		validateArtistPresence(presentationArtist, presentationCity, dateString);

		Presentation newPresentation = new Presentation(dateString, presentationArtist, presentationCity, priceTicket,
				duration, ticketsSold, ticketsTotal);
		presentationArtist.addPresentation(newPresentation);
		presentationCity.addPresentation(newPresentation);
		DAOPresentation.create(newPresentation);
		DAOArtist.update(presentationArtist);
		DAOCity.update(presentationCity);
		DAO.commit();
		return newPresentation;
	}

	public Presentation delete(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("ID must be greater than zero");
		}

		Presentation presentation = DAOPresentation.read(id);
		if (presentation == null) {
			throw new IllegalArgumentException("No presentation found with the given ID");
		}

		Artist presentationArtist = presentation.getArtist();
		City presentationCity = presentation.getCity();
		if (presentationArtist == null) {
			throw new NullPointerException("Artist of the presentation is null");
		}
		if (presentationCity == null) {
			throw new NullPointerException("City of the presentation is null");
		}

		presentationArtist.removePresentation(presentation);
		presentationCity.removePresentation(presentation);

		DAOArtist.update(presentationArtist);
		DAOCity.update(presentationCity);
		DAOPresentation.delete(presentation);
		DAO.commit();

		return presentation;
	}
	

	public Presentation delete(String date, String artist, String city) {
		if (date == null || artist == null || city == null) {
			throw new NullPointerException("None of the arguments can be null");
		}

		Artist presentationArtist = this.validateArtist(artist);
		City presentationCity = this.validateCity(city);
		Presentation presentation = this.DAOPresentation.getCombination(presentationArtist, presentationCity, date);

		if (presentation == null) {
			throw new IllegalArgumentException(
					"No presentation found for the given date, artist, and city combination");
		}

		presentationArtist.removePresentation(presentation);
		presentationCity.removePresentation(presentation);
		DAOArtist.update(presentationArtist);
		DAOCity.update(presentationCity);
		DAOPresentation.delete(presentation);

		DAO.commit();
		return presentation;
	}

	public Presentation update(int id, String dateString, String artist, String city, double priceTicket, int duration,
			int ticketsSold, int ticketsTotal) {
		if (id <= 0) {
			throw new IllegalArgumentException("ID must be greater than zero");
		}

		Presentation presentation = DAOPresentation.read(id);
		if (presentation == null) {
			throw new IllegalArgumentException("No presentation found with the given ID");
		}

		Artist newPresentationArtist = this.validateArtist(artist);
		City newPresentationCity = this.validateCity(city);

		validateTicket(ticketsSold, ticketsTotal);
		validateTicketPrice(priceTicket);
		validateDuration(duration);
		validateDate(dateString);
		validateArtistPresence(newPresentationArtist, newPresentationCity, dateString);

		presentation.setDate(dateString);
		presentation.setArtist(newPresentationArtist);
		presentation.setCity(newPresentationCity);
		presentation.setPriceTicket(priceTicket);
		presentation.setDuration(duration);
		presentation.setTicketsSold(ticketsSold);
		presentation.setTicketsTotal(ticketsTotal);

		Artist oldArtist = presentation.getArtist();
		City oldCity = presentation.getCity();
		if (oldArtist != null) {
			oldArtist.removePresentation(presentation);
			DAOArtist.update(oldArtist);
		}
		if (oldCity != null) {
			oldCity.removePresentation(presentation);
			DAOCity.update(oldCity);
		}

		newPresentationArtist.addPresentation(presentation);
		newPresentationCity.addPresentation(presentation);
		DAOArtist.update(newPresentationArtist);
		DAOCity.update(newPresentationCity);

		DAOPresentation.update(presentation);
		DAO.commit();

		return presentation;
	}

	public List<Presentation> getPresentationByCityAndDate(String city, String date) {
		try {
			LocalDate.parse(date, DATE_FORMATTER);
		} catch (DateTimeParseException e) {
			throw new PresentationException("Date is invalid.");
		}
		City cityFounded = this.validateCity(city);
		return this.DAOPresentation.getByCityAndDate(cityFounded, date);
	}

	public List<Presentation> getPresentationByDate(String date) {
		try {
			LocalDate.parse(date, DATE_FORMATTER);
		} catch (DateTimeParseException e) {
			throw new PresentationException("Date is invalid.");
		}
		return DAOPresentation.getByDate(date);
	}

	public Presentation getPresentationById(int id) {
		return DAOPresentation.read(id);
	}

	public Presentation getByArtistCityAndDate(String artist, String city, String date) {
		try {
			LocalDate.parse(date, DATE_FORMATTER);
		} catch (DateTimeParseException e) {
			throw new PresentationException("Date is invalid.");
		}
		Artist newPresentationArtist = this.validateArtist(artist);
		City newPresentationCity = this.validateCity(city);

		return DAOPresentation.getCombination(newPresentationArtist, newPresentationCity, date);
	}

	public Presentation update(String dateString, String artist, String city, double priceTicket, int duration,
			int ticketsSold, int ticketsTotal) {
		return null;
	}

	public void clear() {
		DAOPresentation.clear();
	}

	private void validateDate(String date) {
		try {
			LocalDate parsedDate = LocalDate.parse(date, DATE_FORMATTER);

			LocalDate currentDate = LocalDate.now();

			if (parsedDate.isBefore(currentDate)) {
				throw new PresentationException("Date happens in the past.");
			}
		} catch (DateTimeParseException e) {
			throw new PresentationException("Date is invalid.");
		}
	}

	private void validateArtistPresence(Artist artist, City city, String date) {
		if (DAOPresentation.getCombination(artist, city, date) != null) {
			throw new PresentationException("An artist can only perform once in a city ​​on the same date");
		}
	}

	private City validateCity(String verifyCity) {
		if (verifyCity == null) {
			throw new NullPointerException("City value cannot be null.");
		}

		City cityFounded = DAOCity.read(verifyCity);
		if (cityFounded == null) {
			throw new PresentationException("City with name '" + verifyCity + "' not found.");
		} else {
			return cityFounded;
		}

	}

	private Artist validateArtist(String verifyArtist) {
		if (verifyArtist == null) {
			throw new NullPointerException("Artist value cannot be null.");
		}

		Artist artistFounded = DAOArtist.read(verifyArtist);
		if (artistFounded == null) {
			throw new PresentationException("City with name '" + verifyArtist + "' not found.");
		} else {
			return artistFounded;
		}
	}

	private void validateTicket(int verifyTickets, int verifyTotalTickets) {
		if (verifyTickets < 0 || verifyTotalTickets < 0) {
			throw new PresentationException("Both ticket counts must be non-negative.");
		}
		if (verifyTickets < 0) {
			throw new PresentationException("the total number of tickets sold cannot be less than 0");
		}
		if (verifyTotalTickets < 0) {
			throw new PresentationException("the total number of tickets cannot be less than 0");
		}
		if (verifyTickets > verifyTotalTickets) {
			throw new PresentationException("The number of tickets sold is greater than the total number");
		}
	}

	private void validateTicketPrice(double ticketPrice) {
		if (ticketPrice < 0) {
			throw new PresentationException("ticket price cannot be less than 0");
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
