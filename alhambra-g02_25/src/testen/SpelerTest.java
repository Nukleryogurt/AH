package testen;

import domein.Speler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpelerTest {
    @Test
    public void testValidSpeler() {
        Speler speler = new Speler("Gebruiker123", 2010);
        assertEquals("Gebruiker123", speler.getGebruikersnaam());
        assertEquals(2010, speler.getGeboortejaar());
        assertEquals(0, speler.getAantalOverwinningen());
        assertEquals(0, speler.getAantalGespeeld());
    }

    @Test
    public void testInvalidGebruikersnaam() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Speler("Gebr", 2010);
        });
        assertEquals("Gebruikersnaam moet uniek zijn en minstens 6 karakters lang.", exception.getMessage());
    }

    @Test
    public void testInvalidGeboortejaar() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Speler("Gebruiker123", 2025);
        });
        assertEquals("Ongeldig geboortejaar. De speler moet minstens 6 jaar en maximaal 100 jaar oud zijn.", exception.getMessage());
    }
}