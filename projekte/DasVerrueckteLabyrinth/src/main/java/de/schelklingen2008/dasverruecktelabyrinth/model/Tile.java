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

    private boolean testCurve1(Tile pTile)
    {
        if (pTile.getLeft() == true && pTile.getDown() == true && pTile.getRight() == false && pTile.getUp() == false) return true;

        return false;
    }

    private boolean testCurve2(Tile pTile)
    {
        if (pTile.getLeft() == false && pTile.getDown() == true && pTile.getRight() == true && pTile.getUp() == false) return true;

        return false;
    }

    private boolean testCurve3(Tile pTile)
    {
        if (pTile.getLeft() == false && pTile.getDown() == false && pTile.getRight() == true && pTile.getUp() == true) return true;

        return false;
    }

    private boolean testCurve4(Tile pTile)
    {
        if (pTile.getLeft() == true && pTile.getDown() == false && pTile.getRight() == false && pTile.getUp() == true) return true;

        return false;
    }

    private boolean testHorizontal(Tile pTile)
    {
        if (pTile.getLeft() == true && pTile.getDown() == false && pTile.getRight() == true && pTile.getUp() == false) return true;

        return false;
    }

    private boolean testVertikal(Tile pTile)
    {
        if (pTile.getLeft() == false && pTile.getDown() == true && pTile.getRight() == false && pTile.getUp() == true) return true;

        return false;
    }

    private boolean testCross(Tile pTile)
    {
        if (pTile.getLeft() == true && pTile.getDown() == true && pTile.getRight() == true && pTile.getUp() == true) return true;

        return false;
    }
}
