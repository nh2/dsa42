package de.schelklingen2008.mmpoker.model;

public class Spielkarte extends GameModel {

    private Kartentyp  kartentyp;
    private Kartenwert kartenwert;

    public Spielkarte(Kartentyp kartentyp, Kartenwert kartenwert) {
        super();
        kartentyp = kartentyp;
        kartenwert = kartenwert;
    }

    public Kartentyp getKartentyp() {
        return kartentyp;
    }

    public Kartenwert getKartenwert() {
        return kartenwert;
    }
}
