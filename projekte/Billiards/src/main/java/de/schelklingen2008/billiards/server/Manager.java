package de.schelklingen2008.billiards.server;

import java.util.logging.Logger;

import com.threerings.crowd.data.BodyObject;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.parlor.game.server.GameManager;

import de.schelklingen2008.billiards.model.GameModel;
import de.schelklingen2008.billiards.model.Player;
import de.schelklingen2008.billiards.transport.SharedState;
import de.schelklingen2008.util.LoggerFactory;

/**
 * Handles the server side of the game.
 */
public class Manager extends GameManager
{

    private class ShotCalculationThread extends Thread
    {

        @Override
        public void run()
        {
            long startTick = System.currentTimeMillis(), milliSecondsPassed = 0;
            while (gameModel.isInMotion())
            {
                gameModel.processTimeStep(0.01d);
                milliSecondsPassed += 10;
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

    private Logger logger = LoggerFactory.create();

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

        logger.info(String.format("Received shot from %s, sending game state to other clients.", client.username));

        gameModel.takeShot(getPlayer(client), angle, velocity);

        updateSharedState();

        startShotCalculationThread();

    }

    private void startShotCalculationThread()
    {
        new ShotCalculationThread().start();
    }

}
