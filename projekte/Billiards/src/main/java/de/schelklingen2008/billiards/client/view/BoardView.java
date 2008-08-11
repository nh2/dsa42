package de.schelklingen2008.billiards.client.view;

import static de.schelklingen2008.billiards.GlobalConstants.BALL_RADIUS;
import static de.schelklingen2008.billiards.GlobalConstants.BG_IMAGE_FILENAME;
import static de.schelklingen2008.billiards.GlobalConstants.BORDER_HEIGHT;
import static de.schelklingen2008.billiards.GlobalConstants.BORDER_WIDTH;
import static de.schelklingen2008.billiards.GlobalConstants.STRIPE_HEIGHT;

import java.awt.Color;
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
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import de.schelklingen2008.billiards.GlobalConstants;
import de.schelklingen2008.billiards.client.controller.Controller;
import de.schelklingen2008.billiards.client.controller.GameChangeListener;
import de.schelklingen2008.billiards.client.model.GameContext;
import de.schelklingen2008.billiards.model.Ball;
import de.schelklingen2008.billiards.model.GameModel;
import de.schelklingen2008.billiards.model.Ball.BallType;
import de.schelklingen2008.billiards.util.Vector2d;
import de.schelklingen2008.util.LoggerFactory;

/**
 * Displays the main game interface (the board).
 */
public class BoardView extends JPanel implements GameChangeListener
{

    private static final Logger logger           = LoggerFactory.create();

    /**
     * 
     */
    private static final long   serialVersionUID = -9064861386229239709L;
    private Controller          controller;
    private BallGauge           gauge;
    private Image               bg;
    private Timer               timer;
    private int                 cursorPosX;
    private int                 cursorPosY;

    private boolean             buttonWasPressed;

    /**
     * Constructs a view which will initialize itself and prepare to display the game board.
     */
    public BoardView(Controller controller, BallGauge gauge)
    {
        this.controller = controller;
        this.gauge = gauge;
        controller.addChangeListener(this);

        addMouseMotionListener(new MouseMotionAdapter()
        {

            @Override
            public void mouseMoved(MouseEvent e)
            {
                moved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e)
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

            @Override
            public void mouseReleased(MouseEvent e)
            {
                released(e);
            }

        });

        try
        {
            bg = ImageIO.read(new File(BG_IMAGE_FILENAME));
        }
        catch (IOException e)
        {
            logger.severe(String.format("Could not open file: %s", BG_IMAGE_FILENAME));
            throw new RuntimeException("Could not open file: %s", e);
        }

    }

    private void moved(MouseEvent e)
    {
        if (!getGameModel().isTurnHolder(getGameContext().getMyPlayer()))
        {
            return;
        }
        cursorPosX = Math.min(e.getX(), (int) Math.round(GlobalConstants.MAX_X + 2 * GlobalConstants.BORDER_WIDTH));
        cursorPosY = Math.min(e.getY(), (int) Math.round(GlobalConstants.MAX_Y + 2 * GlobalConstants.BORDER_HEIGHT));

        repaint();
    }

    private void pressed(MouseEvent e)
    {
        if (e.getButton() != MouseEvent.BUTTON1)
        {
            return;
        }

        buttonWasPressed = true;

        if (!getGameModel().isTurnHolder(getGameContext().getMyPlayer()))
        {
            return;
        }

        gauge.setValue(1);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {

            boolean fallingVelocity = false;

            @Override
            public void run()
            {
                if (gauge.getValue() >= 100 - 1E-4 || gauge.getValue() <= 1E-4)
                {
                    fallingVelocity = !fallingVelocity;
                }

                if (fallingVelocity)
                {
                    gauge.setValue(gauge.getValue() - 1);
                }
                else
                {
                    gauge.setValue(gauge.getValue() + 1);
                }
            }

        }, 40, 40);

    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(860, 497);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

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

        Ball whiteBall = gameModel.getWhiteBall();
        if (gameModel.isTurnHolder(getGameContext().getMyPlayer()) && !gameModel.isInMotion() && !whiteBall.isSunk())
        {
            gfx.drawLine((int) Math.round(whiteBall.getPosition().getX() + BORDER_WIDTH),
                         (int) Math.round(whiteBall.getPosition().getY() + BORDER_HEIGHT), cursorPosX, cursorPosY);
        }

        for (Ball ball : gameModel.getBallsOnTable())
        {
            if (ball.getType() == BallType.STRIPED)
            {
                gfx.setColor(Color.WHITE);
            }
            else
            {
                gfx.setColor(ball.getColor());
            }

            gfx.fillOval((int) Math.round(ball.getPosition().getX() + BORDER_WIDTH - BALL_RADIUS),
                         (int) Math.round(ball.getPosition().getY() + BORDER_HEIGHT - BALL_RADIUS),
                         (int) Math.round(2 * BALL_RADIUS), (int) Math.round(2 * BALL_RADIUS));

            final int x = (int) Math.round(Math.sqrt(1
                                                     - 0.25
                                                     * STRIPE_HEIGHT
                                                     / BALL_RADIUS
                                                     * STRIPE_HEIGHT
                                                     / BALL_RADIUS)
                                           * BALL_RADIUS);
            final int y = (int) Math.round(0.5 * BALL_RADIUS);
            final int angle = (int) Math.round(Math.atan((double) y / (double) x) * 180 / Math.PI);

            if (ball.getType() == BallType.STRIPED)
            {
                gfx.setColor(ball.getColor());
                gfx.fillArc((int) Math.round(ball.getPosition().getX() + BORDER_WIDTH - BALL_RADIUS),
                            (int) Math.round(ball.getPosition().getY() - BALL_RADIUS + BORDER_HEIGHT),
                            (int) Math.round(2 * BALL_RADIUS), (int) Math.round(2 * BALL_RADIUS), -angle, 2 * angle);

                gfx.fillArc((int) Math.round(ball.getPosition().getX() + BORDER_WIDTH - BALL_RADIUS),
                            (int) Math.round(ball.getPosition().getY() - BALL_RADIUS + BORDER_HEIGHT),
                            (int) Math.round(2 * BALL_RADIUS), (int) Math.round(2 * BALL_RADIUS), 180 - angle,
                            2 * angle);
                gfx.fillRect((int) Math.round(ball.getPosition().getX() - x + BORDER_WIDTH),
                             (int) Math.round(ball.getPosition().getY() - y + BORDER_HEIGHT), 2 * x, 2 * y);

            }
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

    private void released(MouseEvent e)
    {
        if (e.getButton() != MouseEvent.BUTTON1 || !buttonWasPressed)
        {
            return;
        }

        buttonWasPressed = false;

        final GameModel gameModel = getGameModel();

        if (!gameModel.isTurnHolder(getGameContext().getMyPlayer()))
        {
            return;
        }

        timer.cancel();

        Ball whiteBall = gameModel.getWhiteBall();
        Vector2d distance = new Vector2d(e.getX() - BORDER_WIDTH, e.getY() - BORDER_HEIGHT).subtract(whiteBall.getPosition());
        double angle = distance.getAngle();

        gameModel.takeShot(getGameContext().getMyPlayer(), angle, gauge.getValue() * GlobalConstants.MAX_VELOCITY / 100);
        controller.startBoardProcessThread(this);
    }

}
