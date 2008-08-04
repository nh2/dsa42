package de.schelklingen2008.billiards.model;

import java.awt.Color;

public class Ball
{

    private Color    color;
    private Vector2d Position;
    private Vector2d Velocity;
    private boolean  sunk;

    public Color getBallColor()
    {
        return color;
    }

    public void setBallColor(Color ballColor)
    {
        color = ballColor;
    }

    public Vector2d getPosition()
    {
        return Position;
    }

    public void setPosition(Vector2d position)
    {
        Position = position;
    }

    public Vector2d getVelocity()
    {
        return Velocity;
    }

    public void setVelocity(Vector2d velocity)
    {
        Velocity = velocity;
    }

    public boolean isSunk()
    {
        return sunk;
    }

    public void setSunk(boolean sunk)
    {
        this.sunk = sunk;
    }

}
