package de.schelklingen2008.billiards.model;

public class GameEndEvent
{

    private final Player winner;

    public GameEndEvent(Player winner)
    {
        this.winner = winner;
    }

    public Player getWinner()
    {
        return winner;
    }

}
