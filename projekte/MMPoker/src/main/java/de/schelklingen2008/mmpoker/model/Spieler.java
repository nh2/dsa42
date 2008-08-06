package de.schelklingen2008.mmpoker.model;

public class Spieler extends GameModel
{

    private Spielkarte[] handblatt;
    private int          geld;
    private Blind        wasfuernBlind;
    private int          wettsumme;

    public void betfraise(int wettsumme)
    {
        // sendet dem Server die Summe/Aktion
    }

    public void check()
    {
        // sendet dem Server die Aktion
    }

    public void fold()
    {
        // sendet dem Server die Aktion
    }

    public int getWettsumme()
    {
        return wettsumme;
    }

    public void setWettsumme(int wettbetrag)
    {
        wettsumme = wettbetrag;
    }

    public Blatt blattErmitteln()
    { // Kartenwert, Blatt, Höchste Karte

    }

}
