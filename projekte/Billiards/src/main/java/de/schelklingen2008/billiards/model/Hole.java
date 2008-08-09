package de.schelklingen2008.billiards.model;

import static de.schelklingen2008.billiards.GlobalConstants.HOLE_RADIUS;
import de.schelklingen2008.billiards.util.Vector2d;

public class Hole
{

    private Vector2d position;

    public Hole(Vector2d position)
    {
        this.position = position;
    }

    public Vector2d getPosition()
    {
        return position;
    }

    public boolean ballIsSunk(Ball ball)
    {
        return position.subtract(ball.getPosition()).getLength() < HOLE_RADIUS;
    }

}
