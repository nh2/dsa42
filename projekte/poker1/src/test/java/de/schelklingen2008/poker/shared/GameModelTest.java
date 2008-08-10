package de.schelklingen2008.poker.shared;

import junit.framework.TestCase;
import de.schelklingen2008.poker.model.GameModel;

public class GameModelTest extends TestCase
{

    private static final String[] TEST_NAMES = new String[] { "tobias", "matthias" };
    private GameModel             gameModel  = new GameModel(TEST_NAMES);

    public void testInit() throws Exception
    {
        gameModel.getPlayerList();

        assertEquals(0, gameModel.getActPlayerIndex());
    }

    public void testFirstMove() throws Exception
    {
        assertEquals(0, gameModel.getActPlayerIndex());

        try
        {
            gameModel.call();
            fail("call ganz am Anfang geht nicht");
        }
        catch (IllegalStateException e)
        {
            // erwartet
        }

        gameModel.reRaise(100);

    }
}
