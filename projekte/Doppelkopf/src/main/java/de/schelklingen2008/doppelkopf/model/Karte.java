package de.schelklingen2008.doppelkopf.model;

import java.io.Serializable;
import java.util.List;

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
        // Herz-10
        if (farbe == Farbe.Herz && bild == Bild.Zehn) return 1;
        if (other.farbe == Farbe.Herz && other.bild == Bild.Zehn) return -1;

        // Damen
        if (bild == Bild.Dame ^ other.bild == Bild.Dame)
        {
            if (bild == Bild.Dame) return 1;
            if (other.bild == Bild.Dame) return -1;
        }

        // Buben
        if (bild == Bild.Bube ^ other.bild == Bild.Bube)
        {
            if (bild == Bild.Bube) return 1;
            if (other.bild == Bild.Bube) return -1;
        }

        // Normal-Trumpf
        if (isTrumpf() ^ other.isTrumpf())
        {
            if (isTrumpf()) return 1;
            if (other.isTrumpf()) return -1;
        }

        // Standard
        if (farbe != other.farbe)
        {
            return -farbe.compareTo(other.farbe);
        }
        return -bild.compareTo(other.bild);
    }

    public boolean sticht(Karte other)
    {
        if (isTrumpf())
        {
            if (!other.isTrumpf()) return true;
            if (compareTo(other) > 0) return true;
        }
        if (farbe == other.farbe) if (compareTo(other) > 0) return true;
        return false;
    }

    public static Karte gibtHoechsteAusStapel(List<Karte> stapel)
    {
        int hoechste = 0;
        for (int i = 0; i < 3; i++)
        {
            if (stapel.get(i + 1).sticht(stapel.get(hoechste))) hoechste = i + 1;
        }
        Karte k = stapel.get(hoechste);
        return stapel.get(hoechste);
    }

    // public boolean bedient(Karte other)
    // {
    // if()
    // }
}
