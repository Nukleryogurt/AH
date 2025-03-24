package domein;

public class Bonusfiche {

	private int waarde;

	public Bonusfiche() {
	}

	public int getWaarde() {
		return waarde;
	}

	public void setWaarde(int waarde) {
		double randomGetal = Math.random();
		if (randomGetal <= 0.33) {
			waarde = 1;
		} else if (randomGetal <= 0.66) {
			waarde = 2;
		} else
			waarde = 3;

		this.waarde = waarde;
	}

}
