package testen;

import domein.Spel;
import domein.Speler;
import utils.BeschikbareKleuren;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SpelTest {
    private Spel spel;

    @BeforeEach
    public void setUp() {
        spel = new Spel();
    }

    @Test
    public void testStartSpel() {
        Speler speler1 = new Speler("Speler1", 2010);
        Speler speler2 = new Speler("Speler2", 2009);
        Speler speler3 = new Speler("Speler3", 2008);
        spel.voegSpelerToe(speler1, BeschikbareKleuren.BLAUW);
        spel.voegSpelerToe(speler2, BeschikbareKleuren.GROEN);
        spel.voegSpelerToe(speler3, BeschikbareKleuren.WIT);
        spel.startSpel();
        assertNotNull(spel.getStartSpeler());
    }

    @Test
    public void testVoegMeerDan6SpelersToe() {
        for (int i = 0; i < 6; i++) {
            spel.voegSpelerToe(new Speler("Speler" + i, 2010 + i), BeschikbareKleuren.values()[i]);
        }
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            spel.voegSpelerToe(new Speler("Speler6", 2016), BeschikbareKleuren.ROOD);
        });
        assertEquals("Het spel kan maximaal 6 spelers hebben.", exception.getMessage());
    }
}