package domein;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GebouwPunten extends Gebieden {

	
	private Ronde ronde;
	public GebouwPunten() {

		


	}

	public void geefGebouwPuntenVolgendsWaardering(List<Speler> gekozenSpelers) {
		Collections.sort(gekozenSpelers, Comparator.comparingInt(Speler::getGebouwPunten).reversed());
		
        
        switch (ronde.getVolgnummer()) {
            case 1:
                if (!gekozenSpelers.isEmpty()) {
                    gekozenSpelers.get(0).voegGebouwPuntenToe(ronde.getVolgnummer());; 
                }
                
            case 2:
                if (gekozenSpelers.size() > 0) {
                    gekozenSpelers.get(0).voegGebouwPuntenToe(ronde.getVolgnummer()); 
                }
                if (gekozenSpelers.size() > 1) {
                    gekozenSpelers.get(1).voegGebouwPuntenToe(ronde.getVolgnummer()); 
                }
                
            case 3:
                if (gekozenSpelers.size() > 0) {
                    gekozenSpelers.get(0).voegGebouwPuntenToe(ronde.getVolgnummer());; 
                }
                if (gekozenSpelers.size() > 1) {
                }
                if (gekozenSpelers.size() > 2) {
                    gekozenSpelers.get(2).voegGebouwPuntenToe(ronde.getVolgnummer()); 
                }
                
            default:
      
        }
        
        for (Speler speler : gekozenSpelers) {
			speler.voegPuntenFicheToe(null);
		}
        
	}
}
