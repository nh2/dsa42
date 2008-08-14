package de.schelklingen2008.billiards.client.view;

import static de.schelklingen2008.billiards.GlobalConstants.PLAYERS;
import static de.schelklingen2008.billiards.model.Ball.BallType.SOLID;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.schelklingen2008.billiards.client.Constants;
import de.schelklingen2008.billiards.client.controller.Controller;
import de.schelklingen2008.billiards.client.controller.GameChangeListener;
import de.schelklingen2008.billiards.client.model.GameContext;
import de.schelklingen2008.billiards.model.BallMappingSetEvent;
import de.schelklingen2008.billiards.model.GameEventAdapter;
import de.schelklingen2008.billiards.model.GameModel;
import de.schelklingen2008.billiards.model.Player;
import de.schelklingen2008.billiards.model.TurnHolderChangeEvent;

/**
 * Displays a list of players and turn change information in a turn-based game.
 */
public class TurnPanel extends JPanel implements GameChangeListener
{

    private class TurnPanelGameEventAdapter extends GameEventAdapter
    {

        @Override
        public void turnHolderChanged(TurnHolderChangeEvent e)
        {
            refreshPlayers();
        }

        @Override
        public void ballMappingSet(BallMappingSetEvent e)
        {
            refreshPlayers();
        }

        @Override
        public void boardStoppedMoving()
        {
            refreshPlayers();
        }

    }

    /**
     * 
     */
    private static final long serialVersionUID = -2000217486866087547L;
    private Controller controller;
    private Icon[] icons = new Icon[PLAYERS];
    private Icon whiteBallIcon, emptyIcon;

    public TurnPanel(Controller controller)
    {
        this.controller = controller;
        controller.addChangeListener(this);

        icons[0] = new ImageIcon(Constants.ICON_SOLID_FILENAME);
        icons[1] = new ImageIcon(Constants.ICON_STRIPED_FILENAME);

        emptyIcon = new ImageIcon(new BufferedImage(18, 18, BufferedImage.TYPE_4BYTE_ABGR));
        whiteBallIcon = new ImageIcon(getWhiteBallImage());

        getGameModel().addGameEventListener(new TurnPanelGameEventAdapter());

    }

    private Image getWhiteBallImage()
    {
        Image image = new BufferedImage(18, 18, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.WHITE);
        g.fillOval(0, 0, 18, 18);
        return image;
    }

    public void gameChanged()
    {
        getGameModel().addGameEventListener(new TurnPanelGameEventAdapter());
        refreshPlayers();
    }

    private void refreshPlayers()
    {
        removeAll();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (int i = 0; i < PLAYERS; i++)
        {
            String name = getGameContext().getName(i);
            JLabel label = new JLabel(name);

            if (getGameModel().getTurnHolder() != null && getGameModel().getTurnHolder().getId() == i)
            {
                if (getGameModel().ballMappingFixed())
                {
                    Player player = getGameModel().getPlayer(i);
                    int index = getGameModel().getPlayersBallType(player) == SOLID ? 0 : 1;
                    label.setIcon(icons[index]);
                }
                else
                {
                    label.setIcon(whiteBallIcon);
                }
            }
            else
            {
                label.setIcon(emptyIcon);
            }

            add(label);
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
