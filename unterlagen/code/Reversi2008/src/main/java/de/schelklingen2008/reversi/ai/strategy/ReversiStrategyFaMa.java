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
        for (Iterator iterator = gameModel.getLegalMovesSet(gameModel.getTurnHolder()).iterator(); iterator.hasNext();)
        {
            Piece move = (Piece) iterator.next();
            GameModel clone = new GameModel(gameModel); // erzeugt ein Klon des aktuellen Spielstands
            clone.placePiece(move.getX(), move.getY(), move.getPlayer());
            int value = mmvalue(clone, 5, false);
            if (value > best)
            {
                best = value;
                bestMove = move;
            }
        }
        return bestMove;
    }

    public int mmvalue(GameModel gameModel, int depth, boolean isMax)
    {
        if (depth == 0) return evalFunction.evaluatePosition(gameModel, gameModel.getTurnHolder());
        int best = 0;
        if (isMax)
        {
        }
        return best;
    }

    public int getCount()
    {
        return 5;
    }

}
