package de.schelklingen2008.poker.client.model;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import de.schelklingen2008.poker.model.GameModel;
import de.schelklingen2008.poker.model.Player;
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
    private int                 myIndex;

    /** Provides a name for each player in the game. */
    private Map<Player, String> playerNames = new HashMap<Player, String>();

    public String getName(Player player)
    {
        return playerNames.get(player);
    }

    public String getMyName()
    {
        return getGameModel().getPlayerList().get(myIndex).getName();
    }

    public int getMyIndex()
    {
        return myIndex;
    }

    public void setMyName(String myName)
    {
        sLogger.fine("setMyName: " + myName);
        for (int i = 0; i < gameModel.getPlayerList().size(); i++)
        {
            if (gameModel.getPlayerList().get(i).getName().equals(myName) == true)
            {
                this.myName = myName;
                myIndex = i;
            }
        }
    }

    public void setPlayers(String[] names)
    {
        for (int i = 0; i < names.length; i++)
        {
            String string = names[i];
            gameModel.getPlayerList().add(new Player(names[i]));
        }

    }

    public Player getMyPlayer()
    {
        if (myName == null) return null;
        return gameModel.getPlayerList().get(myIndex);
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
