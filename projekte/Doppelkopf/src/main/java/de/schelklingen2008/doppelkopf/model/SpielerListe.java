package de.schelklingen2008.doppelkopf.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SpielerListe extends ArrayList<Spieler> implements Serializable
{

    private static final long serialVersionUID = 1L;
    private int               anDerReihe;

    public SpielerListe()
    {
    }

    public Spieler getAnDerReihe()
    {
        return get(anDerReihe);
    }

    public Spieler getNext()
    {
        return get(nextAnDerReihe(anDerReihe));
    }

    public Spieler next()
    {
        Spieler naechster = get(nextAnDerReihe(anDerReihe));
        rotieren();
        return naechster;
    }

    public void rotieren()
    {
        anDerReihe = nextAnDerReihe(anDerReihe);
    }

    public Spieler getSpieler(String spielerName)
    {
        for (Spieler p : this)
        {
            if (p.getName().equals(spielerName)) return p;
        }

        return null; // TODO Exception
    }

    private int nextAnDerReihe(int aktuell)
    {
        if (aktuell + 1 < size())
            return aktuell + 1;
        else
            return 0;
    }
}