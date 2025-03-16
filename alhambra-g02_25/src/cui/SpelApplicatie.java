package cui;

import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import domein.Speler;
import exceptions.GebruikersnaamInGebruikException;
import utils.BeschikbareKleuren;

public class SpelApplicatie {
    private final Scanner invoer = new Scanner(System.in);
    private final String[] keuzesOverzichtsMenu = { "Registreer nieuwe speler", "Start nieuw spel", "Afsluiten" };
    private final Menu overzichtsMenu;

    private final DomeinController dc;

    public SpelApplicatie(DomeinController dc) {
        this.dc = dc;
        this.overzichtsMenu = new Menu("Menu", keuzesOverzichtsMenu);
    }

    public void start() {
        int keuze = -1;
        do {
            keuze = overzichtsMenu.geefKeuze();
            switch (keuze) {
                case 1 -> voegNieuweSpelerToe();
                case 2 -> startNieuwSpel();
            }
        } while (keuze != keuzesOverzichtsMenu.length);

        System.out.println("Tot een volgende keer!");
    }

    private void voegNieuweSpelerToe() {
        try {
            System.out.println("Geef de gebruikersnaam in:");
            String gebruikersnaam = invoer.nextLine();

            System.out.println("Geef je geboortejaar in:");
            int geboortejaar = Integer.parseInt(invoer.nextLine());

            dc.registreerSpeler(gebruikersnaam, geboortejaar);

            System.out.println("De speler is geregistreerd!");
        } catch (NumberFormatException e) {
            System.out.println("Ongeldig geboortejaar. Gelieve een geldig getal in te voeren.");
        } catch (GebruikersnaamInGebruikException e) {
            System.out.println("De gebruikersnaam is al in gebruik. Kies een andere naam.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void startNieuwSpel() {
        try {
            dc.startNieuwSpel(List<Speler> geselecteerdeSpelers, List<BeschikbareKleuren> geselecteerdeKleuren);
            System.out.println("Een nieuw spel is gestart!");
            // Add any additional logic for starting a new game here
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}