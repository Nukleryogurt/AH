package domein;

public class GebouwPunten {
    private String naam;
    private int punten;

    public GebouwPunten(String naam, int punten) {
        this.naam = naam;
        this.punten = punten;
    }

    public String getNaam() {
        return naam;
    }

    public int getPunten() {
        return punten;
    }
}