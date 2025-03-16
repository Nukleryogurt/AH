package domein;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Spel {

    // Enum for dice colors
    public enum DobbelsteenKleur {
        BLAUW, ROOD, BRUIN, GRIJS, GROEN, PAARS
    }

    private String startspelerfiche = "Startspeler";
    // The game board consisting of 3 areas
    private String[] spelbordGebieden = { "Gebouwpunten", "Bonus- en startspelerfiches", "Dobbelresultaten" };

    // Random player as start player
    private Speler startspeler = new Speler("Speler1", 2000);

    // Per player: Score 0, 6 building stones, Action stones
    private int score = 0;
    private int gebouwstenen = 6;
    private int zetstenen = 0;

    // List of registered, available players
    private SpelerRepository spelerRepository = new SpelerRepository();
    private List<Speler> gekozenSpelers = new ArrayList<>();

    // Colors that have not yet been chosen
    private HashSet<String> beschikbareKleuren = new HashSet<>();

    // Game rounds
    private int ronde = 0;
    private static final int MAX_RONDES = 3;

    public Spel() {
        beschikbareKleuren.addAll(Speler.BESCHIKBARE_KLEUREN);
    }

    public void startSpel() {
        if (spelerRepository.size() < 3) {
            throw new IllegalArgumentException("Minimum 3 registered players required.");
        }

        // Display available players and colors
        // (Should be handled by a user interface class)
        for (Speler s : spelerRepository.getAllSpelers()) {
            if (s.getKleur() == null) {
                System.out.println(s);
            }
        }
        beschikbareKleuren.forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        while (gekozenSpelers.size() < 3 || gekozenSpelers.size() < 6) {
            System.out.println("Enter a username (or leave blank to finish):");
            String gebruikersnaam = scanner.nextLine();
            if (gebruikersnaam.isBlank()) {
                break;
            }
            System.out.println("Enter birth year:");
            int geboortejaar = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            voegSpelerToe(gebruikersnaam, geboortejaar);
        }

        if (gekozenSpelers.size() < 3) {
            throw new IllegalArgumentException("At least 3 players are required to start the game.");
        }

        // Determine number of action stones based on number of players
        switch (gekozenSpelers.size()) {
            case 3:
                zetstenen = 5;
                break;
            case 4:
                zetstenen = 4;
                break;
            case 5:
            case 6:
                zetstenen = 3;
                break;
        }

        // Register new game (TODO)
        System.out.println(this); // (Should be handled by a user interface class)
    }

    public void speelSpel() {
        while (ronde < MAX_RONDES) {
            vulAan();
            speelRonde();
            ronde++;
        }
        registreerWinnaar();
        toonScoreOverzicht();
    }

    private void vulAan() {
        Random random = new Random();
        if (ronde == MAX_RONDES - 1) {
            // Last round: place bonus tokens randomly
            System.out.println("Placing bonus tokens randomly.");
        } else {
            // Other rounds: place start player token and bonus tokens
            int startSpelerGebouw = random.nextInt(spelbordGebieden.length);
            System.out.println("Placing start player token at " + spelbordGebieden[startSpelerGebouw]);
        }
    }

    private void speelRonde() {
        System.out.println("Playing round " + (ronde + 1));
        // Simulate playing a round
        // (Should be handled by a user interface class)
    }

    private void registreerWinnaar() {
        Speler winnaar = gekozenSpelers.get(0);
        for (Speler speler : gekozenSpelers) {
            if (speler.getScore() > winnaar.getScore()) {
                winnaar = speler;
            }
        }
        winnaar.gewonnen();
        for (Speler speler : gekozenSpelers) {
            speler.gespeeld();
        }
        System.out.println("Winner registered: " + winnaar.getGebruikersnaam());
    }

    private void toonScoreOverzicht() {
        System.out.println("Score overview:");
        for (Speler speler : gekozenSpelers) {
            System.out.println(speler.getGebruikersnaam() + " - Wins: " + speler.getAantalOverwinningen() + ", Games played: " + speler.getAantalGespeeld());
        }
    }

    public void registreerSpeler(String gebruikersnaam, int geboortejaar) {
        if (gebruikersnaam == null || gebruikersnaam.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank.");
        }
        if (geboortejaar > Year.now().getValue() || geboortejaar < Year.now().getValue() - 100) {
            throw new IllegalArgumentException("Invalid birth year.");
        }
        spelerRepository.voegToe(new Speler(gebruikersnaam, geboortejaar));
    }

    public void voegSpelerToe(String gebruikersnaam, int geboortejaar) {
        Speler speler = new Speler(gebruikersnaam, geboortejaar);
        if (!beschikbareKleuren.isEmpty()) {
            System.out.println("Choose a color:");
            String kleur = new Scanner(System.in).nextLine();
            if (beschikbareKleuren.contains(kleur)) {
                speler.setKleur(kleur);
                gekozenSpelers.add(speler);
                beschikbareKleuren.remove(kleur);
            } else {
                throw new IllegalArgumentException("Color not available.");
            }
        } else {
            throw new IllegalArgumentException("No colors available.");
        }
    }

    public List<Speler> getGekozenSpelers() {
        return gekozenSpelers;
    }

    @Override
    public String toString() {
        return "Spel{" +
                "startspelerfiche='" + startspelerfiche + '\'' +
                ", spelbordGebieden=" + Arrays.toString(spelbordGebieden) +
                ", startspeler=" + startspeler +
                ", score=" + score +
                ", gebouwstenen=" + gebouwstenen +
                ", zetstenen=" + zetstenen +
                ", gekozenSpelers=" + gekozenSpelers +
                '}';
    }
}