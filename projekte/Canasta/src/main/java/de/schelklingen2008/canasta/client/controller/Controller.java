package de.schelklingen2008.canasta.client.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.threerings.crowd.client.PlaceView;
import com.threerings.crowd.data.PlaceConfig;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.crowd.util.CrowdContext;
import com.threerings.parlor.game.client.GameController;
import com.threerings.presents.dobj.AttributeChangeListener;
import com.threerings.presents.dobj.AttributeChangedEvent;
import com.threerings.presents.dobj.ElementUpdateListener;
import com.threerings.presents.dobj.ElementUpdatedEvent;
import com.threerings.presents.dobj.EntryAddedEvent;
import com.threerings.presents.dobj.EntryRemovedEvent;
import com.threerings.presents.dobj.EntryUpdatedEvent;
import com.threerings.presents.dobj.SetListener;
import com.threerings.toybox.util.ToyBoxContext;
import com.threerings.util.MessageBundle;

import de.schelklingen2008.canasta.client.Constants;
import de.schelklingen2008.canasta.client.model.GameContext;
import de.schelklingen2008.canasta.client.view.GamePanel;
import de.schelklingen2008.canasta.model.Card;
import de.schelklingen2008.canasta.model.GameModel;
import de.schelklingen2008.canasta.model.Player;
import de.schelklingen2008.canasta.transport.SharedState;
import de.schelklingen2008.util.LoggerFactory;

/**
 * Handles the client side of the game.
 */
public class Controller extends GameController
{

    private static final Logger      sLogger         = LoggerFactory.create();

    private GameContext              gameContext     = new GameContext();

    private List<GameChangeListener> changeListeners = new ArrayList<GameChangeListener>();

    private SharedState              sharedState;

    private ToyBoxContext            toyBoxContext;

    @Override
    public void init(CrowdContext crowdContext, PlaceConfig placeConfig)
    {
        sLogger.finer("trace");

        toyBoxContext = (ToyBoxContext) crowdContext;
        gameContext.setMyName(toyBoxContext.getUsername().toString());
        super.init(crowdContext, placeConfig);
    }

    @Override
    protected PlaceView createPlaceView(CrowdContext crowdContext)
    {
        sLogger.finer("trace");
        return new GamePanel(this);
    }

    @Override
    public void didLeavePlace(PlaceObject placeObject)
    {
        super.didLeavePlace(placeObject);
        sLogger.finer("trace");

        sharedState = null;
    }

    @Override
    public void willEnterPlace(PlaceObject placeObject)
    {
        sLogger.finer("trace");

        sharedState = (SharedState) placeObject;
        sharedState.addListener(new SharedStateListener());
        updateGameContext();
        super.willEnterPlace(placeObject);
    }

    public void leaveButtonClicked()
    {
        toyBoxContext.getLocationDirector().moveBack();
    }

    public void talonClicked()
    {
        if (!gameContext.isMyTurn())
        {
            sLogger.info("Ich bin nicht dran!");
            return;
        }
        if (gameContext.getGameModel().hasDrawn())
        {
            sLogger.info("Es wurde schon eine Karte gezogen!");
            return;
        }
        sharedState.manager.invoke("drawCard");
    }

    public void discardClicked(int[] selectedCardNumbers, boolean isDiscardSelected)
    {
        // discard card
        if (!gameContext.isMyTurn())
        {
            sLogger.info("Ich bin nicht dran!");
            return;
        }

        if (selectedCardNumbers.length != 1)
        {
            sLogger.info("Select exactly one card please!");
            sLogger.info(Arrays.toString(selectedCardNumbers));
            return;
        }

        sharedState.manager.invoke("discardCard", selectedCardNumbers[0]);
    }

    public void makeOutlay(int[] selectedCardNumbers, boolean isDiscardSelected)
    {
        sLogger.info("make Outlay");
        if (!gameContext.isMyTurn())
        {
            sLogger.info("Ich bin nicht dran!");
            return;
        }

        Player player = getGameContext().getMyPlayer();

        Card[] cards = player.getHand().getAll(selectedCardNumbers);

        if (getGameContext().getGameModel().hasDrawn())
        {
            // outlay machen

            if (GameModel.getRank(cards) == null)
            {
                sLogger.info("cards in a cardstack must have the same rank!");
                return;
            }

            if (player.getOutlay().size() <= 0 && !GameModel.isFirstMeldLegal(player, cards))
            {
                sLogger.info("first meld not enough points!");
                return;
            }

            sharedState.manager.invoke("makeOutlay", selectedCardNumbers);
        }
        else
        {
            // discard nehmen
            if (!isDiscardSelected)
            {
                sLogger.info("draw card first!");
                return;
            }

            cards = Arrays.copyOf(cards, cards.length + 1);
            cards[cards.length - 1] = getGameContext().getGameModel().getDiscard().peek();

            if (!GameModel.isMeldLegal(cards))
            {
                sLogger.info("impossible!");
                return;
            }

            if (player.getOutlay().size() <= 0 && !GameModel.isFirstMeldLegal(player, cards))
            {
                sLogger.info("first meld not enough points!");
                for (Card card : cards)
                {
                    sLogger.info(card.toString());
                }

                return;
            }
            sharedState.manager.invoke("takeDiscard", selectedCardNumbers);
        }
    }

    public void cardStackClicked(int[] selectedCardNumbers, boolean isDiscardSelected, int whichCardStack)
    {
        if (!gameContext.isMyTurn())
        {
            sLogger.info("Ich bin nicht dran!");
            return;
        }

        Player player = getGameContext().getMyPlayer();
        Card[] cards = player.getHand().getAll(selectedCardNumbers);

        if (getGameContext().getGameModel().hasDrawn())
        {
            // make outlay
            if (!GameModel.isMeldLegal(player.getHand().getAll(selectedCardNumbers), player.getOutlay()
                                                                                           .get(whichCardStack))) return;

            sharedState.manager.invoke("addCardsToStack", selectedCardNumbers, whichCardStack);
        }
        else
        {
            // take discard
            if (!isDiscardSelected)
            {
                sLogger.info("draw card first!");
                return;
            }
            cards = Arrays.copyOf(cards, cards.length + 1);
            cards[cards.length - 1] = getGameContext().getGameModel().getDiscard().peek();

            if (!GameModel.isMeldLegal(cards))
            {
                sLogger.info("impossible!");
                return;
            }

            sharedState.manager.invoke("takeDiscardToStack", selectedCardNumbers, whichCardStack);

        }
    }

    private void updateGameContext()
    {
        if (sharedState == null) return;
        if (sharedState.getModel() == null) return;

        // gameContext.setPlayers(sharedState.getPlayerNames());
        gameContext.setGameModel(sharedState.getModel());

        fireGameChange();
    }

    public void addChangeListener(GameChangeListener listener)
    {
        changeListeners.add(listener);
    }

    public void removeChangeListeners()
    {
        changeListeners.clear();
    }

    private void fireGameChange()
    {
        for (GameChangeListener listener : changeListeners)
            listener.gameChanged();
    }

    private MessageBundle getMsgBundle()
    {
        return toyBoxContext.getMessageManager().getBundle(Constants.BUNDLE_NAME);
    }

    public String getMessage(String key)
    {
        return getMsgBundle().get(key);
    }

    public GameContext getGameContext()
    {
        return gameContext;
    }

    public ToyBoxContext getToyBoxContext()
    {
        return toyBoxContext;
    }

    private class SharedStateListener implements AttributeChangeListener, SetListener, ElementUpdateListener
    {

        public void attributeChanged(AttributeChangedEvent event)
        {
            updateGameContext();
        }

        public void elementUpdated(ElementUpdatedEvent event)
        {
            updateGameContext();
        }

        public void entryAdded(EntryAddedEvent event)
        {
            updateGameContext();
        }

        public void entryRemoved(EntryRemovedEvent event)
        {
            updateGameContext();
        }

        public void entryUpdated(EntryUpdatedEvent event)
        {
            updateGameContext();
        }
    }

}
