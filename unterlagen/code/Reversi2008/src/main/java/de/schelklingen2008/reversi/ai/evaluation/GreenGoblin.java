package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class GreenGoblin implements EvaluationFunction
{

    private int greenGoblin = 0;

    int[][]     greenField  = { { 9999, 5, 500, 200, 200, 500, 5, 9999 }, { 5, 1, 50, 150, 150, 50, 1, 5 },
            { 500, 50, 250, 100, 100, 250, 50, 500 }, { 200, 150, 100, 50, 50, 100, 150, 200 },
            { 200, 150, 100, 50, 50, 100, 150, 200 }, { 500, 50, 250, 100, 100, 250, 50, 500 },
            { 5, 1, 50, 150, 150, 50, 1, 5 }, { 9999, 5, 500, 200, 200, 500, 5, 9999 } };

    public int evaluatePosition(GameModel gameModel, Player player)
    {

        int countPieces = gameModel.countPieces(player);
        greenGoblin += countPieces;

        if (gameModel.isFinished() && gameModel.isWinner(player)) return Integer.MAX_VALUE - 1;
        if (gameModel.isFinished() && !gameModel.isWinner(player)) return Integer.MIN_VALUE + 1;

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (gameModel.getPlayer(i, j) == null)
                {
                }
                else if (gameModel.getPlayer(i, j).equals(player))
                {
                    greenGoblin += greenField[i][j];
                }
                else if (!gameModel.getPlayer(i, j).equals(player))
                {
                    greenGoblin -= greenField[i][j];
                }

            }

        }

        return greenGoblin;

    }
}