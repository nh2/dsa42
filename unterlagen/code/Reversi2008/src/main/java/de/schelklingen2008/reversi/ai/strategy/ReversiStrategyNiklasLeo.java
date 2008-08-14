package de.schelklingen2008.reversi.ai.strategy;

import java.util.Set;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class ReversiStrategyNiklasLeo implements ReversiStrategy
{

    private final EvaluationFunction greenie;
    private final int                depth;
    private Player                   me;

    public ReversiStrategyNiklasLeo(EvaluationFunction greenie, int depth)
    {
        this.greenie = greenie;
        this.depth = depth;
    }

    public Piece move(GameModel gameModel)
    {
        me = gameModel.getTurnHolder();

        int best = Integer.MIN_VALUE;
        Piece bestMove = null;

        Set<Piece> legalMoves = gameModel.getLegalMovesSet(me);
        for (Piece piece : legalMoves)
        {
            GameModel clone = new GameModel(gameModel);
            clone.placePiece(piece.getX(), piece.getY(), me);
            int value = mmVal(gameModel, depth);
            if (value > best)
            {
                best = value;
                bestMove = piece;
            }
        }

        if (bestMove == null)
        {
            throw new IllegalStateException("no move found");
        }
        return bestMove;
    }

    public int mmVal(GameModel model, int depth)
    {
        if (depth == 0 || model.isFinished()) return greenie.evaluatePosition(model, me);

        int best;

        // Setze best auf minus viel oder plus viel
        if (model.getTurnHolder() == me) // max ist dran
            best = Integer.MIN_VALUE;
        else
            best = Integer.MAX_VALUE;

        Set<Piece> legalMoves = model.getLegalMovesSet(model.getTurnHolder());
        for (Piece piece : legalMoves)
        {
            GameModel clone = new GameModel(model);
            clone.placePiece(piece.getX(), piece.getY(), model.getTurnHolder());
            int value = mmVal(clone, depth - 1);

            if (model.getTurnHolder() == me) // MAX ist dran
            {
                if (value > best) best = value;
            }
            else
            {
                if (value < best) best = value;
            }

        }

        return best;
    }

    @Override
    public String toString()
    {
        return "Niklas ist cool aber Leo rul0rt ihn weg";
    }

    public int getCount()
    {
        // TODO Auto-generated method stub
        return 1;
    }

}
