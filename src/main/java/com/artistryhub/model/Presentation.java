package com.artistryhub.model;

import java.time.LocalDate;
import java.util.Objects;

public class Presentation {

	private Long id;
	private LocalDate date;
	private Artist artist;
	private City city;
	private double priceTicket;
	private int duration;
	private int ticketsSold;
	private int ticketsTotal;

	public Presentation() {
		super();
	}

	public Presentation(Long id, LocalDate date, Artist artist, City city, double priceTicket, int duration,
			int ticketsSold, int ticketsTotal) {
		super();
		this.id = id;
		this.date = date;
		this.artist = artist;
		this.city = city;
		this.priceTicket = priceTicket;
		this.duration = duration;
		this.ticketsSold = ticketsSold;
		this.ticketsTotal = ticketsTotal;
	}

	@Override
	public String toString() {
		return "Presentation [id=" + id + ", date=" + date + ", artist=" + artist + ", city=" + city + ", priceTicket="
				+ priceTicket + ", duration=" + duration + ", ticketsSold=" + ticketsSold + ", ticketsTotal="
				+ ticketsTotal + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Presentation other = (Presentation) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public double getPriceTicket() {
		return priceTicket;
	}

	public void setPriceTicket(double priceTicket) {
		this.priceTicket = priceTicket;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getTicketsSold() {
		return ticketsSold;
	}

	public void setTicketsSold(int ticketsSold) {
		this.ticketsSold = ticketsSold;
	}

	public int getTicketsTotal() {
		return ticketsTotal;
	}

	public void setTicketsTotal(int ticketsTotal) {
		this.ticketsTotal = ticketsTotal;
	}

}
