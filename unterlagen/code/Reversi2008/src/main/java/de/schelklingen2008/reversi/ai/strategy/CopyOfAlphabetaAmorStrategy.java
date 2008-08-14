package de.schelklingen2008.reversi.ai.strategy;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class CopyOfAlphabetaAmorStrategy implements ReversiStrategy
{

    private final EvaluationFunction evalFunction;
    private final int                depth;
    private long                     durschnitt;
    private long                     minimalst;
    private long                     maximalst;
    private int                      count;
    Player                           player;

    public CopyOfAlphabetaAmorStrategy(EvaluationFunction evalFunction, int depth)
    {
        this.depth = depth;
        this.evalFunction = evalFunction;
    }

    public int getCount()
    {
        return count;
    }

    public Piece move(GameModel gameModel)
    {
        player = gameModel.getTurnHolder();
        int best = Integer.MIN_VALUE;
        Piece bestMove = null;

        int value = 0;

        for (Piece piece : gameModel.getLegalMovesSet(gameModel.getTurnHolder()))
        {

            GameModel clone;
            clone = new GameModel(gameModel);
            clone.placePiece(piece);
            // value = AlphaBetaNegaMax(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, clone);
            value = min(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, clone, true);

            if (value >= best)
            {
                best = value;
                bestMove = piece;
            }

        }

        return bestMove;

    }

    private int max(int depth, int alpha, int beta, GameModel gameModel, boolean kannWeiterSuchen)
    {
        if (depth == 0)
        {
            count++;
            return  evalFunction.evaluatePosition(gameModel, player);



        }

        for (Piece piece : gameModel.getLegalMovesSet(gameModel.getTurnHolder()))
        {
            GameModel clone = new GameModel(gameModel);
            clone.placePiece(piece);

            alpha = Math.max(alpha, min(depth - 1, alpha, beta, clone, true));

            if (alpha >= beta)
            {
                return beta;
            }

        }
        return alpha;

    }

    private int min(int depth, int alpha, int beta, GameModel gameModel, boolean kannWeiterSuchen)
    {
        if (depth == 0)
        {
            count++;
            return evalFunction.evaluatePosition(gameModel, player);

        }

        for (Piece piece : gameModel.getLegalMovesSet(gameModel.getTurnHolder()))
        {
            GameModel clone = new GameModel(gameModel);
            clone.placePiece(piece);

            beta = Math.min(beta, max(depth - 1, alpha, beta, clone, true));

            if (beta <= alpha)
            {
                return alpha;
            }

        }
        return beta;

    }

    // private int AlphaBetaNegaMax(int depth, int alpha, int beta, GameModel gameModel)
    // {
    // if (depth == 0)
    // {
    // count++;
    // int position = evalFunction.evaluatePosition(gameModel, player);
    // setDurschnitt(getDurschnitt() + position);
    // if (position < minimalst)
    // {
    // setMinimalst(position);
    // }
    // if (position > maximalst)
    // {
    // setMaximalst(position);
    // }
    //
    // return position;
    // }
    //
    //
    //
    // for (Piece piece : gameModel.getLegalMovesSet(gameModel.getTurnHolder()))
    // {
    // GameModel clone = new GameModel(gameModel);
    // clone.placePiece(piece);
    // int value;
    //
    //
    //
    // value = -AlphaBetaNegaMax(depth -1, -alpha, -beta, gameModel);
    //
    // if (value >= beta)
    //
    // return beta;
    //
    // if (value > alpha)
    //
    // alpha = value;
    //
    // }
    // return alpha;
    //
    // }

    public void setDurschnitt(long durschnitt)
    {
        this.durschnitt = durschnitt;
    }

    public long getDurschnitt()
    {
        return durschnitt;
    }

    public void setMinimalst(long minimalst)
    {
        this.minimalst = minimalst;
    }

    public long getMinimalst()
    {
        return minimalst;
    }

    public void setMaximalst(long maximalst)
    {
        this.maximalst = maximalst;
    }

    public long getMaximalst()
    {
        return maximalst;
    }
}
