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

    public void placePlayer(BodyObject client, int x, int y)
    {
        gameModel.placePlayer(x, y, getPlayerType(client));
        updateSharedState();
        if (gameModel.isFinished()) endGame();
    }

    @Override
    protected void gameWillStart()
    {
        super.gameWillStart();

        gameModel = new GameModel(getPlayerCount());
        updateSharedState();
    }

    // TODO add methods to make a move, etc. that can be called by clients

    public void rechtsDrehen(BodyObject client)
    {
        if (getPlayerType(client) == gameModel.getTurnHolder())
        {
            gameModel.rechtsDrehen();
        }
    }

    public void linksDrehen(BodyObject client)
    {

        if (getPlayerType(client) == gameModel.getTurnHolder())
        {
            gameModel.linksDrehen();
        }
    }

    public void insert(BodyObject client)
    {
        if (getPlayerType(client) == gameModel.getTurnHolder())
        {
            gameModel.insert(null);
        }
    }

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
