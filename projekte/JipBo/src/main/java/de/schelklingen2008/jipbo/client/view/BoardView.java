package de.schelklingen2008.jipbo.client.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.schelklingen2008.jipbo.client.Constants;
import de.schelklingen2008.jipbo.client.controller.Controller;
import de.schelklingen2008.jipbo.client.controller.GameChangeListener;
import de.schelklingen2008.jipbo.client.model.GameContext;
import de.schelklingen2008.jipbo.model.GameModel;

/**
 * Displays the main game interface (the board).
 */
public class BoardView extends JPanel implements GameChangeListener
{

    private Controller controller;

    /**
     * Constructs a view which will initialize itself and prepare to display the game board.
     */
    public BoardView(Controller controller)
    {
        this.controller = controller;
        controller.addChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Constants.COL_BOARD_BACKGROUND);
        // Other Players Panel
        int[] pA = { 2, 4, 8, 10, 0 };
        int[] pE = { 7, 2, 8, 1, 4 };
        JLabel playerALabel = new JLabel("Spieler A");
        add(playerALabel);
        playerALabel.setAlignmentX(CENTER_ALIGNMENT);
        BoardPanel playerA = new BoardPanel(pA, false, Color.BLUE);
        add(playerA);

        // BoardPanel playerB = new BoardPanel(pA, false, Color.RED);
        // add(playerB);
        JLabel playerBLabel = new JLabel("Spieler B");
        playerBLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(playerBLabel);
        BoardPanel playerC = new BoardPanel(pE, false, Color.CYAN);
        add(playerC);

        // BoardPanel playerD = new BoardPanel(pA);
        // add(playerD);
        JLabel playerDLabel = new JLabel("Spieler D");
        playerDLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(playerDLabel);
        BoardPanel playerD = new BoardPanel(pE, false, Color.decode("#00DC143C"));
        add(playerD);

        // Public Cards Panel
        JLabel publicLabel = new JLabel("Ablegestapel");
        publicLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(publicLabel);
        int[] pPC = { 0, 7, 12, 12, -1 };
        BoardPanel publicCards = new BoardPanel(pPC, false, getBackground());
        add(publicCards);

        // Spacer
        add(Box.createVerticalStrut(20));
        // Own Cards Panel
        JLabel myLabel = new JLabel("Meine Karten");
        myLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(myLabel);
        int[] pOwn = { 0, 0, 0, 0, 0 };
        BoardPanel myBoardPanel = new BoardPanel(pE, false, Color.decode("#00005500"));
        myBoardPanel.setValue(pOwn);
        add(myBoardPanel);
        JPanel drawPilePanel = new JPanel();
        drawPilePanel.setBackground(Color.decode("#00005500"));

        JLabel drawPileLabel = new JLabel("Meine Hand");
        drawPileLabel.setAlignmentX(CENTER_ALIGNMENT);
        drawPileLabel.setForeground(Color.WHITE);
        drawPilePanel.add(drawPileLabel);
        add(drawPilePanel);
        BoardPanel drawPile = new BoardPanel(pE, true, Color.decode("#00005500"));
        add(drawPile);

        addMouseMotionListener(new MouseMotionAdapter()
        {

            @Override
            public void mouseMoved(MouseEvent e)
            {
                moved(e);
            }
        });

        addMouseListener(new MouseAdapter()
        {

            @Override
            public void mousePressed(MouseEvent e)
            {
                pressed(e);
            }
        });
    }

    private void moved(MouseEvent e)
    {
        // TODO respond to player´s mouse movements
    }

    private void pressed(MouseEvent e)
    {
        // TODO respond to player´s mouse clicks
    }

    public void gameChanged()
    {
        getGameModel();
        repaint();
    }

    private GameModel getGameModel()
    {
        return getGameContext().getGameModel();
    }

    private GameContext getGameContext()
    {
        return controller.getGameContext();
    }

}
