package de.schelklingen2008.reversi.ai.strategy;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.Random;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class AlphaBetaStrategy implements ReversiStrategy
{

    private final EvaluationFunction evalFunction;
    private final int                depth;
    private final Random             random = new Random();
    private int                      count;
    private final boolean            randomMoveOnEqual;
    private Player                   currentPlayer;
    private int                      maxDepth;
    private int                      free;
    private final int                endGameLimit;
    private long                     time;
    private final boolean            debug;

    public AlphaBetaStrategy(EvaluationFunction evalFunction,
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

        boolean[][] legalMoves = gameModel.getLegalMoves(player);
        for (int i = 0; i < legalMoves.length; i++)
        {
            for (int j = 0; j < legalMoves[i].length; j++)
            {
                if (!legalMoves[i][j]) continue;

                GameModel newGameModel = new GameModel(gameModel);
                newGameModel.placePiece(i, j, player);

                int weight = minValue(newGameModel, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);

                if (randomMoveOnEqual && weight == bestWeight && random.nextInt() % 2 == 0)
                {
                    bestMove = new Piece(i, j, player);
                }
                if (weight > bestWeight)
                {
                    bestWeight = weight;
                    bestMove = new Piece(i, j, player);
                }
            }
        }
        return bestMove;
    }

    private int maxValue(GameModel gameModel, int depth, int alpha, int beta)
    {
        Player player = gameModel.getTurnHolder();

        if (cancel(gameModel, depth))
        {
            count++;
            if (depth < maxDepth) maxDepth = depth;
            return evalFunction.evaluatePosition(gameModel, currentPlayer);
        }

        boolean[][] legalMoves = gameModel.getLegalMoves(player);
        for (int i = 0; i < legalMoves.length; i++)
        {
            for (int j = 0; j < legalMoves[i].length; j++)
            {
                if (!legalMoves[i][j]) continue;

                GameModel newGameModel = new GameModel(gameModel);
                newGameModel.placePiece(i, j, player);

                alpha = max(alpha, minValue(newGameModel, depth - 1, alpha, beta));
                if (alpha >= beta) return beta;
            }
        }
        return alpha;
    }

    private int minValue(GameModel gameModel, int depth, int alpha, int beta)
    {
        Player player = gameModel.getTurnHolder();

        if (cancel(gameModel, depth))
        {
            count++;
            if (depth < maxDepth) maxDepth = depth;
            return evalFunction.evaluatePosition(gameModel, currentPlayer);
        }

        boolean[][] legalMoves = gameModel.getLegalMoves(player);
        for (int i = 0; i < legalMoves.length; i++)
        {
            for (int j = 0; j < legalMoves[i].length; j++)
            {
                if (!legalMoves[i][j]) continue;

                GameModel newGameModel = new GameModel(gameModel);
                newGameModel.placePiece(i, j, player);

                beta = min(beta, maxValue(newGameModel, depth - 1, alpha, beta));
                if (beta <= alpha) return alpha;
            }
        }
        return beta;
    }

    private boolean cancel(GameModel gameModel, int depth)
    {
        return depth <= 0 && free >= endGameLimit || !gameModel.hasLegalMoves();
    }

    @Override
    public String toString()
    {
        return "AlphaBeta(" + evalFunction + ", " + depth + "," + endGameLimit + ")";
    }

    public int getCount()
    {
        return count;
    }

}
