package de.schelklingen2008.reversi.ai.strategy;

import java.util.Set;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class ElitzaStrategy implements ReversiStrategy
{

    private final EvaluationFunction eval;
    private final int                depth;
    private Player                   me;

    public ElitzaStrategy(EvaluationFunction eval, int depth)
    {
        this.depth = depth;
        this.eval = eval;
    }

    public Piece move(GameModel original)
    {
        int best = Integer.MIN_VALUE;
        Piece bestMove = null;
        me = original.getTurnHolder();

        Set<Piece> legalMovesSet = original.getLegalMovesSet(me);
        for (Piece piece : legalMovesSet)
        {
            GameModel clone = new GameModel(original);
            clone.placePiece(piece);

            int val = minmaxval(clone, depth);
            if (val > best)
            {
                best = val;
                bestMove = piece;
            }
        }

        if (bestMove == null) throw new IllegalStateException("no move found");
        return bestMove;
    }

    public int minmaxval(GameModel original, int depth)
    {
        if (depth == 0)
        {
            return eval.evaluatePosition(original, me);
        }

        int best;
        Player turnHolder = original.getTurnHolder();
        if (turnHolder == me)
        {
            best = Integer.MIN_VALUE;
        }
        else
        {
            best = Integer.MAX_VALUE;
        }

        Set<Piece> legalMovesSet = original.getLegalMovesSet(turnHolder);
        for (Piece piece : legalMovesSet)
        {
            GameModel clone = new GameModel(original);
            clone.placePiece(piece);

            int val = minmaxval(clone, depth - 1);

            if (turnHolder == me)
            {
                best = Math.max(val, best);
            }
            else
            {
                best = Math.min(val, best);
            }
        }
        return best;
    }

    public int getCount()
    {
        return 0;
    }
}
