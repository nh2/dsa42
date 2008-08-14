package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class GeorgEvaluationFunctionCandidate implements EvaluationFunction
{

    private static final int[][] eval = new int[8][];
    static
    {
        eval[0] = new int[] { 50, 01, 05, 02, 02, 02, 01, 50 };
        eval[1] = new int[] { 01, 10, 01, 01, 01, 01, 10, 01 };
        eval[2] = new int[] { 05, 01, 01, 01, 01, 01, 01, 05 };
        eval[3] = new int[] { 02, 01, 01, 00, 00, 01, 01, 02 };
        eval[4] = new int[] { 02, 01, 01, 00, 00, 01, 01, 02 };
        eval[5] = new int[] { 02, 01, 01, 01, 01, 01, 01, 05 };
        eval[6] = new int[] { 01, 10, 01, 01, 01, 01, 10, 01 };
        eval[7] = new int[] { 50, 01, 05, 02, 02, 05, 01, 50 };
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
                if (board[x][y] == null) continue;

                int value = eval[x][y];
                if (x == 0 && y == 1 && board[0][0] == null) value = -value;
                if (x == 1 && y == 1 && board[0][0] == null) value = -value;
                if (x == 1 && y == 0 && board[0][0] == null) value = -value;

                if (x == 0 && y == 6 && board[0][7] == null) value = -value;
                if (x == 1 && y == 6 && board[0][7] == null) value = -value;
                if (x == 1 && y == 7 && board[0][7] == null) value = -value;

                if (x == 6 && y == 0 && board[7][0] == null) value = -value;
                if (x == 6 && y == 1 && board[7][0] == null) value = -value;
                if (x == 7 && y == 1 && board[7][0] == null) value = -value;

                if (x == 6 && y == 7 && board[7][7] == null) value = -value;
                if (x == 6 && y == 6 && board[7][7] == null) value = -value;
                if (x == 7 && y == 6 && board[7][7] == null) value = -value;

                if (board[x][y] == player)
                    weight += value;
                else
                    weight -= value;
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
