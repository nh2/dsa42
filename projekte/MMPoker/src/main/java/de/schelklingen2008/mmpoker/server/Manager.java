package de.schelklingen2008.mmpoker.server;

import com.threerings.crowd.data.BodyObject;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.parlor.game.server.GameManager;

import de.schelklingen2008.mmpoker.model.GameModel;
import de.schelklingen2008.mmpoker.model.Player;
import de.schelklingen2008.mmpoker.transport.SharedState;

/**
 * Handles the server side of the game.
 */
public class Manager extends GameManager {

    /** Is the state transmitted to the clients and managed by the server. */
    private SharedState sharedState;

    /** Implements the game logic with an own internal model. */
    private GameModel   gameModel;

    @Override
    protected PlaceObject createPlaceObject() {
        return sharedState = new SharedState();
    }

    @Override
    protected void gameWillStart() {
        super.gameWillStart();

        gameModel = new GameModel();
        updateSharedState();
    }

    // TODO add methods to make a move, etc. that can be called by clients

    public void fold(BodyObject client) {
        // TODO game model anpassen
        updateSharedState();

    }

    public void check(BodyObject client) {
        // TODO game model anpassen
        updateSharedState();

    }

    public void bet(BodyObject client, int wettsumme) {
        gameModel.betraise(wettsumme, getPlayer(client));
        updateSharedState();

    }

    public void raise(BodyObject client, int wettsumme) {
        gameModel.betraise(wettsumme, getPlayer(client));
        updateSharedState();

    }

    /**
     * Updates the shared state and thus send changes to all clients.
     */
    private void updateSharedState() {
        sharedState.setModel(gameModel);
    }

    private Player getPlayer(BodyObject client) {
        return Player.valueOf(getPlayerIndex(client.username));
    }
}
