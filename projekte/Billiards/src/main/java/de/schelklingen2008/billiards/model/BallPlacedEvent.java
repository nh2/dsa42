package de.schelklingen2008.billiards.model;

public class BallPlacedEvent
{

    private final Ball ball;

    public BallPlacedEvent(Ball ball)
    {
        this.ball = ball;
    }

    public Ball getBall()
    {
        return ball;
    }

}
