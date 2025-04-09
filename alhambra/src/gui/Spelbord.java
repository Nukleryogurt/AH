package gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.gebouwKleuren;

public class Spelbord extends BorderPane {

	public Spelbord() {
		super();
		
		//Vakjes 0-99 aan de rand
		setLeft(new VBox());
		setRight(new VBox());
		setTop(new HBox());;
		setBottom(new HBox());
		
		//Kolommen voor de spelers
		HBox kolomBox = new HBox();
		for (gebouwKleuren sk : gebouwKleuren.values()) {
			kolomBox.getChildren().add(new GebouwKolom(sk));
		}
		setCenter(kolomBox);
	}
	
}
