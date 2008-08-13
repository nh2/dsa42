package de.schelklingen2008.doppelkopf.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.threerings.crowd.data.BodyObject;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.parlor.game.server.GameManager;

import de.schelklingen2008.doppelkopf.model.Bild;
import de.schelklingen2008.doppelkopf.model.Farbe;
import de.schelklingen2008.doppelkopf.model.GameModel;
import de.schelklingen2008.doppelkopf.model.Spieler;
import de.schelklingen2008.doppelkopf.transport.SharedState;
import de.schelklingen2008.util.LoggerFactory;

/**
 * Handles the server side of the game.
 */
public class Manager extends GameManager
{

    private static final Logger logger = LoggerFactory.create();

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

        logger.log(Level.WARNING, Integer.toString(getPlayerCount()));

        String[] spielerNamen = new String[getPlayerCount()];
        for (int i = 0; i < getPlayerCount(); i++)
        {
            spielerNamen[i] = getPlayer(i).username.toString();
            logger.log(Level.WARNING, spielerNamen[i]);
        }

        gameModel = new GameModel(spielerNamen);
        updateSharedState();
    }

    // TODO add methods to make a move, etc. that can be called by clients

    public void karteAusspielen(BodyObject client, Farbe farbe, Bild bild)
    {
        Spieler spieler = getPlayer(client);
        // String name2 = player.name();
        // String name = getPlayer(client).name().toString();
        gameModel.karteAusspielen(spieler, spieler.getBlatt().getKarte(farbe, bild));
        if (gameModel.getTisch().getMitte().size() == 4)
        {
            Runnable runnable = new Runnable()
            {

                public void run()
                {
                    try
                    {
                        Thread.sleep(5000);
                    }
                    catch (InterruptedException e)
                    {
                    }
                    // Spiel fortsetzen
                    // gameModel.pause = false;
                    gameModel.stichFertigAktion();
                    updateSharedState();
                }

            };
            new Thread(runnable).start();
        }

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
        return gameModel.getSpieler().getSpieler(client.username.toString());
    }
}
