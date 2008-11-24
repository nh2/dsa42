package de.schelklingen2008.doppelkopf.client.view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.schelklingen2008.doppelkopf.client.controller.Controller;
import de.schelklingen2008.doppelkopf.client.controller.GameChangeListener;
import de.schelklingen2008.doppelkopf.client.model.GameContext;
import de.schelklingen2008.doppelkopf.model.GameModel;
import de.schelklingen2008.doppelkopf.model.Spieler;

/**
 * Displays a list of players and turn change information in a turn-based game.
 */
public class TurnPanel extends JPanel implements GameChangeListener
{
	private static final long serialVersionUID = 1L;
	
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

        for (Spieler p : getGameModel().getSpieler())
        {
            int gesamtpunkte = 0;
            for (int punkt : p.rundenpunkte)
                gesamtpunkte += punkt;
            String name = p.getName();
            name += " : " + gesamtpunkte;

            add(new JLabel(name));
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
