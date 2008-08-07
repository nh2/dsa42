package de.schelklingen2008.jipbo.client.model;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import de.schelklingen2008.jipbo.model.GameModel;
import de.schelklingen2008.jipbo.model.Player;
import de.schelklingen2008.util.LoggerFactory;

/**
 * Provides all necessary game information. On top of the game model it adds information on player names and
 * on which player corresponds to the client the context is used in.
 */
public class GameContext
{

    private static final Logger sLogger     = LoggerFactory.create();

    /** Contains the rules and the state of the game. */
    private GameModel           gameModel   = new GameModel();

    /** Is the name of the player playing in this client. */
    private String              myName;

    /** Provides a name for each player in the game. */
    private Map<Player, String> playerNames = new HashMap<Player, String>();

    public String getName(Player player)
    {
        return playerNames.get(player);
    }

    public String getMyName()
    {
        return myName;
    }

    public void setMyName(String myName)
    {
        sLogger.fine("setMyName: " + myName);
        this.myName = myName;
    }

    public void setPlayers(String[] names)
    {
        // TODO
    }

    public Player getMyPlayer()
    {
        // TODO
        return null;
    }

    public GameModel getGameModel()
    {
        return gameModel;
    }

    public void setGameModel(GameModel model)
    {
        gameModel = model;
    }
}
