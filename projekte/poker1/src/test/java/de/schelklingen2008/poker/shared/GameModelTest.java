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
        assertEquals(1200, gameModel.getPot());
        assertEquals(1, gameModel.getHighestBetIndex());
        assertEquals(600, gameModel.getHighestBet());
    }

    public void testSecondMove() throws Exception
    {
        String[] TEST_NAMES = new String[] { "Tobias", "Matthias", "Georg", "Ben", "Jo", "Paula" };
        GameModel gameModel = new GameModel(TEST_NAMES);

        gameModel.call(3);

        for (int i = 0; i < 6; i++)
        {
            if (i != 4)
            {
                assertEquals(false, gameModel.mustCallOrReRaise(i));
                assertEquals(false, gameModel.mustCheckOrRaise(i));
                assertEquals(false, gameModel.playerIsTurnHolder(i));
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

        assertEquals(true, gameModel.mustCallOrReRaise(4));
        assertEquals(false, gameModel.mustCheckOrRaise(4));
        gameModel.reRaise(4, 500);
        assertEquals(1900, gameModel.getPot());
        assertEquals(4, gameModel.getHighestBetIndex());
        assertEquals(900, gameModel.getHighestBet());
    }

    public void testThirdMove() throws Exception
    {
        String[] TEST_NAMES = new String[] { "Tobias", "Matthias", "Georg", "Ben", "Jo", "Paula" };
        GameModel gameModel = new GameModel(TEST_NAMES);

        gameModel.call(3);
        gameModel.call(4);

        assertEquals(1400, gameModel.getPot());
        assertEquals(2, gameModel.getHighestBetIndex());
        assertEquals(400, gameModel.getHighestBet());
        assertEquals(5, gameModel.getActPlayerIndex());

        for (int i = 0; i < 6; i++)
        {
            if (i != 5)
            {
                assertEquals(false, gameModel.mustCallOrReRaise(i));
                assertEquals(false, gameModel.mustCheckOrRaise(i));
                assertEquals(false, gameModel.playerIsTurnHolder(i));
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
        }

        try
        {
            gameModel.raise(5, 500);
            fail("Spieler muss reraisen, folden oder callen");
        }
        catch (IllegalStateException e)
        {
            // erwartet
        }

        try
        {
            gameModel.check(5);
            fail("Spieler muss reraisen, folden oder callen");
        }
        catch (IllegalStateException e)
        {
            // erwartet
        }

        assertEquals(true, gameModel.mustCallOrReRaise(5));
        assertEquals(false, gameModel.mustCheckOrRaise(5));
        gameModel.reRaise(5, 500);
        assertEquals(2300, gameModel.getPot());
        assertEquals(5, gameModel.getHighestBetIndex());
        assertEquals(900, gameModel.getHighestBet());
    }

    public void testEntrySecondPhase() throws Exception
    {
        String[] TEST_NAMES = new String[] { "Tobias", "Matthias", "Georg", "Ben", "Jo", "Paula" };
        GameModel gameModel = new GameModel(TEST_NAMES);

        assertEquals(2, gameModel.getHighestBetIndex());

        gameModel.call(3);
        gameModel.call(4);
        gameModel.call(5);
        gameModel.call(0);
        gameModel.call(1);
        gameModel.check(2);

        assertEquals(2200, gameModel.getPot());
        assertEquals(1, gameModel.getHighestBetIndex());
        assertEquals(0, gameModel.getHighestBet());
        assertEquals(1, gameModel.getActPlayerIndex());

        for (int i = 0; i < 6; i++)
        {
            if (i != 1)
            {
                assertEquals(false, gameModel.mustCallOrReRaise(i));
                assertEquals(false, gameModel.mustCheckOrRaise(i));
                assertEquals(false, gameModel.playerIsTurnHolder(i));
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
        }

        try
        {
            gameModel.reRaise(1, 500);
            fail("Spieler muss raisen, folden oder checken");
        }
        catch (IllegalStateException e)
        {
            // erwartet
        }

        try
        {
            gameModel.call(1);
            fail("Spieler muss raisen, folden oder checken");
        }
        catch (IllegalStateException e)
        {
            // erwartet
        }

        assertEquals(true, gameModel.mustCallOrReRaise(5));
        assertEquals(false, gameModel.mustCheckOrRaise(5));
        gameModel.raise(5, 500);
        assertEquals(2300, gameModel.getPot());
        assertEquals(2, gameModel.getHighestBetIndex());
        assertEquals(400, gameModel.getHighestBet());
    }
}
