package de.schelklingen2008.doppelkopf.model;

public class Karte
{

    public final Farbe farbe;
    public final Bild  bild;

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
