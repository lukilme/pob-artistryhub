package com.artistryhub.model;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.artistryhub.dao.DAO;
import com.artistryhub.dao.DAOCity;

public class CityTest {
	private static DAOCity dao = new DAOCity();
	@Test
    public void addTest(){
		DAO.open();
		DAO.begin();
		City newCity = new City(1, "João Pessoa");
		dao.create(newCity);
		DAO.commit();
		DAO.close();
    }
	
	@Test
    public void get(){
		DAO.open();
		//Artist artist = new Artist(1, "Katy Perry", null, new ArrayList<String>(Arrays.asList("rock", "pop")), "artist who sings and does things");
		City result = dao.read(1);
		System.out.println(result);
		DAO.close();  
    }
	
	@Test
    public void getAll(){
		DAO.open();
		List<City> result = dao.readAll();
		for(Object aux: result) {
			System.out.println(aux);
		}
		DAO.close();
	}
	
	@Test
    public void delete(){
		DAO.open();
		City newCity = new City(1, "João Pessoa");
		dao.create(newCity);
		City result = dao.read(1);
		System.out.println(result);
		dao.delete(result);
		DAO.close();
	}
	
	@Test
    public void clear(){
		DAO.open();
		dao.clear();
		DAO.close();  
	}
	
}
