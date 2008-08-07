package de.schelklingen2008.mmpoker.model;

import java.io.Serializable;

public class Spieler implements Serializable
{

    private Spielkarte[] handblatt;
    private int          geld;
    private Blind        wasfuernBlind;
    private int          wettsumme;
    private String       name;

    public Spieler()
    {
        setGeld(0);
        wasfuernBlind = Blind.NICHTS;
        Spielkarte[] handblatt = new Spielkarte[2];
        wettsumme = 0;
        name = "DefaultSpieler";
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

    public Blatt blattErmitteln()
    { // Kartenwert, Blatt, H�chste Karte
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

}
