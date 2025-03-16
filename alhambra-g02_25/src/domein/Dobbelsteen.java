package domein;

import utils.gebouwKleuren;

import java.util.Random;

public class Dobbelsteen {
    private gebouwKleuren huidigeKleur;

    public Dobbelsteen() {
        werp();
    }

    public void werp() {
        gebouwKleuren[] kleuren = gebouwKleuren.values();
        Random rand = new Random();
        huidigeKleur = kleuren[rand.nextInt(kleuren.length)];
    }

    public gebouwKleuren getHuidigeKleur() {
        return huidigeKleur;
    }
}