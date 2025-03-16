package domein;

import utils.BeschikbareKleuren;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spel {
    private List<Speler> spelers;
    private List<BeschikbareKleuren> kleuren;
    private String startSpeler;

    public Spel() {
        spelers = new ArrayList<>();
        kleuren = new ArrayList<>();
        for (BeschikbareKleuren kleur : BeschikbareKleuren.values()) {
            kleuren.add(kleur);
        }
    }

    public void voegSpelerToe(Speler speler, BeschikbareKleuren kleur) {
        if (spelers.size() >= 6) {
            throw new IllegalArgumentException("Het spel kan maximaal 6 spelers hebben.");
        }
        if (!kleuren.contains(kleur)) {
            throw new IllegalArgumentException("Ongeldige kleur geselecteerd.");
        }
        spelers.add(speler);
        kleuren.remove(kleur);
    }

    public void startSpel() {
        if (spelers.size() < 3) {
            throw new IllegalArgumentException("Het spel moet minstens 3 spelers hebben.");
        }
        Random rand = new Random();
        startSpeler = spelers.get(rand.nextInt(spelers.size())).getGebruikersnaam();
    }

    public List<Speler> getSpelers() {
        return spelers;
    }

    public String getStartSpeler() {
        return startSpeler;
    }
}