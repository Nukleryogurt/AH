package domein;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import utils.SpelerKleur;

public class Speler {
	public static final Collection<SpelerKleur> BESCHIKBARE_KLEUREN = Arrays.asList(SpelerKleur.values());
	private String gebruikersnaam;
	private int geboortejaar;
	private int aantalOverwinningen, aantalGespeeld;
	private SpelerKleur kleur;
	private int score;
	private int gebouwPunten;

	List<String> alleSpelers = new ArrayList<>();

	private static SpelerRepository spelerRepository = new SpelerRepository();

	public Speler(String gebruikersnaam, int geboortejaar) {
		this(gebruikersnaam, geboortejaar, 0, 0, null);
	}

	public Speler(String gebruikersnaam, int geboortejaar, int aantalOverwinningen, int aantalGespeeld,
			String spelerKleur) {
		setGebruikersnaam(gebruikersnaam);
		setGeboortejaar(geboortejaar);
		setAantalOverwinningen(aantalOverwinningen);
		setAantalGespeeld(aantalGespeeld);
		setKleur(kleur);
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	private void setGebruikersnaam(String gebruikersnaam) {
		if (gebruikersnaam == null || gebruikersnaam.isBlank())
			throw new IllegalArgumentException("Naam mag niet leeg of een spatie bevatten.");
		if (gebruikersnaam.length() < 6)
			throw new IllegalArgumentException("Naam mag niet korter zijn dan 6 karakters.");
		for (String gb : alleSpelers) {
			if (gebruikersnaam.equals(gb))
				throw new IllegalArgumentException("Naam bestaat al, voer een nieuwe naam in.");
		}
		this.gebruikersnaam = gebruikersnaam;
	}

	public int getGeboortejaar() {
		return geboortejaar;
	}

	private void setGeboortejaar(int geboortejaar) {
		int huidigeJaar = Year.now().getValue();
		int leeftijd = huidigeJaar - geboortejaar;
		if (geboortejaar > huidigeJaar)
			throw new IllegalArgumentException("Geboortejaar mag niet in de toekomst liggen.");
		if (leeftijd < 6 || leeftijd > 100)
			throw new IllegalArgumentException("Speler moet minstens 6 jaar en maximaal 100 jaar oud zijn.");
		this.geboortejaar = geboortejaar;
	}

	public int getAantalOverwinningen() {
		return aantalOverwinningen;
	}

	private void setAantalOverwinningen(int aantalOverwinningen) {
		if (aantalOverwinningen < 0) {
			throw new IllegalArgumentException("Aantal gewonnen mag niet negatief zijn.");
		}
		this.aantalOverwinningen = aantalOverwinningen;
	}

	public int getAantalGespeeld() {
		return aantalGespeeld;
	}

	private void setAantalGespeeld(int aantalGespeeld) {
		if (aantalGespeeld < 0) {
			throw new IllegalArgumentException("Aantal gespeeld mag niet negatief zijn.");
		}
		this.aantalGespeeld = aantalGespeeld;
	}

	public SpelerKleur getKleur() {
		return kleur;
	}

	public void setKleur(SpelerKleur spelerKleur) {
		if (spelerKleur != null && !BESCHIKBARE_KLEUREN.contains(spelerKleur)) {
			throw new IllegalArgumentException("De gegeven kleur " + spelerKleur + " is ongeldig."); // null als
																										// defaultwaarde?
		}
		this.kleur = spelerKleur;
	}

	public void gewonnen() {
		this.setAantalOverwinningen(this.aantalOverwinningen + 1);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = 2;
	}

	public void gespeeld() {
		this.setAantalGespeeld(this.aantalGespeeld + 1);
	}

	public static void controleerAantalSpelers(int aantalSpelers) {
		if (aantalSpelers < 3 || aantalSpelers > 6) {
			throw new IllegalArgumentException("Het spel heeft minimaal 3 en maximaal 6 spelers.");
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(gebruikersnaam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Speler other = (Speler) obj;
		return Objects.equals(gebruikersnaam, other.gebruikersnaam);
	}

	public void voegGebouwPuntenToe(int rondePunten) {
		
	}
	public int getGebouwPunten() {
		return gebouwPunten;
	}

	public void voegPuntenFicheToe(Fiche huidigefiche) {
		
		
	}
}