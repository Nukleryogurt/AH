package domein;

import java.util.ArrayList;
import java.util.List;

public class Dobbelresultaten extends Gebieden {
	private int resultaat;
	private int beloning1;
	
	private int beloning2;
	
	public int getResultaat() {
		return resultaat;
	}



	public void setResultaat(int resultaat) {
		this.resultaat = resultaat;
	}



	public int getBeloning1() {
		return beloning1;
	}



	public int geefDeEersteBeloningGebouwpuntenAanSpeler(int beloning1) {
		GebouwPunten gbPt1 = new GebouwPunten();
		GebouwPunten gbPt2 = new GebouwPunten();
		this.beloning1 = beloning1;
		List<GebouwPunten> gebouwpunten = new ArrayList<>();
		gebouwpunten.add(gbPt1);
		gebouwpunten.add(gbPt2);
		return gebouwpunten.size(); // of return 2? mss is deze code wel nog bruikbaar hahaha
	}
	public int getBeloning2() {
		return beloning2;
	}



	public int geefDeTweedeBeloningGebouwpuntenAanSpeler(int beloning1) {
		GebouwPunten gbPt1 = new GebouwPunten();
		GebouwPunten gbPt2 = new GebouwPunten();
		this.beloning1 = beloning1;
		List<GebouwPunten> gebouwpunten = new ArrayList<>();
		gebouwpunten.add(gbPt1);
		gebouwpunten.add(gbPt2);
		return gebouwpunten.size(); // of return 2? mss is deze code wel nog bruikbaar hahaha
	}



	public Dobbelresultaten() {

		
	}

}
