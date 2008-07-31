package de.schelklingen2008.reversi.shared;

import junit.framework.TestCase;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class GameModelTest extends TestCase
{

    public void testFirstLegal() throws Exception
    {
        GameModel logic = new GameModel();
        assertTrue("move must be legal", logic.isLegalMove(2, 3, Player.WHITE));
        assertTrue("move must be legal", logic.isLegalMove(3, 2, Player.WHITE));
        assertTrue("move must be legal", logic.isLegalMove(4, 5, Player.WHITE));
        assertTrue("move must be legal", logic.isLegalMove(5, 4, Player.WHITE));

        assertFalse("move must not be legal", logic.isLegalMove(2, 4, Player.WHITE));
        assertFalse("move must not be legal", logic.isLegalMove(4, 2, Player.WHITE));
        assertFalse("move must not be legal", logic.isLegalMove(3, 5, Player.WHITE));
        assertFalse("move must not be legal", logic.isLegalMove(5, 3, Player.WHITE));

        for (int i = -1; i < GameModel.SIZE + 1; i++)
        {
            assertFalse("move must not be legal", logic.isLegalMove(i, i, Player.WHITE));
            assertFalse("move must not be legal", logic.isLegalMove(i, 7 - i, Player.WHITE));
        }

        for (int x = -1; x < GameModel.SIZE + 1; x++)
            for (int y = -1; y < GameModel.SIZE + 1; y++)
                assertFalse("black is not the turnholder", logic.isLegalMove(x, y, Player.BLACK));
    }

    public void testFirstMove() throws Exception
    {
        GameModel logic = new GameModel();

        assertTrue("move must be legal", logic.isLegalMove(2, 3, Player.WHITE));
        assertEquals("piece ist not placed yet", null, logic.getPlayer(2, 3));
        assertEquals("black is the owner at the beginning", Player.BLACK, logic.getPlayer(3, 3));
        assertEquals("white is the owner at the beginning", Player.WHITE, logic.getPlayer(4, 3));

        logic.placePiece(2, 3, Player.WHITE);
        assertEquals("piece must have been placed", Player.WHITE, logic.getPlayer(2, 3));
        assertEquals("piece must have been flipped", Player.WHITE, logic.getPlayer(3, 3));
        assertEquals("white must still be the owner", Player.WHITE, logic.getPlayer(4, 3));
    }

    public void testSecondLegal() throws Exception
    {
        GameModel logic = new GameModel();
        logic.placePiece(2, 3, Player.WHITE);

        assertTrue("move must be legal", logic.isLegalMove(2, 2, Player.BLACK));
        assertTrue("move must be legal", logic.isLegalMove(2, 4, Player.BLACK));
        assertTrue("move must be legal", logic.isLegalMove(4, 2, Player.BLACK));

        assertFalse("move must not be legal", logic.isLegalMove(3, 5, Player.BLACK));
        assertFalse("move must not be legal", logic.isLegalMove(5, 3, Player.BLACK));

        for (int i = -1; i < GameModel.SIZE + 1; i++)
        {
            assertFalse("move must not be legal", logic.isLegalMove(i, 7 - i, Player.BLACK));
        }

        for (int x = -1; x < GameModel.SIZE + 1; x++)
            for (int y = -1; y < GameModel.SIZE + 1; y++)
                assertFalse("white is not the turnholder", logic.isLegalMove(x, y, Player.WHITE));
    }

    public void testSecondMove() throws Exception
    {
        GameModel logic = new GameModel();

        logic.placePiece(2, 3, Player.WHITE);

        assertTrue("move must be legal", logic.isLegalMove(2, 2, Player.BLACK));
        assertEquals("piece ist not placed yet", null, logic.getPlayer(2, 2));
        assertEquals("black is the owner at the beginning", Player.WHITE, logic.getPlayer(3, 3));
        assertEquals("white is the owner at the beginning", Player.BLACK, logic.getPlayer(4, 4));

        logic.placePiece(2, 2, Player.BLACK);
        assertEquals("piece must have been placed", Player.BLACK, logic.getPlayer(2, 2));
        assertEquals("piece must have been flipped", Player.BLACK, logic.getPlayer(3, 3));
        assertEquals("white must still be the owner", Player.BLACK, logic.getPlayer(4, 4));
    }
}
