package de.schelklingen2008.doppelkopf.model;

import java.util.ArrayList;

public class SpielerListe extends ArrayList<Spieler>
{

    private static final long serialVersionUID = 1L;
    private int               anDerReihe;

    public Spieler getAnDerReihe()
    {
        return get(anDerReihe);
    }

    public Spieler getNext()
    {
        return get(nextAnDerReihe(anDerReihe));
    }

    public Spieler Next()
    {
        Spieler nächster = get(nextAnDerReihe(anDerReihe));
        rotieren();
        return nächster;
    }

    public void rotieren()
    {
        anDerReihe = nextAnDerReihe(anDerReihe);
    }

    private int nextAnDerReihe(int aktuell)
    {
        if (aktuell + 1 < size())
            return aktuell + 1;
        else
            return 0;
    }
}