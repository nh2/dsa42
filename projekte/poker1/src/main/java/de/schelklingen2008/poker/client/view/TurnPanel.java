package de.schelklingen2008.poker.client.view;

import javax.swing.JPanel;

import de.schelklingen2008.poker.client.controller.Controller;
import de.schelklingen2008.poker.client.controller.GameChangeListener;
import de.schelklingen2008.poker.client.model.GameContext;
import de.schelklingen2008.poker.model.GameModel;

/**
 * Displays a list of players and turn change information in a turn-based game.
 */
public class TurnPanel extends JPanel implements GameChangeListener
{

    private Controller controller;

    public TurnPanel(Controller controller)
    {
        this.controller = controller;
        controller.addChangeListener(this);
    }

    public void gameChanged()
    {
        // removeAll();
        //
        // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //
        // for (Player player : Player.values())
        // {
        // String name = getGameContext().getName(player);
        // add(new JLabel(name));
        // }
        //
        // revalidate();
        repaint();
    }

    private GameContext getGameContext()
    {
        return controller.getGameContext();
    }

    private GameModel getGameModel()
    {
        return getGameContext().getGameModel();
    }
}
