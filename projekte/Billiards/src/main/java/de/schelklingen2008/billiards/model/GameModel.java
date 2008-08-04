package de.schelklingen2008.billiards.model;

import static de.schelklingen2008.billiards.GlobalConstants.MAX_X;
import static de.schelklingen2008.billiards.GlobalConstants.MAX_Y;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.schelklingen2008.billiards.util.Vector2d;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    private Player[]         players      = new Player[2];
    private Player           turnHolder   = null;
    private boolean          inMotion     = false;                 // Are there any balls in motion?

    private Collection<Ball> balls        = new LinkedList<Ball>();
    private List<Ball>       ballsOnTable = new ArrayList<Ball>();

    public GameModel()
    {

        balls.add(new Ball(Ball.BallType.WHITE, Color.WHITE));
        balls.add(new Ball(Ball.BallType.BLACK, Color.BLACK));

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
        if (!inMotion)
        {
            return;
        }

        double remainingTime = deltaT;
        boolean collisionHappened;

        final double EPSILON = 0.000001;

        do
        {

            double tCollision = Double.NaN;
            Ball ball1 = null, ball2 = null;
            boolean isWallCollision = false;

            collisionHappened = false;

            for (int i = 0; i < ballsOnTable.size(); i++)
            {

                double wallCollisionTime = ballsOnTable.get(i).getNextWallCollision();
                if (Double.isNaN(tCollision) || !Double.isNaN(wallCollisionTime) || wallCollisionTime < tCollision)
                {
                    tCollision = wallCollisionTime;
                    ball1 = ballsOnTable.get(i);
                    isWallCollision = true;
                }

                for (int j = i + 1; j < ballsOnTable.size(); j++)
                {
                    double ballCollisionTime = ballsOnTable.get(i).getNextBallCollision(ballsOnTable.get(j));

                    if (Double.isNaN(tCollision) || !Double.isNaN(ballCollisionTime) || ballCollisionTime < tCollision)
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
                collisionHappened = true;
            }
            else
            {
                moveBalls(remainingTime);
            }

        } while (remainingTime >= EPSILON);

    }

    private void moveBalls(double deltaT)
    {

        inMotion = false;

        for (Ball ball : ballsOnTable)
        {
            ball.move(deltaT);
            inMotion |= ball.isInMotion();
        }

    }

    private void handleCollision(double tCollision, Ball ball1, Ball ball2, boolean isWallCollision)
    {

        final double EPSILON = 0.000001;

        if (isWallCollision)
        {
            if (ball1.getPosition().getX() < EPSILON || ball1.getPosition().getX() >= MAX_X - EPSILON)
            {
                ball1.setVelocity(new Vector2d(-ball1.getVelocity().getX(), ball1.getVelocity().getY()));
            }
            else if (ball1.getPosition().getX() < EPSILON || ball1.getPosition().getY() >= MAX_Y - EPSILON)
            {
                ball1.setVelocity(new Vector2d(ball1.getVelocity().getX(), -ball1.getVelocity().getY()));
            }
        }
        else
        {

            double alpha = ball1.getPosition().subtract(ball2.getPosition()).getAngle();

            Vector2d v1 = ball1.getVelocity().rotate(alpha);
            Vector2d v2 = ball2.getVelocity().rotate(alpha);

            double tmp = v1.getX();
            v1 = new Vector2d(v2.getX(), v1.getY());
            v2 = new Vector2d(tmp, v2.getY());

            ball1.setVelocity(v2.rotate(-alpha));
            ball2.setVelocity(v2.rotate(-alpha));

        }

    }

}