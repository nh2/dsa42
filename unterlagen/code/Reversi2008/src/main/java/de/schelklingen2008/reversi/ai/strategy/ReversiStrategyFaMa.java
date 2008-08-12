package de.schelklingen2008.reversi.ai.strategy;

import java.util.Iterator;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunctionFaMa;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;

public class ReversiStrategyFaMa implements ReversiStrategy
{

    private EvaluationFunctionFaMa evalFunction;

    public Piece move(GameModel gameModel)
    {
        int best = Integer.MIN_VALUE;
        Piece bestMove = null;
        for (Iterator<Piece> iterator = gameModel.getLegalMovesSet(gameModel.getTurnHolder()).iterator(); iterator.hasNext();)
        {
            Piece move = iterator.next();
            GameModel clone = new GameModel(gameModel); // erzeugt ein Klon des aktuellen Spielstands
            clone.placePiece(move);
            int value = mmvalue(clone, 5, false);
            if (value > best)
            {
                best = value;
                bestMove = move;
            }
        }
        if (bestMove == null) throw new IllegalArgumentException("bestMove is null");
        return bestMove;
    }

    public int mmvalue(GameModel gameModel, int depth, boolean isMax)
    {
        if (depth == 0 || !gameModel.hasLegalMoves())
        {
            return evalFunction.evaluatePosition(gameModel, gameModel.getTurnHolder());
        }
        int best = 0;
        if (isMax)
        {
            best = Integer.MIN_VALUE;
        }
        else
        {
            best = Integer.MAX_VALUE;
        }

        for (Iterator<Piece> iterator = gameModel.getLegalMovesSet(gameModel.getTurnHolder()).iterator(); iterator.hasNext();)
        {
            iterator.next();
            int value = mmvalue(new GameModel(gameModel), depth - 1, (isMax ? false : true));
            if (isMax)
            {
                if (best > value) best = value;
            }
            else
            {
                if (best < value) best = value;
            }
        }

        return best;
    }

    public int getCount()
    {
        return 5;
    }

}
