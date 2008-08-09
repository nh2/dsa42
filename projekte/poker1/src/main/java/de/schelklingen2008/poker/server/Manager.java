package de.schelklingen2008.poker.server;

import java.util.Iterator;
import java.util.logging.Logger;

import com.threerings.crowd.data.BodyObject;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.parlor.game.server.GameManager;

import de.schelklingen2008.poker.model.GameModel;
import de.schelklingen2008.poker.model.Player;
import de.schelklingen2008.poker.transport.SharedState;
import de.schelklingen2008.util.LoggerFactory;

/**
 * Handles the server side of the game.
 */
public class Manager extends GameManager
{

    private static final Logger sLogger = LoggerFactory.create();

    /** Is the state transmitted to the clients and managed by the server. */
    private SharedState         sharedState;

    /** Implements the game logic with an own internal model. */
    private GameModel           gameModel;

    @Override
    protected PlaceObject createPlaceObject()
    {
        return sharedState = new SharedState();
    }

    @Override
    protected void gameWillStart()
    {
        super.gameWillStart();

        int playerCount = getPlayerCount();
        String[] names = new String[playerCount];
        for (int i = 0; i < names.length; i++)
            names[i] = getPlayer(i).username.toString();

        gameModel = new GameModel(names);
        updateSharedState();
    }

    // TODO add methods to make a move, etc. that can be called by clients

    public void call(BodyObject client)
    {
        sLogger.fine("call by " + client);
        gameModel.call();
        updateSharedState();
    }

    public void check(BodyObject client)
    {
        sLogger.fine("check by " + client);
        gameModel.check();
        updateSharedState();
    }

    public void fold(BodyObject client)
    {
        sLogger.fine("fold by " + client);
        gameModel.fold();
        updateSharedState();
    }

    public void raise(BodyObject client, long bet)
    {
        sLogger.fine("raise by " + client);
        gameModel.raise(bet);
        updateSharedState();
    }

    public void reRaise(BodyObject client, long bet)
    {
        sLogger.fine("reRaise by " + client);
        gameModel.reRaise(bet);
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
