package de.schelklingen2008.dasverruecktelabyrinth.model;

public class Tile
{

    private boolean      unmoveable = false;
    private boolean      up, down, right, left;
    private TreasureCard tc         = null;

    public Tile(boolean pUp, boolean pDown, boolean pRight, boolean pLeft, TreasureCard pTc)
    {
        up = pUp;
        down = pDown;
        right = pRight;
        left = pLeft;
        tc = pTc;
    }
}
