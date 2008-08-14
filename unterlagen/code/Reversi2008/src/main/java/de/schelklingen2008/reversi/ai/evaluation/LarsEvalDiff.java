package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class LarsEvalDiff implements EvaluationFunction
{

    public int evaluatePosition(GameModel gameModel, Player player)
    {

        int value = 0;

        value += gameModel.countPieces(player) - gameModel.countPieces(player.other());
        value += countSafePieces(gameModel, player) * 2;

        return value;
    }

    public boolean[][] getSafePieces(Player[][] board, Player player)
    {
        boolean[][] safePieces = new boolean[GameModel.SIZE][GameModel.SIZE];

        int bound = GameModel.SIZE - 1;
        int[] xStart = { 0, bound, 0, bound };
        int[] yStart = { 0, 0, bound, bound };
        int[] xDirection = { 1, -1, 1, -1 };
        int[] yDirection = { 1, 1, -1, -1 };

        for (int i = 0; i < xStart.length; i++)
        {
            int x = xStart[i];
            int y = yStart[i];
            int lastRow = 8;

            // y direction
            try
            {
                while (board[x][y] == player)
                {
                    int rowCount = 0;
                    // x direction
                    while (board[x][y] == player)
                    {
                        if (rowCount >= lastRow)
                        {
                            break;
                        }
                        safePieces[x][y] = true;
                        rowCount++;
                        x += xDirection[i];
                        if (x >= GameModel.SIZE || x < 0 /* || safePieces[x][y] */)
                        {
                            lastRow = 8;
                            break;
                        }
                    }

                    // set to beginning of new row
                    y += yDirection[i];
                    if (y >= GameModel.SIZE || y < 0)
                    {
                        break;
                    }
                    lastRow = rowCount;
                    x = xStart[i];
                }
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.err.println("exeption on x = " + x + " and y = " + y);
                e.printStackTrace();
            }
        }

        return safePieces;
    }

    public boolean[][] getSafePieces(GameModel gameModel, Player player)
    {
        Player[][] board = gameModel.getBoard();
        return getSafePieces(board, player);
    }

    public int countSafePieces(Player[][] board, Player player)
    {
        boolean[][] safePieces = getSafePieces(board, player);

        int count = 0;

        for (boolean[] bs : safePieces)
        {
            for (boolean b : bs)
            {
                if (b)
                {
                    count++;
                }
            }
        }

        return count;
    }

    public int countSafePieces(GameModel gameModel, Player player)
    {
        Player[][] board = gameModel.getBoard();
        return countSafePieces(board, player);
    }

}
