package domein;

import exceptions.GebruikersnaamInGebruikException;
import persistentie.SpelerMapper;

import java.util.List;

public class SpelerRepository {

    private final SpelerMapper mapper;

    public SpelerRepository() {
        mapper = new SpelerMapper();
    }

    public void voegToe(Speler speler) {
        if (bestaatSpeler(speler.getGebruikersnaam()))
            throw new GebruikersnaamInGebruikException();
        mapper.voegToe(speler);
    }

    public boolean bestaatSpeler(String gebruikersnaam) {
        return mapper.geefSpeler(gebruikersnaam) != null;
    }

    public List<Speler> getAllSpelers() {
        return mapper.geefAlleSpelers();
    }

    public int size() {
        return mapper.geefAlleSpelers().size();
    }
}