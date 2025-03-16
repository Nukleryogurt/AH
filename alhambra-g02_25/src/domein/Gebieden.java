package domein;

import utils.gebouwKleuren;

import java.util.HashMap;
import java.util.Map;

public class Gebieden {
    private Map<gebouwKleuren, GebouwPunten> gebouwen;

    public Gebieden() {
        gebouwen = new HashMap<>();
        for (gebouwKleuren kleur : gebouwKleuren.values()) {
            gebouwen.put(kleur, new GebouwPunten(kleur.name(), 0));
        }
    }

    public void voegPuntenToe(gebouwKleuren gebouwNaam, int punten) {
        GebouwPunten gebouw = gebouwen.get(gebouwNaam);
        if (gebouw != null) {
            gebouwen.put(gebouwNaam, new GebouwPunten(gebouwNaam.name(), gebouw.getPunten() + punten));
        }
    }

    public Map<gebouwKleuren, GebouwPunten> getGebouwen() {
        return gebouwen;
    }
}