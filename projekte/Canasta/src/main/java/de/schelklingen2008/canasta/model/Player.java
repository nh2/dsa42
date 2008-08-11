package de.schelklingen2008.canasta.model;

import java.io.Serializable;

/**
 * Is a simple abstraction for a player entity. To be merged with PlayerState
 */
public class Player implements Serializable
{

    private final String name;
    private int          score;
    private Hand         hand;
    private Outlay       outlay;

    public Player(String name)
    {
        super();

        this.name = name;

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

    public void setScore(int score)
    {
        this.score = score;
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
        /**
         * TODO implement hasCanasta()
         */
        return true;
    }
}
