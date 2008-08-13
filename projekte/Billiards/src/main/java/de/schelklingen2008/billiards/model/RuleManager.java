package de.schelklingen2008.billiards.model;

import java.io.Serializable;

public class RuleManager implements CollisionListener, GameEventListener, Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -1327349770200883433L;

    private GameModel gameModel;

    public RuleManager(GameModel gameModel)
    {
        this.gameModel = gameModel;

        gameModel.addCollisionListener(this);
        gameModel.addGameEventListener(this);
    }

    public void ballCollisionHappened(BallCollisionEvent e)
    {

    }

    public void ballCollisionImminent(BallCollisionEvent e)
    {

    }

    public void wallCollisionHappened(WallCollisionEvent e)
    {

    }

    public void wallCollisionImminent(WallCollisionEvent e)
    {

    }

    public void ballSunk(BallSunkEvent e)
    {
        Player player = e.getPlayer();
        Ball.BallType playersBallType = gameModel.getPlayersBallType(player);
        int ballsOfPlayer;
        boolean doNotSwitchTurnholder = false, foul = false;

        if (gameModel.ballMappingFixed())
        {
            ballsOfPlayer = 0;
            for (Ball ball : gameModel.getBallsOnTable())
            {
                if (ball.getType().equals(playersBallType))
                {
                    ballsOfPlayer++;
                }
            }
        }
        else
        {
            ballsOfPlayer = -1;
        }

        if (e.getBall().getType() == Ball.BallType.BLACK)
        {

            if (!gameModel.ballMappingFixed())
            {
                gameModel.setWinner(gameModel.getOtherPlayer(player));
            }
            else if (ballsOfPlayer == 0)
            {
                gameModel.setWinner(player);
            }
            else
            {
                gameModel.setWinner(gameModel.getOtherPlayer(player));
            }

        }
        else if (e.getBall().getType() == Ball.BallType.WHITE)
        {
            if (ballsOfPlayer == 0)
            {
                gameModel.setWinner(gameModel.getOtherPlayer(player));
            }
            else
            {
                foul = true;
            }
        }
        else if (!gameModel.breakHasHappened() && !gameModel.ballMappingFixed())
        {

        }
        else
        {
            if (!e.getBall().getType().equals(gameModel.getPlayersBallType(player)))
            {
                if (ballsOfPlayer == 0)
                {
                    gameModel.setWinner(gameModel.getOtherPlayer(player));
                }
                else
                {
                    foul = true;
                }
            }
            else
            {
                doNotSwitchTurnholder = true;
            }
        }

        if (!doNotSwitchTurnholder)
        {
            gameModel.changeTurnHolder();
        }

        if (gameModel.getWhiteBall().isSunk())
        {
            gameModel.resetWhiteBall();
        }

        if (foul)
        {
            // TODO Add fouls
        }

    }

    public void gameStarted()
    {

    }

    public void shotTaken(ShotEvent e)
    {

    }

    public void ballSet(BallSetEvent e)
    {

    }

}
