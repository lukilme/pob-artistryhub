package com.artistryhub.model;

import java.util.List;
import java.util.Objects;

public class Artist {
	private Long id;
	private String name;
	private List<Presentation> presentations;
	private List<String> type;
	private String biography;

	public Artist() {
		super();
	}

	public Artist(Long id, String name, List<Presentation> presentations, List<String> type, String biography) {
		super();
		this.id = id;
		this.name = name;
		this.presentations = presentations;
		this.type = type;
		this.biography = biography;
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + ", presentations=" + presentations + ", type=" + type
				+ ", biography=" + biography + "]";
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
		Artist other = (Artist) obj;
		return Objects.equals(id, other.id);
	}

	public void add(Presentation newPresentation) {
		this.presentations.add(newPresentation);
	}

	public void remove(Presentation removedPresentation) {
		this.presentations.remove(removedPresentation);
	}

	public void add(String newType) {
		this.type.add(newType);
	}

	public void remove(String removedType) {
		this.type.remove(removedType);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

}
