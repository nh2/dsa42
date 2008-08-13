package de.schelklingen2008.billiards.model;

public class BallSetEvent
{

    private final Ball ball;

    public BallSetEvent(Ball ball)
    {
        this.ball = ball;
    }

    public Ball getBall()
    {
        return ball;
    }

}
