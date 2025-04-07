package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import domein.Speler;
import utils.SpelerKleur;

public class SpelerRepositoryTest {

	public SpelerRepositoryTest() {
		@Test
		void startNieuwSpel_metDrieSpelers_systeemMaaktNieuwSpel() {

			// Voeg spelers toe aan het spel
			spel.voegSpelerToe("Speler1", SpelerKleur.BLAUW);
			spel.voegSpelerToe("Speler2", SpelerKleur.GROEN);
			spel.voegSpelerToe("Speler3", SpelerKleur.WIT);

			// Start het spel
			spel.startSpel();

			// Controleer de spelinstelling
			List<Speler> gekozenSpelers = spel.getGekozenSpelers();
			assertEquals(3, gekozenSpelers.size());
			assertEquals("blauw", gekozenSpelers.get(0).getKleur());
			assertEquals("groen", gekozenSpelers.get(1).getKleur());
			assertEquals("wit", gekozenSpelers.get(2).getKleur());
		}

	}

}
