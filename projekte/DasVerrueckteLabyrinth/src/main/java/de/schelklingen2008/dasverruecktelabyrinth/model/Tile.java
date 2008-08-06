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

    public boolean getUnmoveable()
    {
        return unmoveable;
    }

    public boolean getUp()
    {
        return up;
    }

    public boolean getDown()
    {

        return down;
    }

    public boolean getRight()
    {

        return right;
    }

    public boolean getLeft()
    {

        return left;
    }

    public TreasureCard getTC()
    {
        return tc;
    }

    public void setUnmoveable(boolean pboolean)
    {
        unmoveable = pboolean;
    }
}
