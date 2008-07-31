package de.schelklingen2008.reversi.ai;

import java.util.Observable;
import java.util.logging.Logger;

import com.threerings.parlor.game.data.GameObject;
import com.threerings.presents.dobj.AttributeChangeListener;
import com.threerings.presents.dobj.AttributeChangedEvent;
import com.threerings.presents.dobj.ElementUpdateListener;
import com.threerings.presents.dobj.ElementUpdatedEvent;
import com.threerings.presents.dobj.EntryAddedEvent;
import com.threerings.presents.dobj.EntryRemovedEvent;
import com.threerings.presents.dobj.EntryUpdatedEvent;
import com.threerings.presents.dobj.SetListener;

import de.schelklingen2008.reversi.ai.strategy.ReversiStrategy;
import de.schelklingen2008.reversi.client.model.GameContext;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;
import de.schelklingen2008.reversi.transport.SharedState;
import de.schelklingen2008.util.LoggerFactory;

public class ReversiAI extends Observable
{

    private static final Logger       sLogger       = LoggerFactory.create();

    private static final Logger       sScoreLogger  = LoggerFactory.create("score");

    private static int                sScore        = 0;

    private static int                sGamesPlayed  = 0;

    private final GameContext         gameContext;

    private final SharedState         sharedState;

    private final ReversiStrategy     strategy      = AIToyBoxApp.REVERSI_STRATEGY;

    private final GameEventListener   listener;

    private final SharedStateListener stateListener = new SharedStateListener();

    public ReversiAI(final GameContext gameContext, final SharedState sharedState, final GameEventListener listener)
    {
        this.gameContext = gameContext;
        this.sharedState = sharedState;
        this.listener = listener;

        sharedState.addListener(stateListener);
        sharedState.manager.invoke("playerReady");
        sLogger.info("inited");
    }

    public void gameChanged()
    {
        if (sharedState == null) return;

        gameContext.setPlayers(sharedState.getPlayerNames());
        boolean changed = getGameModel().setTurnHolder(sharedState.getTurnHolder());
        getGameModel().setBoard(sharedState.getBoard());
        if (changed && gameContext.isMyTurn()) makeAMove();
        if (isFinished()) gameDidEnd();
    }

    private boolean isFinished()
    {
        if (sharedState.state == GameObject.GAME_OVER) return true;
        if (sharedState.state == GameObject.CANCELLED) return true;
        return false;
    }

    private GameModel getGameModel()
    {
        return gameContext.getGameModel();
    }

    private void makeAMove()
    {
        Piece move = strategy.move(getGameModel());
        if (move == null) return;

        sLogger.info("moved: " + move + " considered: " + strategy.getCount() + " positions.");
        sharedState.manager.invoke("placePiece", move.getX(), move.getY());
    }

    /**
     * Called when the game transitions to the <code>GAME_OVER</code> state. This happens when the game
     * reaches some end condition by normal means (is not cancelled or aborted).
     */
    protected void gameDidEnd()
    {
        GameModel gameModel = getGameModel();
        Player me = gameContext.getMyPlayer();

        int myP = gameModel.countPieces(me);
        String myName = gameContext.getMyName();

        int hisP = gameModel.countPieces(me.other());
        String hisName = gameContext.getName(me.other()).toString();

        if (myP > hisP) sScore++;
        if (myP >= hisP) sScore++;
        sGamesPlayed++;

        sScoreLogger.info(sScore
                          + ";"
                          + sGamesPlayed
                          + ";"
                          + me
                          + ";"
                          + myName
                          + ";"
                          + hisName
                          + ";"
                          + myP
                          + ";"
                          + hisP
                          + ";");

        // to make sure that two AIs find each other again, let WHITE sleep a second
        if (me == Player.WHITE) sleep(1000);

        sharedState.removeListener(stateListener);
        listener.gameDidEnd();
    }

    private void sleep(final long millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (Exception e)
        {
            sLogger.warning("sleep interrupted: " + e);
        }
    }

    private class SharedStateListener implements AttributeChangeListener, SetListener, ElementUpdateListener
    {

        public void attributeChanged(AttributeChangedEvent event)
        {
            gameChanged();
        }

        public void elementUpdated(ElementUpdatedEvent event)
        {
            gameChanged();
        }

        public void entryAdded(EntryAddedEvent event)
        {
            gameChanged();
        }

        public void entryRemoved(EntryRemovedEvent event)
        {
            gameChanged();
        }

        public void entryUpdated(EntryUpdatedEvent event)
        {
            gameChanged();
        }
    }
}
