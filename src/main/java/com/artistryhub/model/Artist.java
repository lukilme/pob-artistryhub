package com.artistryhub.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "artists") // Especifica o nome da tabela no banco de dados
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id") // Define o nome da coluna no banco de dados
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Presentation> presentations = new ArrayList<>();

    @Column(name = "genre", length = 50)
    private String genre;

    @Lob
    @Column(name = "biography", columnDefinition = "TEXT") // Define que o campo biografia será armazenado como texto longo
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

	public List<Presentation> getPresentations() {
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
