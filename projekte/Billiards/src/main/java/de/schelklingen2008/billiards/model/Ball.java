package de.schelklingen2008.billiards.model;

import static de.schelklingen2008.billiards.GlobalConstants.BALL_RADIUS;
import static de.schelklingen2008.billiards.GlobalConstants.MAX_X;
import static de.schelklingen2008.billiards.GlobalConstants.MAX_Y;
import static java.awt.Color.BLUE;
import static java.awt.Color.GREEN;
import static java.awt.Color.ORANGE;
import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;

import java.awt.Color;

import de.schelklingen2008.billiards.util.Vector2d;

public class Ball
{

    public enum BallType
    {
        BLACK, WHITE, SOLID, STRIPED
    }

    public static final Color[] BALL_COLORS = { YELLOW, GREEN, ORANGE, RED, BLUE, Color.getHSBColor(200, 240, 60), // PURPLE
            Color.getHSBColor(0, 240, 60)  // MAROON
                                            };

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (color == null ? 0 : color.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Ball other = (Ball) obj;
        if (color == null)
        {
            if (other.color != null) return false;
        }
        else if (!color.equals(other.color)) return false;
        if (type == null)
        {
            if (other.type != null) return false;
        }
        else if (!type.equals(other.type)) return false;
        return true;
    }

    private BallType type;
    private Color    color;
    private Vector2d position;
    private Vector2d velocity;
    private boolean  sunk;

    public Ball(BallType type, Color color)
    {
        position = Vector2d.ZERO;
        velocity = Vector2d.ZERO;
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
        this.position = position;
    }

    public Vector2d getVelocity()
    {
        return velocity;
    }

    private void checkVelocity()
    {
        final double EPSILON = 0.000001d;

        if (velocity.getX() < EPSILON && velocity.getY() < EPSILON)
        {
            velocity = Vector2d.ZERO;
        }
    }

    void setVelocity(Vector2d velocity)
    {
        this.velocity = velocity;
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
        double b = 2 * (deltaX * deltaVX + deltaY * deltaVY);
        double c = deltaX * deltaX + deltaY * deltaY - 4 * BALL_RADIUS * BALL_RADIUS;

        if (a == 0)
        {
            if (b == 0)
            {
                if (c == 0)
                {
                    return 0;
                }
                else
                {
                    return Double.NaN;
                }
            }
            else
            {
                return -c / b;
            }
        }

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

    public boolean isInMotion()
    {
        return velocity.equals(Vector2d.ZERO);
    }

    void move(double deltaT)
    {
        // TODO Add friction

        position.add(velocity.scale(deltaT));

        checkVelocity();
    }

}
