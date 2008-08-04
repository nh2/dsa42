package de.schelklingen2008.dasverruecktelabyrinth.model;

public class Tile
{

    private boolean   unmoveable;
    private boolean   up, down, right, left;
    private int       x, y;
    private boolean   engaged;
    private FrameCard fc = null;

    public Tile()
    {

    }
}
