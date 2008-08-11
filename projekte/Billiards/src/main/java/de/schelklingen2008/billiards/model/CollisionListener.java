package de.schelklingen2008.billiards.model;

public interface CollisionListener
{

    public void ballCollisionImminent(BallCollisionEvent e);

    public void ballCollisionHappened(BallCollisionEvent e);

    public void wallCollisionImminent(WallCollisionEvent e);

    public void wallCollisionHappened(WallCollisionEvent e);

}
