package de.schelklingen2008.billiards.model;

import static de.schelklingen2008.billiards.GlobalConstants.BALL_RADIUS;
import static de.schelklingen2008.billiards.GlobalConstants.MAX_X;
import static de.schelklingen2008.billiards.GlobalConstants.MAX_Y;
import static java.awt.Color.BLUE;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;

import java.awt.Color;
import java.io.Serializable;

import de.schelklingen2008.billiards.GlobalConstants;
import de.schelklingen2008.billiards.util.Vector2d;

public class Ball implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -5973044172239068676L;

    public enum BallType
    {
        BLACK, WHITE, SOLID, STRIPED
    }

    private static final double ballXDist = Math.sqrt(3) * GlobalConstants.BALL_RADIUS + 1E-4;
    private static final double ballYDist = GlobalConstants.BALL_RADIUS + 1E-4;

    private static final double[] INITIAL_BALL_POSITIONS_X =
        { 0.75d * MAX_X - 2 * ballXDist, 0.75d * MAX_X - ballXDist, 0.75d * MAX_X, 0.75d * MAX_X + ballXDist,
         0.75d * MAX_X + 2 * ballXDist };

    private static final double[] INITIAL_BALL_POSITIONS_Y =
        { 0.5d * MAX_Y - 4 * ballYDist, 0.5d * MAX_Y - 3 * ballYDist, 0.5d * MAX_Y - 2 * ballYDist,
         0.5d * MAX_Y - ballYDist, 0.5d * MAX_Y, 0.5d * MAX_Y + ballYDist, 0.5d * MAX_Y + 2 * ballYDist,
         0.5d * MAX_Y + 3 * ballYDist, 0.5d * MAX_Y + 4 * ballYDist };

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
    private boolean pocketed;

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

    public boolean isPocketed()
    {
        return pocketed;
    }

    void setPocketed(boolean pocketed)
    {
        this.pocketed = pocketed;
    }

    double getNextBallCollision(Ball other)
    {

        double deltaX = position.getX() - other.position.getX();
        double deltaY = position.getY() - other.position.getY();
        double deltaVX = velocity.getX() - other.velocity.getX();
        double deltaVY = velocity.getY() - other.velocity.getY();

        if (deltaX * deltaX + deltaY * deltaY < 4 * BALL_RADIUS * BALL_RADIUS)
        {
            double factor = 2 * BALL_RADIUS / new Vector2d(deltaX, deltaY).getLength();
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
        double v, a;

        if (velocity.getX() == 0)
        {
            v = velocity.getY();
            a = -0.5d * Math.sin(velocity.getAngle()) * GlobalConstants.FRICTION_FACTOR;
        }
        else
        {
            v = velocity.getX();
            a = -0.5d * Math.cos(velocity.getAngle()) * GlobalConstants.FRICTION_FACTOR;
        }

        double b = v;
        double c = -v * t;
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
            position = position.add(velocity.scale(0.5d * deltaT));
        }
        else
        {
            velocity = velocity.scale(1 - frictionFactor);
            position =
                position.add(velocity.scale(deltaT))
                        .subtract(
                                  Vector2d.getPolarVector(velocity.getAngle(), 0.5d * GlobalConstants.FRICTION_FACTOR
                                                                               * deltaT * deltaT));

        }

        checkVelocity();
    }

    public boolean overlapsWithBallAtPosition(Vector2d position2)
    {
        return position.subtract(position2).getSquaredLength() <= 4 * BALL_RADIUS * BALL_RADIUS;
    }
}
