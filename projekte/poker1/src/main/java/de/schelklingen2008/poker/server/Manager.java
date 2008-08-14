package de.schelklingen2008.poker.server;

import java.util.Iterator;
import java.util.List;
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
        {
            names[i] = getPlayer(i).username.toString();
            sLogger.finer("gameWillStart name: " + names[i]);
        }

        gameModel = new GameModel(names);
        updateSharedState();
    }

    public void call(BodyObject client)
    {
        sLogger.fine("call by " + client.username);
        gameModel.call(getPlayerIndex(client));
        updateSharedState();
    }

    public void check(BodyObject client)
    {
        sLogger.fine("check by " + client.username);
        gameModel.check(getPlayerIndex(client));
        updateSharedState();
    }

    public void fold(BodyObject client)
    {
        sLogger.fine("fold by " + client.username);
        gameModel.fold(getPlayerIndex(client));
        updateSharedState();
    }

    public void raise(BodyObject client, long bet)
    {
        sLogger.fine("raise by " + client.username);
        gameModel.raise(getPlayerIndex(client), bet);
        updateSharedState();
    }

    public void reRaise(BodyObject client, long bet)
    {
        sLogger.fine("reRaise by " + client.username);
        gameModel.reRaise(getPlayerIndex(client), bet);
        updateSharedState();
    }

    public void okay(BodyObject client)
    {
        gameModel.setOkayWithNextRound(getPlayerIndex(client), true);
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
        String clientName = client.username.toString();
        for (Iterator<Player> iterator = gameModel.getPlayerList().iterator(); iterator.hasNext();)
        {
            Player player = iterator.next();
            if (player.getName().equals(clientName)) return player;
        }
        throw new IllegalStateException("did not find player: " + clientName);
    }

    private int getPlayerIndex(BodyObject client)
    {
        List<Player> players = gameModel.getPlayerList();
        String clientName = client.username.toString();
        for (int i = 0; i < players.size(); i++)
        {
            if (players.get(i).getName().equals(clientName)) return i;
        }
        throw new IllegalStateException("did not find player: " + clientName);
    }
}
