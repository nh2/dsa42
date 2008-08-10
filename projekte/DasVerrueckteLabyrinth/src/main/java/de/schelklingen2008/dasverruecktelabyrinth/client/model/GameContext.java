package de.schelklingen2008.dasverruecktelabyrinth.client.model;

import java.util.logging.Logger;

import de.schelklingen2008.dasverruecktelabyrinth.model.GameModel;
import de.schelklingen2008.dasverruecktelabyrinth.model.PlayerType;
import de.schelklingen2008.util.LoggerFactory;

/**
 * Provides all necessary game information. On top of the game model it adds information on player names and
 * on which player corresponds to the client the context is used in.
 */
public class GameContext
{

    private static final Logger sLogger   = LoggerFactory.create();

    /** Contains the rules and the state of the game. */
    private GameModel           gameModel = null;

    /** Is the name of the player playing in this client. */
    private String              myName;

    public String getMyName()
    {
        return myName;
    }

    public void setMyName(String myName)
    {
        sLogger.fine("setMyName: " + myName);
        this.myName = myName;
    }

    public PlayerType getMyPlayerType()
    {
        return gameModel.getPlayer(myName).getPlayerType();
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
