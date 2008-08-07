package de.schelklingen2008.poker.server;

import java.util.Iterator;

import com.threerings.crowd.data.BodyObject;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.parlor.game.server.GameManager;

import de.schelklingen2008.poker.model.GameModel;
import de.schelklingen2008.poker.model.Player;
import de.schelklingen2008.poker.transport.SharedState;

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

    public void call(BodyObject client)
    {
        // TODO ans Model weitergeben
        updateSharedState();
    }

    public void check(BodyObject client)
    {
        // TODO ans Model weitergeben
        updateSharedState();
    }

    public void fold(BodyObject client)
    {
        // TODO ans Model weitergeben
        updateSharedState();
    }

    public void raise(BodyObject client, int bet)
    {

        // TODO and model weitergeben
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
        for (Iterator iterator = gameModel.getPlayerList().iterator(); iterator.hasNext();)
        {
            Player player = (Player) iterator.next();
            if (player.getName().equals(client.username)) return player;
        }
        return null;
    }
}
