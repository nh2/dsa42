package de.schelklingen2008.reversi.ai.strategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class LarsStratMmFixedDepth implements ReversiStrategy
{

    private final EvaluationFunction eval;
    private final int                depth;
    private GameModel                rootModel;

    Random                           random = new Random();

    public LarsStratMmFixedDepth(EvaluationFunction eval, int depth)
    {
        super();
        this.depth = depth;
        this.eval = eval;
    }

    public int getCount()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public Piece move(GameModel m)
    {
        // get current player and a array with all legal moves
        rootModel = m;
        Player turnHolder = rootModel.getTurnHolder();
        Set<Piece> legalPieces = rootModel.getLegalMovesSet(turnHolder);

        int best = Integer.MIN_VALUE;
        List<Piece> bestPieces = new ArrayList<Piece>();
        for (Piece piece : legalPieces)
        {
            GameModel childModel = new GameModel(rootModel);
            childModel.placePiece(piece.getX(), piece.getY(), turnHolder);
            int value = minimax(childModel, depth);
            if (value > best)
            {
                best = value;
                bestPieces.clear();
                bestPieces.add(piece);
            }
            else if (value == best)
            {
                bestPieces.add(piece);
            }
        }

        // int i = random.nextInt(bestPieces.size());
        int i = 0;
        return bestPieces.get(i);
    }

    private int minimax(GameModel model, int depth)
    {
        Player turnHolder = model.getTurnHolder();

        // end node without childs
        if (model.isFinished())
        {
            Player winner = model.getWinner();
            if (winner == rootModel.getTurnHolder())
            {
                return Integer.MAX_VALUE;
            }
            else if (winner == rootModel.getTurnHolder().other())
            {
                return Integer.MIN_VALUE;
            }
            else if (model.isDraw())
            {
                return 0;
            }
            else
            {
                throw new RuntimeException(new IllegalStateException("game finished but no winner or draw"));
            }
        }

        // we reached the maximum depth and must evaluate now
        if (depth == 0)
        {
            return eval.evaluatePosition(model, rootModel.getTurnHolder());
        }

        int best = rootModel.getTurnHolder() == turnHolder ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        // copy all legal pieces into a list
        List<Piece> legalPieces = new LinkedList<Piece>();

        boolean[][] legalMovesField = model.getLegalMoves(turnHolder);
        for (int x = 0; x < legalMovesField.length; x++)
        {
            for (int y = 0; y < legalMovesField[x].length; y++)
            {
                if (legalMovesField[x][y])
                {
                    legalPieces.add(new Piece(x, y, turnHolder));
                }
            }
        }

        // create tree childs
        for (Piece piece : legalPieces)
        {
            // clone the model
            GameModel childModel = new GameModel(model);

            // playce the piece on the clone
            childModel.placePiece(piece.getX(), piece.getY(), turnHolder);
            int value;
            if (turnHolder == childModel.getTurnHolder())
            {
                // extra turn
                if (turnHolder == rootModel.getTurnHolder())
                {
                    value = minimax(childModel, depth);
                }
                else
                {
                    value = minimax(childModel, depth - 1);
                }
            }
            else
            {
                // call recursion
                value = minimax(childModel, depth - 1);
            }

            if (rootModel.getTurnHolder() == turnHolder ? value > best : value < best)
            {
                best = value;
            }
        }

        return best;
    }

}
