package domein;

import java.util.List;

import utils.gebouwKleuren;

public class DomeinController {

	private final SpelerRepository spelerRepository;

	private Spel spel1;
	private Ronde ronde = new Ronde();

	public DomeinController() {
		spelerRepository = new SpelerRepository();
	}

	public void registreerSpeler(String gebruikersnaam, int geboortejaar) {
		Speler nieuweSpeler = new Speler(gebruikersnaam, geboortejaar);
		spelerRepository.voegToe(nieuweSpeler);
	}

	public List<Speler> geefAlleBeschikbareSpelers() {
		return spelerRepository.geefAlleBeschikbareSpeler();
	}

	public Speler getSpeler(String gebruikersnaam) {
		return spelerRepository.getSpeler(gebruikersnaam);
	}

	// TODO:
	public void startNieuwSpel(List<Speler> spelerLijst) {
		this.spel1 = new Spel(spelerLijst);
		this.spel1.startSpel();

		BonusEnStartspelerfiches gebiedBessf = new BonusEnStartspelerfiches();
		gebiedBessf.speelRonde();
	}

	public List<Bonusfiche> geefPlaatsBonusfichesEersteRondes() {
		Ronde r = new Ronde();
		return r.bepaalPlaatsBonusfiche();
	}

	public List<Bonusfiche> geefPlaatsBonusfichesLaatsteRonde() {
		LaatsteRonde lr = new LaatsteRonde();
		return lr.bepaalPlaatsFiche();
	}

	public Speler geefWinnaar() {
		return spel1.bepaalWinnaar();

	}

	public Speler bepaalStartSpeler() {
		return spel1.bepaalStartSpeler();
	}

	public List<String> bepaalVolgordeSpelersGebruikersnaam() {

		return spel1.bepaalVolgordeGebruikersnamen();

	}

	public int toonVolgnummerVanRonde() {
		return ronde.getVolgnummer();
	}

	public List<gebouwKleuren> toonresultaten1rolbeurt(List<gebouwKleuren> dobbelstenenLijst) {
		return spel1.rolEenKeerDeDobbelStenen(dobbelstenenLijst);
	}
}
