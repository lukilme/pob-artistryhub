package com.artistryhub.model;

import java.util.ArrayList;
import java.util.Objects;

public class Artist {
    private int id;
    private String name;
    private ArrayList<Presentation> presentations= new ArrayList<Presentation>();;
    private ArrayList<String> type = new ArrayList<String>();
    private String biography;

    public Artist() {
        
    }

    public Artist(int id, String name, ArrayList<String> type, String biography) {
 
        this.id = id;
        this.name = name;
        this.type = type;
        this.biography = biography;
    }

    @Override
    public String toString() {
        return "Artist [id=" + id + ", name=" + name + ", presentations=" + presentations + ", type=" + type + ", biography=" + biography + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Artist other = (Artist) obj;
        return Objects.equals(biography, other.biography) && id == other.id && Objects.equals(name, other.name) && Objects.equals(presentations, other.presentations) && Objects.equals(type, other.type);
    }

  
    public void addType(String newType) {
        this.type.add(newType);
    }

    public void removeType(String removedType) {
        this.type.remove(removedType);
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

    public ArrayList<String> getType() {
        return type;
    }

    public void setType(ArrayList<String> type) {
        this.type = type;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

}
