package cui;

import java.util.Scanner;

public class Menu {
    private final Scanner invoer;
    private final String titel;
    private final String[] keuzes;

    public Menu(String titel, String[] keuzes, Scanner invoer) {
        this.titel = titel;
        this.keuzes = keuzes;
        this.invoer = invoer;
    }

    public int geefKeuze() {
        int keuze = -1;
        boolean keuzeOK = false;

        do {
            toonMenu();
            String input = invoer.nextLine();

            try {
                keuze = Integer.parseInt(input);
                keuzeOK = keuze >= 1 && keuze <= keuzes.length;
                if (!keuzeOK) {
                    System.out.printf("Fout: Kies een getal tussen 1 en %d.%n", keuzes.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige invoer! Voer een geheel getal in.");
            }
        } while (!keuzeOK);

        return keuze;
    }

    private void toonMenu() {
        System.out.println("\n" + titel);
        System.out.println("=".repeat(titel.length()));
        for (int i = 0; i < keuzes.length; i++) {
            System.out.printf("%d. %s%n", i + 1, keuzes[i]);
        }
        System.out.print("Voer je keuze in: ");
    }
}
