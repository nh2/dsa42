package de.schelklingen2008.reversi.ai.strategy;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;

public class ElitzaStrategy implements ReversiStrategy
{

    private final EvaluationFunction eval;

    public ElitzaStrategy(EvaluationFunction eval)
    {
        this.eval = eval;
    }

    public Piece move(GameModel gameModel)
    {
        int best = Integer.MIN_VALUE;
        Piece bestMove = null;
        boolean[][] legalMoves = gameModel.getLegalMoves(gameModel.getTurnHolder());
        for (int m = 0; m < GameModel.SIZE; m++)
        {
            for (int n = 0; n < GameModel.SIZE; n++)
            {
                if (legalMoves[m][n])
                {
                    gameModel = new GameModel(gameModel);

                    int val;
                    val = minval(gameModel, best);
                    if (val > best)
                    {
                        best = val;
                        Piece Piece = null;
                        bestMove = Piece;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minval(Object clone, int i)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    private int minVal(GameModel gameModel, int depth)
    {
        return 0;

    }

    public int getCount()
    {
        return 0;
    }
}
