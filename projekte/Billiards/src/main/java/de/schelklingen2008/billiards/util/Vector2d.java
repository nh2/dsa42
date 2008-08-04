package de.schelklingen2008.billiards.util;
import static java.lang.Math.PI;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Vector2d
{

    private double x, y, length = Double.NaN, angle = Double.NaN;

    private Vector2d()
    {

    }

    public Vector2d(double x, double y)
    {
        this.x = x;
        this.y = y;
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
            if (y < 0)
            {
                result += PI; // atan() always returns
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

}
