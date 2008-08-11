package de.schelklingen2008.jipbo.shared;

import junit.framework.TestCase;
import de.schelklingen2008.jipbo.model.GameModel;

public class GameModelTest extends TestCase
{

    private GameModel gameModel = new GameModel();

    public void testCreateGame() throws Exception
    {
        String[] playerNames = new String[] { "Elitsa", "Fabio" };
        gameModel.createGame(playerNames);
        assertEquals("Should be Elitsa", "Elitsa", gameModel.getPlayerNameIndexOf(0));
        assertEquals("Should be Fabio", "Fabio", gameModel.getPlayerNameIndexOf(1));

        assertEquals("There should be 2 players", 2, gameModel.getPlayerSize());

        assertEquals("This should be 0", 0, gameModel.getPlayerIDByName(new String("Elitsa")));
        assertEquals("This should be 1", 1, gameModel.getPlayerIDByName(new String("Fabio")));
        // assertEquals("This should be 2", -1, gameModel.getPlayerIDByName(new
        // String("Kein Spieler gefunden.")));

        assertEquals("This should be 3 ", 3, gameModel.getLastStockCard());
    }

}
