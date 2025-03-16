package domein;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Spel {

	// TODO: mss klasses voor spelbordgebieden en fiches maken
	// TODO: opnieuw verdelen onder de verschillende klassen (marte heeft deze
	// geschreven)
	// kleuren van zijden van de dobbelstenen
	private String[] dobbelsteenKleuren = { "blauw", "rood", "bruin", "grijs", "groen", "paars" };// m:is enum

	private String startspelerfiche = "Startspeler";
	// Het spelbord bestaande uit 3 gebieden
	private String[] spelbordGebieden = { "Gebouwpunten", "Bonus- en startspelerfiches", "Dobbelresultaten" };

	// Willekeurige speler als startspeler
	private Speler Startspeler = new Speler("Speler1", 2000);

	// Per speler: Score 0, 6 gebouwstenen, Zetstenen
	private int score = 0;
	private int gebouwstenen = 6;
	private int zetstenen = 0;

	// Lijst met geregistreerde, beschikbare spelers
	private ArrayList<Speler> spelerLijst = new ArrayList<>(); // TODO van marte: spelerlijst = spelerrepository -->
																// vervangen
	private ArrayList<Speler> gekozenSpelers = new ArrayList<>();

	// Welke kleuren nog niet gekozen zijn
	private HashSet<String> beschikbareKleuren = new HashSet<>();

	public Spel() {
		// TODO: geef geregistreerde spelers mee. Waar / op welk moment worden spelers
		// geregistreerd?
		// for (int i = 0; i < bonusfiches.length; i++) {
		// bonusfiches[i] = (int) (Math.random() * 3) + 1;
		// } // marte: bonusfiches worden gemaakt door constructor van bonusfiches -->
		// moet worden aangepast want bonusfiches zijn hier geen attribuut meer dus heb
		// ff in commentaar gezet :)

		beschikbareKleuren.addAll(Speler.BESCHIKBARE_KLEUREN);

		// Zetstenen afhankelijk van het aantal spelers
	}

	public void startSpel() {// bedenking van marte: moeten alle println niet in package cui? weet niet of
								// het juist in mijn hoofd zit en kan dus ook fout zijn :)
		assert spelerLijst.size() >= 3; // Minimum 3 geregistreerde spelers nodig
		System.out.println("Ongekozen, beschikbare spelers:");
		for (Speler s : spelerLijst) {
			// Ongekozen spelers hebben nog geen kleur.
			if (s.getKleur().equals(null)) {
				System.out.println(s);
			}
		}
		System.out.println("Ongekozen, beschikbare kleuren:");
		beschikbareKleuren.forEach(k -> System.out.println(k));

		System.out.println("Voeg minstens 3 nieuwe spelers toe. Bevestig met een lege gebruikersnaam.");
		System.out.println("Geef een gebruikersnaam in:");
		Scanner scanner = new Scanner(System.in);
		String gebruikersnaam = scanner.nextLine();
		do {
			int geboortejaar = scanner.nextInt();
			gekozenSpelers.add(new Speler(gebruikersnaam, geboortejaar));
			gebruikersnaam = scanner.nextLine();
		} while ((gekozenSpelers.size() < 3 && gekozenSpelers.size() < 6) || !gebruikersnaam.equals("")); // TODO fix
																											// logica,
																											// ik heb
																											// hoofdpijn.

		// We kennen aantal zetstenen pas na aantal aanwezige spelers
		switch (gekozenSpelers.size()) {
		case 3:
			zetstenen = 5;
			break;
		case 4:
			zetstenen = 4;
			break;
		case 5:
		case 6:
			zetstenen = 3;
			break;
		}
		// TODO: 6. nieuw spel registreren
		System.out.println(this);
	}
}
