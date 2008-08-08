package de.schelklingen2008.risiko.client.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.samskivert.swing.ShapeIcon;

import de.schelklingen2008.risiko.client.controller.Controller;
import de.schelklingen2008.risiko.client.controller.GameChangeListener;
import de.schelklingen2008.risiko.client.model.GameContext;
import de.schelklingen2008.risiko.model.GameModel;

/**
 * Displays a list of players and turn change information in a turn-based game.
 */
public class TurnPanel extends JPanel implements GameChangeListener
{

    // TODO turnpanel throws NullPointer

    private Controller             controller;
    private String                 msgWin;

    private static final Polygon   TRIANGLE  = new Polygon(new int[] { 0, 12, 0 }, new int[] { 0, 6, 12 }, 3);
    private static final Ellipse2D CIRCLE    = new Ellipse2D.Float(0, 0, 12, 12);
    private static final ShapeIcon ICON_TURN = new ShapeIcon(TRIANGLE, Color.YELLOW, null);

    public TurnPanel(Controller controller)
    {
        this.controller = controller;
        controller.addChangeListener(this);

        msgWin = controller.getMessage(de.schelklingen2008.risiko.client.Constants.MSG_WINNER);

        gameChanged();
    }

    public void gameChanged()
    {
        removeAll();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        GridBagLayout gridbag = new GridBagLayout();
        setLayout(gridbag);

        GridBagConstraints turnHolderConstraints = new GridBagConstraints();
        turnHolderConstraints.fill = GridBagConstraints.BOTH;

        GridBagConstraints nameAndCountConstraints = new GridBagConstraints();
        nameAndCountConstraints.fill = GridBagConstraints.BOTH;
        nameAndCountConstraints.weightx = 1.0;
        nameAndCountConstraints.insets.left = 10;
        nameAndCountConstraints.gridwidth = GridBagConstraints.REMAINDER;

        // TODO auskommentierte Teile throw nullpointer weil player nicht init werden
        for (int i = 0; i < 5/* getGameModel().getPlayerArray().length */; i++)
        {
            /* Player player = getGameModel().valueOf(i); */
            JLabel turnHolderLabel = new JLabel();
            turnHolderLabel.setForeground(Color.BLACK);
            /*
             * if (getGameModel().isWinner(player)) turnHolderLabel.setText(msgWin); if
             * (player.equals(getGameModel().getTurnholder())) turnHolderLabel.setIcon(ICON_TURN);
             * add(turnHolderLabel, turnHolderConstraints);
             */

            String name = "a";/* getGameContext().getName(player); */
            // count = number of units player has in all countrys
            int count = i;/* player.getPlayerUnits(); */
            Color color = Color.BLUE;/* player.getPlayerColor(); */
            JLabel nameAndCountLabel = new JLabel(name + ": " + count);
            nameAndCountLabel.setIcon(new ShapeIcon(CIRCLE, color, null));
            add(nameAndCountLabel, nameAndCountConstraints);
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
