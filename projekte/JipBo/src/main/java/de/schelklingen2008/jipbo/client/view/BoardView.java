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
        for (int i = 1; i < getGameModel().getPlayerSize(); i++)
        {

            JLabel playerLabel = new JLabel(getGameModel().getPlayerNameIndexOf(i));
            playerLabel.setAlignmentX(CENTER_ALIGNMENT);
            playerLabel.setForeground(Color.WHITE);
            add(playerLabel);
            BoardPanel player = new BoardPanel(null,
                                               getGameModel().getPlayerIndexOf(i).getBoardCards(),
                                               false,
                                               Color.decode("#000054" + i * 15));
            add(player);
        }

        // Public Cards Panel
        JLabel publicLabel = new JLabel("Ablegestapel");
        publicLabel.setAlignmentX(CENTER_ALIGNMENT);
        publicLabel.setForeground(Color.WHITE);
        add(publicLabel);
        BoardPanel publicCards = new BoardPanel(controller, getGameModel().getBuildPile(), false, getBackground());
        add(publicCards);

        // Spacer
        add(Box.createVerticalStrut(20));
        // Own Cards Panel
        JLabel myNameLabel = new JLabel(getGameModel().getPlayerNameIndexOf(0));
        myNameLabel.setAlignmentX(CENTER_ALIGNMENT);
        myNameLabel.setForeground(Color.WHITE);
        add(myNameLabel);
        JLabel myLabel = new JLabel("Meine Karten");
        myLabel.setAlignmentX(CENTER_ALIGNMENT);
        myLabel.setForeground(Color.WHITE);
        add(myLabel);
        BoardPanel myBoardPanel = new BoardPanel(controller,
                                                 getGameModel().getPlayerIndexOf(0).getBoardCards(),
                                                 false,
                                                 Color.decode("#00005500"));
        add(myBoardPanel);
        JPanel drawPilePanel = new JPanel();
        drawPilePanel.setBackground(Color.decode("#00005500"));

        JLabel drawPileLabel = new JLabel("Meine Hand");
        drawPileLabel.setAlignmentX(CENTER_ALIGNMENT);
        drawPileLabel.setForeground(Color.WHITE);
        drawPilePanel.add(drawPileLabel);
        add(drawPilePanel);
        BoardPanel drawPile = new BoardPanel(controller,
                                             getGameModel().getPlayerIndexOf(0).getDrawPile(),
                                             true,
                                             Color.decode("#00005500"));
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
