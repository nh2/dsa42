package de.schelklingen2008.canasta.client;

import javax.swing.JComponent;

import com.threerings.toybox.util.GameViewTest;
import com.threerings.toybox.util.ToyBoxContext;

import de.schelklingen2008.canasta.client.controller.Controller;
import de.schelklingen2008.canasta.client.model.GameContext;
import de.schelklingen2008.canasta.client.view.BoardView;

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
        // ctx.setPlayers(new String[] { "dick", "doof" });
        ctx.setLocalPlayerNumber(0);
        return new BoardView(controller);
    }
}
