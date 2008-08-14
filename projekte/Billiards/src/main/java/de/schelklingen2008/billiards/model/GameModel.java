package de.schelklingen2008.billiards.model;

import static de.schelklingen2008.billiards.GlobalConstants.BALL_RADIUS;
import static de.schelklingen2008.billiards.GlobalConstants.MAX_X;
import static de.schelklingen2008.billiards.GlobalConstants.MAX_Y;
import static de.schelklingen2008.billiards.GlobalConstants.PLAYERS;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import de.schelklingen2008.billiards.GlobalConstants;
import de.schelklingen2008.billiards.model.Ball.BallType;
import de.schelklingen2008.billiards.util.Vector2d;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel implements Serializable
{

    protected class RuleManager implements GameEventListener, CollisionListener, Serializable
    {

        /**
         * 
         */
        private static final long serialVersionUID = -1327349770200883433L;

        private boolean foul, doNotChangeTurnholder, touchedWallAfterTouchedFirstBall, restartGame;
        private Ball firstBallTouched;

        private void reset()
        {
            foul = false;
            doNotChangeTurnholder = false;
            firstBallTouched = null;
            touchedWallAfterTouchedFirstBall = false;
            restartGame = false;
        }

        public void ballCollisionHappened(BallCollisionEvent e)
        {
            if (firstBallTouched == null)
            {
                if (e.getBall1().equals(whiteBall))
                {
                    firstBallTouched = e.getBall2();
                }
                else if (e.getBall1().equals(whiteBall))
                {
                    firstBallTouched = e.getBall1();
                }
            }
        }

        public void ballCollisionImminent(BallCollisionEvent e)
        {

        }

        public void wallCollisionHappened(WallCollisionEvent e)
        {
            touchedWallAfterTouchedFirstBall = true;
        }

        public void wallCollisionImminent(WallCollisionEvent e)
        {

        }

        public void ballPocketed(BallPocketedEvent e)
        {
            Player player = e.getPlayer();
            Ball.BallType playersBallType = getPlayersBallType(player);
            int ballsOfPlayer;

            if (ballMappingFixed())
            {
                ballsOfPlayer = 0;
                for (Ball ball : ballsOnTable)
                {
                    if (ball.getType().equals(playersBallType))
                    {
                        ballsOfPlayer++;
                    }
                }
            }
            else
            {
                ballsOfPlayer = -1;
            }

            if (e.getBall().getType() == Ball.BallType.BLACK)
            {

                if (!breakHasHappened)
                {
                    restartGame = true;
                }
                else if (!ballMappingFixed())
                {
                    setWinner(getOtherPlayer(player));
                }
                else if (ballsOfPlayer == 0)
                {
                    setWinner(player);
                }
                else
                {
                    setWinner(getOtherPlayer(player));
                }

            }
            else if (e.getBall().getType() == Ball.BallType.WHITE)
            {
                if (ballsOfPlayer == 0)
                {
                    setWinner(getOtherPlayer(player));
                }
                else
                {
                    foul = true;
                }
            }
            else if (breakHasHappened() && !ballMappingFixed())
            {
                setBallMapping(e.getPlayer(), e.getBall().getType());
                doNotChangeTurnholder = true;
            }
            else
            {
                if (!e.getBall().getType().equals(getPlayersBallType(player)))
                {
                    if (ballsOfPlayer == 0)
                    {
                        setWinner(getOtherPlayer(player));
                    }
                    else
                    {
                        foul = true;
                    }
                }
                else
                {
                    doNotChangeTurnholder = true;
                }
            }

        }

        public void gameStarted()
        {

        }

        public void shotTaken(ShotEvent e)
        {
            reset();
        }

        public void ballPlaced(BallPlacedEvent e)
        {

        }

        public void gameEnded(GameEndEvent e)
        {

        }

        public void turnHolderChanged(TurnHolderChangeEvent e)
        {

        }

        public void boardStoppedMoving()
        {
            if (restartGame)
            {
                restartGame();
            }

            int ballsOfPlayer;
            Ball.BallType playersBallType = getPlayersBallType(turnHolder);
            Ball.BallType otherPlayersBallType = getPlayersBallType(getOtherPlayer(turnHolder));

            if (ballMappingFixed())
            {
                ballsOfPlayer = 0;
                for (Ball ball : ballsOnTable)
                {
                    if (ball.getType().equals(playersBallType))
                    {
                        ballsOfPlayer++;
                    }
                }
            }
            else
            {
                ballsOfPlayer = -1;
            }

            if (!foul && firstBallTouched == null || !touchedWallAfterTouchedFirstBall || ballMappingFixed()
                && firstBallTouched.getType().equals(otherPlayersBallType) || ballsOfPlayer == 0
                && firstBallTouched.getType().equals(BallType.BLACK))
            {
                foul = true;
            }

            if (!doNotChangeTurnholder || foul)
            {
                changeTurnHolder();
            }

            if (foul)
            {
                BallPlacementConstraints c;

                if (getWhiteBall().isPocketed() && !breakHasHappened)
                {
                    c =
                        new BallPlacementConstraints(21 + BALL_RADIUS,
                                                     21 + BALL_RADIUS,
                                                     0.25d * GlobalConstants.MAX_X,
                                                     GlobalConstants.MAX_Y - 21 - BALL_RADIUS);
                }
                else
                {
                    c =
                        new BallPlacementConstraints(21 + BALL_RADIUS,
                                                     21 + BALL_RADIUS,
                                                     GlobalConstants.MAX_X - 21 - BALL_RADIUS,
                                                     GlobalConstants.MAX_Y - 21 - BALL_RADIUS);
                }

                placeWhiteBall(c, true);
            }

        }

        public void ballMappingSet(BallMappingSetEvent e)
        {

        }

        public void gameRestarted()
        {
            // TODO Auto-generated method stub

        }

    }

    /**
     * 
     */
    private static final long serialVersionUID = -1305594069915531267L;

    protected Player[] players = new Player[2];
    protected Player turnHolder = null;
    protected boolean inMotion = false;

    protected boolean breakHasHappened;

    protected List<Ball> balls = new ArrayList<Ball>();
    protected List<Ball> ballsOnTable = new ArrayList<Ball>();

    protected List<Wall> walls = new ArrayList<Wall>();
    protected List<Hole> holes = new ArrayList<Hole>();

    protected Map<Player, BallType> playersBallTypes = new HashMap<Player, BallType>();

    protected List<GameEventListener> gameEventListeners = new LinkedList<GameEventListener>();

    protected Ball whiteBall, blackBall;

    protected List<CollisionListener> collisionListeners = new LinkedList<CollisionListener>();
    protected RuleManager ruleManager;

    protected Player winner;

    private WhiteBallPlacementHandler whiteBallPlacementHandler;

    private BallPlacementConstraints whiteBallPlacementConstraints;

    private boolean gamePaused;

    public boolean isInMotion()
    {
        return inMotion;
    }

    void placeWhiteBall(BallPlacementConstraints c, boolean isFoul)
    {
        whiteBallPlacementConstraints = c;
        gamePaused = true;

        if (getWhiteBallPlacementHandler() != null)
        {
            getWhiteBallPlacementHandler().placeWhiteBall(c, isFoul);
        }

    }

    public boolean isGamePaused()
    {
        return gamePaused;
    }

    public void placeWhiteBall(Vector2d position)
    {

        gamePaused = false;

        if (whiteBallPlacementConstraints == null)
        {
            throw new IllegalStateException("White ball does not have to be placed.");
        }

        if (!whiteBallPlacementConstraints.checkPosition(position))
        {
            throw new IllegalStateException("White ball placed out of allowed area.");
        }

        if (!canPlaceWhiteBallAtPosition(position))
        {
            throw new IllegalStateException("White ball may not touch any other balls when placed.");
        }

        whiteBall.setPosition(position);
        whiteBall.setPocketed(false);
        if (!ballsOnTable.contains(whiteBall))
        {
            ballsOnTable.add(whiteBall);
        }

        for (GameEventListener listener : gameEventListeners)
        {
            listener.ballPlaced(new BallPlacedEvent(whiteBall));
        }

    }

    public List<Ball> getBalls()
    {
        return balls;
    }

    public List<Ball> getBallsOnTable()
    {
        return new ArrayList<Ball>(ballsOnTable);
    }

    private void addPlayers()
    {
        for (int i = 0; i < PLAYERS; i++)
        {
            players[i] = new Player(i);
        }
    }

    private void addBalls()
    {
        whiteBall = new Ball(Ball.BallType.WHITE, Color.WHITE);
        blackBall = new Ball(Ball.BallType.BLACK, Color.BLACK);

        balls.add(whiteBall);
        balls.add(blackBall);

        for (Color color : Ball.BALL_COLORS)
        {
            balls.add(new Ball(Ball.BallType.SOLID, color));
            balls.add(new Ball(Ball.BallType.STRIPED, color));
        }
    }

    private void addWalls()
    {

        walls.add(new Wall(new Vector2d(0, 28), new Vector2d(21, 48)));
        walls.add(new Wall(new Vector2d(21, 48), new Vector2d(21, 382)));
        walls.add(new Wall(new Vector2d(21, 382), new Vector2d(0, 402)));

        walls.add(new Wall(new Vector2d(MAX_X, 28), new Vector2d(MAX_X - 21, 48)));
        walls.add(new Wall(new Vector2d(MAX_X - 21, 48), new Vector2d(MAX_X - 21, 382)));
        walls.add(new Wall(new Vector2d(MAX_X - 21, 382), new Vector2d(MAX_X, 402)));

        walls.add(new Wall(new Vector2d(25, 0), new Vector2d(43, 21)));
        walls.add(new Wall(new Vector2d(43, 21), new Vector2d(354, 21)));
        walls.add(new Wall(new Vector2d(354, 21), new Vector2d(373, 0)));

        walls.add(new Wall(new Vector2d(25, MAX_Y), new Vector2d(43, MAX_Y - 21)));
        walls.add(new Wall(new Vector2d(43, MAX_Y - 21), new Vector2d(354, MAX_Y - 21)));
        walls.add(new Wall(new Vector2d(354, MAX_Y - 21), new Vector2d(373, MAX_Y)));

        walls.add(new Wall(new Vector2d(416, 0), new Vector2d(434, 21)));
        walls.add(new Wall(new Vector2d(434, 21), new Vector2d(745, 21)));
        walls.add(new Wall(new Vector2d(745, 21), new Vector2d(765, 0)));

        walls.add(new Wall(new Vector2d(416, MAX_Y), new Vector2d(434, MAX_Y - 21)));
        walls.add(new Wall(new Vector2d(434, MAX_Y - 21), new Vector2d(745, MAX_Y - 21)));
        walls.add(new Wall(new Vector2d(745, MAX_Y - 21), new Vector2d(765, MAX_Y)));
    }

    public List<Wall> getWalls()
    {
        return walls;
    }

    private void addHoles()
    {
        holes.add(new Hole(new Vector2d(0, 0)));
        holes.add(new Hole(new Vector2d(MAX_X / 2, 0)));
        holes.add(new Hole(new Vector2d(MAX_X, 0)));
        holes.add(new Hole(new Vector2d(0, MAX_Y)));
        holes.add(new Hole(new Vector2d(MAX_X / 2, MAX_Y)));
        holes.add(new Hole(new Vector2d(MAX_X, MAX_Y)));
    }

    public List<Hole> getHoles()
    {
        return holes;
    }

    public void addGameEventListener(GameEventListener listener)
    {
        gameEventListeners.add(listener);
    }

    public void addCollisionListener(CollisionListener listener)
    {
        collisionListeners.add(listener);
    }

    public void takeShot(Player player, double angle, double velocity) throws IllegalStateException
    {
        if (isInMotion())
        {
            throw new IllegalStateException("Cannot take a shot while balls are still moving.");
        }

        if (!player.equals(turnHolder))
        {
            throw new IllegalStateException("Player is not the turnholder.");
        }

        whiteBall.setVelocity(Vector2d.getPolarVector(angle, velocity));
        inMotion = true;

        for (GameEventListener listener : gameEventListeners)
        {
            listener.shotTaken(new ShotEvent(angle, velocity, turnHolder));
        }

    }

    public GameModel()
    {

        addPlayers();
        addBalls();
        addWalls();
        addHoles();

        addRuleManager();

        setUpGame();

    }

    public void restartGame()
    {
        setUpGame();
        for (GameEventListener listener : gameEventListeners)
        {
            listener.gameRestarted();
        }
    }

    private void addRuleManager()
    {
        ruleManager = new RuleManager();
        addCollisionListener(ruleManager);
        addGameEventListener(ruleManager);
    }

    public void setUpGame()
    {

        for (Player player : players)
        {
            player.resetScore();
        }

        turnHolder = players[0];
        winner = null;

        inMotion = false;
        breakHasHappened = false;
        playersBallTypes = null;

        for (Ball ball : balls)
        {
            ball.setPocketed(false);
        }

        ballsOnTable.clear();
        ballsOnTable.addAll(balls);

        playersBallTypes = null;

        resetBalls();

        for (GameEventListener listener : gameEventListeners)
        {
            listener.gameStarted();
        }

        placeWhiteBall(new BallPlacementConstraints(21 + BALL_RADIUS, 21 + BALL_RADIUS, 0.25d * MAX_X, MAX_Y - 21
                                                                                                       - BALL_RADIUS),
                       false);

    }

    private void resetBalls()
    {

        for (Ball ball : balls)
        {
            ball.setVelocity(Vector2d.ZERO);
            ball.setPocketed(false);
        }

        List<Ball> tmpBalls = new ArrayList<Ball>(balls);
        Collections.shuffle(tmpBalls);

        ballsOnTable.remove(whiteBall);
        blackBall.setPosition(new Vector2d(0.75d * MAX_X, 0.5d * MAX_Y));

        if (tmpBalls.get(tmpBalls.size() - 1).getType().equals(tmpBalls.get(tmpBalls.size() - 5).getType()))
        {
            BallType ball2Type =
                tmpBalls.get(tmpBalls.size() - 1).getType() == BallType.SOLID ? BallType.STRIPED : BallType.SOLID;
            for (int i = 0; i < tmpBalls.size(); i++)
            {
                if (tmpBalls.get(i).getType().equals(ball2Type))
                {
                    Collections.swap(tmpBalls, i, tmpBalls.size() - 1);
                    break;
                }
            }
        }

        int i = 0;
        for (Ball ball : tmpBalls)
        {
            if (ball == whiteBall || ball == blackBall)
            {
                continue;
            }
            ball.setPosition(Ball.INITIAL_BALL_POSITIONS[i++]);
        }

    }

    void setBallMapping(Player player, Ball.BallType ballType)
    {
        playersBallTypes = new HashMap<Player, BallType>();
        playersBallTypes.put(player, ballType);

        Player otherPlayer = getOtherPlayer(player);
        Ball.BallType otherPlayersBalls;

        if (ballType == Ball.BallType.SOLID)
        {
            otherPlayersBalls = Ball.BallType.STRIPED;
        }
        else
        {
            otherPlayersBalls = Ball.BallType.SOLID;
        }

        playersBallTypes.put(player, ballType);
        playersBallTypes.put(otherPlayer, otherPlayersBalls);

        for (GameEventListener listener : gameEventListeners)
        {
            listener.ballMappingSet(new BallMappingSetEvent(player, otherPlayer, ballType, otherPlayersBalls));
        }
    }

    public boolean breakHasHappened()
    {
        return breakHasHappened;
    }

    public boolean ballMappingFixed()
    {
        return playersBallTypes != null;
    }

    public Player getPlayer(int id)
    {

        return players[id];

    }

    public boolean isTurnHolder(Player player)
    {
        return player.equals(turnHolder);
    }

    private void pocketBall(Ball ball)
    {
        ball.setPocketed(true);
        for (GameEventListener listener : gameEventListeners)
        {
            listener.ballPocketed(new BallPocketedEvent(ball, turnHolder));
        }
    }

    public boolean isTurnHolder(int player)
    {
        return turnHolder.getId() == player;
    }

    public Player getTurnHolder()
    {
        return turnHolder;
    }

    void changeTurnHolder()
    {
        Player oldTurnHolder = turnHolder;
        turnHolder = players[1 - turnHolder.getId()];
        for (GameEventListener listener : gameEventListeners)
        {
            listener.turnHolderChanged(new TurnHolderChangeEvent(oldTurnHolder, turnHolder));
        }
    }

    public Ball.BallType getPlayersBallType(Player player)
    {
        if (playersBallTypes == null)
        {
            return null;
        }
        else
        {
            return playersBallTypes.get(player);
        }
    }

    public void processTimeStep(double deltaT)
    {
        final double EPSILON = 0.0001d;

        if (!inMotion || deltaT < EPSILON)
        {
            return;
        }

        Iterator<Ball> ballIterator = ballsOnTable.iterator();
        for (Ball ball; ballIterator.hasNext();)
        {
            ball = ballIterator.next();

            for (Hole hole : holes)
            {
                if (hole.ballIsSunk(ball))
                {
                    pocketBall(ball);
                    ballIterator.remove();
                    break;
                }
            }
        }

        double remainingTime = deltaT;
        PriorityQueue<Collision> collisions = new PriorityQueue<Collision>();

        do
        {

            for (int i = 0; i < ballsOnTable.size(); i++)
            {

                Ball ball1 = ballsOnTable.get(i);

                if (ball1.isInMotion())
                {

                    for (Wall wall : walls)
                    {
                        Collision collision = Collision.getWallCollision(ball1, wall);
                        if (collision != null && collision.getTime() <= remainingTime)
                        {
                            collisions.offer(collision);
                        }
                    }

                }

                for (int j = i + 1; j < ballsOnTable.size(); j++)
                {
                    Ball ball2 = ballsOnTable.get(j);

                    if (!ball1.isInMotion() && !ball2.isInMotion())
                    {
                        continue;
                    }

                    Collision collision = Collision.getBallCollision(ball1, ball2);
                    if (collision != null && collision.getTime() <= remainingTime)
                    {
                        collisions.offer(collision);
                    }
                }
            }

            if (!collisions.isEmpty())
            {
                double firstCollisionTime = collisions.peek().getTime();

                Collision collision = collisions.poll();

                moveBalls(collision.getTime());
                remainingTime -= collision.getTime();

                do
                {
                    handleCollision(collision);
                    collision = collisions.poll();
                } while (!collisions.isEmpty() && collision.getTime() - firstCollisionTime < EPSILON);
            }
            else
            {
                moveBalls(remainingTime);
                remainingTime = 0;
            }

        } while (remainingTime > 0);

    }

    private void handleCollision(Collision collision)
    {
        if (collision.isWallCollision())
        {
            WallCollisionEvent e = new WallCollisionEvent(collision.getBall(), collision.getWall());

            for (CollisionListener listener : collisionListeners)
            {
                listener.wallCollisionImminent(e);
            }

            collision.handle();

            for (CollisionListener listener : collisionListeners)
            {
                listener.wallCollisionHappened(e);
            }

        }
        else
        {

            BallCollisionEvent e = new BallCollisionEvent(collision.getBall1(), collision.getBall2());

            for (CollisionListener listener : collisionListeners)
            {
                listener.ballCollisionImminent(e);
            }

            collision.handle();

            for (CollisionListener listener : collisionListeners)
            {
                listener.ballCollisionHappened(e);
            }

        }
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

            for (GameEventListener listener : gameEventListeners)
            {
                listener.boardStoppedMoving();
            }

            breakHasHappened = true;
        }

    }

    public Ball getWhiteBall()
    {
        return whiteBall;
    }

    void setWinner(Player player)
    {
        if (winner == null)
        {
            return;
        }

        winner = player;
        turnHolder = null;
        for (GameEventListener listener : gameEventListeners)
        {
            listener.gameEnded(new GameEndEvent(winner));
        }
    }

    public boolean isFinished()
    {
        return winner != null;
    }

    public Player getOtherPlayer(Player player)
    {
        return players[1 - player.getId()];
    }

    void resetWhiteBall()
    {
        if (!whiteBall.isPocketed())
        {
            return;
        }

        whiteBall.setPosition(new Vector2d(0.25d * MAX_X, 0.5d * MAX_Y));
        whiteBall.setPocketed(false);
        ballsOnTable.add(whiteBall);

        for (GameEventListener listener : gameEventListeners)
        {
            listener.ballPlaced(new BallPlacedEvent(whiteBall));
        }

    }

    public boolean canPlaceWhiteBallAtPosition(Vector2d position)
    {
        if (whiteBallPlacementConstraints == null)
        {
            return false;
        }

        for (Ball ball : ballsOnTable)
        {
            if (ball.equals(whiteBall))
            {
                continue;
            }

            if (ball.overlapsWithBallAtPosition(position))
            {
                return false;
            }
        }

        return true;
    }

    public void setWhiteBallPlacementHandler(WhiteBallPlacementHandler whiteBallPlacementHandler)
    {
        if (this.whiteBallPlacementHandler == null && whiteBallPlacementHandler != null
            && whiteBallPlacementConstraints != null)
        {
            this.whiteBallPlacementHandler = whiteBallPlacementHandler;
            getWhiteBallPlacementHandler().placeWhiteBall(whiteBallPlacementConstraints, false);
        }
        else
        {
            this.whiteBallPlacementHandler = whiteBallPlacementHandler;
        }

    }

    public WhiteBallPlacementHandler getWhiteBallPlacementHandler()
    {
        return whiteBallPlacementHandler;
    }

}