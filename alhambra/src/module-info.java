module alhambra_g02_25 {
	exports persistentie;
	exports zorgenVoorLater;
	exports cui;
	exports utils;
	exports domein;
	exports testen;
	exports exceptions;

	requires java.sql;
	requires org.junit.jupiter.api;
	
	requires javafx.controls;
	requires javafx.fxml;
	
	// main is de naam van de package die de main-methode bevat
	opens gui to javafx.graphics;
	// gui is de naam van de package die de lay-outs bevat
	//opens gui to javafx.fxml;
	
}