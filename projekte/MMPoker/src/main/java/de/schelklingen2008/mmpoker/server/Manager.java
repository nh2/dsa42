package de.schelklingen2008.mmpoker.server;

import com.threerings.crowd.data.BodyObject;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.parlor.game.server.GameManager;
import com.threerings.util.Name;

import de.schelklingen2008.mmpoker.model.GameModel;
import de.schelklingen2008.mmpoker.model.Spieler;
import de.schelklingen2008.mmpoker.transport.SharedState;

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

        String[] names = new String[getPlayerCount()];
        for (int i = 0; i < names.length; i++)
            names[i] = getPlayer(i).username.toString();

        gameModel = new GameModel(names);
        updateSharedState();
    }

    public void fold(BodyObject client)
    {
        gameModel.fold(getPlayer(client));
        updateSharedState();

    }

    public void check(BodyObject client)
    {
        gameModel.check(getPlayer(client));
        updateSharedState();

    }

    public void bet(BodyObject client, String wettsumme)
    {
        gameModel.betraise(wettsumme, getPlayer(client));
        updateSharedState();

    }

    public void raise(BodyObject client, String wettsumme)
    {
        gameModel.betraise(wettsumme, getPlayer(client));
        updateSharedState();

    }

    /**
     * Updates the shared state and thus send changes to all clients.
     */
    private void updateSharedState()
    {
        sharedState.setModel(gameModel);
    }

    private Spieler getPlayer(BodyObject client)
    {
        Name username = client.username;
        for (int i = 1; i < gameModel.getSpielerliste().size(); i++)
        {

            Spieler spieler = gameModel.getSpielerliste().get(i);
            if (username.toString().equals(spieler.getName())) return spieler;

        }
        throw new IllegalStateException("Name not Found:" + username);
    }
}
