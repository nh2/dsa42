package de.schelklingen2008.mmpoker.client;

import javax.swing.JComponent;

import com.threerings.toybox.util.GameViewTest;
import com.threerings.toybox.util.ToyBoxContext;

import de.schelklingen2008.mmpoker.client.controller.Controller;
import de.schelklingen2008.mmpoker.client.model.GameContext;
import de.schelklingen2008.mmpoker.client.view.PokerPanel;

/**
 * A test harness for our board view.
 */
public class BoardViewTest extends GameViewTest {

    public static void main(String[] args) {
        BoardViewTest test = new BoardViewTest();
        test.display();
    }

    @Override
    protected JComponent createInterface(ToyBoxContext tbc) {
        Controller controller = new Controller();
        GameContext ctx = controller.getGameContext();
        ctx.setPlayers(new String[] { "dick", "doof" });
        ctx.setMyName("dick");
        return new PokerPanel(controller);
    }
}
