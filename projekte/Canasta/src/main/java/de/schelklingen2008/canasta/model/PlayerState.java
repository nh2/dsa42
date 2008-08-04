package de.schelklingen2008.canasta.model;

public class PlayerState
{

    private int    score;
    private Hand   hand;
    private Outlay outlay;

    public PlayerState()
    {
        super();

        hand = new Hand();
        outlay = new Outlay();
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

}
