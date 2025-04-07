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
	public int geefBelongingToeAllesIn1(List<Speler> gekozenSpelers) {
	    gekozenSpelers.sort(Comparator.comparingInt(Speler::getScore).reversed());

	    if (gekozenSpelers.isEmpty()) {
	        return 0; // Geen beloningen als er geen spelers zijn
	    }

	    // Controleer of er maar 1 zetsteen aanwezig is
	    if (gekozenSpelers.size() == 1) {
	        Speler enigeSpeler = gekozenSpelers.get(0);
	        enigeSpeler.voegGebouwPuntenToe(beloning1); // Krijgt beloning 1
	        return 1;
	    }

	    // Controleer of de 1e en 2e zetsteen van dezelfde speler zijn
	    Speler besteSpeler = gekozenSpelers.get(0);
	    Speler tweedeBesteSpeler = gekozenSpelers.get(1);

	 
		if (besteSpeler.equals(tweedeBesteSpeler)) {
	        besteSpeler.voegGebouwPuntenToe(beloning1); // Krijgt beloning 1
	        besteSpeler.voegGebouwPuntenToe(beloning2); // Krijgt beloning 2
	        besteSpeler.voegFicheToe(huidigefiche);
	        return 2;
	    }

	    // Eerste beloning toekennen
	    besteSpeler.voegGebouwPuntenToe(beloning1);

	    // Tweede beloning toekennen
	    tweedeBesteSpeler.voegGebouwPuntenToe(beloning2);
	    tweedeBesteSpeler.voegFicheToe(huidigefiche);

	    return 2; 
	}


//	public int geefDeEersteBeloningGebouwpuntenAanSpeler(int beloning1) {
//		GebouwPunten gbPt1 = new GebouwPunten();
//		GebouwPunten gbPt2 = new GebouwPunten();
//		this.beloning1 = beloning1;
//		List<GebouwPunten> gebouwpunten = new ArrayList<>();
//		gebouwpunten.add(gbPt1);
//		gebouwpunten.add(gbPt2);
//		return gebouwpunten.size(); // of return 2? mss is deze code wel nog bruikbaar hahaha
//	}
//	public int getBeloning2() {
//		return beloning2;
//	}



//	public int geefDeTweedeBeloningGebouwpuntenAanSpeler(int beloning1) {
//		GebouwPunten gbPt1 = new GebouwPunten();
//		GebouwPunten gbPt2 = new GebouwPunten();
//		this.beloning1 = beloning1;
//		List<GebouwPunten> gebouwpunten = new ArrayList<>();
//		gebouwpunten.add(gbPt1);
//		gebouwpunten.add(gbPt2);
//		return gebouwpunten.size(); // of return 2? mss is deze code wel nog bruikbaar hahaha
//	}



	public Dobbelresultaten() {

		
	}

}
