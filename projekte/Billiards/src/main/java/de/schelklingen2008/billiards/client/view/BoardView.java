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
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

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

    private static final Logger logger = LoggerFactory.create();

    /**
     * 
     */
    private static final long serialVersionUID = -9064861386229239709L;
    private Controller controller;
    private Image bg;

    private Vector2d rayEndingPoint;

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
        GameModel gameModel = getGameModel();
        if (gameModel.isTurnHolder(getGameContext().getMyPlayer()))
        {
            castRay(e.getX(), e.getY());
        }
    }

    private void castRay(int x, int y)
    {
        int deltaX = x - BORDER_WIDTH, deltaY = y - BORDER_HEIGHT;
        double slope = deltaY / deltaX, yIntercept = y - slope * x;
        Vector2d position = new Vector2d(x, y);

        GameModel gameModel = getGameModel();

        Vector2d rayEndingPoint = null;
        Double minSquaredLength = Double.NaN;

        for (Ball ball : gameModel.getBallsOnTable())
        {
            if (Math.signum(ball.getPosition().getX() - x) == Math.signum(deltaX)
                && Math.signum(ball.getPosition().getY() - y) == Math.signum(deltaY))
            {
                Vector2d castPoint = castRayOnBall(x, y, slope, yIntercept, ball);
                Double raySquaredLength = position.subtract(castPoint).getSquaredLength();
                if (castPoint != null && (rayEndingPoint == null || raySquaredLength < minSquaredLength))
                {
                    rayEndingPoint = castPoint;
                }
            }
        }

        this.rayEndingPoint = rayEndingPoint;
    }

    private Vector2d castRayOnBall(int x, int y, double slope, double yIntercept, Ball ball)
    {
        double a = slope * slope + 1;
        double b = 2 * slope * yIntercept;
        double c = yIntercept * yIntercept - BALL_RADIUS * BALL_RADIUS;

        double disc = b * b - 4 * a * c;
        if (disc < 0)
        {
            return null;
        }
        else
        {
            double xCollision = (-b - Math.sqrt(disc)) / (2 * a);
            double yCollision = xCollision * slope + yIntercept;
            return new Vector2d(xCollision, yCollision);
        }
    }

    private void pressed(MouseEvent e)
    {
        // TODO respond to player´s mouse clicks
        final GameModel gameModel = getGameModel();
        Ball ball = gameModel.getWhiteBall();

        // TODO remove this
        ball.setVelocity(new Vector2d(e.getX() - BORDER_WIDTH, e.getY() - BORDER_HEIGHT).subtract(ball.getPosition())
                                                                                        .scale(1.5d));
        gameModel.inMotion = true;

        controller.startBoardProcessThread(this);

    }

    @Override
    public Dimension getPreferredSize()
    {
        // TODO calculate correct dimensions for the board view
        return new Dimension(860, 497);
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

        if (rayEndingPoint != null)
        {
            int x1 = (int) Math.round(gameModel.getWhiteBall().getPosition().getX() + BORDER_WIDTH);
            int y1 = (int) Math.round(gameModel.getWhiteBall().getPosition().getY() + BORDER_HEIGHT);
            int x2 = (int) Math.round(rayEndingPoint.getX() + BORDER_WIDTH);
            int y2 = (int) Math.round(rayEndingPoint.getY() + BORDER_HEIGHT);

            gfx.drawLine(x1, y1, x2, y2);
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

            final int x =
                (int) Math.round(Math.sqrt(1 - 0.25 * STRIPE_HEIGHT / BALL_RADIUS * STRIPE_HEIGHT / BALL_RADIUS)
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
}
