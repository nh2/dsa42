package de.schelklingen2008.mmpoker.model;

public class Spielkarte extends GameModel {

    private int        kartennummer;
    private Kartentyp  kartentyp;
    private Kartenwert kartenwert;

    public Spielkarte(int kartennummer, Kartentyp kartentyp, Kartenwert kartenwert) {
        super();
        kartennummer = kartennummer;
        kartentyp = kartentyp;
        kartenwert = kartenwert;
    }

    public Kartentyp getKartentyp() {
        return kartentyp;
    }

    public Kartenwert getKartenwert() {
        return;
    }
}
