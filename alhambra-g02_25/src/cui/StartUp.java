package cui;

import domein.DomeinController;

public class StartUp {

	public StartUp() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		new SpelApplicatie(new DomeinController()).start();
	}
}
