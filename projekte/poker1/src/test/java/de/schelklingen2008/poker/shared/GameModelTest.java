package de.schelklingen2008.poker.shared;

import junit.framework.TestCase;
import de.schelklingen2008.poker.model.GameModel;

public class GameModelTest extends TestCase
{

    public void testInitTwoPlayers() throws Exception
    {
        String[] TEST_NAMES = new String[] { "tobias", "matthias" };
        GameModel gameModel = new GameModel(TEST_NAMES);

        gameModel.getPlayerList();

        assertEquals(1, gameModel.getActPlayerIndex());
    }

    public void testInitSixPlayers() throws Exception
    {
        String[] TEST_NAMES = new String[] { "Tobias", "Matthias", "Georg", "Ben", "Jo", "Paula" };
        GameModel gameModel = new GameModel(TEST_NAMES);

        gameModel.getPlayerList();

        assertEquals(3, gameModel.getActPlayerIndex());
    }

    public void testFirstMove() throws Exception
    {
        String[] TEST_NAMES = new String[] { "tobias", "matthias" };
        GameModel gameModel = new GameModel(TEST_NAMES);
        assertEquals(1, gameModel.getActPlayerIndex());
        assertEquals(400, gameModel.getHighestBet());
        assertEquals(0, gameModel.getHighestBetIndex());

        try
        {
            gameModel.call(0);
            fail("Spieler ist nicht dran -> darf nicht callen");
        }
        catch (IllegalStateException e)
        {
            // erwartet
        }

        try
        {
            gameModel.raise(0, 500);
            fail("Spieler ist nicht dran -> darf nicht raisen");
        }
        catch (IllegalStateException e)
        {
            // erwartet
        }

        try
        {
            gameModel.fold(0);
            fail("Spieler ist nicht dran -> darf nicht folden");
        }
        catch (IllegalStateException e)
        {
            // erwartet
        }

        try
        {
            gameModel.reRaise(0, 500);
            fail("Spieler ist nicht dran -> darf nicht reraisen");
        }
        catch (IllegalStateException e)
        {
            // erwartet
        }

        try
        {
            gameModel.check(0);
            fail("Spieler ist nicht dran -> darf nicht checken");
        }
        catch (IllegalStateException e)
        {
            // erwartet
        }

        try
        {
            gameModel.check(1);
            fail("Spieler muss reraisen, folden oder callen");
        }
        catch (IllegalStateException e)
        {
            // erwartet
        }

        try
        {
            gameModel.raise(1, 500);
            fail("Spieler muss reraisen, folden oder callen");
        }
        catch (IllegalStateException e)
        {
            // erwartet
        }

        gameModel.reRaise(1, 200);

    }

    public void testSecondMove() throws Exception
    {
        String[] TEST_NAMES = new String[] { "Tobias", "Matthias", "Georg", "Ben", "Jo", "Paula" };
        GameModel gameModel = new GameModel(TEST_NAMES);

        gameModel.call(3);
        for (int i = 0; i < 4; i++)
        {
            try
            {
                gameModel.call(i);
                fail("Spieler Nr. " + i + " ist nicht dran -> darf nicht callen");
            }
            catch (IllegalStateException e)
            {
                // erwartet
            }

            try
            {
                gameModel.raise(i, 500);
                fail("Spieler ist nicht dran -> darf nicht raisen");
            }
            catch (IllegalStateException e)
            {
                // erwartet
            }

            try
            {
                gameModel.fold(i);
                fail("Spieler ist nicht dran -> darf nicht folden");
            }
            catch (IllegalStateException e)
            {
                // erwartet
            }

            try
            {
                gameModel.reRaise(i, 500);
                fail("Spieler ist nicht dran -> darf nicht reraisen");
            }
            catch (IllegalStateException e)
            {
                // erwartet
            }

            try
            {
                gameModel.check(i);
                fail("Spieler ist nicht dran -> darf nicht checken");
            }
            catch (IllegalStateException e)
            {
                // erwartet
            }
        }

        try
        {
            gameModel.raise(4, 500);
            fail("Spieler muss raisen, folden oder checken");
        }
        catch (IllegalStateException e)
        {
            // erwartet
        }

        try
        {
            gameModel.check(4);
            fail("Spieler muss raisen, folden oder checken");
        }
        catch (IllegalStateException e)
        {
            // erwartet
        }

        gameModel.reRaise(4, 500);

    }

}
