package cui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
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
			System.out.println("Geef de gebruikersnaam in");// TODO exceptions catchen
			String gebruikersnaam = invoer.nextLine();
			System.out.println("Geef je geboortejaar in:");// TODO exceptions catchen
			int geboortejaar = Integer.parseInt(invoer.nextLine());
			dc.registreerSpeler(gebruikersnaam, geboortejaar);

			System.out.println("De speler is geregistreerd!");
		} catch (IllegalArgumentException e) {
			System.out.println("Geen geldig input!");
		}
	}

	public void startSpel() {
		List<Speler> beschikbareSpelers = dc.geefAlleBeschikbareSpelers();
		if (beschikbareSpelers.isEmpty()) {
			System.out.println("Geen beschikbare spelers gevonden.");
			return;
		}

		HashSet<String> beschikbareKleuren = new HashSet<>();
		for (SpelerKleur k : SpelerKleur.values()) {
			beschikbareKleuren.add(k.name());
		}
		 List<Speler> gekozenSpelers = new ArrayList<>();
		System.out.println("Kies minstens 3 nieuwe spelers en hun kleuren. Bevestig met een lege gebruikersnaam.");

		while (true) {
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

			if (spelerLijst.size() >= 3 && spelerLijst.size() <= 6) {
				break;
			}
		}

		if (spelerLijst.size() < 3 || spelerLijst.size() > 6) {
			System.out.println("Ongeldige spelerslijst.");
			return;
		}

		for (Speler i : spelerLijst) {
			LocalDate vandaag = LocalDate.now();
			int huidigJaar = vandaag.getYear();
			int leeftijd = huidigJaar - i.getGeboortejaar();

			System.out.printf("%s, %s, %d%n", i.getGebruikersnaam(), i.getKleur(), leeftijd);// zetstenen
		}

		dc.startNieuwSpel(spelerLijst);
	}
}
