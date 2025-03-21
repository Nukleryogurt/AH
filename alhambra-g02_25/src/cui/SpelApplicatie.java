package cui;

import java.util.Scanner;

import domein.DomeinController;

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
			case 2 -> dc.startNieuwSpel();
			}
		} while (keuze != keuzesOverzichtsMenu.length);

		System.out.println("Tot een volgende keer!");

	}

	private void voegNieuweSpelerToe() {
		System.out.println("Geef de gebruikersnaam in");// TODO exceptions catchen
		String gebruikersnaam = invoer.nextLine();
		System.out.println("Geef je geboortejaar in:");// TODO exceptions catchen
		int geboortejaar = Integer.parseInt(invoer.nextLine());
		dc.registreerSpeler(gebruikersnaam, geboortejaar);

		System.out.println("De speler is geregistreerd!");
	}

}
