package de.schelklingen2008.billiards.model;

import static de.schelklingen2008.billiards.GlobalConstants.BALL_RADIUS;
import static de.schelklingen2008.billiards.GlobalConstants.MAX_X;
import static de.schelklingen2008.billiards.GlobalConstants.MAX_Y;

import java.awt.Color;

import de.schelklingen2008.billiards.util.Vector2d;

public class Ball
{

    public enum BallType
    {
        BLACK, WHITE, SOLID, STRIPED
    }

    private BallType type;
    private Color    color;
    private Vector2d position;
    private Vector2d velocity;
    private boolean  sunk;

    public Ball(BallType type, Color color)
    {
        this.type = type;
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }

    public BallType getType()
    {
        return type;
    }

    public Vector2d getPosition()
    {
        return position;
    }

    void setPosition(Vector2d position)
    {
        position = position;
    }

    public Vector2d getVelocity()
    {
        return velocity;
    }

    void setVelocity(Vector2d velocity)
    {
        velocity = velocity;
    }

    public boolean isSunk()
    {
        return sunk;
    }

    void setSunk(boolean sunk)
    {
        this.sunk = sunk;
    }

    double getNextWallCollision()
    {

        double[] collisionTimes = new double[4];
        int nCollisions;

        collisionTimes[0] = (BALL_RADIUS - position.getX()) / velocity.getX();
        collisionTimes[1] = (BALL_RADIUS - position.getY()) / velocity.getY();
        collisionTimes[2] = (MAX_X - position.getX() - BALL_RADIUS) / velocity.getX();
        collisionTimes[3] = (MAX_Y - position.getY() - BALL_RADIUS) / velocity.getY();

        double minTime = Double.NaN;

        for (int i = 0; i < collisionTimes.length; i++)
        {
            if (collisionTimes[i] >= 0 && (Double.isNaN(minTime) || collisionTimes[i] < minTime))
            {
                minTime = collisionTimes[i];
            }
        }

        return minTime;

    }

    double getNextBallCollision(Ball other)
    {

        double deltaX = position.getX() - other.position.getX();
        double deltaY = position.getY() - other.position.getY();
        double deltaVX = velocity.getX() - other.velocity.getX();
        double deltaVY = velocity.getY() - other.velocity.getY();

        double a = deltaVX * deltaVX + deltaVY * deltaVY;

        if (a == 0)
        {
            return Double.NaN;
        }

        double b = 2 * (deltaX * deltaVX + deltaY * deltaVY);
        double c = deltaX * deltaX + deltaY * deltaY - 4 * BALL_RADIUS * BALL_RADIUS;

        double disc = b * b - 4 * a * c;

        if (disc < 0)
        {
            return Double.NaN;
        }
        else
        {
            double t = (-b - Math.sqrt(disc)) / (2 * a);
            if (t >= 0)
            {
                return t;
            }
            else
            {
                return Double.NaN;
            }
        }

    }

    void processTimeStep(double deltaT)
    {

    }

}
