package de.schelklingen2008.dasverruecktelabyrinth.model;

import java.io.Serializable;

public class Player implements Serializable
{

    private PlayerType playertype;
    private String     name;
    private int        x;
    private int        y;

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

    public String getName()
    {
        return name;
    }

}
