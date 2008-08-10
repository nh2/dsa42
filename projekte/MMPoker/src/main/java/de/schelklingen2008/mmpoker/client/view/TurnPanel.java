package de.schelklingen2008.mmpoker.client.view;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.schelklingen2008.mmpoker.client.controller.Controller;
import de.schelklingen2008.mmpoker.client.controller.GameChangeListener;
import de.schelklingen2008.mmpoker.client.model.GameContext;
import de.schelklingen2008.mmpoker.model.GameModel;
import de.schelklingen2008.mmpoker.model.Spieler;

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
        removeAll();

        if (getGameModel() == null) return;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        List<Spieler> liste = getGameModel().getSpielerliste();
        for (Spieler spieler : liste)
        {
            add(new JLabel(spieler.getName()));
        }

        revalidate();
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
