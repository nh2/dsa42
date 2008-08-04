package de.schelklingen2008.billiards.model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    private Player[]         players = new Player[2];
    private int              turnHolder;

    private Collection<Ball> balls   = new LinkedList<Ball>();

    public int getPlayerScore(int player)
    {

        return players[player].getScore();

    }

    public boolean isTurnHolder(int player)
    {
        return player == turnHolder;
    }

}