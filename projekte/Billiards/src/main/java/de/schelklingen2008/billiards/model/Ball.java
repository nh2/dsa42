package de.schelklingen2008.billiards.model;

import java.awt.Color;

import de.schelklingen2008.billiards.util.Vector2d;

public class Ball
{

    public enum BallType
    {
        BLACK, WHITE, SOLID, STRIPED
    }

    private BallType type;
    private Color    color;
    private Vector2d position;
    private Vector2d velocity;
    private boolean  sunk;

    public Ball(BallType type, Color color)
    {
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
        position = position;
    }

    public Vector2d getVelocity()
    {
        return velocity;
    }

    void setVelocity(Vector2d velocity)
    {
        velocity = velocity;
    }

    public boolean isSunk()
    {
        return sunk;
    }

    void setSunk(boolean sunk)
    {
        this.sunk = sunk;
    }

}
