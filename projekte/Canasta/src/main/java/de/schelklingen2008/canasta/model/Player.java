package de.schelklingen2008.canasta.model;

/**
 * Is a simple abstraction for a player entity. To be merged with PlayerState
 */
public class Player
{

    private String name;
    private int    score;
    private Hand   hand;
    private Outlay outlay;

    public Player()
    {
        super();

        hand = new Hand();
        outlay = new Outlay();
    }

    public String getName()
    {
        return name;
    }

    public int getScore()
    {
        return score;
    }

    public Hand getHand()
    {
        return hand;
    }

    public Outlay getOutlay()
    {
        return outlay;
    }

    public boolean hasCanasta()
    {
        return true;
    }
}
