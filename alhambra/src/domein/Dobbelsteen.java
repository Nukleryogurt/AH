package domein;

import java.util.Random;

import utils.gebouwKleuren;

public class Dobbelsteen {

	private String dobbelUitkomst;

	public Dobbelsteen() {

	}

	public String getDobbelUitkomst() {
		return dobbelUitkomst;
	}

	public gebouwKleuren rolDobbelsteen() {

		int random = new Random().nextInt(gebouwKleuren.values().length);
		return gebouwKleuren.values()[random];
	}

}
