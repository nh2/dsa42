package de.schelklingen2008.billiards.model;

public class WallCollisionEvent
{

    private final Ball ball;
    private final Wall wall;

    public Ball getBall()
    {
        return ball;
    }

    public Wall getWall()
    {
        return wall;
    }

    public WallCollisionEvent(Ball ball, Wall wall)
    {
        this.ball = ball;
        this.wall = wall;
    }

}
