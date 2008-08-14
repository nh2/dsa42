package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class BenEvalFunction implements EvaluationFunction
{

    private static final int[][] INDEX        = new int[][] { { 0, 1, 2, 3, 3, 2, 1, 0 }, { 1, 4, 5, 6, 6, 5, 4, 1 },
            { 2, 5, 7, 8, 8, 7, 5, 2 }, { 3, 6, 8, 9, 9, 8, 6, 3 }, { 3, 6, 8, 9, 9, 8, 6, 3 },
            { 2, 5, 7, 8, 8, 7, 5, 2 }, { 1, 4, 5, 6, 6, 5, 4, 1 }, { 0, 1, 2, 3, 3, 2, 1, 0 }, };

    private int[]                squareRating = new int[] { 100, -10, 10, 10, -10, -1, -1, 1, 1, 1 };

    private static final int     WIN          = Integer.MAX_VALUE;

    public BenEvalFunction()
    {
    }

    public BenEvalFunction(int[] squareRating)
    {
        if (squareRating == null || squareRating.length != 10) throw new IllegalArgumentException("array of length 10 expected");
        this.squareRating = squareRating.clone();
    }

    public void setSquareRating(int[] squareRating)
    {
        this.squareRating = squareRating;
    }

    public int[] getSquareRating()
    {
        return squareRating.clone();
    }

    public int evaluatePosition(final GameModel game, final Player player)
    {
        Player other = player.other();
        boolean finished = game.isFinished();
        if (finished && game.isWinner(player)) return WIN;
        if (finished && game.isWinner(other)) return -WIN;
        if (finished && game.isDraw()) return 0;

        Player[][] board = game.getBoard();
        int rating = 0;
        for (int y = 0; y < GameModel.SIZE; y++)
        {
            for (int x = 0; x < GameModel.SIZE; x++)
            {
                Player occupier = board[x][y];
                if (occupier == null) continue;
                int val = squareRating[INDEX[x][y]];
                if (occupier == player) rating += val;
                if (occupier == other) rating -= val;
            }
        }
        return rating;
    }

    @Override
    public String toString()
    {
        return "Ben";
    }
}
