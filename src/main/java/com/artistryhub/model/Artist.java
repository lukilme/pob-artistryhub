package com.artistryhub.model;

import java.util.ArrayList;
import java.util.Objects;

public class Artist {
	private String name;
	private ArrayList<Presentation> presentations = new ArrayList<Presentation>();;
	private String genre;
	private String biography;

	public Artist() {

	}

	public Artist(String name, String genre, String biography) {
		this.name = name;
		this.genre = genre;
		this.biography = biography;
	}

	@Override
	public String toString() {
		return "Artist name=" + name + ", type=" + genre + ", biography=" + biography + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		return Objects.equals(biography, other.biography) && Objects.equals(name, other.name)
				&& Objects.equals(presentations, other.presentations) && Objects.equals(genre, other.genre);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Presentation> getPresentations() {
		return presentations;
	}

	public void setPresentations(ArrayList<Presentation> presentations) {
		this.presentations = presentations;
	}

	public void addPresentation(Presentation presentation) {
		this.presentations.add(presentation);
	}

	public void removePresentation(Presentation presentation) {
		this.presentations.remove(presentation);
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

}
