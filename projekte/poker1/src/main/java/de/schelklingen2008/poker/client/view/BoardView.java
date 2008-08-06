package de.schelklingen2008.poker.client.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.schelklingen2008.poker.client.Constants;
import de.schelklingen2008.poker.client.controller.Controller;
import de.schelklingen2008.poker.client.controller.GameChangeListener;
import de.schelklingen2008.poker.client.model.GameContext;
import de.schelklingen2008.poker.model.GameModel;

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

        // addMouseMotionListener(new MouseMotionAdapter()
        // {
        //
        // @Override
        // public void mouseMoved(MouseEvent e)
        // {
        // moved(e);
        // }
        // });
        //
        // addMouseListener(new MouseAdapter()
        // {
        //
        // @Override
        // public void mousePressed(MouseEvent e)
        // {
        // pressed(e);
        // }
        // });
    }

    // private void moved(MouseEvent e)
    // {
    // // TODO respond to player´s mouse movements
    // }
    //
    // private void pressed(MouseEvent e)
    // {
    // // TODO respond to player´s mouse clicks
    // }

    @Override
    public Dimension getPreferredSize()
    {
        // TODO calculate correct dimensions for the board view
        return new Dimension(800, 600);
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
        JPanel playerPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel cardPanel = new JPanel();
        JPanel potPanel = new JPanel();
        JPanel myPanel = new JPanel();
        JPanel myCardPanel = new JPanel();
        JPanel myInfoPanel = new JPanel();
        JPanel myButtonPanel = new JPanel();

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.LINE_AXIS));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.LINE_AXIS));
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.LINE_AXIS));
        potPanel.setLayout(new BoxLayout(potPanel, BoxLayout.PAGE_AXIS));
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.LINE_AXIS));
        myCardPanel.setLayout(new BoxLayout(myCardPanel, BoxLayout.PAGE_AXIS));
        myInfoPanel.setLayout(new BoxLayout(myInfoPanel, BoxLayout.PAGE_AXIS));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.LINE_AXIS));

        add(Box.createHorizontalStrut(5));
        add(playerPanel);
        add(Box.createHorizontalStrut(5));
        add(middlePanel);
        add(Box.createHorizontalStrut(5));
        add(myPanel);
        add(Box.createHorizontalStrut(5));

        playerPanel.add(Box.createVerticalStrut(5));
        for (int i = 0; i < Constants.PLAYER_COUNT; i++)
        {
            playerPanel.add(new JLabel("Spieler" + (i + 1)));
            playerPanel.add(Box.createVerticalStrut(5));
        }

        // playerPanel.add(new JLabel("Hallo, Label 1"));
        middlePanel.add(Box.createVerticalStrut(5));
        middlePanel.add(cardPanel);
        middlePanel.add(Box.createVerticalStrut(5));
        middlePanel.add(potPanel);
        middlePanel.add(Box.createVerticalStrut(5));

        cardPanel.add(Box.createVerticalStrut(5));
        for (int i = 0; i < 5; i++)
        {
            cardPanel.add(new JLabel("Karte " + (i + 1)));
            cardPanel.add(Box.createVerticalStrut(5));
        }
        potPanel.add(Box.createVerticalStrut(5));
        potPanel.add(new JLabel("pot"));
        potPanel.add(Box.createVerticalStrut(5));

        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(myCardPanel);
        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(myInfoPanel);
        myPanel.add(Box.createVerticalStrut(5));

        myCardPanel.add(Box.createVerticalStrut(5));
        myCardPanel.add(new JLabel("Karte1"));
        myCardPanel.add(Box.createVerticalStrut(5));
        myCardPanel.add(new JLabel("Karte2"));
        myCardPanel.add(Box.createVerticalStrut(5));

        myInfoPanel.add(Box.createHorizontalStrut(5));
        myInfoPanel.add(new JLabel("Info"));
        myInfoPanel.add(Box.createHorizontalStrut(5));
        myInfoPanel.add(myButtonPanel);
        myInfoPanel.add(Box.createHorizontalStrut(5));

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
