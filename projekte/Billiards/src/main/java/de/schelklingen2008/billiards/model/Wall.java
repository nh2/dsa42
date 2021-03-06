package de.schelklingen2008.billiards.model;

import static de.schelklingen2008.billiards.GlobalConstants.BALL_RADIUS;

import java.io.Serializable;

import de.schelklingen2008.billiards.GlobalConstants;
import de.schelklingen2008.billiards.util.Vector2d;

public class Wall implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 5163253895724147660L;

    private double slope, yIntercept, minX, minY, maxX, maxY, angle;

    public Wall(Vector2d p1, Vector2d p2)
    {

        Vector2d v = p1.subtract(p2);
        angle = v.getAngle();
        if (angle > Math.PI)
        {
            angle -= Math.PI;
        }
        slope = v.getY() / v.getX();
        if (!Double.isInfinite(slope))
        {
            yIntercept = p1.getY() - slope * p1.getX();
        }

        if (p1.getX() < p2.getX())
        {
            minX = p1.getX();
            maxX = p2.getX();
        }
        else
        {
            minX = p2.getX();
            maxX = p1.getX();
        }

        if (p1.getY() < p2.getY())
        {
            minY = p1.getY();
            maxY = p2.getY();
        }
        else
        {
            minY = p2.getY();
            maxY = p1.getY();
        }

    }

    public double getCollisionTime(Ball ball)
    {
        if (!ball.isInMotion())
        {
            return Double.NaN;
        }

        final double EPSILON = 0.0001d;

        if (Math.abs(slope) < EPSILON)
        {
            return getHorizontalWallCollisionTime(ball);
        }
        else if (Double.isInfinite(slope))
        {
            return getVerticalWallCollisionTime(ball);
        }
        else
        {

            double x0 = ball.getPosition().getX(), y0 = ball.getPosition().getY();

            double deltaY = yIntercept + slope * x0 - y0;

            double xCollision;

            if (ball.getVelocity().getX() == 0)
            {
                xCollision = x0;
                if (Math.signum(deltaY) != Math.signum(ball.getVelocity().getY()))
                {
                    return Double.NaN;
                }
            }
            else
            {
                double vSlope = ball.getVelocity().getY() / ball.getVelocity().getX();
                xCollision = (x0 * vSlope - y0 + yIntercept) / (vSlope - slope);
                if (Math.signum(xCollision - x0) != Math.signum(ball.getVelocity().getX()))
                {
                    return Double.NaN;
                }
            }

            double perpendicularAngle = angle + Math.PI / 2;
            if (perpendicularAngle > Math.PI)
            {
                perpendicularAngle -= Math.PI;
            }

            double alpha = Math.abs(perpendicularAngle - ball.getVelocity().getAngle());

            double perpendicularDist = Math.abs(Math.cos(perpendicularAngle - Math.PI / 2) * Math.abs(deltaY));
            double dist = Math.abs((perpendicularDist - BALL_RADIUS) / Math.cos(alpha));

            double t = dist / ball.getVelocity().getLength();

            Vector2d collisionPos = ball.getPosition().add(ball.getVelocity().scale(t));

            if (t < 0 || Double.isNaN(t))
            {
                return Double.NaN;
            }

            if (deltaY > 0)
            {
                collisionPos =
                    collisionPos.add(Vector2d.getPolarVector(perpendicularAngle + Math.PI, GlobalConstants.BALL_RADIUS));
            }
            else
            {
                collisionPos =
                    collisionPos.add(Vector2d.getPolarVector(perpendicularAngle, GlobalConstants.BALL_RADIUS));
            }

            if (collisionPos.getX() < minX || collisionPos.getX() > maxX || collisionPos.getY() < minY
                || collisionPos.getY() > maxY)
            {
                Vector2d edge;
                if (collisionPos.getX() < minX)
                {
                    edge = new Vector2d(minX, minX * slope + yIntercept);
                }
                else
                {
                    edge = new Vector2d(maxX, maxX * slope + yIntercept);
                }
                return getEdgeCollisionTime(ball, edge);
            }
            else
            {
                return ball.adjustCollisionTime(t);
            }

        }
    }

    private double getEdgeCollisionTime(Ball ball, Vector2d collisionPos)
    {

        double vX = ball.getVelocity().getX(), vY = ball.getVelocity().getY();
        double deltaX = collisionPos.getX() - ball.getPosition().getX(), deltaY =
            collisionPos.getY() - ball.getPosition().getY();

        double a = vX * vX + vY * vY;
        double b = -2 * (deltaX * vX + deltaY * vY);
        double c = deltaX * deltaX + deltaY * deltaY - GlobalConstants.BALL_RADIUS * GlobalConstants.BALL_RADIUS;

        double disc = b * b - 4 * a * c;

        if (disc < 0)
        {
            return Double.NaN;
        }
        else
        {
            double t = (-b - Math.sqrt(disc)) / (2 * a);
            if (t < 0)
            {
                return Double.NaN;
            }
            else
            {
                return ball.adjustCollisionTime(t);
            }
        }

    }

    private double getVerticalWallCollisionTime(Ball ball)
    {
        double t;

        if (ball.getVelocity().getX() < 0)
        {
            t = (minX - ball.getPosition().getX() + BALL_RADIUS) / ball.getVelocity().getX();
        }
        else if (ball.getVelocity().getX() > 0)
        {
            t = (minX - ball.getPosition().getX() - BALL_RADIUS) / ball.getVelocity().getX();
        }
        else
        {
            t = Double.NaN;
        }

        if (!Double.isNaN(t))
        {
            double y = ball.getPosition().getY() + ball.getVelocity().getY() * t;
            if (y < minY || y > maxY)
            {
                t = Double.NaN;
            }
            else
            {
                t = ball.adjustCollisionTime(t);
            }
        }

        return t;

    }

    private double getHorizontalWallCollisionTime(Ball ball)
    {
        double t;

        if (ball.getVelocity().getY() < 0)
        {
            t = (minY - ball.getPosition().getY() + BALL_RADIUS) / ball.getVelocity().getY();
        }
        else if (ball.getVelocity().getY() > 0)
        {
            t = (minY - ball.getPosition().getY() - BALL_RADIUS) / ball.getVelocity().getY();
        }
        else
        {
            t = Double.NaN;
        }

        if (!Double.isNaN(t))
        {
            double x = ball.getPosition().getX() + ball.getVelocity().getX() * t;
            if (x < minX || x > maxX)
            {
                t = Double.NaN;
            }
            else
            {
                t = ball.adjustCollisionTime(t);
            }
        }

        return t;
    }

    public void handleWallCollision(Ball ball)
    {
        final double EPSILON = 0.0001d;

        if (Math.abs(slope) < EPSILON)
        {
            handleHorizontalWallCollision(ball);
        }
        else if (Double.isInfinite(slope))
        {
            handleVerticalWallCollision(ball);
        }
        else
        {

            ball.setVelocity(Vector2d.getPolarVector(2 * angle - ball.getVelocity().getAngle(),
                                                     ball.getVelocity().getLength()
                                                             * (1 - GlobalConstants.COLLISION_IMPULSE_LOSS)));

        }
    }

    private void handleVerticalWallCollision(Ball ball)
    {
        ball.setVelocity(new Vector2d(-ball.getVelocity().getX(), ball.getVelocity().getY()).scale(1 - GlobalConstants.COLLISION_IMPULSE_LOSS));
    }

    private void handleHorizontalWallCollision(Ball ball)
    {
        ball.setVelocity(new Vector2d(ball.getVelocity().getX(), -ball.getVelocity().getY()).scale(1 - GlobalConstants.COLLISION_IMPULSE_LOSS));
    }

    public double getSlope()
    {
        return slope;
    }

    public void setSlope(double slope)
    {
        this.slope = slope;
    }

    public double getYIntercept()
    {
        return yIntercept;
    }

    public void setYIntercept(double intercept)
    {
        yIntercept = intercept;
    }

    public double getMinX()
    {
        return minX;
    }

    public void setMinX(double minX)
    {
        this.minX = minX;
    }

    public double getMinY()
    {
        return minY;
    }

    public void setMinY(double minY)
    {
        this.minY = minY;
    }

    public double getMaxX()
    {
        return maxX;
    }

    public void setMaxX(double maxX)
    {
        this.maxX = maxX;
    }

    public double getMaxY()
    {
        return maxY;
    }

    public void setMaxY(double maxY)
    {
        this.maxY = maxY;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(maxX);
        result = prime * result + (int) (temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(maxY);
        result = prime * result + (int) (temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(minX);
        result = prime * result + (int) (temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(minY);
        result = prime * result + (int) (temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(slope);
        result = prime * result + (int) (temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(yIntercept);
        result = prime * result + (int) (temp ^ temp >>> 32);
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Wall other = (Wall) obj;
        if (Double.doubleToLongBits(maxX) != Double.doubleToLongBits(other.maxX)) return false;
        if (Double.doubleToLongBits(maxY) != Double.doubleToLongBits(other.maxY)) return false;
        if (Double.doubleToLongBits(minX) != Double.doubleToLongBits(other.minX)) return false;
        if (Double.doubleToLongBits(minY) != Double.doubleToLongBits(other.minY)) return false;
        if (Double.doubleToLongBits(slope) != Double.doubleToLongBits(other.slope)) return false;
        if (Double.doubleToLongBits(yIntercept) != Double.doubleToLongBits(other.yIntercept)) return false;
        return true;
    }

}
