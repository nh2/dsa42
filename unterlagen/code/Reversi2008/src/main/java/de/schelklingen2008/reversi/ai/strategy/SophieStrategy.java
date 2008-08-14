package de.schelklingen2008.reversi.ai.strategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class SophieStrategy implements ReversiStrategy
{

    private EvaluationFunction eval;
    private int                depth;
    private Player             initialPlayer;
    private Random             random;

    public SophieStrategy(EvaluationFunction eval, int depth)
    {
        this.eval = eval;
        this.depth = depth;
        // TODO Auto-generated constructor stub
    }

    public int getCount()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public Piece move(GameModel gameModel)
    {
        initialPlayer = gameModel.getTurnHolder();
        return miniMax(gameModel);
    }

    private Piece miniMax(GameModel model)
    {
        int best = Integer.MIN_VALUE;

        Piece bestMove = null;
        Player turnHolder = model.getTurnHolder();
        Set<Piece> possiblePieces = model.getLegalMovesSet(turnHolder);
        List<Piece> bestMovesList = new LinkedList<Piece>();
        for (Piece piece : possiblePieces)
        {
            GameModel cloneModel = new GameModel(model);
            cloneModel.placePiece(piece.getX(), piece.getY(), cloneModel.getTurnHolder());

            int value = mMVal(cloneModel, depth);

            if (value > best)
            {
                best = value;
                bestMovesList.clear();
                bestMovesList.add(piece);
            }
            if (value == best)
            {
                bestMovesList.add(piece);
            }

        }

        random = new Random();
        int rndBestMove = random.nextInt(bestMovesList.size());
        bestMove = bestMovesList.get(rndBestMove);

        return bestMove;

    }

    private int mMVal(GameModel model, int i)
    {
        Player turnHolder = model.getTurnHolder();
        if (i == 0) return eval.evaluatePosition(model, initialPlayer);

        int best;

        if (initialPlayer == turnHolder)
            best = Integer.MIN_VALUE;
        else
            best = Integer.MAX_VALUE;

        Set<Piece> possiblePieces = model.getLegalMovesSet(turnHolder);
        for (Piece piece : possiblePieces)
        {
            GameModel cloneModel = new GameModel(model);
            cloneModel.placePiece(piece.getX(), piece.getY(), cloneModel.getTurnHolder());

            int val = mMVal(cloneModel, i - 1);

            if (turnHolder != null)
            {
                if (initialPlayer == turnHolder)
                {
                    if (val > best) best = val;
                }
                else
                {
                    if (val < best) best = val;
                }
            }

        }
        return best;
    }

}
