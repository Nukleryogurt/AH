package domein;

import java.util.Random;

public class Bonusfiches {
    private int waarde;

    public Bonusfiches() {
        Random rand = new Random();
        this.waarde = rand.nextInt(3) + 1; // Random waarde tussen 1 en 3
    }

    public int getWaarde() {
        return waarde;
    }
}