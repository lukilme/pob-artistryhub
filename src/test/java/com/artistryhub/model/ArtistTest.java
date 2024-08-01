package com.artistryhub.model;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.artistryhub.dao.DAO;
import com.artistryhub.dao.DAOArtist;


public class ArtistTest {
	private static DAOArtist daoArtist = new DAOArtist(); 
	
	@Test
    public void addTest(){
		DAO.open();
		DAO.begin();
		Artist artist = new Artist(1, "Katy Perry", "pop", "artist who sings and does things");
		daoArtist.create(artist);
		DAO.commit();
		DAO.close();
    }
	
	@Test
    public void get(){
		DAO.open();
		//Artist artist = new Artist(1, "Katy Perry", null, new ArrayList<String>(Arrays.asList("rock", "pop")), "artist who sings and does things");
		Artist result = daoArtist.read(1);
		System.out.println(result);
		DAO.close();  
    }
	
	@Test
    public void getAll(){
		DAO.open();
		List<Artist> result = daoArtist.readAll();
		for(Object aux: result) {
			System.out.println(aux);
		}
		DAO.close();
	}
	
	@Test
    public void delete(){
		DAO.open();
		Artist artist = new Artist(1, "Katy Perry", "pop", "artist who sings and does things");
		daoArtist.create(artist);
		Artist result = daoArtist.read(1);
		daoArtist.delete(result);
		DAO.close();
	}
	
	@Test
    public void clear(){
		DAO.open();
		daoArtist.clear();
		DAO.close();  
	}
	
	
   
}
