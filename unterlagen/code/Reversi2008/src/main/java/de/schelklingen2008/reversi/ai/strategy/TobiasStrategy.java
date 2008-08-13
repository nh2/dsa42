package de.schelklingen2008.reversi.ai.strategy;

import java.util.Set;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class TobiasStrategy implements ReversiStrategy
{

    private final EvaluationFunction evaluationFunction;
    private final int                depth;

    public TobiasStrategy(EvaluationFunction eval, int depth)
    {
        evaluationFunction = eval;
        this.depth = depth;
    }

    public Piece move(GameModel model)
    {

        int best = Integer.MIN_VALUE;
        Piece bestMove = null;
        Player me = model.getTurnHolder();
        Set<Piece> legalMoves = model.getLegalMovesSet(me);

        for (Piece piece : legalMoves)
        {
            GameModel clone = new GameModel(model);
            clone.placePiece(piece);
            int value = minimaxValue(model, depth, me);
            if (value > best)
            {
                best = value;
                bestMove = piece;
            }
            else if (value == best)
            {
                if (Math.random() < 0.5)
                {
                    best = value;
                    bestMove = piece;
                }
            }
        }

        return bestMove;
    }

    private int minimaxValue(GameModel model, int depth, Player me)
    {
        if (depth == 0)
        {
            return evaluationFunction.evaluatePosition(model, me);
        }

        int best;
        Player turnHolder = model.getTurnHolder();

        if (me == turnHolder)
        {
            best = Integer.MIN_VALUE;
        }
        else
        {
            best = Integer.MAX_VALUE;
        }

        Set<Piece> legalMoves = model.getLegalMovesSet(turnHolder);
        for (Piece piece : legalMoves)
        {
            GameModel clone = new GameModel(model);
            clone.placePiece(piece);
            int val = minimaxValue(clone, depth - 1, me);
            if (me == turnHolder) // MAX ist dran
            {
                if (val > best)
                {
                    best = val;
                }
            }
            else
            {
                if (val < best)
                {
                    best = val;
                }
            }
        }
        return best;
    }

    int NegaMax(GameModel model, int depth, int alpha, int beta)
    {
        if (depth == 0) return evaluationFunction.evaluatePosition(model, model.getTurnHolder());
        Set<Piece> legalMoves = model.getLegalMovesSet(model.getTurnHolder());

        for (Piece piece : legalMoves)
        {
            int value;
            GameModel clone = new GameModel(model);
            clone.placePiece(piece);
            value = -NegaMax(clone, depth - 1, -beta, -alpha);
            if (value >= beta) return beta;
            if (value > alpha) alpha = value;
        }
        return alpha;
    }

    public int getCount()
    {
        return depth;
    }

}
