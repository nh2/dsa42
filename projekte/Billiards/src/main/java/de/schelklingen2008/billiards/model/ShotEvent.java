package de.schelklingen2008.billiards.model;

public class ShotEvent
{

    private final double angle;
    private final double velocity;
    private final Player player;

    public ShotEvent(double angle, double velocity, Player player)
    {
        this.angle = angle;
        this.velocity = velocity;
        this.player = player;
    }

    public double getAngle()
    {
        return angle;
    }

    public double getVelocity()
    {
        return velocity;
    }

    public Player getPlayer()
    {
        return player;
    }

}
