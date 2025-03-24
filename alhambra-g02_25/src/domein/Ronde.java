package domein;

import java.util.ArrayList;
import java.util.List;

public class Ronde {

	private int aantalBonusfiches;

	List<Bonusfiche> bonusfiches = new ArrayList<>();

	public Ronde() {

	}

	public int getAantalBonusfiches() {
		return aantalBonusfiches;
	}

	public void setAantalBonusfiches(int aantalBonusfiches) {
		this.aantalBonusfiches = aantalBonusfiches;
	}

	public void bepaalPlaatsBonusfiche() {
		if (aantalBonusfiches <= 5) {
			Bonusfiche b = new Bonusfiche();
			// TODO: nog zorgen da die op een random plaats komen
			bonusfiches.add(b);

		}
	}

	public void speelEenRonde() {
		bepaalPlaatsBonusfiche();
	}

}
