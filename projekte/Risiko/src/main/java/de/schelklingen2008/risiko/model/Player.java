package de.schelklingen2008.risiko.model;

import java.awt.Color;

import com.threerings.crowd.data.BodyObject;

/**
 * Is a simple abstraction for a player entity.
 */
public class Player
{

    private String name;
    private int    units;
    private int    index;
    private Color  c;

    public Player()
    {
        name = "";
    }

    public String getPlayerName(BodyObject bo)
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

}
