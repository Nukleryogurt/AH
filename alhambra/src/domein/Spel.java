package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import utils.SpelerKleur;
import utils.gebouwKleuren;

public class Spel {

	private String startspelerfiche = "Startspeler";
	private String[] spelbordGebieden = { "Gebouwpunten", "Bonus- en startspelerfiches", "Dobbelresultaten" };
	private Speler startspeler;
	private int score = 0;
	private int gebouwstenen = 6;
	private int zetstenen = 0;
	private List<Speler> gekozenSpelers = new ArrayList<>();
	private HashSet<SpelerKleur> beschikbareKleuren = new HashSet<>();
	private int ronde = 0;
	private static final int MAX_RONDES = 3;
	private SpelerRepository spelerRepository = new SpelerRepository();

	private List<gebouwKleuren> overgeblevenKleuren = new ArrayList<>();

	public Spel() {
		for (SpelerKleur kleur : SpelerKleur.values()) {
			beschikbareKleuren.add(kleur);
		}
	}

	public Spel(List<Speler> spelerLijst) {
		this.gekozenSpelers = spelerLijst;
		for (SpelerKleur kleur : SpelerKleur.values()) {
			beschikbareKleuren.add(kleur);
		}
	}

	public void startSpel() {
		if (gekozenSpelers.size() < 3) {
			throw new IllegalArgumentException("Minimum 3 geregistreerde spelers vereist.");
		}

		setZetstenen();

	}

	public Speler bepaalStartSpeler() {
		Random random = new Random();
		startspeler = gekozenSpelers.get(random.nextInt(gekozenSpelers.size()));
		return startspeler;

	}

	public List<String> bepaalVolgordeGebruikersnamen() {
		int indexStartSpeler = gekozenSpelers.indexOf(startspeler);
		List<String> volgordeSpelers = new ArrayList<>();

		for (int i = indexStartSpeler; i < gekozenSpelers.size(); i++) {
			volgordeSpelers.add(gekozenSpelers.get(i).getGebruikersnaam());
		}

		for (int i = 0; i < indexStartSpeler; i++) {
			volgordeSpelers.add(gekozenSpelers.get(i).getGebruikersnaam());
		}

		return volgordeSpelers;
	}

	public void speelSpel() {// wordt nergens aangeroepen??
		while (ronde < MAX_RONDES) {
			vulAan();
			speelRonde();

			ronde++;
		}
		bepaalWinnaar();
		toonScoreOverzicht();

	}

	public void geefRolresultatenAlleSpelers() {
		List<gebouwKleuren> overgeblevenKleuren = new ArrayList<>();
		gebouwKleuren kleur = null;

		for (Speler s : gekozenSpelers) {
			for (int i = 0; i < 3; i++)
				rolEenKeerDeDobbelStenen(overgeblevenKleuren);
			overgeblevenGebouwenKleurenBijhouden(kleur);// kleur dat meegegeven wordt ipv null
		}

	}

	public List<gebouwKleuren> rolEenKeerDeDobbelStenen(List<gebouwKleuren> overgeblevenKleuren) {
		for (int j = 0; j < 8; j++) {
			Dobbelsteen dobbelsteen = new Dobbelsteen(); // nog veranderen naar beschikbare dobbelstenen
			overgeblevenKleuren.add(dobbelsteen.rolDobbelsteen());

			this.overgeblevenKleuren = overgeblevenKleuren;

		}
		return overgeblevenKleuren;

	}

	public List<gebouwKleuren> overgeblevenGebouwenKleurenBijhouden(gebouwKleuren dobbelsteen) { // dobbelstenen die
																									// opnieuw gerold
																									// moeten worden
		if (overgeblevenKleuren.contains(dobbelsteen)) {
			overgeblevenKleuren.remove(dobbelsteen);
		}
		return overgeblevenKleuren;
	}

	public int getZetstenen() {
		return zetstenen;
	}

	public void setZetstenen() {
		switch (gekozenSpelers.size()) {
		case 3:
			zetstenen = 5;
			break;// break weg
		case 4:
			zetstenen = 4;
			break;
		case 5:
		case 6:// default gebruiken
			zetstenen = 3;
			break;
		}
	}

	private void vulAan() {
		Random random = new Random();

		if (ronde == MAX_RONDES - 1) {
			System.out.println("Bonustokens willekeurig plaatsen.");
		} else {
			int startSpelerGebouw = random.nextInt(spelbordGebieden.length);
			System.out.println("Startspelerfiche plaatsen bij " + spelbordGebieden[startSpelerGebouw]);
		}
	}

	private void speelRonde() {

	}

	public Speler bepaalWinnaar() {
		Speler winnaar = gekozenSpelers.get(2);
		for (Speler speler : gekozenSpelers) {
			if (speler.getScore() > winnaar.getScore()) {
				winnaar = speler;
			}
		}

		winnaar.gewonnen();
		for (Speler speler : gekozenSpelers) {
			speler.gespeeld();
		}
		// System.out.println("Winnaar geregistreerd: " + winnaar.getGebruikersnaam());
		// --> enkel prints in spel applicatie
		return winnaar;
	}

	private void toonScoreOverzicht() {
		// moest in spelapplicatie dus mag weg?
		System.out.println("Scoreoverzicht:");
		for (Speler speler : gekozenSpelers) {
			System.out.println(speler.getGebruikersnaam() + " - Overwinningen: " + speler.getAantalOverwinningen()
					+ ", Gespeelde spellen: " + speler.getAantalGespeeld());
		}
	}

	public void voegSpelerToe(String gebruikersnaam, SpelerKleur kleur) {
		Speler speler = spelerRepository.getSpeler(gebruikersnaam);
		if (speler == null) 
			throw new IllegalArgumentException("Speler niet gevonden.");
		
		if (!beschikbareKleuren.contains(kleur)) {
			throw new IllegalArgumentException("Kleur niet beschikbaar.");
		}
		speler.setKleur(kleur);
		gekozenSpelers.add(speler);
		beschikbareKleuren.remove(kleur);
	}

	public List<Speler> getGekozenSpelers() {
		return gekozenSpelers;
	}

	@Override
	public String toString() {
		return "Spel{" + "startspelerfiche='" + startspelerfiche + '\'' + ", spelbordGebieden="
				+ Arrays.toString(spelbordGebieden) + ", startspeler=" + startspeler + ", score=" + score
				+ ", gebouwstenen=" + gebouwstenen + ", zetstenen=" + zetstenen + ", gekozenSpelers=" + gekozenSpelers
				+ '}';
	}
}