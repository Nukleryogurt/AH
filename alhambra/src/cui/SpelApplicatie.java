package cui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import domein.Speler;
import utils.SpelerKleur;
import utils.gebouwKleuren;

public class SpelApplicatie {
	private final Scanner invoer;
	private final String[] keuzesOverzichtsMenu = { "Registreer nieuwe speler", "Start nieuw spel", "Afsluiten" };
	private final Menu overzichtsMenu;

	private final DomeinController dc;

	List<Speler> spelerLijst = new ArrayList<>();

	List<gebouwKleuren> dobbelstenenLijst = new ArrayList<>();
	List<gebouwKleuren> lijst = new ArrayList<>(); // alle resultaten van alle dobbelstenen

	public SpelApplicatie(DomeinController dc, Scanner invoer) {
		this.dc = dc;
		this.invoer = invoer;
		this.overzichtsMenu = new Menu("Menu", keuzesOverzichtsMenu, invoer);
	}

	public void start() {
		int keuze = -1;
		do {
			try {
				keuze = overzichtsMenu.geefKeuze();
				switch (keuze) {
				case 1 -> voegNieuweSpelerToe();
				case 2 -> startSpel();

				}
			} catch (Exception e) {
				System.out.println("Er ging iets mis: " + e.getMessage());
			}
		} while (keuze != keuzesOverzichtsMenu.length);

		System.out.println("Tot een volgende keer!");

	}

	private void voegNieuweSpelerToe() {
		try {
			System.out.println("Geef de gebruikersnaam in:");
			String gebruikersnaam = invoer.nextLine();
			if (gebruikersnaam.length() < 3) {
				throw new IllegalArgumentException("Gebruikersnaam moet minstens 3 karakters lang zijn!");
			}

			System.out.println("Geef je geboortejaar in:");
			int geboortejaar = Integer.parseInt(invoer.nextLine());

			dc.registreerSpeler(gebruikersnaam, geboortejaar);
			System.out.println("De speler is geregistreerd!");

		} catch (NumberFormatException e) {
			System.out.println("Ongeldig geboortejaar. Voer een getal in.");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Onverwachte fout: " + e.getMessage());
		}
	}

	public void startSpel() {
		try {
			List<Speler> beschikbareSpelers = dc.geefAlleBeschikbareSpelers();

			if (beschikbareSpelers.isEmpty()) {
				System.out.println("Geen beschikbare spelers gevonden.");
				return;
			}

			HashSet<String> beschikbareKleuren = new HashSet<>();
			for (SpelerKleur kleur : SpelerKleur.values()) {
				beschikbareKleuren.add(kleur.name());
			}

			if (spelerLijst.size() >= 3 && spelerLijst.size() < 6) { // momenteel kunnen we maar 3 spelers toevoegen
																		// aant spel
				System.out.println("Wil je nog een speler invoeren? 1 -> ja 2 -> nee");
				int antwoord = Integer.parseInt(invoer.nextLine());
				if (antwoord == 1) {
					kiesSpelerEnSpelerKleur(beschikbareSpelers, beschikbareKleuren);
				}
			}

			kiesSpelerEnSpelerKleur(beschikbareSpelers, beschikbareKleuren);

			if (spelerLijst.size() < 3 || spelerLijst.size() > 6) {
				throw new IllegalArgumentException("Aantal spelers moet tussen 3 en 6 liggen.");
			}

			dc.startNieuwSpel(spelerLijst);
			Speler startSpeler = dc.bepaalStartSpeler();

			System.out.printf("%nDe startspeler is = %s%n", startSpeler.getGebruikersnaam());

	
          System.out.printf("%nDe startspeler is = %s%n", startSpeler.getGebruikersnaam());
          
          List<String> volgordeSpelerGebruikersnamen = new ArrayList<>();
          volgordeSpelerGebruikersnamen = dc.bepaalVolgordeSpelersGebruikersnaam();
         System.out.println("Speler volgorde gaat als volgt:");
          System.out.println(volgordeSpelerGebruikersnamen);
          
		
		for (Speler i : spelerLijst) {
			LocalDate vandaag = LocalDate.now();
			int huidigJaar = vandaag.getYear();
			int leeftijd = huidigJaar - i.getGeboortejaar();

			for (Speler i : spelerLijst) {
				LocalDate vandaag = LocalDate.now();
				int huidigJaar = vandaag.getYear();
				int leeftijd = huidigJaar - i.getGeboortejaar();

				List<String> volgordeSpelers = dc.bepaalVolgordeSpelersGebruikersnaam();
				System.out.println("Spelvolgorde: " + volgordeSpelers);

				dobbelstenenRollen();

				System.out.printf("De volgnummer van de ronde is %d%n", dc.toonVolgnummerVanRonde());
				System.out.printf("De volgnummer van de ronde is %d%n", dc.toonVolgnummerVanRonde());
				System.out.printf("De volgnummer van de ronde is %d%n", dc.toonVolgnummerVanRonde());

				scoreOverzicht();
			}
		}
		}
		 catch (IllegalArgumentException e) {
			System.out.println("Fout: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Er is een fout opgetreden tijdens het starten van het spel: " + e.getMessage());
		}
	}

	private void dobbelstenenRollen() {

		for (Speler s : spelerLijst) {
			for (int i = 0; i < 3; i++) {
				System.out.printf("%s, beurt %d: %s%n", s.getGebruikersnaam(), i + 1, // zorgen dat het van de juiste
																						// speler is
						dc.toonresultaten1rolbeurt(dobbelstenenLijst));

				lijst.addAll(dobbelstenenLijst);
				for (int l = 0; l < lijst.size(); l++)
					System.out.printf("%d. %s%n", l + 1, lijst.get(l));

				// zorgen dat het van de juiste speler is s.gebruikersnaam -> spelerlijst in
				// juiste volgorde zetten?

				// meerdere nummers verwijderen tegelijkertijd:

				int extraDobbelsteenRollen = 1;

				while (extraDobbelsteenRollen == 1) {
					System.out.println("001" + extraDobbelsteenRollen);
					dobbelstenenOpnieuwGebruiken(s);
					System.out.println("002" + extraDobbelsteenRollen);
					System.out.println("Wil je nog een dobbelsteen ingeven? 1 = ja, 2 = nee");
					extraDobbelsteenRollen = Integer.parseInt(invoer.nextLine());
					System.out.println("003" + extraDobbelsteenRollen);
				}

				dobbelstenenLijst.clear();
			}
		}
	}

	private void dobbelstenenOpnieuwGebruiken(Speler s) {

		System.out.printf("%s, welke dobbelsteen wil je opnieuw rollen?%n", s.getGebruikersnaam());

		int keuze = Integer.parseInt(invoer.nextLine());
		for (int index = 0; index < lijst.size(); index++) {
			if (keuze == index + 1) {
				lijst.remove(index);
				System.out.println("test");
			}
		}

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
		try {
			while (spelerLijst.size() < 6) {
				System.out.println("Beschikbare spelers:");
				for (int i = 0; i < beschikbareSpelers.size(); i++) {
					System.out.printf("%d. %s%n", i + 1, beschikbareSpelers.get(i).getGebruikersnaam());
				}

				System.out.println("Beschikbare kleuren:");
				int index = 1;
				for (String kleur : beschikbareKleuren) {
					System.out.printf("%d. %s%n", index++, kleur);
				}

				System.out.println("Kies een speler (nummer) of druk op Enter om te stoppen:");
				String input = invoer.nextLine();
				if (input.isEmpty())
					break;

				int spelerIndex = Integer.parseInt(input) - 1;
				if (spelerIndex < 0 || spelerIndex >= beschikbareSpelers.size()) {
					System.out.println("Ongeldige keuze!");
					continue;
				}

				System.out.println("Kies een kleur (nummer):");
				int kleurIndex = Integer.parseInt(invoer.nextLine()) - 1;
				if (kleurIndex < 0 || kleurIndex >= beschikbareKleuren.size()) {
					System.out.println("Ongeldige kleur!");
					continue;
				}

				Speler gekozenSpeler = beschikbareSpelers.get(spelerIndex);
				String gekozenKleur = (String) beschikbareKleuren.toArray()[kleurIndex];
				gekozenSpeler.setKleur(SpelerKleur.valueOf(gekozenKleur));
				spelerLijst.add(gekozenSpeler);
				beschikbareKleuren.remove(gekozenKleur);
				beschikbareSpelers.remove(spelerIndex);
			}
		} catch (NumberFormatException e) {
			System.out.println("Voer een geldig nummer in!");
		} catch (Exception e) {
			System.out.println("Fout bij het kiezen van spelers: " + e.getMessage());
		}
	}
	public void spelBeurt() {
	    try {
	        System.out.println("Start van een nieuwe spelbeurt!");

	        for (Speler speler : spelerLijst) {
	            System.out.printf("%n%s is aan de beurt.%n", speler.getGebruikersnaam());

	            // Toon huidige score en beschikbare acties
	            System.out.printf("Huidige score: %d%n", speler.getScore());
	            System.out.println("Beschikbare acties:");
	            System.out.println("1. Dobbelstenen rollen");
	            System.out.println("2. Gebouwpunten toevoegen");
	            System.out.println("3. Beurt beëindigen");

	            boolean actiefBeurt = true;
	            while (actiefBeurt) {
	                System.out.println("Kies een actie (nummer):");
	                int keuze = Integer.parseInt(invoer.nextLine());

	                switch (keuze) {
	                    case 1 -> {
	                        // Dobbelstenen rollen
	                        List<gebouwKleuren> dobbelResultaten = dc.toonresultaten1rolbeurt(dobbelstenenLijst);
	                        System.out.println("Resultaten van de worp:");
	                        for (gebouwKleuren kleur : dobbelResultaten) {
	                            System.out.println(kleur.name());
	                        }
	                    }
	                    case 2 -> {
	                        // Gebouwpunten toevoegen
	                        System.out.println("Hoeveel punten wil je toevoegen?");
	                        int punten = Integer.parseInt(invoer.nextLine());
	                        speler.voegGebouwPuntenToe(punten);
	                        System.out.printf("%d punten toegevoegd. Totale gebouwpunten: %d%n", punten, speler.getGebouwPunten());
	                    }
	                    case 3 -> {
	                        // Beurt beëindigen
	                        System.out.printf("Beurt van %s beëindigd.%n", speler.getGebruikersnaam());
	                        actiefBeurt = false;
	                    }
	                    default -> System.out.println("Ongeldige keuze. Probeer opnieuw.");
	                }
	            }
	        }

	       
	    } catch (NumberFormatException e) {
	        System.out.println("Ongeldige invoer. Voer een geldig nummer in.");
	    } catch (Exception e) {
	        System.out.println("Er is een fout opgetreden tijdens de spelbeurt: " + e.getMessage());
	    }
	}
}