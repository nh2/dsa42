package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.samskivert.swing.ShapeIcon;

import de.schelklingen2008.dasverruecktelabyrinth.client.Constants;
import de.schelklingen2008.dasverruecktelabyrinth.client.controller.Controller;
import de.schelklingen2008.dasverruecktelabyrinth.client.controller.GameChangeListener;
import de.schelklingen2008.dasverruecktelabyrinth.client.model.GameContext;
import de.schelklingen2008.dasverruecktelabyrinth.model.GameModel;
import de.schelklingen2008.dasverruecktelabyrinth.model.PlayerType;

/**
 * Displays a list of players and turn change information in a turn-based game.
 */
public class TurnPanel extends JPanel implements GameChangeListener
{

    private Controller             controller;
    private static final Polygon   TRIANGLE  = new Polygon(new int[] { 0, 12, 0 }, new int[] { 0, 6, 12 }, 3);
    private static final Ellipse2D CIRCLE    = new Ellipse2D.Float(0, 0, 12, 12);
    private static final ShapeIcon ICON_TURN = new ShapeIcon(TRIANGLE, Color.PINK, null);

    public TurnPanel(Controller controller)
    {
        this.controller = controller;
        controller.addChangeListener(this);

        gameChanged();
    }

    public void gameChanged()
    {
        removeAll();
        if (getGameModel() == null) return;

        GridBagLayout gridbag = new GridBagLayout();
        setLayout(gridbag);

        GridBagConstraints turnHolderConstraints = new GridBagConstraints();
        turnHolderConstraints.fill = GridBagConstraints.BOTH;

        GridBagConstraints namesConstraints = new GridBagConstraints();
        namesConstraints.fill = GridBagConstraints.BOTH;
        namesConstraints.weightx = 1.0;
        namesConstraints.insets.left = 10;
        namesConstraints.gridwidth = GridBagConstraints.REMAINDER;

        PlayerType turnHolder = getGameModel().getTurnHolder();

        for (int i = 0; i < getGameModel().getPlayers().size(); i++)
        {
        	PlayerType playerType = PlayerType.valueOf(i);
            JLabel turnHolderLabel = new JLabel();
            turnHolderLabel.setForeground(Color.BLACK);
            if (playerType.equals(turnHolder)) turnHolderLabel.setIcon(ICON_TURN);
            add(turnHolderLabel, turnHolderConstraints);

            String name = getGameModel().getName(playerType);
            
            int neededTCs = getGameModel().getHiddenCards(playerType).size();

            Color color = Constants.COL_PIECE.get(playerType);

            JLabel namesLabel = new JLabel(name + " sucht noch " + neededTCs + " Sch�tze.");
            namesLabel.setIcon(new ShapeIcon(CIRCLE, color, null));
            add(namesLabel, namesConstraints);
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
