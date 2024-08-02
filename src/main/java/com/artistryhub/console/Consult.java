package com.artistryhub.console;

import java.util.List;
import com.artistryhub.model.Artist;
import com.artistryhub.model.Presentation;
import com.artistryhub.service.Facade;

public class Consult {
	public static void main(String[] args) {
		Facade facade = new Facade();

		try {
			// Query 1: Artists by Presentation Count
			System.out.println("Query Artist By Presentation Count 2");
			List<Artist> resultArtistQuery = facade.getArtistByPresentationCount(2);
			resultArtistQuery.forEach(System.out::println);

			// Query 2: Artists by City and Date
			System.out.println("Query Artist By City and Date Porto Alegre on 12/09/2024");
			List<Artist> resultArtistCityAndDate = facade.getArtistByDatePresentationInCity("Porto Alegre",
					"12/09/2024");
			resultArtistCityAndDate.forEach(System.out::println);

			// Query 3: Presentations by Date
			System.out.println("Query Presentation by Date 10/11/2024");
			List<Presentation> presentationsByDate = facade.getPresentationByDate("10/11/2024");
			presentationsByDate.forEach(System.out::println);
		} catch (Exception excep) {
			System.out.println(excep.getMessage());
		}

		finally {
			facade.finish();
		}
	}
}
