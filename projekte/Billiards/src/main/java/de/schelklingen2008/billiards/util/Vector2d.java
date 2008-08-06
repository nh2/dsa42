package de.schelklingen2008.billiards.util;

import static java.lang.Math.PI;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Vector2d
{

    public static final Vector2d ZERO = new Vector2d(0, 0);

    private double               x, y, length = Double.NaN, angle = Double.NaN;

    private Vector2d()
    {

    }

    public Vector2d(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ temp >>> 32);
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Vector2d other = (Vector2d) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) return false;
        return true;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    private Vector2d(double x, double y, double length, double angle)
    {
        this(x, y);
        this.length = length;
        this.angle = angle;
    }

    public double getSquaredLength()
    {
        return x * x + y * y;
    }

    public double getLength()
    {
        if (Double.isNaN(length))
        {
            length = Math.sqrt(getSquaredLength());
        }
        return length;
    }

    public double getAngle()
    {

        if (x == 0)
        {
            if (y == 0)
            {
                return Double.NaN; // undefined
            }
            else
            {
                return PI / 2; // 90°
            }
        }
        else
        {
            double result = atan(y / x);
            if (x < 0)
            {
                result += PI;
            }
            return result;
        }

    }

    public Vector2d rotate(double alpha)
    {

        double newX, newY, newAngle = Double.NaN;

        newX = cos(alpha) * x - sin(alpha) * y;
        newY = sin(alpha) * x + cos(alpha) * y;

        if (!Double.isNaN(angle))
        {
            newAngle = angle + alpha;
        }

        return new Vector2d(newX, newY, length, newAngle);

    }

    public Vector2d scale(double factor)
    {

        double newX = factor * x, newY = factor * y;
        double newLength = factor * length;

        return new Vector2d(newX, newY, newLength, angle);

    }

    public Vector2d add(Vector2d v)
    {
        return new Vector2d(x + v.x, y + v.y);
    }

    public Vector2d subtract(Vector2d v)
    {
        return new Vector2d(x - v.x, y - v.y);
    }

    @Override
    public String toString()
    {
        return String.format("(%.2f; %.2f)", x, y);
    }

}
