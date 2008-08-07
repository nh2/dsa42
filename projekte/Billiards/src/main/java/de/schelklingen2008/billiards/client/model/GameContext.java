package de.schelklingen2008.billiards.client.model;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import de.schelklingen2008.billiards.model.GameModel;
import de.schelklingen2008.billiards.model.Player;
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

    public String getName(int id)
    {
        return playerNames.get(gameModel.getPlayer(id));
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
        playerNames.clear();
        playerNames.put(gameModel.getPlayer(0), names[0]);
        playerNames.put(gameModel.getPlayer(1), names[1]);
    }

    public Player getMyPlayer()
    {
        if (myName == null) return null;
        if (myName.equals(playerNames.get(0))) return gameModel.getPlayer(0);
        if (myName.equals(playerNames.get(1))) return gameModel.getPlayer(1);
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
