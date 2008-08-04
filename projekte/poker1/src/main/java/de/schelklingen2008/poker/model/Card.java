package de.schelklingen2008.poker.model;

public class Card
{

    private int number;
    private int color;

    public String getValue()
    {
        // Wandelt die int number in die Zahl der Spielkarte um (2 bis Ass)
        return "";
    }

    public String getColor()
    {
        // Wandelt die in color in die entsprechende Farbe um
        return "";
    }

    public String getName()
    {
        return getColor() + " " + getValue();
    }
}
