package de.schelklingen2008.reversi.ai.strategy;

import java.util.Set;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunctionDadaLena;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class ReversiStrategyDadaLena implements ReversiStrategy
{

    EvaluationFunction evalFunction = new EvaluationFunctionDadaLena();
    Player             me;

    public int getCount()
    {
        return 0;
    }

    public Piece move(GameModel gameModel)
    {
        me = gameModel.getTurnHolder();
        int best = Integer.MIN_VALUE;
        Piece bestMove = null;

        Set<Piece> moves = gameModel.getLegalMovesSet(me);
        for (Piece move : moves)
        {
            GameModel clone = new GameModel(gameModel);
            clone.placePiece(move.getX(), move.getY(), me);
            int value = mnval(clone, 3);

            if (value > best)
            {
                best = value;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private int mnval(GameModel model, int depth)
    {
        Player turnHolder = model.getTurnHolder();
        if (depth == 0 || turnHolder == null)
        {
            return evalFunction.evaluatePosition(model, me);
        }

        boolean max = turnHolder.equals(me);

        int best;
        if (max)
        {
            best = Integer.MIN_VALUE;
        }
        else
        {
            best = Integer.MAX_VALUE;
        }

        for (Piece move : model.getLegalMovesSet(turnHolder))
        {
            GameModel clone = new GameModel(model);
            clone.placePiece(move.getX(), move.getY(), turnHolder);
            int val = mnval(clone, depth - 1);

            if (max)
            {
                if (val > best) best = val;
            }
            else
            {
                if (val < best) best = val;
            }
        }

        return best;
    }
}
