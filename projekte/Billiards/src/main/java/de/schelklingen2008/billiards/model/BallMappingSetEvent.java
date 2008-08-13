package de.schelklingen2008.billiards.model;

public class BallMappingSetEvent
{

    private final Player player, otherPlayer;
    private final Ball.BallType playersBalls, otherPlayersBalls;

    public BallMappingSetEvent(Player player,
                               Player otherPlayer,
                               Ball.BallType playersBalls,
                               Ball.BallType otherPlayersBalls)
    {
        this.player = player;
        this.otherPlayer = otherPlayer;
        this.playersBalls = playersBalls;
        this.otherPlayersBalls = otherPlayersBalls;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Player getOtherPlayer()
    {
        return otherPlayer;
    }

    public Ball.BallType getPlayersBalls()
    {
        return playersBalls;
    }

    public Ball.BallType getOtherPlayersBalls()
    {
        return otherPlayersBalls;
    }

}
