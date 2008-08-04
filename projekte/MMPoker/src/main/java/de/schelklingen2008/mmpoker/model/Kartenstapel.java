package de.schelklingen2008.mmpoker.model;

public class Kartenstapel extends GameModel {

    // Set Karten = new Set;
    private Spielkarte zufallsKarte() {
        // randomize;
        // result = Karten(randomzahl)

    }

    public void kartenSetzen() {
        for (Kartenwert kartenwert : Kartenwert.values()) {

        }

        for (int i = 0; i < 13; i++) {

            for (int j = 0; j < 3; j++) {
                new Spielkarte(i, j);
            }
        }

    }
}
