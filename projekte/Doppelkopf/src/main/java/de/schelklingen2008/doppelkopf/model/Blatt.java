package de.schelklingen2008.doppelkopf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Blatt implements Serializable
{

    private Set<Karte> karten;

    public Blatt()
    {
        karten = new HashSet<Karte>();
    }

    public void add(Karte k)
    {
        karten.add(k);
    }

    public void remove(Karte k)
    {
        karten.remove(k);
    }

    public int getKartenanzahl()
    {
        return karten.size();
    }

    /**
     * Erzeugt eine Kopie des Spielerblattes. Das gewährleistet, dass das Spielerblatt nicht direkt geändert
     * werden kann.
     * 
     * @return Kopie des Spielerblattes
     */
    public Set<Karte> getKarten()
    {
        return new HashSet<Karte>(karten);
    }

    public ArrayList<Karte> getKartenSortiert() // TODO Sortierregeln hinzuf�gen
    {
        ArrayList<Karte> sortierteKarten = new ArrayList<Karte>(karten);
        // Karten sortieren
        return sortierteKarten;
    }

    @Override
    public String toString()
    {
        StringBuffer ausgabe = new StringBuffer();
        for (Karte k : karten)
        {
            ausgabe.append(k.toString());
            ausgabe.append(" | ");
        }
        return ausgabe.toString();
    }
}
