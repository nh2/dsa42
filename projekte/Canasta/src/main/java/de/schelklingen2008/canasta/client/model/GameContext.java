package de.schelklingen2008.canasta.client.model;

import java.util.logging.Logger;

import de.schelklingen2008.canasta.model.GameModel;
import de.schelklingen2008.canasta.model.Player;
import de.schelklingen2008.util.LoggerFactory;

/**
 * Provides all necessary game information. On top of the game model it adds information on player names and
 * on which player corresponds to the client the context is used in.
 */
public class GameContext
{

    private static final Logger sLogger = LoggerFactory.create();

    /**
     * Contains the rules and the state of the game. TODO correct player count (should be optained from
     * controller)
     */
    private GameModel           gameModel;

    /** Is the name of the player playing in this client. */
    private int                 localPlayerNumber;

    //
    // /** Provides a name for each player in the game. */
    // private Map<Player, String> playerNames = new HashMap<Player, String>();

    public GameContext()
    {
        /*
         * TODO retrieve player names from controller
         */

        gameModel = new GameModel(new String[] { "Lars", "Alexander" });
    }

    public int getLocalPlayerNumber()
    {
        return localPlayerNumber;
    }

    public void setLocalPlayerNumber(int localPlayerNumber)
    {
        sLogger.fine("setMyName: " + localPlayerNumber);
        this.localPlayerNumber = localPlayerNumber;
    }

    /*
     * might be solved in constructor
     */
    // public void setPlayers(Player[] players)
    // {
    // playerNames.clear();
    // playerNames.put(Player.valueOf(0), names[0]);
    // playerNames.put(Player.valueOf(1), names[1]);
    // }
    public Player getMyPlayer()
    {
        return gameModel.getPlayers()[localPlayerNumber];
    }

    public GameModel getGameModel()
    {
        return gameModel;
    }
}
