package de.schelklingen2008.billiards.model;

public class BallSunkEvent
{

    private final Ball ball;
    private final Player player;

    public BallSunkEvent(Ball ball, Player player)
    {
        this.ball = ball;
        this.player = player;
    }

    public Ball getBall()
    {
        return ball;
    }

    public Player getPlayer()
    {
        return player;
    }

}
