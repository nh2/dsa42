package de.schelklingen2008.mmpoker.model;

public class Spieler extends GameModel {

    private Spielkarte[] Handblatt;
    private int          Geld;
    private Blind        wasfuernblind;

    public void betfraise(int wettsumme) {
        // sendet dem Server die Summe/Aktion
    }

    public void check() {
        // sendet dem Server die Aktion
    }

    public void fold() {
        // sendet dem Server die Aktion
    }

    private enum Blind {
        BIGBLIND, SMALLBLIND, DEALER, NICHTS;
    }
}
