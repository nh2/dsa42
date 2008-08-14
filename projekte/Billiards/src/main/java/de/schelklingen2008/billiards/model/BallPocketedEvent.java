package de.schelklingen2008.billiards.model;

public class BallPocketedEvent
{

    private final Ball ball;
    private final Player player;

    public BallPocketedEvent(Ball ball, Player player)
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
