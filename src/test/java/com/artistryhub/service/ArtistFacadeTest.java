package com.artistryhub.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.artistryhub.exception.ExceptionCode;
import com.artistryhub.exception.FacadeException;
import com.artistryhub.model.Artist;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArtistFacadeTest {
	public ArtistFacade facade = new ArtistFacade();

	public void restoreDataForTesting() {

	}

	@Test
	public void artistCreationTest() {
		Artist artist1 = new Artist(2, "Roberto Carlos", null, new ArrayList<String>(Arrays.asList("MPB", "romance")),
				"Roberto Carlos Braga OMC é um cantor, compositor e empresário brasileiro. Foi considerado pela revista Rolling Stone Brasil como o 6.º maior artista da história da música brasileira.");
		Artist artist2 = new Artist(3, "Fernando Mendes", null, new ArrayList<String>(Arrays.asList("MPB", "romance")),
				"Luiz Fernando Mendes Ferreira, conhecido como Fernando Mendes, é um cantor e compositor brasileiro. Destacou-se durante a década de 1970 com a canção Cadeira de rodas, no disco que vendeu mais de um milhão de cópias, sendo executada nas rádios de todo o país.");
		Artist artist3 = new Artist(4, "Laura Brehm", null, new ArrayList<String>(Arrays.asList("eletronic", "pop")),
				"Laura Brehm vive e respira música. Aos 33 anos ela se tornou conhecida como cantora eletrônica");
		Artist artist4 = new Artist(5, "Kendrick Lamar", null, new ArrayList<String>(Arrays.asList("rip rop")),
				"Kendrick Lamar Duckworth, mais conhecido como Kendrick Lamar, é um rapper, compositor e produtor musical, vencedor de 17 prêmios Grammy e único músico fora da música clássica e de jazz a receber");
		
		
		assertEquals(artist1, facade.create("Roberto Carlos", new ArrayList<String>(Arrays.asList("MPB", "romance")),
				"Roberto Carlos Braga OMC é um cantor, compositor e empresário brasileiro. Foi considerado pela revista Rolling Stone Brasil como o 6.º maior artista da história da música brasileira."));
		assertEquals(artist2, facade.create("Fernando Mendes", new ArrayList<String>(Arrays.asList("MPB", "romance")),
				"Luiz Fernando Mendes Ferreira, conhecido como Fernando Mendes, é um cantor e compositor brasileiro. Destacou-se durante a década de 1970 com a canção Cadeira de rodas, no disco que vendeu mais de um milhão de cópias, sendo executada nas rádios de todo o país."));
		assertEquals(artist3, facade.create("Laura Brehm", new ArrayList<String>(Arrays.asList("eletronic", "pop")),
				"Laura Brehm vive e respira música. Aos 33 anos ela se tornou conhecida como cantora eletrônica"));
		assertEquals(artist4, facade.create("Kendrick Lamar", new ArrayList<String>(Arrays.asList("rip rop")),
				"Kendrick Lamar Duckworth, mais conhecido como Kendrick Lamar, é um rapper, compositor e produtor musical, vencedor de 17 prêmios Grammy e único músico fora da música clássica e de jazz a receber"));
		assertThatExceptionOfType(FacadeException.class)
				.isThrownBy(() -> facade.create("2Pac", new ArrayList<String>(Arrays.asList("rip rop")),
						"Rapper morto na base da bala. Era um suposto inimigo do NotoriusBig"))
				.matches(ex -> ex.getExceptionCode() == ExceptionCode.INVALID_NAME);
		assertThatExceptionOfType(FacadeException.class)
				.isThrownBy(() -> facade.create("Leno Brega", new ArrayList<String>(Arrays.asList("romance")),
						"Ele cantava"))
				.matches(ex -> ex.getExceptionCode() == ExceptionCode.INVALID_BIOGRAPHY);
		assertThatExceptionOfType(FacadeException.class)
				.isThrownBy(() -> facade.create("Phil collins", new ArrayList<String>(Arrays.asList("rip rop","romcance","contry","rock","pop")),
						"Little bay-lookin' boy So bay, I can barely say it with a straight face, lookin' boy"))
				.matches(ex -> ex.getExceptionCode() == ExceptionCode.INVALID_TYPE);

		facade.clear();
	}

	@Test
	public void artistRemovalTest() {

	}

	@Test
	public void artistSearchTest() {

	}

	@Test
	public void artistUpdateTest() {

	}

}
