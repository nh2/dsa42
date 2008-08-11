package de.schelklingen2008.doppelkopf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Spieler implements Serializable
{

    private String       name;
    private Blatt        blatt;
    private List<Karte>  gewinnstapel;
    public List<Integer> rundenpunkte;

    public Spieler(String name)
    {
        this.name = name;
        blatt = new Blatt();
        gewinnstapel = new ArrayList<Karte>();
    }

    @Override
    public String toString()
    {
        return name;
    }

    public String getName()
    {
        return name;
    }

    public Blatt getBlatt()
    {
        return blatt;
    }

    public List<Karte> getGewinnstapel()
    {
        return gewinnstapel;
    }
}
