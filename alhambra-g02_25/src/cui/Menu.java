package cui;

import java.util.Scanner;

public class Menu {

	private static final Scanner invoer = new Scanner(System.in);
	private final String titel;
	private final String[] keuzes;

	public Menu(String titel, String[] keuzes) {
		this.titel = titel;
		this.keuzes = keuzes;
	}

	public int geefKeuze() {
		int keuze = 0;
		boolean keuzeOK = false;
		do {
			try {
				toonMenu();
				keuze = Integer.parseInt(invoer.nextLine());// nextline ipv nextInt, nextline wordt ingelezen in een
															// string, parseInt maakt er een getal van
				keuzeOK = keuze >= 1 && keuze <= keuzes.length;
				if (!keuzeOK)
					System.out.printf("Je keuze moet tussen 1 en %d liggen!%n", keuzes.length);
			} catch (NumberFormatException e) {
				System.out.printf("Gelieve een geheel getal tussen 1 en %d getal in te voeren! probeer opnieuw.... %n",
						keuzes.length);
			}
		} while (!keuzeOK);
		return keuze;
	}

	private void toonMenu() {
		System.out.println(titel);
		System.out.printf("%s%n", "=".repeat(titel.length()));
		for (int i = 0; i < keuzes.length; i++) {
			System.out.printf("%d. %s%n", i + 1, keuzes[i]);
		}
		System.out.print("Voer je keuze in: ");
	}
}
