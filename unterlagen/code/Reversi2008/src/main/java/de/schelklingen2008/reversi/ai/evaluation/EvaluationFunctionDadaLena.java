package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class EvaluationFunctionDadaLena implements EvaluationFunction
{

    public int evaluatePosition(GameModel gameModel, Player player)
    {
        int eval;
        eval = gameModel.countPieces(player);
        return eval;
    }

}
