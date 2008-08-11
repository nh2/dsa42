package de.schelklingen2008.mmpoker.model;

import java.io.Serializable;

public class Spieler implements Serializable
{

    private Spielkarte[] handblatt;
    private int          geld;
    private Blind        wasfuernBlind;
    private int          wettsumme;
    private String       name;
    private LetzteAktion letzteAktion;
    private Blatt        handkarten;

    public Spieler(String name)
    {
        setGeld(0);
        setWasfuernBlind(Blind.NICHTS);
        Spielkarte[] handblatt = new Spielkarte[2];
        wettsumme = 0;
        this.name = name;

    }

    public int getWettsumme()
    {

        return wettsumme;
    }

    public void setWettsumme(int wettbetrag)
    {
        wettsumme = wettbetrag;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public Blatt blattErmitteln(Spielkarte[] gemeinschaftsKarten)
    { // Kartenwert, Blatt, Höchste Karte
        Spielkarte[] endKarten = new Spielkarte[6];
        for (int i = 0; i < 2; i++)
        {
            endKarten[i] = getHandblatt()[i];
        }
        for (int i = 2; i < endKarten.length; i++)
        {
            endKarten[i] = gemeinschaftsKarten[i];
        }

        int[] flush = new int[3];
        for (int i = 0; i < flush.length; i++)
        {
            for (int j = 0; i < flush.length; j++)
            {
                flush[endKarten[j].getKartentyp().ordinal()]++;
            }
            if (flush[i] >= 5)
            {
                handkarten.blatt = Blaetter.FLUSH;
                setHandkarten(handkarten);
            }
        }

        return null;
    }

    public void setGeld(int geld)
    {
        this.geld = geld;
    }

    public int getGeld()
    {
        return geld;
    }

    public void setWasfuernBlind(Blind wasfuernBlind)
    {
        this.wasfuernBlind = wasfuernBlind;
    }

    public Blind getWasfuernBlind()
    {
        return wasfuernBlind;
    }

    public void setHandblatt(Spielkarte[] handblatt)
    {
        this.handblatt = handblatt;
    }

    public Spielkarte[] getHandblatt()
    {
        return handblatt;
    }

    public void setLetzteAktion(LetzteAktion letzteAktion)
    {
        this.letzteAktion = letzteAktion;
    }

    public LetzteAktion getLetzteAktion()
    {
        return letzteAktion;
    }

    public void setHandkarten(Blatt handkarten)
    {
        this.handkarten = handkarten;
    }

    public Blatt getHandkarten()
    {
        return handkarten;
    }

    @Override
    public String toString()
    {
        return name + "(" + geld + ")";
    }
}
