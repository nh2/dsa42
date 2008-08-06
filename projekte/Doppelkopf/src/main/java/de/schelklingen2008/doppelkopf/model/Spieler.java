package de.schelklingen2008.doppelkopf.model;

public class Spieler
{

    public String name;
    public Blatt  blatt;

    public Spieler(String name)
    {
        this.name = name;
        blatt = new Blatt();
    }

    @Override
    public String toString()
    {
        return name;
    }
}
