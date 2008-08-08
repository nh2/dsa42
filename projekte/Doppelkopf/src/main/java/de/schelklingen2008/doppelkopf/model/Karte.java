package de.schelklingen2008.doppelkopf.model;

import java.io.Serializable;

public class Karte implements Serializable
{

    public Farbe farbe;
    public Bild  bild;

    public Karte()
    {
    }

    public Karte(Farbe f, Bild b)
    {
        farbe = f;
        bild = b;
    }

    @Override
    public String toString()
    {
        return farbe.toString() + " " + bild.toString();
    }

}
