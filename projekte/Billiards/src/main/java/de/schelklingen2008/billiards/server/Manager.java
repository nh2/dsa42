package de.schelklingen2008.billiards.server;

import com.threerings.crowd.data.BodyObject;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.parlor.game.server.GameManager;

import de.schelklingen2008.billiards.model.GameModel;
import de.schelklingen2008.billiards.model.Player;
import de.schelklingen2008.billiards.transport.SharedState;

/**
 * Handles the server side of the game.
 */
public class Manager extends GameManager
{

    /** Is the state transmitted to the clients and managed by the server. */
    private SharedState sharedState;

    /** Implements the game logic with an own internal model. */
    private GameModel gameModel;

    @Override
    protected PlaceObject createPlaceObject()
    {
        return sharedState = new SharedState();
    }

    @Override
    protected void gameWillStart()
    {
        super.gameWillStart();

        gameModel = new GameModel();
        updateSharedState();
    }

    // TODO add methods to make a move, etc. that can be called by clients

    /**
     * Updates the shared state and thus send changes to all clients.
     */
    private void updateSharedState()
    {
        sharedState.setModel(gameModel);
    }

    private Player getPlayer(BodyObject client)
    {
        return gameModel.getPlayer(getPlayerIndex(client.username));
    }

    public void takeShot(BodyObject client, double angle, double velocity)
    {

        gameModel.takeShot(getPlayer(client), angle, velocity);
        updateSharedState();

        long startTick = System.currentTimeMillis(), milliSecondsPassed = 0;
        while (gameModel.isInMotion())
        {
            gameModel.processTimeStep(0.001d);
            milliSecondsPassed++;
        }

        long deltaTime = System.currentTimeMillis() - startTick;

        try
        {
            Thread.sleep(milliSecondsPassed - deltaTime);
        }
        catch (InterruptedException e)
        {

        }

        updateSharedState();

    }

}
