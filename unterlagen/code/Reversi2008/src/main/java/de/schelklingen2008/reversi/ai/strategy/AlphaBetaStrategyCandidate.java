package de.schelklingen2008.reversi.ai.strategy;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class AlphaBetaStrategyCandidate implements ReversiStrategy
{

    private static final boolean     maxSort = false;
    private static final boolean     minSort = true;

    private final EvaluationFunction evalFunction;
    private final int                depth;
    private final Random             random  = new Random();
    private int                      count;
    private final boolean            randomMoveOnEqual;
    private Player                   currentPlayer;
    private int                      maxDepth;
    private int                      free;
    private final int                endGameLimit;
    private int                      time;
    private final boolean            debug;

    public AlphaBetaStrategyCandidate(EvaluationFunction evalFunction,
                                      int depth,
                                      int endGameLimit,
                                      boolean randomMoveOnEqual,
                                      boolean debug)
    {
        this.evalFunction = evalFunction;
        this.depth = depth;
        this.endGameLimit = endGameLimit;
        this.randomMoveOnEqual = randomMoveOnEqual;
        this.debug = debug;
    }

    public Piece move(GameModel gameModel)
    {
        count = 0;
        time = 0;
        maxDepth = depth;
        free = gameModel.countFree();
        currentPlayer = gameModel.getTurnHolder();
        long s = System.currentTimeMillis();
        Piece result = alphaBeta(gameModel);
        long e = System.currentTimeMillis();
        if (debug) System.out.println(this
                                      + " considered "
                                      + count
                                      + " positions in "
                                      + (e - s)
                                      + "ms. Move ["
                                      + result.getX()
                                      + ","
                                      + result.getY()
                                      + "]");
        time += e - s;
        return result;
    }

    private Piece alphaBeta(GameModel gameModel)
    {
        if (!gameModel.hasLegalMoves()) return null;

        Player player = gameModel.getTurnHolder();

        Piece bestMove = null;
        int bestWeight = Integer.MIN_VALUE;

        Collection<WeightedMove> moves = getSortedMoves(gameModel, minSort);
        for (WeightedMove move : moves)
        {
            int weight = minValue(move.position, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);

            if (randomMoveOnEqual && weight == bestWeight && random.nextInt() % 2 == 0)
            {
                bestMove = new Piece(move.x, move.y, player);
            }
            if (weight > bestWeight)
            {
                bestWeight = weight;
                bestMove = new Piece(move.x, move.y, player);
            }
        }
        return bestMove;
    }

    private int maxValue(GameModel gameModel, int depth, int alpha, int beta)
    {
        if (cancel(gameModel, depth))
        {
            count++;
            if (depth < maxDepth) maxDepth = depth;
            return evalFunction.evaluatePosition(gameModel, currentPlayer);
        }

        Collection<WeightedMove> moves = getSortedMoves(gameModel, maxSort);
        for (WeightedMove move : moves)
        {
            alpha = max(alpha, minValue(move.position, depth - 1, alpha, beta));
            if (alpha >= beta) return beta;
        }
        return alpha;
    }

    private int minValue(GameModel gameModel, int depth, int alpha, int beta)
    {
        if (cancel(gameModel, depth))
        {
            count++;
            if (depth < maxDepth) maxDepth = depth;
            return evalFunction.evaluatePosition(gameModel, currentPlayer);
        }

        Collection<WeightedMove> moves = getSortedMoves(gameModel, minSort);
        for (WeightedMove move : moves)
        {
            beta = min(beta, maxValue(move.position, depth - 1, alpha, beta));
            if (beta <= alpha) return alpha;
        }
        return beta;
    }

    private Collection<WeightedMove> getSortedMoves(GameModel gameModel, boolean asc)
    {
        boolean[][] legalMoves = gameModel.getLegalMoves(gameModel.getTurnHolder());
        List<WeightedMove> result = new ArrayList<WeightedMove>();
        for (int i = 0; i < legalMoves.length; i++)
        {
            for (int j = 0; j < legalMoves[i].length; j++)
            {
                if (!legalMoves[i][j]) continue;
                GameModel newGameModel = new GameModel(gameModel);
                newGameModel.placePiece(i, j, gameModel.getTurnHolder());
                int weight = evalFunction.evaluatePosition(newGameModel, currentPlayer);
                result.add(new WeightedMove(i, j, weight, asc, newGameModel));
            }
        }
        Collections.sort(result);
        return result;
    }

    private boolean cancel(GameModel gameModel, int depth)
    {
        return depth <= 0 && free >= endGameLimit || !gameModel.hasLegalMoves();
    }

    @Override
    public String toString()
    {
        return "AlphaBetaCandidate(" + evalFunction + ", " + depth + "," + endGameLimit + ")";
    }

    public int getCount()
    {
        return count;
    }

    private static class WeightedMove implements Comparable<WeightedMove>
    {

        final int       x;
        final int       y;
        final int       weight;
        final boolean   asc;
        final GameModel position;

        public WeightedMove(int x, int y, int weight, boolean asc, GameModel position)
        {
            this.weight = weight;
            this.x = x;
            this.y = y;
            this.asc = asc;
            this.position = position;
        }

        public int compareTo(WeightedMove o)
        {
            if (asc)
                return weight - o.weight;
            else
                return o.weight - weight;
        }

        @Override
        public String toString()
        {
            return "[" + x + "," + y + "," + weight + "," + asc + "]";
        }
    }
}
