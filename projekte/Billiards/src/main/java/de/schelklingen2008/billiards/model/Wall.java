package de.schelklingen2008.billiards.model;

import static de.schelklingen2008.billiards.GlobalConstants.BALL_RADIUS;
import de.schelklingen2008.billiards.GlobalConstants;
import de.schelklingen2008.billiards.util.Vector2d;

public class Wall
{

    private double slope, yIntercept, minX, minY, maxX, maxY, angle;

    public Wall(Vector2d p1, Vector2d p2)
    {
        Vector2d v = p1.subtract(p2);
        angle = v.getAngle();
        slope = v.getY() / v.getX();
        yIntercept = p1.getY();

        if (p1.getX() < p2.getX())
        {
            minX = p1.getX();
            maxX = p2.getX();
        }
        else
        {
            minX = p1.getX();
            maxX = p2.getX();
        }

        if (p1.getY() < p2.getY())
        {
            minY = p1.getY();
            maxY = p2.getY();
        }
        else
        {
            minY = p1.getY();
            maxY = p2.getY();
        }

    }

    public double getCollisionTime(Ball ball)
    {
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

            double deltaY = Math.abs(slope * x0 - y0);

            double alpha = angle + Math.PI / 2 - ball.getVelocity().getAngle();

            double dist = Math.cos(-angle) * deltaY - GlobalConstants.BALL_RADIUS / Math.cos(alpha);
            double t = dist / ball.getVelocity().getAngle();

            Vector2d collisionPos = ball.getPosition().add(ball.getVelocity().scale(t));

            if (t < 0 || Double.isNaN(t))
            {
                return Double.NaN;
            }

            collisionPos = collisionPos.add(Vector2d.getPolarVector(angle + Math.PI / 2, GlobalConstants.BALL_RADIUS));

            if (collisionPos.getX() < minX || collisionPos.getX() > maxX || collisionPos.getY() < minY
                || collisionPos.getY() > maxY)
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
            t = (minX - ball.getPosition().getX() - BALL_RADIUS) / ball.getVelocity().getX();
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
            double x = ball.getPosition().getX() - ball.getVelocity().getX() * t;
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

    private double getHorizontalWallCollisionTime(Ball ball)
    {
        double t;

        if (ball.getVelocity().getY() < 0)
        {
            t = (minY - ball.getPosition().getY() - BALL_RADIUS) / ball.getVelocity().getY();
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
            double y = ball.getPosition().getY() - ball.getVelocity().getY() * t;
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

}