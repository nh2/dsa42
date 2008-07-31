package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class CountEvaluationFunction implements EvaluationFunction
{

    public int evaluatePosition(final GameModel gameModel, final Player player)
    {
        if (gameModel.isFinished() && gameModel.isWinner(player)) return 10000;
        if (gameModel.isFinished() && !gameModel.isWinner(player)) return -10000;
        return gameModel.countPieces(player);
    }

    @Override
    public String toString()
    {
        return "Count";
    }

}
