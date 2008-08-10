package de.schelklingen2008.poker.client;

import javax.swing.JComponent;

import com.threerings.toybox.util.GameViewTest;
import com.threerings.toybox.util.ToyBoxContext;

import de.schelklingen2008.poker.client.controller.Controller;
import de.schelklingen2008.poker.client.model.GameContext;
import de.schelklingen2008.poker.client.view.BoardView;
import de.schelklingen2008.poker.model.GameModel;

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
        ctx.setMyName("Matthias");

        GameModel model = controller.getGameContext().getGameModel();
        model.setPlayers(new String[] { "Tobias", "Matthias", "Georg", "Ben" });

        return new BoardView(controller);

    }
}
