package de.schelklingen2008.reversi.ai.strategy;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class AmorStrategy implements ReversiStrategy
{

    private final EvaluationFunction evalFunction;
    private final int                depth;
    private int alpha = Integer.MIN_VALUE;
    private int beta = Integer.MAX_VALUE;
    private int durschnitt[] = new int[3];


    Player                           player;

    public AmorStrategy(EvaluationFunction evalFunction, int depth)
    {
        this.depth = depth;
        this.evalFunction = evalFunction;
    }

    public int getCount()
    {

        return 0;
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
            value = mmVal(depth, alpha, beta, clone);

            if (value > best)
            {
                best = value;
                bestMove = piece;

            }

        }

        return bestMove;

    }

    private int mmVal(int depth, Integer alpha, Integer beta, GameModel gameModel)
    {
        if (depth == 0) return evalFunction.evaluatePosition(gameModel, player);
        int best;
        int value = 0;

        if (gameModel.isTurnHolder(player))
            best = Integer.MIN_VALUE;
        else
            best = Integer.MAX_VALUE;

        for (Piece piece : gameModel.getLegalMovesSet(gameModel.getTurnHolder()))
        {
            GameModel clone = new GameModel(gameModel);
            clone.placePiece(piece.getX(), piece.getY(), gameModel.getTurnHolder());
            value = mmVal(depth - 1, alpha, beta, clone);
            //            durschnitt[0]=durschnitt[0] + value;
            //            durschnitt[1]++;
            //            durschnitt[2] = durschnitt[0]/durschnitt[1];
            if (gameModel.isTurnHolder(player))
            {
                if ( value > alpha )
                    alpha = value;
                if (value >= beta)
                    return beta;


            }
            else {


                if (value <= alpha)
                    return alpha;
                if (value < beta)
                    beta = value;



            }


        }
        if (gameModel.isTurnHolder(player))
        {
            return alpha;
        }
        else {
            return beta;
        }

        //        if (gameModel.isTurnHolder(player))
        //        {
        //            best = Math.max(value, best);
        //        }
        //        else
        //        {
        //            if (value < best) best = value;
        //        }
        //        return best;

    }
}
