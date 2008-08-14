package de.schelklingen2008.jipbo.shared;

import junit.framework.TestCase;
import de.schelklingen2008.jipbo.client.Constants;
import de.schelklingen2008.jipbo.model.Card;
import de.schelklingen2008.jipbo.model.GameModel;
import de.schelklingen2008.jipbo.model.Player;

public class GameModelTest extends TestCase
{

    private String[]  mPlayerNames = new String[] { "Elitsa", "Fabio" };
    private GameModel gameModel    = new GameModel(mPlayerNames);

    public void testCreateGame() throws Exception
    {
        Player player0 = gameModel.getPlayerIndexOf(0);
        Player player1 = gameModel.getPlayerIndexOf(1);
        Player elitsa = gameModel.getPlayerByName("Elitsa");
        Player fabio = gameModel.getPlayerByName("Fabio");

        // setup test data
        elitsa.getDrawPile()[0].setNumber(1);

        assertEquals("Should be Elitsa", "Elitsa", gameModel.getPlayerNameIndexOf(0));
        assertEquals("Should be Fabio", "Fabio", gameModel.getPlayerNameIndexOf(1));

        assertEquals("There should be 2 players", 2, gameModel.getPlayerSize());

        assertEquals("Elitsa must be the turnholder.", elitsa, gameModel.getTurnHolder());

        assertEquals("This should be 0", 0, gameModel.getPlayerIDByName(new String("Elitsa")));
        assertEquals("This should be 1", 1, gameModel.getPlayerIDByName(new String("Fabio")));

        try
        {
            gameModel.getPlayerIDByName(new String("Harry"));
            fail("Harry is not a player. An exception should be thrown.");
        }
        catch (IllegalStateException e)
        {
            // Das ist die erwartete Exception.
        }

        assertEquals("There should be " + (162 - gameModel.getPlayerSize() * Constants.DURATION - 5) + " cards left.",
                     162 - gameModel.getPlayerSize() * Constants.DURATION - 5, gameModel.getStockCards().size());

        Card[] buildPile = gameModel.getBuildPile();
        assertEquals("There should be 5 piles.", 5, buildPile.length);

        assertEquals("There should be 4 piles.", 4, player0.getDiscardPile().length);

        Card[] drawPile0 = player0.getDrawPile();
        Card[] drawPile1 = player1.getDrawPile();
        assertEquals("There should be 5 cards in the draw pile.", 5, drawPile0.length);
        assertEquals("There should be 5 cards in the draw pile.", 5, drawPile1.length);

        for (int i = 0; i < drawPile0.length; i++)
        {
            assertTrue(drawPile0[i].getNumber() != Constants.EMPTY);
        }
        for (int i = 0; i < drawPile1.length; i++)
        {
            assertEquals(Constants.EMPTY, drawPile1[i].getNumber());
        }

        int stockPileSize = player0.getStockPile().size();
        assertEquals(Constants.DURATION, stockPileSize);
        for (int i = 0; i < 5; i++)
        {
            player0.removeLastCardFromStockPile();
        }
        assertEquals("There must be five cards out of the StockPile", stockPileSize - 5, player0.getStockPile().size());

        assertEquals(Constants.EMPTY, buildPile[3].getNumber());
        gameModel.putCard(elitsa.getName(), 0, true, 3);
        assertEquals(Constants.EMPTY, drawPile0[0].getNumber());
        assertEquals(1, buildPile[3].getNumber());

        assertEquals("Elitsa must be the turnholder.", elitsa, gameModel.getTurnHolder());

        int firstCard = elitsa.getDrawPile()[1].getNumber();
        gameModel.placeCardInDiscardPile("Elitsa", 1, firstCard, 2);

        assertEquals("Fabio must be the turnholder.", fabio, gameModel.getTurnHolder());
        assertEquals("Should be empty (-2)", Constants.EMPTY, elitsa.getDrawPile()[1].getNumber());

        assertEquals("Should be the old card from hand", firstCard, elitsa.getDiscardPile()[2].getNumber());
        assertEquals("There should be a vacant place in the discard pile.", Constants.EMPTY,
                     player0.getDiscardPile()[0].getNumber());
        assertEquals("There should be a vacant place in the discard pile.", Constants.EMPTY,
                     player0.getDiscardPile()[1].getNumber());
        assertEquals("There should be a vacant place in the discard pile.", Constants.EMPTY,
                     player0.getDiscardPile()[3].getNumber());

        // Fabio must have been dealt 5 cards to hand.
        for (int i = 0; i < drawPile1.length; i++)
        {
            assertFalse(Constants.EMPTY == drawPile1[i].getNumber());
        }

        int initialSize = gameModel.getPlayerIndexOf(0).getStockPile().size();
        for (int i = 0; i < initialSize; i++)
        {
            gameModel.getPlayerIndexOf(0).removeLastCardFromStockPile();
        }
        assertEquals("There should be no cards left in the Stockpile and there should be a winner.", 0,
                     gameModel.getPlayerIndexOf(0).getStockPile().size());

        for (int i = 0; i < drawPile0.length; i++)
        {
            gameModel.getPlayerIndexOf(0).removeDrawPileCard(i);
        }
        for (int i = 0; i < drawPile0.length; i++)
        {
            assertEquals(Constants.EMPTY, drawPile0[i].getNumber());
        }
        assertEquals("There should be a vacant place in the drawpile.", 5, drawPile0.length);

        if (gameModel.getWinner() == player0)
        {
            assertEquals("There should be a winner.", gameModel.getPlayerNameIndexOf(0), gameModel.getWinner()
                                                                                                  .equals(mPlayerNames));

            Player winner = player0;
            assertEquals(winner + " is the winner.", gameModel.getWinner(), player0);
            assertEquals("Game is over.", gameModel.getStockCards().isEmpty(), gameModel.isFinished());

            // }
            // else
            // {
            // System.out.println("There is no winner.");

            // for (int i = 0; i < drawPile0.length; i++)
            // {
            // gameModel.getPlayerIndexOf(0).setDrawPile(drawPile0);
            // }
            // assertEquals("The next player should have received 5 cards for his drawpile. ", 5,
            // gameModel.getPlayerIndexOf(0).getDrawPile());
            //
            // assertEquals("There should be 5 cards in the draw pile.", 5,
            // gameModel.getPlayerIndexOf(0).getDrawPile());
            //
            // if (gameModel.getWinner() == player0)
            // {
            // assertEquals("There should be a winner.", gameModel.getPlayerNameIndexOf(0),
            // gameModel.getWinner()
            // .equals(mPlayerNames));
            // Player winner = player0;
            // assertEquals(winner + " is the winner.", gameModel.getWinner(), player0);
            // assertEquals("Game is over.", gameModel.getStockCards().isEmpty(), gameModel.isFinished());
            // }
            // else
            // {
            // System.out.println("There is no winner.");
            //
            // }
            //
        }
    }
}
