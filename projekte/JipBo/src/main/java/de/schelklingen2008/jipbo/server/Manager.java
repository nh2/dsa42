package de.schelklingen2008.jipbo.server;

import com.threerings.crowd.data.BodyObject;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.parlor.game.server.GameManager;

import de.schelklingen2008.jipbo.model.GameModel;
import de.schelklingen2008.jipbo.model.Player;
import de.schelklingen2008.jipbo.transport.SharedState;

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

        String[] playerNames = new String[getPlayerCount()];
        for (int i = 0; i < playerNames.length; i++)
            playerNames[i] = getPlayer(i).username.toString();

        gameModel = new GameModel();
        gameModel.createGame(playerNames);
        updateSharedState();
    }

    // TODO add methods to make a move, etc. that can be called by clients

    public void putCard(BodyObject client, int pCard, boolean pFromHand, int pToCard)
    {
        // TODO fix me
        getPlayer(client);
        gameModel.putCard(client.getOid(), pCard, pFromHand, pToCard);
        updateSharedState();
    }

    /**
     * Updates the shared state and thus send changes to all clients.
     */
    private void updateSharedState()
    {
        sharedState.setModel(gameModel);
    }

    private Player getPlayer(BodyObject client)
    {
        return gameModel.getPlayerByName(client.username.toString());
    }
}
