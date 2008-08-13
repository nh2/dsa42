package de.schelklingen2008.dasverruecktelabyrinth.model;

import java.io.Serializable;

public class Player implements Serializable
{

    private PlayerType playertype;
    private String     name;
    private int        x;
    private int        y;
    private boolean    hasMoved = false;

    public Player(PlayerType pPlayer, String pName, int pX, int pY)
    {
        playertype = pPlayer;
        name = pName;
        x = pX;
        y = pY;
    }

    public PlayerType getPlayerType()
    {
        return playertype;
    }

    public int getXKoordinate()
    {
        return x;
    }

    public int getYKoordinate()
    {
        return y;
    }

    public void setXKoordinate(int pX)
    {
        x = pX;
    }

    public void setYKoordinate(int pY)
    {
        y = pY;
    }

    public void Moved(boolean moved)
    {
        hasMoved = moved;
    }

    public String getName()
    {
        return name;
    }

    public boolean hasMoved()
    {
        return hasMoved;
    }
}
