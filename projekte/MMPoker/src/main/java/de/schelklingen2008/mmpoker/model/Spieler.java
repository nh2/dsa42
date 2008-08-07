package de.schelklingen2008.mmpoker.model;

public class Spieler {

    private Spielkarte[] handblatt;
    private int          geld;
    private Blind        wasfuernBlind;
    private int          wettsumme;
    private String       name;

    public Spieler() {
        geld = 0;
        wasfuernBlind = Blind.NICHTS;
        Spielkarte[] handblatt = new Spielkarte[2];
        wettsumme = 0;
        name = "DefaultSpieler";
    }

    public void betfraise(int wettsumme) {

        // sendet dem Server die Summe/Aktion
    }

    public void check() {
        // sendet dem Server die Aktion
    }

    public void fold() {
        // sendet dem Server die Aktion
    }

    public int getWettsumme() {
        return wettsumme;
    }

    public void setWettsumme(int wettbetrag) {
        wettsumme = wettbetrag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Blatt blattErmitteln() { // Kartenwert, Blatt, Höchste Karte
        return null;
    }

}
