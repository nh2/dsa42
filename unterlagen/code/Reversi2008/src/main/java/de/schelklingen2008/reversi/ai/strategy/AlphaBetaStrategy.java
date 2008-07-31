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

    public AlphaBetaStrategy(EvaluationFunction evalFunction, int depth, boolean randomMoveOnEqual)
    {
        this.evalFunction = evalFunction;
        this.depth = depth;
        this.randomMoveOnEqual = randomMoveOnEqual;
    }

    public Piece move(GameModel gameModel)
    {
        count = 0;
        return alphaBeta(gameModel);
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

                System.out.println("(" + i + "," + j + ") = " + weight);
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

        if (depth == 0 || !gameModel.hasLegalMoves())
        {
            count++;
            return evalFunction.evaluatePosition(gameModel, player);
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

        if (depth == 0 || !gameModel.hasLegalMoves())
        {
            count++;
            return evalFunction.evaluatePosition(gameModel, player);
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

    @Override
    public String toString()
    {
        return "AlphaBeta(" + evalFunction + ", " + depth + ")";
    }

    public int getCount()
    {
        return count;
    }

}
