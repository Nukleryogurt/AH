package domein;

import utils.gebouwKleuren;

import java.util.HashMap;
import java.util.Map;

public class Dobbelresultaten {
    private Map<gebouwKleuren, Integer> resultaten;

    public Dobbelresultaten() {
        resultaten = new HashMap<>();
        for (gebouwKleuren kleur : gebouwKleuren.values()) {
            resultaten.put(kleur, 0);
        }
    }

    public void voegResultaatToe(gebouwKleuren gebouwNaam, int aantal) {
        if (resultaten.containsKey(gebouwNaam)) {
            resultaten.put(gebouwNaam, resultaten.get(gebouwNaam) + aantal);
        }
    }

    public Map<gebouwKleuren, Integer> getResultaten() {
        return resultaten;
    }
}