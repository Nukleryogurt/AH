package domein;

import utils.BeschikbareKleuren;
import persistentie.SpelerMapper;

import java.util.List;

public class DomeinController {
    private SpelerMapper spelerMapper;
    private Spel huidigSpel;

    public DomeinController() {
        spelerMapper = new SpelerMapper();
    }

    public void registreerSpeler(String gebruikersnaam, int geboortejaar) {
        Speler speler = new Speler(gebruikersnaam, geboortejaar);
        spelerMapper.voegToe(speler);
    }

    public List<Speler> geefAlleSpelers() {
        return spelerMapper.geefAlleSpelers();
    }

    public void startNieuwSpel(List<Speler> geselecteerdeSpelers, List<BeschikbareKleuren> geselecteerdeKleuren) {
        huidigSpel = new Spel();
        for (int i = 0; i < geselecteerdeSpelers.size(); i++) {
            huidigSpel.voegSpelerToe(geselecteerdeSpelers.get(i), geselecteerdeKleuren.get(i));
        }
        huidigSpel.startSpel();
    }

    public Spel getHuidigSpel() {
        return huidigSpel;
    }
}