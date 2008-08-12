package de.schelklingen2008.reversi.ai.strategy;

import java.util.Iterator;
import java.util.Set;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunctionFaMa;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class ReversiStrategyFaMa implements ReversiStrategy
{

    private final EvaluationFunctionFaMa mEvalFunction;
    private int                          mDepth;
    private Player                       mMe;

    public ReversiStrategyFaMa(EvaluationFunctionFaMa pEvalFunction, int pDepth)
    {
        mEvalFunction = pEvalFunction;
        mDepth = pDepth;
    }

    public Piece move(final GameModel gameModel)
    {
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
            int value = mmvalue(clone, mDepth, true);
            // System.out.println("(" + move.getX() + "," + move.getY() + ") = " + value);
            if (value > best)
            {
                best = value;
                bestMove = move;
            }
        }
        return bestMove;
    }

    public int mmvalue(final GameModel gameModel, final int depth, final boolean isMax)
    {
        if (depth == 0 || !gameModel.hasLegalMoves())
        {
            return mEvalFunction.evaluatePosition(gameModel, mMe);
        }

        int best = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        Set<Piece> legalMoves = gameModel.getLegalMovesSet(gameModel.getTurnHolder());
        for (Iterator<Piece> iterator = legalMoves.iterator(); iterator.hasNext();)
        {
            Piece move = iterator.next();

            GameModel clone = new GameModel(gameModel); // erzeugt ein Klon des aktuellen Spielstands
            clone.placePiece(move);
            int value = mmvalue(clone, depth - 1, !isMax);

            if (isMax && value > best || !isMax && value < best) best = value;
        }

        return best;
    }

    public int getCount()
    {
        return 5;
    }

}
