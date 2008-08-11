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

    private static final Logger sLogger   = LoggerFactory.create();

    /**
     * Contains the rules and the state of the game. TODO correct player count (should be optained from
     * controller)
     */
    private GameModel           gameModel = null;

    /** Is the name of the player playing in this client. */
    private String              myName;

    public int getLocalPlayerNumber()
    {
        return gameModel.getPlayerIndex(myName);
    }

    public Player getMyPlayer()
    {
        return gameModel == null ? null : gameModel.getPlayer(myName);
    }

    public GameModel getGameModel()
    {
        return gameModel;
    }

    public void setGameModel(GameModel model)
    {
        gameModel = model;
    }

    public void setMyName(String string)
    {
        myName = string;
    }

    public boolean isMyTurn()
    {
        return getLocalPlayerNumber() == gameModel.getTurnHolder();
    }
}
