package de.schelklingen2008.jipbo.client.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

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
        // Other Players Panel
        int[] pA = { 2, 4, 8, 10, 0 };
        int[] pE = { 7, 2, 8, 1, -1 };
        BoardPanel playerA = new BoardPanel(pA);
        add(playerA);
        BoardPanel playerB = new BoardPanel(pA);
        add(playerB);
        BoardPanel playerC = new BoardPanel(pE);
        add(playerC);
        BoardPanel playerD = new BoardPanel(pA);
        add(playerD);
        BoardPanel playerE = new BoardPanel(pE);
        add(playerE);
        // Public Cards Panel
        JPanel publicCards = new JPanel();
        publicCards.setLayout(new BoxLayout(publicCards, BoxLayout.LINE_AXIS));
        publicCards.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        publicCards.add(new CardPanel(-1, true, false));
        publicCards.add(Box.createHorizontalStrut(20));
        publicCards.add(new CardPanel(4, true, false));
        publicCards.add(new CardPanel(9, true, false));
        publicCards.add(new CardPanel(6, true, false));
        publicCards.add(new CardPanel(0, true, false));
        add(publicCards);

        // Own Cards Panel
        int[] pOwn = { 0, 0, 0, 0, 0 };
        BoardPanel own = new BoardPanel(pE);
        own.setValue(pOwn);
        add(own);

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
