package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Year;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Speler;
import domein.Spel;
import domein.Spel.DobbelsteenKleur;
import domein.SpelerRepository;

class SpelTest {
    private Spel spel;
    private SpelerRepository spelerRepository;

    @BeforeEach
    void setUp() {
        // Set up a new game and repository before each test
        spelerRepository = new SpelerRepository();
        spel = new Spel();
    }

    @Test
    void startNieuwSpel_metDrieSpelers_systeemMaaktNieuwSpel() {
        // Register players
        spel.registreerSpeler("Speler1", 2000);
        spel.registreerSpeler("Speler2", 1998);
        spel.registreerSpeler("Speler3", 1995);

        spel.voegSpelerToe("Speler1", "blauw");
        spel.voegSpelerToe("Speler2", "groen");
        spel.voegSpelerToe("Speler3", "wit");

        spel.startSpel();

        // Verify the game setup
        List<Speler> gekozenSpelers = spel.getGekozenSpelers();
        assertEquals(3, gekozenSpelers.size());
        assertEquals("blauw", gekozenSpelers.get(0).getKleur());
        assertEquals("groen", gekozenSpelers.get(1).getKleur());
        assertEquals("wit", gekozenSpelers.get(2).getKleur());
    }

    @Test
    void voegSpelerToe_metOngeldigeKleur_werpException() {
        spel.registreerSpeler("Speler1", 2000);

        assertThrows(IllegalArgumentException.class, () -> {
            spel.voegSpelerToe("Speler1", "paars");
        });
    }

    @Test
    void voegSpelerToe_metGebruikersnaamDieNietBestaat_werpException() {
        assertThrows(IllegalArgumentException.class, () -> {
            spel.voegSpelerToe("OnbekendeSpeler", "blauw");
        });
    }

    @Test
    void voegSpelerToe_metMeerDanMaxSpelers_werpException() {
        spel.registreerSpeler("Speler1", 2000);
        spel.registreerSpeler("Speler2", 1998);
        spel.registreerSpeler("Speler3", 1995);
        spel.registreerSpeler("Speler4", 2001);
        spel.registreerSpeler("Speler5", 1999);
        spel.registreerSpeler("Speler6", 1997);

        spel.voegSpelerToe("Speler1", "blauw");
        spel.voegSpelerToe("Speler2", "groen");
        spel.voegSpelerToe("Speler3", "wit");
        spel.voegSpelerToe("Speler4", "geel");
        spel.voegSpelerToe("Speler5", "oranje");
        spel.voegSpelerToe("Speler6", "rood");

        assertThrows(IllegalArgumentException.class, () -> {
            spel.voegSpelerToe("Speler7", "blauw");
        });
    }

    @Test
    void startSpel_metMinderDanMinSpelers_werpException() {
        spel.registreerSpeler("Speler1", 2000);
        spel.registreerSpeler("Speler2", 1998);

        spel.voegSpelerToe("Speler1", "blauw");
        spel.voegSpelerToe("Speler2", "groen");

        assertThrows(IllegalArgumentException.class, () -> {
            spel.startSpel();
        });
    }

    @Test
    void speelSpel_metDrieRondes_registreertWinnaar() {
        // Register and add players
        spel.registreerSpeler("Speler1", 2000);
        spel.registreerSpeler("Speler2", 1998);
        spel.registreerSpeler("Speler3", 1995);

        spel.voegSpelerToe("Speler1", "blauw");
        spel.voegSpelerToe("Speler2", "groen");
        spel.voegSpelerToe("Speler3", "wit");

        spel.startSpel();
        spel.speelSpel();

        // Verify the winner is registered and scores are updated
        List<Speler> gekozenSpelers = spel.getGekozenSpelers();
        Speler winnaar = gekozenSpelers.get(0);
        for (Speler speler : gekozenSpelers) {
            if (speler.getAantalOverwinningen() > winnaar.getAantalOverwinningen()) {
                winnaar = speler;
            }
        }
        assertEquals(1, winnaar.getAantalOverwinningen());
        for (Speler speler : gekozenSpelers) {
            assertEquals(1, speler.getAantalGespeeld());
        }
    }
}