package de.schelklingen2008.reversi.ai.strategy;

import org.apache.tools.ant.taskdefs.Move;

import com.threerings.ezgame.Game;

import de.schelklingen2008.reversi.ai.evaluation.AmorEvaluationFunction;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class AmorStrategy implements ReversiStrategy
{

    Player player;

    public int getCount()
    {
        // TODO Auto-generated method stub
        return 0;
    }



    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        // TODO Auto-generated method stub
        return super.clone();
    }


    public Piece move(GameModel gameModel)
    {
        player = gameModel.getTurnHolder();
        int best = Integer.MAX_VALUE;
        Piece bestMove = null;
        int value;
        //        Piece piece = (Piece) gameModel.getLegalMovesSet(gameModel.getTurnHolder())
        Player hilf = gameModel.getTurnHolder();
        for (Piece piece : gameModel.getLegalMovesSet(gameModel.getTurnHolder()))
        {

            Game clone = (Game) clone();
            value = mmVal(clone.move(piece), 5);
            if (value > best)
            {
                best = value;
                bestMove = piece;

            }

        }

        return bestMove;

    }



    //    public Move minimax(GameModel gameModel)
    //    {
    //        int best = Integer.MAX_VALUE;
    //        Move bestMove = null;
    //        int value;
    //        Move move = gameModel.allMoves();
    //        Player hilf = gameModel.getTurnHolder();
    //        for (int i = 0; i < gameModel.getLegalMoves(hilf).length; i++)
    //        {
    //
    //            for (int j = 0; j < gameModel.getLegalMoves(hilf)[0].length; j++)
    //            {
    //                Game clone = (Game) gameModel.clone();
    //                value = mmVal(clone.move(move), 5);
    //                if (value > best)
    //                {
    //                    best = value;
    //                    bestMove = move;
    //
    //                }
    //            }
    //
    //            return bestMove;
    //
    //        }
    //
    //    }


    public int mmVal(GameModel gameModel, int depth)
    {
        Player hilf = gameModel.getTurnHolder();
        AmorEvaluationFunction amorEvaluationFunction = new AmorEvaluationFunction();
        if (depth == 0) return amorEvaluationFunction.evaluatePosition(gameModel, hilf);
        int best;
        int value;
        Move move;
        if (gameModel.isTurnHolder(player))
            best = Integer.MIN_VALUE;
        else
            best = Integer.MAX_VALUE;

        for (int i = 0; i < gameModel.getLegalMoves(hilf).length; i++)
        {

            for (int j = 0; j < gameModel.getLegalMoves(hilf)[0].length; j++)
            {
                Game clone = (Game) clone();
                value = mmVal(clone.move(move), depth - 1);
            }
        }
        if (gameModel.isTurnHolder(player))
        {
            if (value > best)
                best = value;
            else if (value < best) best = value;
        }
        return best;

    }
}
