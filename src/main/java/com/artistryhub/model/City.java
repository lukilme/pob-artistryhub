package com.artistryhub.model;

import java.util.List;
import java.util.Objects;

public class City {
	private int id;
	private String name;
	private List<Presentation> presentations;

	public City() {
		super();
	}

	public City(int id, String name, List<Presentation> presentations) {
		super();
		this.id = id;
		this.name = name;
		this.presentations = presentations;
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
		City other = (City) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", presentations=" + presentations + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Presentation> getPresentations() {
		return presentations;
	}

	public void setPresentations(List<Presentation> presentations) {
		this.presentations = presentations;
	}
	
	public void addPresentation(Presentation presentation) {
    	this.presentations.add(presentation);
    }
    
    public void removePresentation(Presentation presentation) {
    	this.presentations.remove(presentation);
    }
	
	

}
