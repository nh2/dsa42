package de.schelklingen2008.billiards.model;

public class BallCollisionEvent
{

    private final Ball ball1, ball2;

    public BallCollisionEvent(Ball ball1, Ball ball2)
    {
        this.ball1 = ball1;
        this.ball2 = ball2;
    }

    public Ball getBall1()
    {
        return ball1;
    }

    public Ball getBall2()
    {
        return ball2;
    }

}
