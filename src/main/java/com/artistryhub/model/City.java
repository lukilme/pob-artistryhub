package com.artistryhub.model;

import java.util.ArrayList;
import java.util.Objects;

public class City {
	private String name;
	private ArrayList<Presentation> presentations = new ArrayList<Presentation>();

	public City() {
		super();
	}

	public City(String name) {

		this.name = name;
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
		City other = (City) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "City name=" + name + "]";
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

}
