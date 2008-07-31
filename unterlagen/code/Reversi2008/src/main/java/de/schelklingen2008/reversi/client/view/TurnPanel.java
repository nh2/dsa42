package de.schelklingen2008.reversi.client.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.samskivert.swing.ShapeIcon;

import de.schelklingen2008.reversi.client.Constants;
import de.schelklingen2008.reversi.client.controller.Controller;
import de.schelklingen2008.reversi.client.controller.GameChangeListener;
import de.schelklingen2008.reversi.client.model.GameContext;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

/**
 * Displays a list of players and turn change information in a turn-based game.
 */
public class TurnPanel extends JPanel implements GameChangeListener
{

    private static final Polygon   TRIANGLE  = new Polygon(new int[] { 0, 12, 0 }, new int[] { 0, 6, 12 }, 3);
    private static final Ellipse2D CIRCLE    = new Ellipse2D.Float(0, 0, 12, 12);
    private static final ShapeIcon ICON_TURN = new ShapeIcon(TRIANGLE, Color.YELLOW, null);

    private Controller             controller;
    private String                 msgDraw;
    private String                 msgWin;

    public TurnPanel(Controller controller)
    {
        this.controller = controller;
        controller.addChangeListener(this);

        msgDraw = controller.getMessage(Constants.MSG_DRAW);
        msgWin = controller.getMessage(Constants.MSG_WINNER);

        gameChanged();
    }

    public void gameChanged()
    {
        removeAll();

        GridBagLayout gridbag = new GridBagLayout();
        setLayout(gridbag);

        GridBagConstraints turnHolderConstraints = new GridBagConstraints();
        turnHolderConstraints.fill = GridBagConstraints.BOTH;

        GridBagConstraints nameAndCountConstraints = new GridBagConstraints();
        nameAndCountConstraints.fill = GridBagConstraints.BOTH;
        nameAndCountConstraints.weightx = 1.0;
        nameAndCountConstraints.insets.left = 10;
        nameAndCountConstraints.gridwidth = GridBagConstraints.REMAINDER;

        Player turnHolder = getGameModel().getTurnHolder();
        for (Player player : Player.values())
        {
            JLabel turnHolderLabel = new JLabel();
            turnHolderLabel.setForeground(Color.BLACK);
            if (getGameModel().isDraw()) turnHolderLabel.setText(msgDraw);
            if (getGameModel().isWinner(player)) turnHolderLabel.setText(msgWin);
            if (player.equals(turnHolder)) turnHolderLabel.setIcon(ICON_TURN);
            add(turnHolderLabel, turnHolderConstraints);

            String name = getGameContext().getName(player);
            int count = getGameModel().countPieces(player);
            Color color = Constants.COL_PIECE.get(player);
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
