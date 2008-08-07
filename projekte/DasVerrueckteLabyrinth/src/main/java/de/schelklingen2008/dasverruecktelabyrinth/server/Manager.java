package de.schelklingen2008.dasverruecktelabyrinth.server;

import com.threerings.crowd.data.BodyObject;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.parlor.game.server.GameManager;

import de.schelklingen2008.dasverruecktelabyrinth.model.GameModel;
import de.schelklingen2008.dasverruecktelabyrinth.model.PlayerType;
import de.schelklingen2008.dasverruecktelabyrinth.transport.SharedState;

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

    private PlayerType getPlayerType(BodyObject client)
    {
        return PlayerType.valueOf(getPlayerIndex(client.username));
    }
}
