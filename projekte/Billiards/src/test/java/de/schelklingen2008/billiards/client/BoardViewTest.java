package de.schelklingen2008.billiards.client;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.threerings.toybox.util.GameViewTest;
import com.threerings.toybox.util.ToyBoxContext;

import de.schelklingen2008.billiards.client.controller.Controller;
import de.schelklingen2008.billiards.client.model.GameContext;
import de.schelklingen2008.billiards.client.view.BallGauge;
import de.schelklingen2008.billiards.client.view.BoardView;

/**
 * A test harness for our board view.
 */
public class BoardViewTest extends GameViewTest
{

    /**
     * 
     */
    private static final long serialVersionUID = -6078529306721733186L;

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
        ctx.setPlayers(new String[] { "dick", "doof" });
        ctx.setMyName("dick");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        BallGauge gauge = new BallGauge();
        BoardView boardView = new BoardView(controller, gauge);

        panel.add(boardView);
        panel.add(gauge);

        return panel;
    }
}
