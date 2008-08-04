package de.schelklingen2008.billiards.model;

import java.awt.Color;

import de.schelklingen2008.billiards.util.Vector2d;

public class Ball
{

    private Color    color;
    private Vector2d position;
    private Vector2d velocity;
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
        return position;
    }

    public void setPosition(Vector2d position)
    {
        position = position;
    }

    public Vector2d getVelocity()
    {
        return velocity;
    }

    public void setVelocity(Vector2d velocity)
    {
        velocity = velocity;
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
