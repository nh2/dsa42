package de.schelklingen2008.billiards.model;

import static de.schelklingen2008.billiards.GlobalConstants.BALL_RADIUS;
import static de.schelklingen2008.billiards.GlobalConstants.MAX_X;
import static de.schelklingen2008.billiards.GlobalConstants.MAX_Y;
import static java.awt.Color.BLUE;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;

import java.awt.Color;

import de.schelklingen2008.billiards.GlobalConstants;
import de.schelklingen2008.billiards.util.Vector2d;

public class Ball
{

    public enum BallType
    {
        BLACK, WHITE, SOLID, STRIPED
    }

    private static final double[] INITIAL_BALL_POSITIONS_X =
        { 0.75 * MAX_X - 34.8d, 0.75 * MAX_X - 17.4d, 0.75 * MAX_X, 0.75 * MAX_X + 17.4d, 0.75 * MAX_X + 34.8d };

    private static final double[] INITIAL_BALL_POSITIONS_Y =
        { 0.5 * MAX_Y - 10d, 0.5 * MAX_Y - 30d, 0.5 * MAX_Y - 20d, 0.5 * MAX_Y - 10d, 0.5 * MAX_Y, 0.5 * MAX_Y + 10d,
         0.5 * MAX_Y + 20d, 0.5 * MAX_Y + 30d, 0.5 * MAX_Y + 40d };

    public static final Vector2d[] INITIAL_BALL_POSITIONS =
        { new Vector2d(INITIAL_BALL_POSITIONS_X[0], INITIAL_BALL_POSITIONS_Y[4]),
         new Vector2d(INITIAL_BALL_POSITIONS_X[1], INITIAL_BALL_POSITIONS_Y[5]),
         new Vector2d(INITIAL_BALL_POSITIONS_X[1], INITIAL_BALL_POSITIONS_Y[3]),
         new Vector2d(INITIAL_BALL_POSITIONS_X[2], INITIAL_BALL_POSITIONS_Y[6]),
         new Vector2d(INITIAL_BALL_POSITIONS_X[2], INITIAL_BALL_POSITIONS_Y[2]),
         new Vector2d(INITIAL_BALL_POSITIONS_X[3], INITIAL_BALL_POSITIONS_Y[7]),
         new Vector2d(INITIAL_BALL_POSITIONS_X[3], INITIAL_BALL_POSITIONS_Y[5]),
         new Vector2d(INITIAL_BALL_POSITIONS_X[3], INITIAL_BALL_POSITIONS_Y[3]),
         new Vector2d(INITIAL_BALL_POSITIONS_X[3], INITIAL_BALL_POSITIONS_Y[1]),
         new Vector2d(INITIAL_BALL_POSITIONS_X[4], INITIAL_BALL_POSITIONS_Y[8]),
         new Vector2d(INITIAL_BALL_POSITIONS_X[4], INITIAL_BALL_POSITIONS_Y[6]),
         new Vector2d(INITIAL_BALL_POSITIONS_X[4], INITIAL_BALL_POSITIONS_Y[4]),
         new Vector2d(INITIAL_BALL_POSITIONS_X[4], INITIAL_BALL_POSITIONS_Y[2]),
         new Vector2d(INITIAL_BALL_POSITIONS_X[4], INITIAL_BALL_POSITIONS_Y[0]) };

    public static final Color[] BALL_COLORS = { YELLOW, GREEN, new Color(0xFF4000), RED, BLUE, new Color(0x800080), // PURPLE
                                               new Color(0x804000) // MAROON
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
    private Color color;
    private Vector2d position;
    private Vector2d velocity;
    private boolean sunk;

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

        if (Math.abs(velocity.getX()) < EPSILON && Math.abs(velocity.getY()) < EPSILON)
        {
            velocity = Vector2d.ZERO;
        }
    }

    // TODO Make this default visibility
    public void setVelocity(Vector2d velocity)
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

        double vx = velocity.getX(), vy = velocity.getY();
        double xCollisionTime, yCollisionTime;

        if (vx < 0)
        {
            xCollisionTime = (BALL_RADIUS - position.getX()) / velocity.getX();
        }
        else if (vx > 0)
        {
            xCollisionTime = (MAX_X - position.getX() - BALL_RADIUS) / velocity.getX();
        }
        else
        {
            xCollisionTime = Double.NaN;
        }

        if (vy < 0)
        {
            yCollisionTime = (BALL_RADIUS - position.getY()) / velocity.getY();
        }
        else if (vy > 0)
        {
            yCollisionTime = (MAX_Y - position.getY() - BALL_RADIUS) / velocity.getY();
        }
        else
        {
            yCollisionTime = Double.NaN;
        }

        double result;

        if (!Double.isNaN(xCollisionTime) && (Double.isNaN(yCollisionTime) || xCollisionTime < yCollisionTime))
        {
            result = xCollisionTime;
        }
        else
        {
            result = yCollisionTime;
        }

        if (Double.isNaN(result))
        {
            return Double.NaN;
        }
        else
        {
            return adjustCollisionTime(result);
        }

    }

    double getNextBallCollision(Ball other)
    {

        double deltaX = position.getX() - other.position.getX();
        double deltaY = position.getY() - other.position.getY();
        double deltaVX = velocity.getX() - other.velocity.getX();
        double deltaVY = velocity.getY() - other.velocity.getY();

        if (deltaX * deltaX + deltaY * deltaY < 4 * BALL_RADIUS * BALL_RADIUS)
        {
            double factor = 400 / new Vector2d(deltaX, deltaY).getLength();
            deltaX *= factor;
            deltaY *= factor;
        }

        double a = deltaVX * deltaVX + deltaVY * deltaVY;
        double b = 2 * (deltaX * deltaVX + deltaY * deltaVY);
        double c = deltaX * deltaX + deltaY * deltaY - 4 * BALL_RADIUS * BALL_RADIUS;

        double result = Double.NaN;

        if (a == 0)
        {
            if (b != 0)
            {
                return -c / b;
            }
        }
        else
        {

            double disc = b * b - 4 * a * c;

            if (disc >= 0)
            {
                result = (-b - Math.sqrt(disc)) / (2 * a);
            }
        }

        if (Double.isNaN(result) || result < 0)
        {
            return Double.NaN;
        }
        else
        {
            if (isInMotion())
            {
                return adjustCollisionTime(result);
            }
            else
            {
                return other.adjustCollisionTime(result);
            }
        }

    }

    public double adjustCollisionTime(double t)
    {

        double a = -0.5 * Math.cos(velocity.getAngle()) * GlobalConstants.FRICTION_FACTOR;
        double b = velocity.getX();
        double c = -velocity.getX() * t;
        double result1 = Double.NaN, result2 = Double.NaN;
        double disc = Double.NaN;

        if (a == 0)
        {
            if (b != 0)
            {
                result1 = -c / b;
            }
        }
        else
        {
            disc = b * b - 4 * a * c;
            if (disc >= 0)
            {
                result1 = (-b + Math.sqrt(disc)) / (2 * a);
                result2 = (-b - Math.sqrt(disc)) / (2 * a);
            }
        }

        if (Double.isNaN(result1) || result1 < 0 || !Double.isNaN(result2) && result2 < result1)
        {
            if (Double.isNaN(result2) || result2 < 0)
            {
                return Double.NaN;
            }
            else
            {
                return result2;
            }
        }
        else
        {
            return result1;
        }

    }

    public boolean isInMotion()
    {
        return !velocity.equals(Vector2d.ZERO);
    }

    void move(double deltaT)
    {
        if (!isInMotion())
        {
            return;
        }

        final double EPSILON = 0.00001d;

        double frictionFactor = GlobalConstants.FRICTION_FACTOR * deltaT / velocity.getLength();

        if (1 - frictionFactor < EPSILON)
        {
            velocity = Vector2d.ZERO;
            position = position.add(velocity.scale(0.5 * deltaT));
        }
        else
        {
            velocity = velocity.scale(1 - frictionFactor);
            position =
                position.add(velocity.scale(deltaT))
                        .subtract(
                                  Vector2d.getPolarVector(velocity.getAngle(), 0.5 * GlobalConstants.FRICTION_FACTOR
                                                                               * deltaT * deltaT));

        }

        checkVelocity();
    }
}
