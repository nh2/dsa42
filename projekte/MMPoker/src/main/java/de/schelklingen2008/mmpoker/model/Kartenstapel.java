package de.schelklingen2008.mmpoker.model;


public class Kartenstapel extends GameModel {

    // private Spielkarte zufallsKarte() {
    // // randomize;
    // // result = Karten(randomzahl)
    // return;
    // }

    public void kartenSetzen() {
        for (Kartenwert kartenwert : Kartenwert.values()) {
            for (Kartentyp kartentyp : Kartentyp.values()) {
                new Spielkarte(kartentyp, kartenwert);
            }
        }

    }
}
