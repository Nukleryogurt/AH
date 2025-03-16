package testen;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domein.Spel;

class SpelTest {
	private Spel spel;

	@Test
	void maakSpel_erZijnGeenGegevens_maaktObject() {
		spel = new Spel();
		Assertions.assertEquals();
	}

}