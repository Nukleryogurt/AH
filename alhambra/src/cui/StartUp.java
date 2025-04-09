package cui;

import java.util.Scanner;

import domein.DomeinController;

public class StartUp {

	public static void main(String[] args) {

		Scanner invoer = new Scanner(System.in);
        new SpelApplicatie(new DomeinController(), invoer).start();
        invoer.close();
        
	}
}