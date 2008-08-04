package de.schelklingen2008.dasverruecktelabyrinth.model;

public class Player
{

    private PlayerType playertype;
    private int        x;
    private int        y;

    public Player(PlayerType pPlayer, int pX, int pY)
    {
        playertype = pPlayer;
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
}
