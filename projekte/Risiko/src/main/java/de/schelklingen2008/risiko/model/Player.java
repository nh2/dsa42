package de.schelklingen2008.risiko.model;

import java.awt.Color;
import java.io.Serializable;

/**
 * Is a simple abstraction for a player entity.
 */
public class Player implements Serializable
{

    private String  name;
    private int     units;
    private int     index;
    private Color   c;
    private int     unitstoset;
    private boolean isplaying;

    public Player(String pName, int pIndex, Color pColor)
    {
        name = pName;
        units = 0;
        index = pIndex;
        c = pColor;
        unitstoset = 0;
        isplaying = true;
    }

    public boolean IsPlaying()
    {
        return isplaying;
    }

    public void setIsPlaying(boolean b)
    {
        isplaying = b;
    }

    public Color getPlayerColor()
    {
        return c;
    }

    public void setPlayerColor(Color c)
    {
        this.c = c;
    }

    public int getPlayerIndex()

    {
        return index;
    }

    public String getPlayerName()
    {
        return name;
    }

    public int getPlayerUnits()
    {
        return units;
    }

    public int getUnitsToSet()
    {
        return unitstoset;
    }

    public void setUnits(int i)
    {
        index = i;
    }

    public void setUnitsToSet(int units)
    {
        unitstoset = units;
    }

    public int getCountrys(Country[] co)
    {
        int number = 0;
        for (int i = 0; i < co.length; i++)
        {
            if (co[i].getOccupier().equals(this))
            {
                number++;
            }
        }
        return number;
    }

}
