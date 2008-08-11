package de.schelklingen2008.canasta.shared;

import junit.framework.TestCase;
import de.schelklingen2008.canasta.client.Constants;
import de.schelklingen2008.canasta.model.Card;
import de.schelklingen2008.canasta.model.CardStack;
import de.schelklingen2008.canasta.model.GameModel;
import de.schelklingen2008.canasta.model.Hand;
import de.schelklingen2008.canasta.model.Outlay;
import de.schelklingen2008.canasta.model.Player;
import de.schelklingen2008.canasta.model.Rank;
import de.schelklingen2008.canasta.model.Suit;

public class GameModelTest extends TestCase
{

    public void testInitialState()
    {
        GameModel logic = new GameModel(new String[] { "Player 1", "Player 2" });

        // how many cards are in the discard pile?
        assertEquals("discard pile has wrong size", 1, logic.getDiscard().size());

        // how many players are there?
        assertEquals("playernumber is not two", 2, logic.getPlayers().length);
        for (Player player : logic.getPlayers())
        {
            // has every player the right card number in hand?
            assertEquals("player " + player.getName() + " has wrong card number in hands",
                         Constants.GAME_INIT_CARD_COUNT, player.getHand().size());
            // is the outlay of every player empty?
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

    public void testMelding()
    {
        GameModel logic = new GameModel(new String[] { "Player 1", "Player 2" });

        Player[] players = logic.getPlayers();

        int handSizes[] = new int[logic.getPlayers().length];
        int i = 0;
        for (Player player : players)
        {
            handSizes[i] = player.getHand().size();
            i++;

            assertEquals(0, player.getOutlay().size());
        }

        logic.meldCards(players[0], new int[] { 0, 1, 2, 3, 4, 5, 6 });

        assertEquals(1, players[0].getOutlay().size());
        assertEquals(7, players[0].getOutlay().get(0).size());

    }

    public void testGetRank()
    {

        Card testCards1[] = new Card[] { new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.ACE, Suit.DIAMONDS),
                new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.ACE, Suit.DIAMONDS),
                new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.ACE, Suit.DIAMONDS),
                new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.ACE, Suit.DIAMONDS) };

        Card testCards2[] = new Card[] { new Card(Rank.QUEEN, Suit.DIAMONDS), new Card(Rank.QUEEN, Suit.DIAMONDS),
                new Card(Rank.JOKER, Suit.DIAMONDS), new Card(Rank.QUEEN, Suit.DIAMONDS),
                new Card(Rank.QUEEN, Suit.DIAMONDS), new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.QUEEN, Suit.DIAMONDS), new Card(Rank.QUEEN, Suit.DIAMONDS) };

        Card testCards3[] = new Card[] { new Card(Rank.QUEEN, Suit.DIAMONDS), new Card(Rank.ACE, Suit.DIAMONDS),
                new Card(Rank.JOKER, Suit.DIAMONDS), new Card(Rank.QUEEN, Suit.DIAMONDS),
                new Card(Rank.THREE, Suit.DIAMONDS), new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.QUEEN, Suit.DIAMONDS), new Card(Rank.TEN, Suit.DIAMONDS) };

        assertEquals(Rank.ACE, GameModel.getRank(testCards1));
        assertEquals(Rank.QUEEN, GameModel.getRank(testCards2));
        assertEquals(null, GameModel.getRank(testCards3));
    }

    public void testFirstMeld()
    {
        Player player = new Player("Horst");
        player.setTotalScore(Constants.GAME_SCORE_LEVEL[0]);

        assertTrue(GameModel.isFirstMeldLegal(player, new Card[] { new Card(Rank.ACE, Suit.DIAMONDS),
                new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.ACE, Suit.DIAMONDS) }));
        assertFalse(GameModel.isFirstMeldLegal(player, new Card[] { new Card(Rank.TEN, Suit.DIAMONDS),
                new Card(Rank.TEN, Suit.DIAMONDS), new Card(Rank.TEN, Suit.DIAMONDS) }));

        player.setTotalScore(Constants.GAME_SCORE_LEVEL[1]);

        assertTrue(GameModel.isFirstMeldLegal(player, new Card[] { new Card(Rank.ACE, Suit.DIAMONDS),
                new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.ACE, Suit.DIAMONDS),
                new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.ACE, Suit.DIAMONDS) }));
        assertFalse(GameModel.isFirstMeldLegal(player,
                                               new Card[] { new Card(Rank.TEN, Suit.DIAMONDS),
                                                       new Card(Rank.TEN, Suit.DIAMONDS),
                                                       new Card(Rank.TEN, Suit.DIAMONDS),
                                                       new Card(Rank.TEN, Suit.DIAMONDS) }));
    }

    public void testMeld()
    {
        CardStack stack = new CardStack();
        Card[] cards;

        stack.add(new Card(Rank.JACK, Suit.CLUBS));
        stack.add(new Card(Rank.JACK, Suit.CLUBS));
        stack.add(new Card(Rank.JACK, Suit.CLUBS));
        assertEquals(3, stack.size());

        cards = new Card[] { new Card(Rank.JACK, Suit.CLUBS) };
        assertTrue(GameModel.isMeldLegal(cards, stack));

        cards = new Card[] { new Card(Rank.JACK, Suit.CLUBS), new Card(Rank.JACK, Suit.CLUBS),
                new Card(Rank.JACK, Suit.CLUBS), new Card(Rank.JACK, Suit.CLUBS), new Card(Rank.JACK, Suit.CLUBS) };
        assertTrue(GameModel.isMeldLegal(cards, stack));

        cards = new Card[] { new Card(Rank.JOKER, null) };
        assertTrue(GameModel.isMeldLegal(cards, stack));

        cards = new Card[] { new Card(Rank.TWO, Suit.CLUBS) };
        assertTrue(GameModel.isMeldLegal(cards, stack));

        cards = new Card[] { new Card(Rank.JOKER, null), new Card(Rank.JOKER, null), new Card(Rank.JOKER, null) };
        assertFalse(GameModel.isMeldLegal(cards, stack));

        cards = new Card[] { new Card(Rank.TWO, Suit.CLUBS), new Card(Rank.JOKER, null), new Card(Rank.JOKER, null) };
        assertFalse(GameModel.isMeldLegal(cards, stack));

        cards = new Card[] { new Card(Rank.JACK, null), new Card(Rank.JOKER, null), new Card(Rank.JOKER, null),
                new Card(Rank.JOKER, null) };
        assertTrue(GameModel.isMeldLegal(cards, stack));

        cards = new Card[] { new Card(Rank.QUEEN, Suit.CLUBS) };
        assertFalse(GameModel.isMeldLegal(cards, stack));

        cards = new Card[] { new Card(Rank.QUEEN, Suit.CLUBS), new Card(Rank.JOKER, null) };
        assertFalse(GameModel.isMeldLegal(cards, stack));

        stack.add(new Card(Rank.JOKER, null));
        assertEquals(4, stack.size());

        cards = new Card[] { new Card(Rank.JOKER, null) };
        assertTrue(GameModel.isMeldLegal(cards, stack));

        cards = new Card[] { new Card(Rank.JOKER, null), new Card(Rank.JOKER, null) };
        assertFalse(GameModel.isMeldLegal(cards, stack));

        cards = new Card[] { new Card(Rank.JACK, null), new Card(Rank.JOKER, null), new Card(Rank.JOKER, null) };
        assertTrue(GameModel.isMeldLegal(cards, stack));

        cards = new Card[] { new Card(Rank.JACK, Suit.CLUBS) };
        assertTrue(GameModel.isMeldLegal(cards, stack));
    }

    public void testScoring()
    {
        Player player = new Player("Horst");
        Hand hand = player.getHand();
        Outlay outlay = player.getOutlay();
        outlay.clear();
        assertEquals(0, player.getCurrentScore());

        CardStack stack = new CardStack();
        stack.add(new Card(Rank.JACK, Suit.CLUBS));
        stack.add(new Card(Rank.JACK, Suit.CLUBS));
        stack.add(new Card(Rank.JACK, Suit.CLUBS));
        outlay.add(stack);
        assertEquals(30, player.getCurrentScore());

        outlay.clear();

        stack = new CardStack();
        stack.add(new Card(Rank.JACK, Suit.CLUBS));
        stack.add(new Card(Rank.JACK, Suit.CLUBS));
        stack.add(new Card(Rank.JACK, Suit.CLUBS));
        stack.add(new Card(Rank.JACK, Suit.CLUBS));
        stack.add(new Card(Rank.JACK, Suit.CLUBS));
        stack.add(new Card(Rank.JACK, Suit.CLUBS));
        stack.add(new Card(Rank.JACK, Suit.CLUBS));
        outlay.add(stack);
        assertEquals(70 + Constants.SCORE_CANASTA_NATURAL, player.getCurrentScore());

        hand.add(new Card(Rank.KING, Suit.CLUBS));
        hand.add(new Card(Rank.KING, Suit.CLUBS));
        hand.add(new Card(Rank.KING, Suit.CLUBS));
        assertEquals(40 + Constants.SCORE_CANASTA_NATURAL, player.getCurrentScore());
    }
}
