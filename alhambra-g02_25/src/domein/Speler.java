package domein;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Speler {
	private String gebruikersnaam;
	private int geboortejaar;
	private int aantalOverwinningen, aantalGespeeld;
	private String kleur = null;

	List<String> alleSpelers = new ArrayList<>();
	static final List<String> BESCHIKBARE_KLEUREN = Arrays.asList("blauw", "groen", "wit", "geel", "oranje", "rood");
	// TODO: onderscheid maken tussen *nog* beschikbare kleuren en welke er allemaal
	// mogelijk zijn (als constante), of niet?

	public Speler(String gebruikersnaam, int geboortejaar) {
		this(gebruikersnaam, geboortejaar, 0, 0);
		SpelerRepository.add(gebruikersnaam); // TODO: repository ipv alleSpelers
	}

	public Speler(String gebruikersnaam, int geboortejaar, int aantalGewonnen, int aantalGespeeld) {
		setGebruikersnaam(gebruikersnaam);
		setGeboortejaar(geboortejaar);
		setAantalOverwinningen(aantalGewonnen);
		setAantalGespeeld(aantalGespeeld);
		SpelerRepository.add(gebruikersnaam);// TODO: repository ipv alleSpelers
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	// foutmeldingen bij naam
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

	// gegeven geboorte jaar komt niet uit de toekomst
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

	public String getKleur() {
		return kleur;
	}

	public void setKleur(String kleur) {
		if (BESCHIKBARE_KLEUREN.indexOf(kleur) == -1) {
			throw new IllegalArgumentException("De gegeven kleur " + kleur + " is ongeldig.");
		} else {
			this.kleur = kleur;
		}
	}

	private void gewonnen() {
		/** Verhoogt aantalGewonnen met 1. **/
		this.setAantalOverwinningen(this.aantalOverwinningen + 1);
	}

	private void gespeeld() {
		/** Verhoogt aantalGespeeld met 1. **/
		this.setAantalGespeeld(this.aantalGespeeld + 1);
	}

	// Controleer het aantal spelers
	public static void controleerAantalSpelers(int aantalSpelers) {
		if (aantalSpelers < 3 || aantalSpelers > 6) {
			throw new IllegalArgumentException("Het spel heeft minimaal 3 en maximaal 6 spelers.");
		}
	}

	// unieke naam
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

	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

}
