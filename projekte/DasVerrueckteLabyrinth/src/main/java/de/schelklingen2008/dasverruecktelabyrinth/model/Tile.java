package de.schelklingen2008.dasverruecktelabyrinth.model;

import java.io.Serializable;

public class Tile implements Serializable
{

    public static final Tile HORIZONTAL = new Tile(false, false, true, true, null);
    public static final Tile VERTICAL   = new Tile(true, true, false, false, null);
    public static final Tile CROSS      = new Tile(true, true, true, true, null);

    public static final Tile CURVE1     = new Tile(false, true, false, true, null);
    public static final Tile CURVE2     = new Tile(false, true, true, false, null);
    public static final Tile CURVE3     = new Tile(true, false, true, false, null);
    public static final Tile CURVE4     = new Tile(true, false, false, true, null);

    private boolean          unmoveable = false;
    private boolean          up, down, right, left;
    private TreasureCard     tc         = null;

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

    public void turnRight()
    {
        left = getDown();
        up = getLeft();
        down = getRight();
        right = getUp();

    }

    public void turnLeft()
    {
        left = getUp();
        up = getRight();
        down = getLeft();
        right = getDown();

    }

    public TreasureCard getTC()
    {
        return tc;
    }

    public void setUnmoveable(boolean pboolean)
    {
        unmoveable = pboolean;
    }

    public boolean isCurve1()
    {
        if (getLeft() == true && getDown() == true && getRight() == false && getUp() == false) return true;

        return false;
    }

    public boolean isCurve2()
    {
        if (getLeft() == false && getDown() == true && getRight() == true && getUp() == false) return true;

        return false;
    }

    public boolean isCurve3()
    {
        if (getLeft() == false && getDown() == false && getRight() == true && getUp() == true) return true;

        return false;
    }

    public boolean isCurve4()
    {
        if (getLeft() == true && getDown() == false && getRight() == false && getUp() == true) return true;

        return false;
    }

    public boolean isHorizontal()
    {
        if (getLeft() == true && getDown() == false && getRight() == true && getUp() == false) return true;

        return false;
    }

    public boolean isVertikal()
    {
        if (getLeft() == false && getDown() == true && getRight() == false && getUp() == true) return true;

        return false;
    }

    public boolean isCross()
    {
        if (getLeft() == true && getDown() == true && getRight() == true && getUp() == true) return true;

        return false;
    }
}
