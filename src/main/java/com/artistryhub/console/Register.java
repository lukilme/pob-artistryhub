package com.artistryhub.console;

import com.artistryhub.service.Facade;

public class Register {
	public static void main(String[] args) {
		Facade facade = new Facade();

		System.out.println("Inserting artists");
		facade.createArtist("Lucas", "rock", "tocou rock nos anos 80, depois disso nunca mais parou");
		facade.createArtist("Ana", "pop", "cantora pop dos anos 90, conhecida por suas baladas emocionantes");
		facade.createArtist("Pedro", "samba", "sambista renomado, famoso por suas letras poéticas");
		facade.createArtist("Maria", "MPB", "ícone da Música Popular Brasileira, com várias canções clássicas");
		facade.createArtist("Joao", "sertanejo", "cantor sertanejo de sucesso, autor de muitos hits");
		facade.createArtist("Carlos", "jazz", "músico de jazz talentoso, com vários álbuns aclamados");
		facade.createArtist("Beatriz", "bossa nova", "cantora de bossa nova, com um estilo suave e elegante");
		facade.createArtist("Fernanda", "eletronica",
				"DJ e produtora de música eletrônica, conhecida por suas batidas inovadoras");
		facade.createArtist("Ricardo", "funk", "cantor de funk, popular nas comunidades urbanas");
		facade.createArtist("Juliana", "forro", "cantora de forró, com um repertório animado e dançante");
		facade.createArtist("Gustavo", "blues", "bluesman talentoso, com uma voz rouca e emotiva");
		facade.createArtist("Camila", "soul", "cantora de soul, com uma voz poderosa e envolvente");
		facade.createArtist("Bruno", "reggae", "músico de reggae, com mensagens de paz e amor em suas músicas");
		facade.createArtist("Larissa", "hiphop",
				"rapper e compositora, conhecida por suas letras fortes e conscientes");
		facade.createArtist("Felipe", "gospel", "cantor gospel, com músicas de louvor e adoração");
		facade.createArtist("Renata", "musica classica",
				"violinista talentosa, com várias apresentações em orquestras");
		facade.createArtist("Leonardo", "metal", "vocalista de banda de metal, com um estilo agressivo e potente");
		facade.createArtist("Sofia", "country", "cantora country, com músicas que contam histórias do campo");
		facade.createArtist("Mateus", "rap", "rapper conhecido por suas rimas rápidas e mensagens impactantes");
		facade.createArtist("Isabela", "indie", "cantora indie, com um estilo único e experimental");

		System.out.println("Inserting cities");
		facade.createCity("Sao Paulo");
		facade.createCity("Rio de Janeiro");
		facade.createCity("Belo Horizonte");
		facade.createCity("Salvador");
		facade.createCity("Porto Alegre");
		facade.createCity("Curitiba");
		facade.createCity("Brasilia");
		facade.createCity("Fortaleza");
		facade.createCity("Recife");
		facade.createCity("Manaus");

		System.out.println("Inserting presentations");
		facade.createPresentation("15/08/2024", "Lucas", "Sao Paulo", 150.00, 120, 8000, 10000);
		facade.createPresentation("20/08/2024", "Ana", "Rio de Janeiro", 200.00, 90, 7000, 9000);
		facade.createPresentation("25/08/2024", "Pedro", "Belo Horizonte", 100.00, 110, 5000, 7000);
		facade.createPresentation("30/08/2024", "Maria", "Salvador", 180.00, 130, 6000, 8000);
		facade.createPresentation("05/09/2024", "Joao", "Porto Alegre", 120.00, 100, 4000, 5000);
		facade.createPresentation("12/09/2024", "Joao", "Porto Alegre", 160.00, 100, 4000, 5000);
		facade.createPresentation("12/09/2024", "Carlos", "Porto Alegre", 160.00, 100, 4000, 5000);
		facade.createPresentation("10/09/2024", "Carlos", "Curitiba", 250.00, 140, 9000, 10000);
		facade.createPresentation("15/09/2024", "Beatriz", "Brasilia", 130.00, 90, 3000, 4000);
		facade.createPresentation("20/09/2024", "Fernanda", "Fortaleza", 220.00, 100, 7500, 8000);
		facade.createPresentation("25/09/2024", "Ricardo", "Recife", 80.00, 80, 2000, 3000);
		facade.createPresentation("30/09/2024", "Juliana", "Manaus", 90.00, 70, 1500, 2500);
		facade.createPresentation("05/10/2024", "Gustavo", "Sao Paulo", 170.00, 110, 6000, 7000);
		facade.createPresentation("10/10/2024", "Camila", "Rio de Janeiro", 160.00, 100, 4500, 6000);
		facade.createPresentation("15/10/2024", "Bruno", "Belo Horizonte", 140.00, 120, 5000, 7000);
		facade.createPresentation("20/10/2024", "Larissa", "Salvador", 200.00, 80, 5500, 6000);
		facade.createPresentation("21/10/2024", "Larissa", "Recife", 200.00, 80, 5500, 6000);
		facade.createPresentation("25/10/2024", "Felipe", "Porto Alegre", 110.00, 90, 2500, 4000);
		facade.createPresentation("30/10/2024", "Renata", "Curitiba", 300.00, 130, 7000, 8000);
		facade.createPresentation("05/11/2024", "Leonardo", "Brasilia", 180.00, 140, 6000, 9000);
		facade.createPresentation("10/11/2024", "Sofia", "Fortaleza", 150.00, 110, 4500, 6000);
		facade.createPresentation("10/11/2024", "Mateus", "Recife", 130.00, 100, 3000, 5000);
		facade.createPresentation("20/11/2024", "Isabela", "Manaus", 170.00, 90, 4000, 5000);

		facade.finish();
	}
}
