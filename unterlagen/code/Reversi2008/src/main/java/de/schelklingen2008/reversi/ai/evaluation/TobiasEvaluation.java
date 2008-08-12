package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class TobiasEvaluation implements EvaluationFunction
{

    public int evaluatePosition(GameModel model, Player me)
    {
        int retVal;
        Player opp;
        if (me == Player.BLACK)
        {
            opp = Player.WHITE;
        }
        else
        {
            opp = Player.BLACK;
        }
        retVal = model.countPieces(me) - model.countPieces(opp);
        return retVal;
    }

}
