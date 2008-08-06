package de.schelklingen2008.canasta.server;

import com.threerings.crowd.data.BodyObject;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.parlor.game.server.GameManager;

import de.schelklingen2008.canasta.model.GameModel;
import de.schelklingen2008.canasta.model.Player;
import de.schelklingen2008.canasta.transport.SharedState;

/**
 * Handles the server side of the game.
 */
public class Manager extends GameManager
{

    /** Is the state transmitted to the clients and managed by the server. */
    private SharedState sharedState;

    /** Implements the game logic with an own internal model. */
    private GameModel   gameModel;

    @Override
    protected PlaceObject createPlaceObject()
    {
        return sharedState = new SharedState();
    }

    @Override
    protected void gameWillStart()
    {
        super.gameWillStart();

        /**
         * TODO optain player names from controller???
         */
        gameModel = new GameModel(new String[] { "Lars", "Alexander" });
        updateSharedState();
    }

    // TODO add methods to make a move, etc. that can be called by clients

    /**
     * Updates the shared state and thus send changes to all clients.
     */
    private void updateSharedState()
    {
        // TODO update the shared state
        // e.g.: sharedState.setTurnHolder(gameModel.getTurnHolder());
    }

    private Player getPlayer(BodyObject client)
    {
        /**
         * TODO ????????????
         */
        return null;
    }
}
