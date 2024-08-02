package com.artistryhub.model;

import java.util.Objects;

public class Presentation {
	private int id;
	private String date;
	private Artist artist;
	private City city;
	private double priceTicket;
	private int duration;
	private int ticketsSold;
	private int ticketsTotal;

	public Presentation() {

	}

	public Presentation(String date, Artist artist, City city, double priceTicket, int duration, int ticketsSold,
			int ticketsTotal) {
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
		return Objects.hash(artist, city, date);
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
		return Objects.equals(artist, other.artist) && Objects.equals(city, other.city)
				&& Objects.equals(date, other.date);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
