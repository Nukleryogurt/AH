package domein;

import java.util.ArrayList;
import java.util.List;

public class LaatsteRonde extends Ronde {

	private int aantalBonusfiches;
	private Bonusfiche startfiche; // moet andere klasse zijn, weet niet goed hoe

	List<Bonusfiche> fiches = new ArrayList<>();

	public LaatsteRonde() {

	}

	public int getAantalBonusfiches() {
		return aantalBonusfiches;
	}

	public void setAantalBonusfiches(int aantalBonusfiches) {
		this.aantalBonusfiches = aantalBonusfiches;
	}

	public void bepaalPlaatsFiche() {
		if (aantalBonusfiches == 0) {
			bonusfiches.add(startfiche);
		} else if (aantalBonusfiches <= 4) {
			Bonusfiche b = new Bonusfiche();
			// TODO: nog zorgen da die op een random plaats komen
			bonusfiches.add(b);

		}
	}

	public void speelEenRonde() {
		bepaalPlaatsFiche();
	}

}
