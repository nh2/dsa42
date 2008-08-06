package de.schelklingen2008.risiko.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Country
{

    private String        name;
    private int           units;
    private int           index;
    private int           positionNameX;
    private int           positionNameY;
    private Player        occupier;
    private List<Country> neighbours = new ArrayList<Country>();
    private Color         color;
    private boolean       selected;

    public String getName()
    {
        return name;
    }

    public int getUnits()
    {
        return units;
    }

    public int getIndex()
    {
        return index;
    }

    public int getPositionNameX()
    {
        return positionNameX;
    }

    public int getPositionNameY()
    {
        return positionNameY;
    }

    public Player getOccupier()
    {
        return occupier;
    }

    public boolean isNeighbour(Country c)
    {
        return neighbours.contains(c);
    }

    public Color getColor()
    {
        return color;
    }

    public boolean isSelected()
    {
        return selected;
    }
}
