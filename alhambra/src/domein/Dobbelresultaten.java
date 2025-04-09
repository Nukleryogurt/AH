package domein;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Dobbelresultaten extends Gebieden {
	private int resultaat;
	private int beloning1;
	private  List<Speler> gekozenSpelers  = new ArrayList<>();
	private int beloning2;
	private Fiche huidigefiche;
	private Spel spel;
	
	
	
	
	public Dobbelresultaten() {
		
	}
	
	
	public int getResultaat() {
		return resultaat;
	}



	public void setResultaat(int resultaat) {
		this.resultaat = resultaat;
	}



	public int getBeloning1() {
		return beloning1;
	}
	


	
//	public int geefDeEersteBeloningGebouwpuntenAanSpeler(int beloning1) {
//		gekozenSpelers.sort(Comparator.comparingInt(Speler::getScore).reversed());
//
//		
//		
//		this.beloning1 = beloning1;
//		List<GebouwPunten> gebouwpunten = new ArrayList<>();
//		gebouwpunten.addAll(gekozenSpelers.get(0));
//		gebouwpunten.addAll(gekozenSpelers.get(1));
//		return gebouwpunten.size(); // of return 2? mss is deze code wel nog bruikbaar hahaha
//		
//	
//	}
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
	
	
	public int geefBelongingToeAllesIn1(List<Speler> gekozenSpelers) {
	    gekozenSpelers.sort(Comparator.comparingInt(Speler::getScore).reversed());

	    if (spel.getZetstenen() < 0) {
	        return 0; 
	    }

	    
	    if (spel.getZetstenen() == 1) {
	        Speler enigeSpeler = gekozenSpelers.get(0);
	        enigeSpeler.voegGebouwPuntenToe(beloning1); 
	        return 1;
	    }

	    
	    Speler besteSpeler = gekozenSpelers.get(0);
	    Speler tweedeBesteSpeler = gekozenSpelers.get(1);

	 
		if (besteSpeler.equals(tweedeBesteSpeler)) {
	        besteSpeler.voegGebouwPuntenToe(beloning1); 
	        besteSpeler.voegGebouwPuntenToe(beloning2); 
	        besteSpeler.voegPuntenFicheToe(huidigefiche);
	        return 1;
	    }

	    
	    besteSpeler.voegGebouwPuntenToe(beloning1);

	    
	    tweedeBesteSpeler.voegGebouwPuntenToe(beloning2);
	    tweedeBesteSpeler.voegPuntenFicheToe(huidigefiche);

	    return 2; 
	}





}
