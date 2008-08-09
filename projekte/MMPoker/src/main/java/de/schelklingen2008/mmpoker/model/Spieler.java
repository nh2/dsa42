package de.schelklingen2008.mmpoker.model;

import java.io.Serializable;

public class Spieler implements Serializable
{

    private Spielkarte[] handblatt;
    private int          geld;
    private Blind        wasfuernBlind;
    private int          wettsumme;
    private String       name;

    private Blatt        handkarten;
    private boolean      nochDabei;

    public Spieler(String name)
    {
        setGeld(0);
        setWasfuernBlind(Blind.NICHTS);
        Spielkarte[] handblatt = new Spielkarte[2];
        wettsumme = 0;
        this.name = name;
        nochDabei = true;
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
    { // Kartenwert, Blatt, Höchste Karte
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

    public void setNochDabei(boolean nochDabei)
    {
        this.nochDabei = nochDabei;
    }

    public boolean isNochDabei()
    {
        return nochDabei;
    }

}
