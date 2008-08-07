package de.schelklingen2008.mmpoker.model;

public class Spielkarte {

    private Kartentyp  kartentyp;
    private Kartenwert kartenwert;

    public Spielkarte(Kartentyp kartentyp, Kartenwert kartenwert) {

        this.kartentyp = kartentyp;
        this.kartenwert = kartenwert;
    }

    public Kartentyp getKartentyp() {
        return kartentyp;
    }

    public Kartenwert getKartenwert() {
        return kartenwert;
    }
}
