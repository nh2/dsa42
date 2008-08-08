package de.schelklingen2008.billiards.model;

import de.schelklingen2008.billiards.GlobalConstants;
import de.schelklingen2008.billiards.util.Vector2d;

public class Collision implements Comparable<Collision>
{

    public static Collision getWallCollision(Ball ball, Wall wall)
    {
        double t = wall.getCollisionTime(ball);
        if (Double.isNaN(t))
        {
            return null;
        }
        else
        {
            return new Collision(ball, wall, t);
        }
    }

    public static Collision getBallCollision(Ball ball1, Ball ball2)
    {
        double t = ball1.getNextBallCollision(ball2);
        if (Double.isNaN(t))
        {
            return null;
        }
        else
        {
            return new Collision(ball1, ball2, t);
        }
    }

    private Ball ball1, ball2;
    private Wall wall;
    private double time;

    public Collision(Ball ball1, Ball ball2, double time)
    {
        this.ball1 = ball1;
        this.ball2 = ball2;
        this.time = time;
    }

    public Collision(Ball ball, Wall wall, double time)
    {
        ball1 = ball;
        this.wall = wall;
        this.time = time;
    }

    public Ball getBall()
    {
        return wall != null ? ball1 : null;
    }

    public Ball getBall1()
    {
        return wall == null ? ball1 : null;
    }

    public Ball getBall2()
    {
        return ball2;
    }

    public Wall getWall()
    {
        return wall;
    }

    public double getTime()
    {
        return time;
    }

    public boolean isWallCollision()
    {
        return wall != null;
    }

    public boolean isBallCollision()
    {
        return wall == null;
    }

    public void handle()
    {

        if (wall != null)
        {
            wall.handleWallCollision(ball1);
        }
        else
        {

            double alpha = -ball1.getPosition().subtract(ball2.getPosition()).getAngle();

            Vector2d v1 = ball1.getVelocity().rotate(-alpha);
            Vector2d v2 = ball2.getVelocity().rotate(-alpha);

            double tmp = v1.getX();
            v1 = new Vector2d(v2.getX(), v1.getY()).scale(1 - GlobalConstants.COLLISION_IMPULSE_LOSS);
            v2 = new Vector2d(tmp, v2.getY()).scale(1 - GlobalConstants.COLLISION_IMPULSE_LOSS);

            ball1.setVelocity(v1.rotate(alpha));
            ball2.setVelocity(v2.rotate(alpha));

        }

    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (ball1 == null ? 0 : ball1.hashCode());
        result = prime * result + (ball2 == null ? 0 : ball2.hashCode());
        long temp;
        temp = Double.doubleToLongBits(time);
        result = prime * result + (int) (temp ^ temp >>> 32);
        result = prime * result + (wall == null ? 0 : wall.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Collision other = (Collision) obj;
        if (ball1 == null)
        {
            if (other.ball1 != null) return false;
        }
        else if (!ball1.equals(other.ball1)) return false;
        if (ball2 == null)
        {
            if (other.ball2 != null) return false;
        }
        else if (!ball2.equals(other.ball2)) return false;
        if (Double.doubleToLongBits(time) != Double.doubleToLongBits(other.time)) return false;
        if (wall == null)
        {
            if (other.wall != null) return false;
        }
        else if (!wall.equals(other.wall)) return false;
        return true;
    }

    public int compareTo(Collision o)
    {
        return Double.compare(time, o.time);
    }

}
