package de.schelklingen2008.canasta.shared;

import junit.framework.TestCase;
import de.schelklingen2008.canasta.client.Constants;
import de.schelklingen2008.canasta.model.GameModel;
import de.schelklingen2008.canasta.model.Player;

public class GameModelTest extends TestCase
{

    public void testInitialState()
    {
        GameModel logic = new GameModel(new String[] { "Player 1", "Player 2" });

        assertEquals("discard pile has wrong size", 1, logic.getDiscard().size());
        assertEquals("playernumber is not two", 2, logic.getPlayers().length);
        for (Player player : logic.getPlayers())
        {
            assertEquals("player " + player.getName() + " has wrong card number in hands",
                         Constants.CANASTA_INITIAL_CARD_COUNT, player.getHand().size());

            assertEquals("player " + player.getName() + " has outlay", 0, player.getOutlay().size());
        }
    }

    public void testCardCounts()
    {
        GameModel logic = new GameModel(new String[] { "Player 1", "Player 2" });

        Player[] players = logic.getPlayers();

        int talonSize = logic.getTalon().size();
        int discardSize = logic.getDiscard().size();
        int handSizes[] = new int[logic.getPlayers().length];

        int i = 0;
        for (Player player : players)
        {
            handSizes[i] = player.getHand().size();
            i++;
        }

        logic.drawCard(players[0]);
        assertEquals(talonSize - 1, logic.getTalon().size());
        assertEquals(discardSize, logic.getDiscard().size());
        assertEquals(handSizes[0] + 1, players[0].getHand().size());
        assertEquals(handSizes[1], players[1].getHand().size());

        logic.drawCard(players[1]);
        assertEquals(talonSize - 1, logic.getTalon().size());
        assertEquals(discardSize, logic.getDiscard().size());
        assertEquals(handSizes[0] + 1, players[0].getHand().size());
        assertEquals(handSizes[1], players[1].getHand().size());

        logic.discardCard(players[0], 0);
        assertEquals(talonSize - 1, logic.getTalon().size());
        assertEquals(discardSize + 1, logic.getDiscard().size());
        assertEquals(handSizes[0], players[0].getHand().size());
        assertEquals(handSizes[1], players[1].getHand().size());

        logic.drawCard(players[1]);
        assertEquals(talonSize - 2, logic.getTalon().size());
        assertEquals(discardSize + 1, logic.getDiscard().size());
        assertEquals(handSizes[0], players[0].getHand().size());
        assertEquals(handSizes[1] + 1, players[1].getHand().size());
    }

}
