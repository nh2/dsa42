package de.schelklingen2008.dasverruecktelabyrinth.client;

import javax.swing.JComponent;

import com.threerings.toybox.util.GameViewTest;
import com.threerings.toybox.util.ToyBoxContext;

import de.schelklingen2008.dasverruecktelabyrinth.client.controller.Controller;
import de.schelklingen2008.dasverruecktelabyrinth.client.model.GameContext;
import de.schelklingen2008.dasverruecktelabyrinth.client.view.BoardView;
import de.schelklingen2008.dasverruecktelabyrinth.model.GameModel;

/**
 * A test harness for our board view.
 */
public class BoardViewTest extends GameViewTest
{

    public static void main(String[] args)
    {
        BoardViewTest test = new BoardViewTest();
        test.display();
    }

    @Override
    protected JComponent createInterface(ToyBoxContext tbc)
    {
        Controller controller = new Controller();
        GameContext ctx = controller.getGameContext();

        ctx.setGameModel(new GameModel(new String[] { "dick", "doof", "Hassel", "Hoff" }));
        ctx.setMyName("dick");

        return new BoardView(controller);

    }
}
