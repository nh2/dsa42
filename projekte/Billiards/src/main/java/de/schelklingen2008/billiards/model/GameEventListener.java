package de.schelklingen2008.billiards.model;

public interface GameEventListener
{

    public void gameStarted();

    public void shotTaken(ShotEvent e);

    public void ballPocketed(BallPocketedEvent e);

    public void ballPlaced(BallPlacedEvent e);

    public void ballMappingSet(BallMappingSetEvent e);

    public void gameEnded(GameEndEvent e);

    public void gameRestarted();

    public void turnHolderChanged(TurnHolderChangeEvent e);

    public void boardStoppedMoving();

}
