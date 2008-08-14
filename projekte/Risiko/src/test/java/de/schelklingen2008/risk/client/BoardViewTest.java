package de.schelklingen2008.risk.client;

import javax.swing.JComponent;

import com.threerings.toybox.util.GameViewTest;
import com.threerings.toybox.util.ToyBoxContext;

import de.schelklingen2008.risk.client.controller.Controller;
import de.schelklingen2008.risk.client.model.GameContext;
import de.schelklingen2008.risk.client.view.BoardView;

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
        ctx.setMyName("dick");
        return new BoardView(controller);
    }
}
