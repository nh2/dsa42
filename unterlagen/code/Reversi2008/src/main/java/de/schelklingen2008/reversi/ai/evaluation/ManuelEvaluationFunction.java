package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class ManuelEvaluationFunction implements EvaluationFunction
{

    private int[] getWallBonuses(GameModel gameModel, Player player)
    {
        Player[][] board = gameModel.getBoard();
        int[] result = new int[2];

        int width = board.length, height = width == 0 ? 0 : board[0].length;

        for (int i = 0; i < width; i++)
        {

            if (board[i][0] != null)
            {
                result[board[i][0].ordinal()]++;
            }

            if (board[i][height - 1] != null)
            {
                result[board[i][height - 1].ordinal()]++;
            }

        }

        for (int i = 0; i < height; i++)
        {

            if (board[0][i] != null)
            {
                result[board[0][i].ordinal()]++;
            }

            if (board[width - 1][i] != null)
            {
                result[board[width - 1][i].ordinal()]++;
            }

        }

        if (board[0][0] != null)
        {
            result[board[0][0].ordinal()] += 2;
        }

        if (board[width - 1][0] != null)
        {
            result[board[width - 1][0].ordinal()] += 2;
        }

        if (board[0][height - 1] != null)
        {
            result[board[0][height - 1].ordinal()] += 2;
        }

        if (board[width - 1][height - 1] != null)
        {
            result[board[width - 1][height - 1].ordinal()] += 2;
        }

        return result;

    }

    public int evaluatePosition(GameModel gameModel, Player player)
    {
        int myPieces = gameModel.countPieces(player);
        int enemyPieces = gameModel.countPieces(player.other());

        int[] wallBonuses = getWallBonuses(gameModel, player);
        int myWallBonus = wallBonuses[player.ordinal()];
        int enemyWallBonus = wallBonuses[player.other().ordinal()];

        return Math.round(myPieces - enemyPieces + 4 * (myWallBonus - enemyWallBonus));
    }

}
