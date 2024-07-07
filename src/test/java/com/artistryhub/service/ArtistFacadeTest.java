package com.artistryhub.service;

import com.artistryhub.exception.CustomException;
import com.artistryhub.exception.ExceptionCode;
import com.artistryhub.model.Artist;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArtistFacadeTest {
	public ArtistFacade facade = new ArtistFacade();
	Artist artist1 = new Artist(1, "Roberto Carlos", null, new ArrayList<String>(Arrays.asList("MPB", "romance")),
			"Roberto Carlos Braga OMC é um cantor, compositor e empresário brasileiro. Foi considerado pela revista Rolling Stone Brasil como o 6.º maior artista da história da música brasileira.");
	Artist artist2 = new Artist(2, "Fernando Mendes", null, new ArrayList<String>(Arrays.asList("MPB", "romance")),
			"Luiz Fernando Mendes Ferreira, conhecido como Fernando Mendes, é um cantor e compositor brasileiro. Destacou-se durante a década de 1970, com discos que vendeu mais de um milhão de cópias,presente nas rádios de todo o país.");
	Artist artist3 = new Artist(3, "Laura Brehm", null, new ArrayList<String>(Arrays.asList("eletronic", "pop")),
			"Laura Brehm vive e respira música. Aos 33 anos ela se tornou conhecida como cantora eletrônica");
	Artist artist4 = new Artist(4, "Kendrick Lamar", null, new ArrayList<String>(List.of("rip rop")),
			"Kendrick Lamar Duckworth, mais conhecido como Kendrick Lamar, é um rapper, compositor e produtor musical, vencedor de 17 prêmios Grammy e único músico fora da música clássica e de jazz a receber");
	
	@Test
	public void artistCreationTest() {
		facade.initialize();
		facade.clear();
		System.out.println("\nartistCreationTest");
		assertEquals(artist1, facade.create("Roberto Carlos", new ArrayList<String>(Arrays.asList("MPB", "romance")),
				"Roberto Carlos Braga OMC é um cantor, compositor e empresário brasileiro. Foi considerado pela revista Rolling Stone Brasil como o 6.º maior artista da história da música brasileira."));

		assertEquals(artist2, facade.create("Fernando Mendes", new ArrayList<String>(Arrays.asList("MPB", "romance")),
				"Luiz Fernando Mendes Ferreira, conhecido como Fernando Mendes, é um cantor e compositor brasileiro. Destacou-se durante a década de 1970, com discos que vendeu mais de um milhão de cópias,presente nas rádios de todo o país."));

		assertEquals(artist3, facade.create("Laura Brehm", new ArrayList<String>(Arrays.asList("electronic", "pop")),
				"Laura Brehm vive e respira música. Aos 33 anos ela se tornou conhecida como cantora eletrônica"));

		assertEquals(artist4, facade.create("Kendrick Lamar", new ArrayList<String>(List.of("rip rop")),
				"Kendrick Lamar Duckworth, mais conhecido como Kendrick Lamar, é um rapper, compositor e produtor musical, vencedor de 17 prêmios Grammy e único músico fora da música clássica e de jazz a receber"));
		List<Artist> artists = facade.getAll();
		if (artists.isEmpty())
			System.out.println("Is Empty");
		else {
			for (Artist artist : artists) {
				System.out.println(artist);
			}
		}
		facade.clear();
		facade.finish();
	}

	@Test
	public void artistCreationTestInstanced() {
		facade.initialize();
		facade.clear();
		System.out.println("\nartistCreationTestInstanced");
		assertEquals(artist1, artist1);
		assertEquals(artist2, artist2);
		assertEquals(artist3, artist3);
		assertEquals(artist4, artist4);

		List<Artist> artists = facade.getAll();
		if (artists.isEmpty())
			System.out.println("Is Empty");
		else {
			for (Artist artist : artists) {
				System.out.println(artist);
			}
		}
		facade.clear();
		facade.finish();
	}

	@Test
	public void artistCreationTestExceptionInstanced() {
		facade.initialize();
		facade.clear();
		Artist artist0 = new Artist(1, "Roberto Carlos", null, new ArrayList<String>(Arrays.asList("MPB", "romance")),
				"Roberto Carlos Braga OMC é um cantor, compositor e empresário brasileiro. Foi considerado pela revista Rolling Stone Brasil como o 6.º maior artista da história da música brasileira.");
		facade.create(artist0);
		Artist artist1 = new Artist(1, "Irmao do Roberto Carlos", null,
				new ArrayList<String>(Arrays.asList("MPB", "romance")),
				"Roberto Carlos Braga OMC é um cantor, compositor e empresário brasileiro. Foi considerado pela revista Rolling Stone Brasil como o 6.º maior artista da história da música brasileira.");
		Artist artist2 = new Artist(2, "Fernando Mendes2", null, new ArrayList<String>(Arrays.asList("MPB", "romance")),
				"Luiz Fernando Mendes Ferreira, conhecido como Fernando Mendes, é um cantor e compositor brasileiro. Destacou-se durante a década de 1970, com discos que vendeu mais de um milhão de cópias,presente nas rádios de todo o país.");
		Artist artist3 = new Artist(3, "Laura Brehm", null,
				new ArrayList<String>(Arrays.asList("eletronic", "pop", "rock", "romance", "folk")),
				"Laura Brehm vive e respira música. Aos 33 anos ela se tornou conhecida como cantora eletrônica");
		Artist artist4 = new Artist(4, "Kendrick Lamar", null, new ArrayList<String>(List.of("rip rop")),
				"Drake's Father");

		System.out.println("\nartistCreationTestExceptionInstanced");
		assertEquals(artist1, artist1);
		assertEquals(artist2, artist2);
		assertEquals(artist3, artist3);
		assertEquals(artist4, artist4);

		List<Artist> artists = facade.getAll();
		if (artists.isEmpty())
			System.out.println("Is Empty");
		else {
			for (Artist artist : artists) {
				System.out.println(artist);
			}
		}
		facade.finish();
	}

	@Test
	public void artistCreationTestException() {
		facade.initialize();
		facade.clear();
		System.out.println("\nartistCreationTestException");
		assertThatExceptionOfType(CustomException.class)
				.isThrownBy(() -> facade.create("2Pac", new ArrayList<String>(List.of("rip rop")),
						"Rapper morto na base da bala. Era um suposto inimigo do NotoriusBig"))
				.matches(ex -> ex.getExceptionCode() == ExceptionCode.INVALID_NAME);
		assertThatExceptionOfType(CustomException.class)
				.isThrownBy(() -> facade.create("Leno Brega", new ArrayList<String>(List.of("romance")), "Ele cantava"))
				.matches(ex -> ex.getExceptionCode() == ExceptionCode.INVALID_BIOGRAPHY);
		assertThatExceptionOfType(CustomException.class)
				.isThrownBy(() -> facade.create("Phil collins",
						new ArrayList<String>(Arrays.asList("rip rop", "romcance", "contry", "rock", "pop")),
						"Little bay-lookin' boy So bay, I can barely say it with a straight face, lookin' boy"))
				.matches(ex -> ex.getExceptionCode() == ExceptionCode.INVALID_TYPE);
		List<Artist> artists = facade.getAll();
		if (artists.isEmpty())
			System.out.println("Is Empty");
		else {
			for (Artist artist : artists) {
				System.out.println(artist);
			}
		}
		facade.clear();
		facade.finish();
	}

	public void insertForTesting() {
		facade.create(artist1);
		facade.create(artist2);
		facade.create(artist3);
		facade.create(artist4);
	}

	@Test
	public void artistRemovalTest() {
		facade.initialize();
		this.insertForTesting();
		System.out.println("\nartistRemovalTest");
		facade.delete(artist1);
		facade.delete(artist2.getName());
		facade.delete(artist3.getId());
		facade.delete(artist4);
		List<Artist> artists = facade.getAll();
		if (artists.isEmpty())
			System.out.println("Is Empty");
		else {
			for (Artist artist : artists) {
				System.out.println(artist);
			}
		}
		facade.finish();

	}

	@Test
	public void artistSearchTest() {

	}

	@Test
	public void artistUpdateTest() {

	}

}
