package de.schelklingen2008.reversi.ai.strategy;

import java.util.ArrayList;
import java.util.List;

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

    private int minimaxvalue(GameModel model, int depth, Player me)
    {
        if (depth == 0)
        {
            return evaluationFunction.evaluatePosition(model, me);
        }

        int best;
        Player turnHolder = model.getTurnHolder();
        boolean[][] legalMovesArray = model.getLegalMoves(turnHolder);
        List<Piece> legalMovesList = new ArrayList<Piece>();
        int val;

        if (me == turnHolder)
        {
            best = Integer.MIN_VALUE;
        }
        else
        {
            best = Integer.MAX_VALUE;
        }

        for (int i = 0; i < legalMovesArray.length; i++)
        {
            for (int j = 0; j < legalMovesArray[i].length; j++)
            {
                if (legalMovesArray[i][j])
                {
                    legalMovesList.add(new Piece(i, j, turnHolder));
                }
            }
        }

        for (int i = 0; i < legalMovesList.size(); i++)
        {
            GameModel clone = new GameModel(model);
            Piece piece = legalMovesList.get(i);
            clone.placePiece(piece.getX(), piece.getY(), piece.getPlayer());
            val = minimaxvalue(clone, depth - 1, me);
            if (me == turnHolder)
            {
                if (val < best)
                {
                    best = val;
                }
            }
            else
            {
                if (val > best)
                {
                    best = val;
                }
            }
        }
        return best;
    }

    public int getCount()
    {
        return depth;
    }

    public Piece move(GameModel model)
    {

        int best = Integer.MIN_VALUE;
        int value;
        Piece bestMove = null;
        Player me = model.getTurnHolder();
        boolean[][] legalMovesArray = model.getLegalMoves(me);
        List<Piece> legalMovesList = new ArrayList<Piece>();

        for (int i = 0; i < legalMovesArray.length; i++)
        {
            for (int j = 0; j < legalMovesArray[i].length; j++)
            {
                if (legalMovesArray[i][j])
                {
                    legalMovesList.add(new Piece(i, j, me));
                }
            }
        }

        for (int i = 0; i < legalMovesList.size(); i++)
        {
            GameModel clone = new GameModel(model);
            Piece piece = legalMovesList.get(i);
            clone.placePiece(piece.getX(), piece.getY(), piece.getPlayer());
            value = minimaxvalue(model, depth, me);
            if (value > best)
            {
                best = value;
                bestMove = piece;
            }
        }
        return bestMove;
    }

}
