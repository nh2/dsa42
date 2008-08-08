package de.schelklingen2008.risiko.model;

import java.awt.Color;
import java.io.Serializable;

/**
 * Is a simple abstraction for a player entity.
 */
public class Player implements Serializable
{

    private String name;
    private int    units;
    private int    index;
    private Color  c;
    private int    unitstoset;

    public Player(String pName, int pIndex, Color pColor)
    {
        name = pName;
        units = 0;
        index = pIndex;
        c = pColor;
    }

    public int getUnitsToSet()
    {
        return unitstoset;
    }

    public void getUnitsToSet(int units)
    {
        unitstoset = units;
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
