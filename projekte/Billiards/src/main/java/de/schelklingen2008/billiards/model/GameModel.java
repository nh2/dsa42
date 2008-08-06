package de.schelklingen2008.billiards.model;

import static de.schelklingen2008.billiards.GlobalConstants.BALL_RADIUS;
import static de.schelklingen2008.billiards.GlobalConstants.MAX_X;
import static de.schelklingen2008.billiards.GlobalConstants.MAX_Y;
import static de.schelklingen2008.billiards.GlobalConstants.PLAYERS;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.schelklingen2008.billiards.util.Vector2d;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    private Player[]   players      = new Player[2];
    private Player     turnHolder   = null;
    // TODO Make this private
    public boolean     inMotion     = false;                // Are there any balls in
    // motion?

    private List<Ball> balls        = new ArrayList<Ball>();
    private List<Ball> ballsOnTable = new ArrayList<Ball>();

    private Ball       whiteBall, blackBall;

    public boolean isInMotion()
    {
        return inMotion;
    }

    public Collection<Ball> getBalls()
    {
        return balls;
    }

    public List<Ball> getBallsOnTable()
    {
        return ballsOnTable;
    }

    public GameModel()
    {

        for (int i = 0; i < PLAYERS; i++)
        {
            players[i] = new Player(i);
        }

        whiteBall = new Ball(Ball.BallType.WHITE, Color.WHITE);
        blackBall = new Ball(Ball.BallType.BLACK, Color.BLACK);

        balls.add(whiteBall);
        balls.add(blackBall);

        for (Color color : Ball.BALL_COLORS)
        {
            balls.add(new Ball(Ball.BallType.SOLID, color));
            balls.add(new Ball(Ball.BallType.STRIPED, color));
        }

        setUpGame();

    }

    public void setUpGame()
    {

        for (Player player : players)
        {
            player.resetScore();
        }

        inMotion = false;

        for (Ball ball : balls)
        {
            ball.setSunk(false);
        }

        ballsOnTable.clear();
        ballsOnTable.addAll(balls);

        resetBalls();

    }

    private void resetBalls()
    {

        for (Ball ball : balls)
        {
            ball.setVelocity(Vector2d.ZERO);
        }

        Collections.shuffle(balls);

        whiteBall.setPosition(new Vector2d(200, 200));
        blackBall.setPosition(new Vector2d(600, 200));

        // TODO initial ball positions
        int i = 0;
        int x = 300, y = 130;
        for (Ball ball : balls)
        {
            if (ball == whiteBall || ball == blackBall)
            {
                continue;
            }
            ball.setPosition(new Vector2d(x, y));
            y += 30;
            if (y > 270)
            {
                y = 160;
                x += 30;
            }
            i++;
        }

    }

    public Player getPlayer(int id)
    {

        return players[id];

    }

    public boolean isTurnHolder(Player player)
    {
        return player.equals(turnHolder);
    }

    public void sinkBall(Ball ball)
    {
        ball.setSunk(true);
        ballsOnTable.remove(ball);
    }

    public boolean isTurnHolder(int player)
    {
        return turnHolder.getId() == player;
    }

    public void processTimeStep(double deltaT)
    {
        final double EPSILON = 0.000001d;

        if (!inMotion || deltaT < EPSILON)
        {
            return;
        }

        double remainingTime = deltaT;

        do
        {

            double tCollision = Double.NaN;
            Ball ball1 = null, ball2 = null;
            boolean isWallCollision = false;

            for (int i = 0; i < ballsOnTable.size(); i++)
            {

                double wallCollisionTime = ballsOnTable.get(i).getNextWallCollision();
                if (!Double.isNaN(wallCollisionTime) && (Double.isNaN(tCollision) || wallCollisionTime < tCollision))
                {
                    tCollision = wallCollisionTime;
                    ball1 = ballsOnTable.get(i);
                    isWallCollision = true;
                }

                for (int j = i + 1; j < ballsOnTable.size(); j++)
                {
                    if (!ballsOnTable.get(i).isInMotion() && !ballsOnTable.get(j).isInMotion())
                    {
                        continue;
                    }

                    double ballCollisionTime = ballsOnTable.get(i).getNextBallCollision(ballsOnTable.get(j));

                    if (!Double.isNaN(ballCollisionTime)
                        && (Double.isNaN(tCollision) || ballCollisionTime < tCollision))
                    {
                        tCollision = ballCollisionTime;
                        ball1 = ballsOnTable.get(i);
                        ball2 = ballsOnTable.get(j);
                        isWallCollision = false;
                    }
                }
            }

            if (!Double.isNaN(tCollision) && tCollision < remainingTime)
            {
                moveBalls(tCollision);
                remainingTime -= tCollision;
                handleCollision(tCollision, ball1, ball2, isWallCollision);
            }
            else
            {
                moveBalls(remainingTime);
                remainingTime = 0;
            }

        } while (remainingTime >= EPSILON);

    }

    private void moveBalls(double deltaT)
    {

        boolean tmpInMotion = false;

        for (Ball ball : ballsOnTable)
        {
            ball.move(deltaT);
            tmpInMotion |= ball.isInMotion();
        }

        if (!tmpInMotion)
        {
            inMotion = false;
        }

    }

    private void handleCollision(double tCollision, Ball ball1, Ball ball2, boolean isWallCollision)
    {

        final double EPSILON = 0.000001d;

        if (isWallCollision)
        {
            if (ball1.getPosition().getX() - BALL_RADIUS < EPSILON
                || ball1.getPosition().getX() + BALL_RADIUS >= MAX_X - EPSILON)
            {
                ball1.setVelocity(new Vector2d(-ball1.getVelocity().getX(), ball1.getVelocity().getY()));
            }
            else if (ball1.getPosition().getY() - BALL_RADIUS < EPSILON
                     || ball1.getPosition().getY() + BALL_RADIUS >= MAX_Y - EPSILON)
            {
                ball1.setVelocity(new Vector2d(ball1.getVelocity().getX(), -ball1.getVelocity().getY()));
            }
        }
        else
        {

            double alpha = ball1.getPosition().subtract(ball2.getPosition()).getAngle();

            Vector2d v1 = ball1.getVelocity().rotate(-alpha);
            Vector2d v2 = ball2.getVelocity().rotate(-alpha);

            double tmp = v1.getX();
            v1 = new Vector2d(v2.getX(), v1.getY());
            v2 = new Vector2d(tmp, v2.getY());

            ball1.setVelocity(v1.rotate(alpha));
            ball2.setVelocity(v2.rotate(alpha));

            ball1.move(0.001d);
            ball2.move(0.001d);

        }

    }

    public Ball getWhiteBall()
    {
        return whiteBall;
    }

}