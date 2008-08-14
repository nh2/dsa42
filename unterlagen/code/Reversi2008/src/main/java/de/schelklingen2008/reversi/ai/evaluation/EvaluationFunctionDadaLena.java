package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class EvaluationFunctionDadaLena implements EvaluationFunction
{

    public int evaluatePosition(GameModel gameModel, Player player)
    {
        Player player2;
        player2 = gameModel.getTurnHolder();
        int i = gameModel.countPieces(player);
        int j = gameModel.countPieces(player2);
        return (i - j) * i;
    }
}
