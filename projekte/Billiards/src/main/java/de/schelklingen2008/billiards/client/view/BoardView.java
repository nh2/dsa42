package de.schelklingen2008.billiards.client.view;

import static de.schelklingen2008.billiards.GlobalConstants.BALL_RADIUS;
import static de.schelklingen2008.billiards.GlobalConstants.BORDER_HEIGHT;
import static de.schelklingen2008.billiards.GlobalConstants.BORDER_WIDTH;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import de.schelklingen2008.billiards.client.BoardProcessThread;
import de.schelklingen2008.billiards.client.controller.Controller;
import de.schelklingen2008.billiards.client.controller.GameChangeListener;
import de.schelklingen2008.billiards.client.model.GameContext;
import de.schelklingen2008.billiards.model.Ball;
import de.schelklingen2008.billiards.model.GameModel;
import de.schelklingen2008.billiards.util.Vector2d;

/**
 * Displays the main game interface (the board).
 */
public class BoardView extends JPanel implements GameChangeListener
{

    /**
     * 
     */
    private static final long serialVersionUID = -9064861386229239709L;
    private Controller        controller;
    private Image             bg;

    private Timer             timer            = new Timer();          // TODO remove this

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

        try
        {
            bg = ImageIO.read(new File("src/main/resources/images/board.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void moved(MouseEvent e)
    {
        // TODO respond to player´s mouse movements
    }

    private void pressed(MouseEvent e)
    {
        // TODO respond to player´s mouse clicks
        final GameModel gameModel = getGameModel();
        Ball ball = gameModel.getWhiteBall();

        // TODO remove this
        ball.setVelocity(new Vector2d(e.getX() - BORDER_WIDTH, e.getY() - BORDER_HEIGHT).subtract(ball.getPosition()));
        gameModel.inMotion = true;

        new BoardProcessThread(gameModel, this).start();

    }

    @Override
    public Dimension getPreferredSize()
    {
        // TODO calculate correct dimensions for the board view
        return new Dimension(860, 460);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // TODO do proper painting of game state
        paintBackground(gfx);
        paintBoard(gfx);
    }

    private void paintBackground(Graphics2D gfx)
    {
        gfx.drawImage(bg, 0, 0, null);
    }

    private void paintBoard(Graphics2D gfx)
    {

        GameModel gameModel = getGameModel();

        for (Ball ball : gameModel.getBallsOnTable())
        {
            gfx.setColor(ball.getColor());
            gfx.fillOval((int) Math.round(ball.getPosition().getX() + BORDER_WIDTH - BALL_RADIUS),
                         (int) Math.round(ball.getPosition().getY() + BORDER_HEIGHT - BALL_RADIUS),
                         (int) Math.round(2 * BALL_RADIUS), (int) Math.round(2 * BALL_RADIUS));
        }

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
