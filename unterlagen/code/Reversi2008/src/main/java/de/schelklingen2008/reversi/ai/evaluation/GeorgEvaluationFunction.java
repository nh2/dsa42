package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class GeorgEvaluationFunction implements EvaluationFunction
{

    private static final int[][] eval = new int[8][];
    static
    {
        eval[0] = new int[] { 51, -07, 01, 02, 02, 01, -07, 51 };
        eval[1] = new int[] { -7, -11, -3, -3, -3, -3, -11, -7 };
        eval[2] = new int[] { 01, -03, -1, 00, 00, -1, -03, 01 };
        eval[3] = new int[] { 02, -03, 00, -1, -1, 00, -03, 02 };
        eval[4] = new int[] { 02, -03, 00, -1, -1, 00, -03, 02 };
        eval[5] = new int[] { 01, -03, -1, 00, 00, -1, -03, 01 };
        eval[6] = new int[] { -7, -11, -3, -3, -3, -3, -11, -7 };
        eval[7] = new int[] { 51, -07, 01, 02, 02, 01, -07, 51 };
    }

    public int evaluatePosition(final GameModel rules, final Player player)
    {
        if (rules.isFinished() && rules.getWinner() == null) return 0;
        if (rules.isFinished() && rules.getWinner() == player) return 10000;
        if (rules.isFinished() && rules.getWinner() != player) return -10000;
        if (player == null) return 0;

        Player[][] board = rules.getBoard();
        int weight = 0;
        for (int x = 0; x < GameModel.SIZE; x++)
        {
            for (int y = 0; y < GameModel.SIZE; y++)
            {
                Player p = board[x][y];
                if (p == null)
                    continue;
                else if (p == player)
                    weight += eval[x][y];
                else
                    weight -= eval[x][y];
            }
        }
        return weight;
    }

    @Override
    public String toString()
    {
        return "Georg";
    }
}
