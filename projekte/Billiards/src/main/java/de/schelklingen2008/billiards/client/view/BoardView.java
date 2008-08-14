package de.schelklingen2008.billiards.client.view;

import static de.schelklingen2008.billiards.GlobalConstants.BALL_RADIUS;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.schelklingen2008.billiards.GlobalConstants;
import de.schelklingen2008.billiards.client.Constants;
import de.schelklingen2008.billiards.client.controller.Controller;
import de.schelklingen2008.billiards.client.controller.GameChangeListener;
import de.schelklingen2008.billiards.client.model.GameContext;
import de.schelklingen2008.billiards.model.Ball;
import de.schelklingen2008.billiards.model.BallPlacedEvent;
import de.schelklingen2008.billiards.model.BallPlacementConstraints;
import de.schelklingen2008.billiards.model.GameEndEvent;
import de.schelklingen2008.billiards.model.GameEventAdapter;
import de.schelklingen2008.billiards.model.GameModel;
import de.schelklingen2008.billiards.model.WhiteBallPlacementHandler;
import de.schelklingen2008.billiards.model.Ball.BallType;
import de.schelklingen2008.billiards.util.Vector2d;
import de.schelklingen2008.util.LoggerFactory;

/**
 * Displays the main game interface (the board).
 */
public class BoardView extends JPanel implements GameChangeListener, WhiteBallPlacementHandler
{

    private class BoardViewGameEventAdapter extends GameEventAdapter
    {

        @Override
        public void gameRestarted()
        {
            JOptionPane.showMessageDialog(null, Constants.MSG_BLACK_BALL_POCKETED_IN_BREAK);
        }

        @Override
        public void ballPlaced(BallPlacedEvent e)
        {
            repaint();
        }

        @Override
        public void gameEnded(GameEndEvent e)
        {
            JOptionPane.showMessageDialog(null, String.format(Constants.MSG_PLAYER_WON,
                                                              getGameContext().getName(e.getWinner().getId())));
        }

    }

    private static final Logger logger = LoggerFactory.create();

    /**
     * 
     */
    private static final long serialVersionUID = -9064861386229239709L;
    private Controller controller;
    private BallGauge gauge;
    private Image bg;
    private Timer timer;
    private int cursorPosX = -1;
    private int cursorPosY;

    private boolean buttonWasPressed;

    private boolean placingWhiteBall;

    private BallPlacementConstraints whiteBallPlacementConstraints;

    /**
     * Constructs a view which will initialize itself and prepare to display the game board.
     */
    public BoardView(Controller controller, BallGauge gauge)
    {
        this.controller = controller;
        this.gauge = gauge;
        setOpaque(false);

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
            public void mouseExited(MouseEvent e)
            {
                cursorPosX = -1;
                repaint();
            }

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
            bg = ImageIO.read(new File(Constants.BG_IMAGE_FILENAME));
        }
        catch (IOException e)
        {
            logger.severe(String.format("Could not open file: %s", Constants.BG_IMAGE_FILENAME));
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
        if (e.getButton() != MouseEvent.BUTTON1 || !getGameModel().isTurnHolder(getGameContext().getMyPlayer())
            || getGameModel().isInMotion())
        {
            return;
        }

        buttonWasPressed = true;

        if (!placingWhiteBall)
        {

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

    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(860, 497);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D gfx = (Graphics2D) g;
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        paintBackground(gfx);
        paintBoard(gfx);
    }

    private void paintBackground(Graphics2D gfx)
    {
        gfx.drawImage(bg, 0, 0, getBackground(), null);
    }

    private void paintBoard(Graphics2D gfx)
    {

        GameModel gameModel = getGameModel();

        Ball whiteBall = gameModel.getWhiteBall();
        if (!placingWhiteBall && gameModel.isTurnHolder(getGameContext().getMyPlayer()) && !gameModel.isInMotion()
            && !whiteBall.isPocketed() && !gameModel.isInMotion() && cursorPosX != -1)
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

        if (placingWhiteBall)
        {
            if (cursorPosX != -1)
            {
                gfx.setColor(Color.WHITE);
                gfx.fillOval((int) Math.round(cursorPosX - BALL_RADIUS), (int) Math.round(cursorPosY - BALL_RADIUS),
                             (int) Math.round(2 * BALL_RADIUS), (int) Math.round(2 * BALL_RADIUS));
            }

            int leftX = (int) Math.round(whiteBallPlacementConstraints.getMinX() + BORDER_WIDTH - BALL_RADIUS);
            int topY = (int) Math.round(whiteBallPlacementConstraints.getMinY() + BORDER_HEIGHT - BALL_RADIUS);
            int rightX = (int) Math.round(whiteBallPlacementConstraints.getMaxX() + BORDER_WIDTH + BALL_RADIUS);
            int bottomY = (int) Math.round(whiteBallPlacementConstraints.getMaxY() + BORDER_HEIGHT + BALL_RADIUS);

            gfx.setColor(Color.RED);
            gfx.drawRect(leftX, topY, rightX - leftX, bottomY - topY);

        }

    }

    public void gameChanged()
    {
        getGameModel().addGameEventListener(new BoardViewGameEventAdapter());
        getGameModel().setWhiteBallPlacementHandler(this);

        if (getGameModel().isInMotion())
        {
            controller.startBoardProcessThread(this);
        }
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

        if (placingWhiteBall)
        {

            Vector2d position = new Vector2d(e.getX() - BORDER_WIDTH, e.getY() - BORDER_HEIGHT);

            if (!whiteBallPlacementConstraints.checkPosition(position))
            {
                return;
            }

            if (getGameModel().canPlaceWhiteBallAtPosition(position))
            {
                getGameModel().placeWhiteBall(position);
                placingWhiteBall = false;
            }

        }
        else
        {

            final GameModel gameModel = getGameModel();

            if (!gameModel.isTurnHolder(getGameContext().getMyPlayer()) || gameModel.isInMotion())
            {
                return;
            }

            if (timer != null)
            {
                timer.cancel();
            }

            Ball whiteBall = gameModel.getWhiteBall();
            Vector2d distance =
                new Vector2d(e.getX() - BORDER_WIDTH, e.getY() - BORDER_HEIGHT).subtract(whiteBall.getPosition());
            double angle = distance.getAngle();

            gameModel.takeShot(getGameContext().getMyPlayer(), angle, gauge.getValue() * GlobalConstants.MAX_VELOCITY
                                                                      / 100);

        }
    }

    public void placeWhiteBall(BallPlacementConstraints c, boolean isFoul)
    {
        if (getGameModel().isTurnHolder(getGameContext().getMyPlayer()))
        {

            placingWhiteBall = true;
            whiteBallPlacementConstraints = c;
            if (isFoul)
            {
                JOptionPane.showMessageDialog(null, Constants.MSG_PLACE_WHITE_BALL);
            }

        }
    }

}
