package de.schelklingen2008.mmpoker.model;

import java.io.Serializable;

public class Spielkarte implements Serializable
{

    private Kartentyp  kartentyp;
    private Kartenwert kartenwert;

    public Spielkarte(Kartentyp kartentyp, Kartenwert kartenwert)
    {

        this.kartentyp = kartentyp;
        this.kartenwert = kartenwert;
    }

    public Kartentyp getKartentyp()
    {
        return kartentyp;
    }

    public Kartenwert getKartenwert()
    {
        return kartenwert;
    }
}
