package de.schelklingen2008.doppelkopf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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

    public Karte getKarte(Farbe f, Bild b)
    {
        Karte gesuchteKarte = null;
        for (Karte karte : karten)
        {
            if (karte.farbe == f && karte.bild == b) gesuchteKarte = karte;
        }
        return gesuchteKarte;
    }

    public int getKartenanzahl()
    {
        return karten.size();
    }

    /**
     * Erzeugt eine Kopie des Spielerblattes. Das gew√§hrleistet, dass das Spielerblatt nicht direkt ge‰ndert
     * werden kann.
     * 
     * @return Kopie des Spielerblattes
     */
    public Set<Karte> getKarten()
    {
        return new HashSet<Karte>(karten);
    }

    public List<Karte> getKartenSortiert()
    {
        List<Karte> sortierteKarten = new ArrayList<Karte>(karten);
        Collections.sort(sortierteKarten, Collections.reverseOrder());
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
