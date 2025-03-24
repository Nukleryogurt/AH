package domein;

import java.util.List;

public class DomeinController {

	private final SpelerRepository spelerRepository;

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
		Spel spel1 = new Spel(spelerLijst);
		spel1.startSpel();

	}

	public void speelRonde() {
		Ronde ronde1 = new Ronde();
		ronde1.speelEenRonde();
	}

}
