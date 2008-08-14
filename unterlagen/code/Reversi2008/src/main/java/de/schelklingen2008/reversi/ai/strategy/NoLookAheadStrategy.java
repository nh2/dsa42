package de.schelklingen2008.reversi.ai.strategy;

import java.util.Random;
import java.util.Set;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class NoLookAheadStrategy implements ReversiStrategy
{

    private final EvaluationFunction evalFunction;
    private final Random             random = new Random();

    public NoLookAheadStrategy(EvaluationFunction evalFunction)
    {
        this.evalFunction = evalFunction;
    }

    public Piece move(final GameModel gameModel)
    {
        Player me = gameModel.getTurnHolder();
        if (me == null) return null;
        Piece bestMove = null;
        int bestRating = Integer.MIN_VALUE;

        Set<Piece> legalMoves = gameModel.getLegalMovesSet(me);
        for (Piece move : legalMoves)
        {
            GameModel clone = new GameModel(gameModel);
            clone.placePiece(move);
            int rating = evalFunction.evaluatePosition(clone, me);
            if (rating == bestRating && random.nextInt() % 2 == 0)
            {
                bestMove = move;
            }
            if (rating > bestRating)
            {
                bestRating = rating;
                bestMove = move;
            }
        }
        return bestMove;
    }

    @Override
    public String toString()
    {
        return "NoLookAhead";
    }

    public int getCount()
    {
        return 1;
    }
}
