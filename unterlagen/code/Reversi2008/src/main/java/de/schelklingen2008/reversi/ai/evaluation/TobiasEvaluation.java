package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class TobiasEvaluation implements EvaluationFunction
{

    private static final int POINTS_BORDER = 5;
    private static final int POINTS_CORNER = 15;

    public int evaluatePosition(GameModel model, Player me)
    {
        int retVal;
        Player opp;
        Player[][] board = model.getBoard();
        if (me == Player.BLACK)
        {
            opp = Player.WHITE;
        }
        else
        {
            opp = Player.BLACK;
        }
        retVal = model.countPieces(me); // - model.countPieces(opp);

        retVal = POINTS_BORDER * getBorderPositions(board, me);
        retVal = POINTS_CORNER * getCornerPositions(board, me);

        return retVal;
    }

    private int getCornerPositions(Player[][] board, Player player)
    {
        int count = 0;

        if (board[0][0] == player) count += 1;
        if (board[0][7] == player) count += 1;
        if (board[7][0] == player) count += 1;
        if (board[7][7] == player) count += 1;

        return count;
    }

    private int getBorderPositions(Player[][] board, Player player)
    {
        int count = 0;

        for (int x = 0; x < board.length; x++)
        {
            if (board[x][0] == player) count += 1;
        }

        for (int x = 0; x < board.length; x++)
        {
            if (board[x][7] == player) count += 1;
        }

        for (int y = 0; y < board[0].length; y++)
        {
            if (board[0][y] == player) count += 1;
        }

        for (int y = 0; y < board[7].length; y++)
        {
            if (board[7][y] == player) count += 1;
        }
        return count;
    }
}