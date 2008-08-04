package de.schelklingen2008.mmpoker.client.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import de.schelklingen2008.mmpoker.client.controller.Controller;
import de.schelklingen2008.mmpoker.client.controller.GameChangeListener;
import de.schelklingen2008.mmpoker.client.model.GameContext;
import de.schelklingen2008.mmpoker.model.GameModel;

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

    @Override
    public Dimension getPreferredSize()
    {
        // TODO calculate correct dimensions for the board view
        return new Dimension(0, 0);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        // TODO do proper painting of game state
        paintBackground(gfx);
        paintBoard(gfx);
    }

    private void paintBackground(Graphics2D gfx)
    {
    }

    private void paintBoard(Graphics2D gfx)
    {
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
