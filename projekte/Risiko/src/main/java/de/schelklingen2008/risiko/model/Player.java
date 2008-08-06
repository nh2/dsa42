package de.schelklingen2008.risiko.model;

import java.awt.Color;

/**
 * Is a simple abstraction for a player entity.
 */
public class Player
{

    private String name;
    private int    units;
    private int    index;
    private Color  c;

    public Player(String pName, int pIndex, Color pColor)
    {
        name = pName;
        units = 0;
        index = pIndex;
        c = pColor;
    }

    public String getPlayerName()
    {
        return name;
    }

    public int getPlayerUnits()
    {
        return units;
    }

    public int getPlayerIndex()
    {
        return index;
    }

    public Color getPlayerColor()
    {
        return c;
    }

    public void setUnits(int i)
    {
        index = i;
    }

}
