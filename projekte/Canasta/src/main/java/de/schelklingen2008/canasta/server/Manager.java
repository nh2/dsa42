package de.schelklingen2008.canasta.server;

import java.util.Arrays;
import java.util.logging.Logger;

import com.threerings.crowd.data.BodyObject;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.parlor.game.server.GameManager;

import de.schelklingen2008.canasta.model.GameModel;
import de.schelklingen2008.canasta.model.Player;
import de.schelklingen2008.canasta.model.Rank;
import de.schelklingen2008.canasta.transport.SharedState;
import de.schelklingen2008.util.LoggerFactory;

/**
 * Handles the server side of the game.
 */
public class Manager extends GameManager
{

    private static final Logger sLogger = LoggerFactory.create();

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

        String[] names = new String[getPlayerCount()];
        for (int i = 0; i < names.length; i++)
            names[i] = getPlayer(i).username.toString();

        gameModel = new GameModel(names);
        updateSharedState();
    }

    public void drawCard(BodyObject client)
    {
        Player player = getPlayer(client);

        if (player != gameModel.getPlayers()[gameModel.getTurnHolder()]) return;

        gameModel.drawCard(player);

        updateSharedState();
    }

    public void discardCard(BodyObject client, int whichCard)
    {
        Player player = getPlayer(client);

        if (player != gameModel.getPlayers()[gameModel.getTurnHolder()]) return;

        gameModel.discardCard(player, whichCard);

        updateSharedState();
    }

    public void makeOutlay(BodyObject client, int[] cardNumbers)
    {
        Player player = getPlayer(client);

        if (player != gameModel.getPlayers()[gameModel.getTurnHolder()]) return;

        sLogger.info("make Outlay on Server: " + Arrays.toString(cardNumbers));

        gameModel.meldCards(player, cardNumbers);

        updateSharedState();
    }

    public void takeDiscard(BodyObject client, int[] cardNumbers)
    {
        Player player = getPlayer(client);

        if (player != gameModel.getPlayers()[gameModel.getTurnHolder()]) return;

        sLogger.info("take Discard on Server: " + Arrays.toString(cardNumbers));

        gameModel.takeDiscard(player, cardNumbers);

        updateSharedState();
    }

    public void takeDiscardToStack(BodyObject client, int[] cardNumbers, int whichCardStack)
    {
        Player player = getPlayer(client);

        if (player != gameModel.getPlayers()[gameModel.getTurnHolder()]) return;

        sLogger.info("take Discard on Server: " + Arrays.toString(cardNumbers));

        gameModel.takeDiscard(player, cardNumbers, whichCardStack);

        updateSharedState();
    }

    public void addCardsToStack(BodyObject client, int[] selectedCardNumbers, int whichCardStack)
    {
        Player player = getPlayer(client);

        if (player != gameModel.getPlayers()[gameModel.getTurnHolder()]) return;

        sLogger.info("add cards on Server: " + Arrays.toString(selectedCardNumbers));

        Rank handRank = GameModel.getRank(player.getHand().getAll(selectedCardNumbers));

        if (!GameModel.isMeldLegal(player.getHand().getAll(selectedCardNumbers), player.getOutlay().get(whichCardStack))) return;

        gameModel.addCardsToStack(player, selectedCardNumbers, whichCardStack);

        updateSharedState();
    }

    /**
     * Updates the shared state and thus send changes to all clients.
     */
    private void updateSharedState()
    {
        sharedState.setModel(gameModel);
    }

    private Player getPlayer(BodyObject client)
    {
        return gameModel.getPlayer(client.username.toString());
    }
}
