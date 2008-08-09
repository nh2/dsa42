package de.schelklingen2008.doppelkopf.model;

import java.io.Serializable;

public class Karte implements Serializable, Comparable<Karte>
{

    public Farbe farbe;
    public Bild  bild;
    int          hierarchie;

    public Karte()
    {
    }

    public Karte(Farbe f, Bild b)
    {
        farbe = f;
        bild = b;
    }

    public boolean isTrumpf()
    {
        if (farbe == Farbe.Karo) return true;
        if (bild == Bild.Dame) return true;
        if (bild == Bild.Bube) return true;
        if (farbe == Farbe.Herz && bild == Bild.Zehn) return true;
        return false;
    }

    @Override
    public String toString()
    {
        return farbe.toString() + " " + bild.toString();
    }

    public int compareTo(Karte other)
    {
        // TODO: Trümpfe richtig sortieren
        if (isTrumpf()) return -1;
        if (other.isTrumpf()) return 1;
        if (farbe != other.farbe)
        {

            return farbe.compareTo(other.farbe);
        }
        return bild.compareTo(other.bild);

    }

}
