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
        try
        {
            gameModel.call(0);
            fail("Spieler ist nicht dran -> darf nicht callen");
        }
        catch (IllegalStateException e)
        {
            // erwartet
        }

        gameModel.reRaise(1, 500);
    }

}
