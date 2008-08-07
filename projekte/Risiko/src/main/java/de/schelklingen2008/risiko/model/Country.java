package de.schelklingen2008.risiko.model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Country implements Serializable
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

    public Country(String pName, int pIndex, int pX, int pY, int r, int g, int b)
    {
        name = pName;
        units = 0;
        index = pIndex;
        positionNameX = pX;
        positionNameY = pY;
        occupier = null;
        neighbours = null;
        color = new Color(r, g, b);
        selected = false;
    }

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

    public void setUnits(int i)
    {
        units = i;
    }

    public void setOccupier(Player p)
    {
        occupier = p;
    }

    public void setNeighbours(ArrayList<Country> pNeighbours)
    {
        neighbours = pNeighbours;
    }

    public void setSelected(boolean b)
    {
        selected = b;
    }
}
