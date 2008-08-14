package de.schelklingen2008.reversi.ai.strategy;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.Iterator;
import java.util.Set;

import de.schelklingen2008.reversi.ai.evaluation.ChaosEvaluation;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class ChaosStrategy implements ReversiStrategy
{

    private final ChaosEvaluation mEvalFunction;
    private int                   mDepth;
    private Player                mMe;
    private int                   mCounter;

    public ChaosStrategy(int pDepth)
    {
        mEvalFunction = new ChaosEvaluation();
        mDepth = 5;
        mCounter = 0;
    }

    public Piece move(final GameModel gameModel)
    {
        // if (false) minimax(gameModel);

        mMe = gameModel.getTurnHolder();
        if (!gameModel.hasLegalMoves()) return null;

        int best = Integer.MIN_VALUE;
        Piece bestMove = null;

        Set<Piece> legalMoves = gameModel.getLegalMovesSet(gameModel.getTurnHolder());
        for (Iterator<Piece> iterator = legalMoves.iterator(); iterator.hasNext();)
        {
            Piece move = iterator.next();
            GameModel clone = new GameModel(gameModel); // erzeugt ein Klon des aktuellen Spielstands
            clone.placePiece(move);
            // int value = mmvalue(clone, mDepth, true);
            int value = minValue(clone, mDepth, -10000, 10000);
            // System.out.println("(" + move.getX() + "," + move.getY() + ") = " + value);
            if (value > best)
            {
                best = value;
                bestMove = move;
            }
        }
        return bestMove;
    }

    public int getCount()
    {
        return mCounter;
    }

    private int maxValue(GameModel gameModel, int depth, int alpha, int beta)
    {

        if (depth == 0 || !gameModel.hasLegalMoves())
        {
            mCounter++;
            return mEvalFunction.evaluatePosition(gameModel, mMe);
        }

        Set<Piece> legalMoves = gameModel.getLegalMovesSet(gameModel.getTurnHolder());
        for (Iterator<Piece> iterator = legalMoves.iterator(); iterator.hasNext();)
        {
            Piece move = iterator.next();
            GameModel clone = new GameModel(gameModel);
            clone.placePiece(move);

            alpha = max(alpha, minValue(clone, depth - 1, alpha, beta));
            if (alpha >= beta) return beta;
        }
        return alpha;
    }

    private void minimax(GameModel pGameModel)
    {
        Player[][] board = pGameModel.getBoard();
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                board[i][j] = mMe;
            }
        }
        pGameModel.setBoard(board);
    }

    private int minValue(GameModel gameModel, int depth, int alpha, int beta)
    {

        if (depth == 0 || !gameModel.hasLegalMoves())
        {
            mCounter++;
            return mEvalFunction.evaluatePosition(gameModel, mMe);
        }

        Set<Piece> legalMoves = gameModel.getLegalMovesSet(gameModel.getTurnHolder());
        for (Iterator<Piece> iterator = legalMoves.iterator(); iterator.hasNext();)
        {
            Piece move = iterator.next();

            GameModel clone = new GameModel(gameModel);
            clone.placePiece(move);

            beta = min(beta, maxValue(clone, depth - 1, alpha, beta));
            if (beta <= alpha) return alpha;
        }
        return beta;
    }

}
