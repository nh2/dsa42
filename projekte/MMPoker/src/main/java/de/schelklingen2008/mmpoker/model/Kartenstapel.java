package de.schelklingen2008.mmpoker.model;

import java.util.HashSet;
import java.util.Set;

public class Kartenstapel extends GameModel {

    Set<Spielkarte> kartenstapel = new HashSet<Spielkarte>();

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
