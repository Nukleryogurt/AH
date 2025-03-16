package domein;

import utils.gebouwKleuren;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BonusEnStartspelerfiches {
    private List<Bonusfiches> bonusfiches;
    private gebouwKleuren startSpelerFiche;

    public BonusEnStartspelerfiches() {
        bonusfiches = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            bonusfiches.add(new Bonusfiches());
        }
        startSpelerFiche = null;
    }

    public void plaatsStartSpelerFicheWillekeurig() {
        gebouwKleuren[] gebouwen = gebouwKleuren.values();
        Random rand = new Random();
        startSpelerFiche = gebouwen[rand.nextInt(gebouwen.length)];
    }

    public List<Bonusfiches> getBonusfiches() {
        return bonusfiches;
    }

    public gebouwKleuren getStartSpelerFiche() {
        return startSpelerFiche;
    }
}