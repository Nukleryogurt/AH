package cui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import domein.Ronde;
import domein.Speler;
import utils.SpelerKleur;

public class SpelApplicatie {
	private final Scanner invoer;
	private final String[] keuzesOverzichtsMenu = { "Registreer nieuwe speler", "Start nieuw spel", "Afsluiten" };
	private final Menu overzichtsMenu;

	private final DomeinController dc;

	List<Speler> spelerLijst = new ArrayList<>();

	public SpelApplicatie(DomeinController dc, Scanner invoer) {
		this.dc = dc;
		this.invoer = invoer;
		this.overzichtsMenu = new Menu("Menu", keuzesOverzichtsMenu, invoer);
	}

	public void start() {
		int keuze = -1;
		do {
			keuze = overzichtsMenu.geefKeuze();
			switch (keuze) {
			case 1 -> voegNieuweSpelerToe();
			case 2 -> startSpel();

			}
		} while (keuze != keuzesOverzichtsMenu.length);

		System.out.println("Tot een volgende keer!");

	}

	private void voegNieuweSpelerToe() {
		try {
			System.out.println("Geef de gebruikersnaam in");
			String gebruikersnaam = invoer.nextLine();
			System.out.println("Geef je geboortejaar in:");
			int geboortejaar = Integer.parseInt(invoer.nextLine());
			dc.registreerSpeler(gebruikersnaam, geboortejaar);

			System.out.println("De speler is geregistreerd!");
		} catch (NumberFormatException e) {
			System.out.println("Geboortejaar moet een getal zijn!");
		} catch (IllegalArgumentException e) {
			System.out.println("Gebruikersnaam moet langer zijn!");
		} catch (Exception e) {
			System.out.println("Er is een fout opgetreden: " + e.getMessage());
		}
	}

	public void startSpel() {
		List<Speler> beschikbareSpelers = dc.geefAlleBeschikbareSpelers();
		HashSet<String> beschikbareKleuren = new HashSet<>();

		System.out.println("Kies minstens 3 nieuwe spelers en hun kleuren. Bevestig met een lege gebruikersnaam.");

		kiesSpelerEnSpelerKleur(beschikbareSpelers, beschikbareKleuren);

		if (spelerLijst.size() >= 3 && spelerLijst.size() < 6) { // momenteel kunnen we maar 3 spelers toevoegen
																	// aant spel
			System.out.println("Wil je nog een speler invoeren? 1 -> ja 2 -> nee");
			int antwoord = Integer.parseInt(invoer.nextLine());
			if (antwoord == 1) {
				kiesSpelerEnSpelerKleur(beschikbareSpelers, beschikbareKleuren);
			}
		}
		
		

		if (spelerLijst.size() < 3 || spelerLijst.size() > 6) {
			System.out.println("Ongeldige spelerslijst.");
			return;
		}

		dc.startNieuwSpel(spelerLijst);
		Speler startSpeler = dc.bepaalStartSpeler();

          System.out.printf("%nDe startspeler is = %s%n", startSpeler.getGebruikersnaam());
          
          List<String> volgordeSpelerGebruikersnamen = new ArrayList<>();
          volgordeSpelerGebruikersnamen = dc.bepaalVolgordeSpelersGebruikersnaam();
         System.out.println("Speler volgorde gaat als volgt:");
          System.out.println(volgordeSpelerGebruikersnamen);
          
		
		for (Speler i : spelerLijst) {
			LocalDate vandaag = LocalDate.now();
			int huidigJaar = vandaag.getYear();
			int leeftijd = huidigJaar - i.getGeboortejaar();

			System.out.printf("%s, %s, %d%n", i.getGebruikersnaam(), i.getKleur(), leeftijd);// zetstenen nog bij
		}

		
		System.out.printf("De volgnummer van de ronde is %d%n", dc.toonVolgnummerVanRonde());
		System.out.printf("De volgnummer van de ronde is %d%n", dc.toonVolgnummerVanRonde());
		System.out.printf("De volgnummer van de ronde is %d%n", dc.toonVolgnummerVanRonde());
		
		scoreOverzicht();

	}

	public void scoreOverzicht() {
		dc.geefWinnaar();
		System.out.printf("%n%16s| %15s| %15s%n", "spelersnamen", "aantal gewonnen", "aantal gespeeld");
		System.out.println("==================================================");
		for (Speler i : spelerLijst) {
			System.out.printf("%16s| %15d| %15d%n", i.getGebruikersnaam(), i.getAantalOverwinningen(),
					i.getAantalGespeeld());
		}
	}

	public void kiesSpelerEnSpelerKleur(List<Speler> beschikbareSpelers, HashSet<String> beschikbareKleuren) {

		if (beschikbareSpelers.isEmpty()) {
			System.out.println("Geen beschikbare spelers gevonden.");
			return;
		}

		for (SpelerKleur k : SpelerKleur.values()) {
			beschikbareKleuren.add(k.name());
		}

		while (true) {

			if (spelerLijst.size() >= 6) {
				break;
			}

			System.out.println("Ongekozen, beschikbare spelers:");
			for (int i = 0; i < beschikbareSpelers.size(); i++) {
				System.out.printf("%d. %s%n", i + 1, beschikbareSpelers.get(i).getGebruikersnaam());
			}

			System.out.println("Ongekozen, beschikbare kleuren:");
			int kleurIndex = 1;
			for (String kleur : beschikbareKleuren) {
				System.out.printf("%d. %s%n", kleurIndex++, kleur);
			}

			System.out.println("Geef een nummer van de gebruikersnaam in:");
			String gebruikersnaamKeuze = invoer.nextLine();
			if (gebruikersnaamKeuze.isEmpty()) {
				break;

			}

			int gebruikersnaamIndex = Integer.parseInt(gebruikersnaamKeuze) - 1;
			if (gebruikersnaamIndex < 0 || gebruikersnaamIndex >= beschikbareSpelers.size()) {
				System.out.println("Ongeldige keuze, probeer opnieuw.");
				continue;
			}

			Speler gekozenSpeler = beschikbareSpelers.get(gebruikersnaamIndex);

			System.out.println("Kies een nummer van de kleur:");
			String kleurKeuze = invoer.nextLine();
			int kleurIndexKeuze = Integer.parseInt(kleurKeuze) - 1;
			if (kleurIndexKeuze < 0 || kleurIndexKeuze >= beschikbareKleuren.size()) {
				System.out.println("Ongeldige keuze, probeer opnieuw.");
				continue;
			}

			String gekozenKleur = (String) beschikbareKleuren.toArray()[kleurIndexKeuze];
			gekozenSpeler.setKleur(SpelerKleur.valueOf(gekozenKleur));
			spelerLijst.add(gekozenSpeler);
			beschikbareKleuren.remove(gekozenKleur);
			beschikbareSpelers.remove(gebruikersnaamIndex);
		}
	}
}
