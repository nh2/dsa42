package de.schelklingen2008.billiards.model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    private Player[]         players    = new Player[2];
    private Player           turnHolder = null;
    private boolean          inMotion   = false;                 // Are there any balls in motion?

    private Collection<Ball> balls      = new LinkedList<Ball>();

    public int getPlayerScore(int player)
    {

        return players[player].getScore();

    }

    public boolean isTurnHolder(Player player)
    {
        return player.equals(turnHolder);
    }

    public boolean isTurnHolder(int player)
    {
        return turnHolder.getId() == player;
    }

    public void processTimeStep(double deltaT)
    {
        if (!inMotion)
        {
            return;
        }

        for (Ball ball : balls)
        {
            ball.processTimeStep(deltaT);
        }

    }

}