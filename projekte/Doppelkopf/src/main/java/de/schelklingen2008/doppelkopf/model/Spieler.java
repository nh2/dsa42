package de.schelklingen2008.doppelkopf.model;

import java.io.Serializable;

public class Spieler implements Serializable
{

    private String name;
    private Blatt  blatt;

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

    public String getName()
    {
        return name;
    }

    public Blatt getBlatt()
    {
        return blatt;
    }
}
