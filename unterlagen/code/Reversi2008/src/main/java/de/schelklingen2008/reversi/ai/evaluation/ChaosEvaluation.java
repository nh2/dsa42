package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class ChaosEvaluation implements EvaluationFunction
{

    public int evaluatePosition(GameModel gameModel, Player player)
    {
        int mReturn = 0;
        if (gameModel.isFinished() && gameModel.isWinner(player)) return 10000;
        if (gameModel.isFinished() && !gameModel.isWinner(player)) return -10000;

        mReturn += gameModel.countPieces(player) * 2;
        if (gameModel.countFree() <= 5)
        {
            mReturn += Math.pow(gameModel.getBoard().length, 4) + Math.pow(gameModel.getBoard().length, 3);
        }

        // rank possible moves
        if (gameModel.getLegalMovesSet(player.other()).size() == 0)
        {
            mReturn += 150;
        }

        // rank fields
        int[][] fieldRanks = new int[][] { { 3000, -500, 200, 100, 100, 200, -500, 3000 },
                { -500, -250, 200, 100, 100, 200, -500, -500 }, { 300, -125, 200, 100, 100, 200, -500, 300 },
                { 200, -60, 200, 100, 100, 200, -500, 200 }, { 200, -60, 200, 100, 100, 200, -500, 200 },
                { 300, -125, 200, 100, 100, 200, -500, 300 }, { -500, -250, 200, 100, 100, 200, -500, -500 },
                { 3000, -500, 300, 200, 200, 200, -500, 3000 } };
        Player[][] gameBoard = gameModel.getBoard();

        for (int v = 0; v < GameModel.SIZE; v++)// vertical
        {
            for (int h = 0; h < GameModel.SIZE; h++)// horizontal
            {
                if (gameBoard[v][h] == player)
                {
                    mReturn += fieldRanks[v][h];
                }
            }
        }

        return mReturn;
    }
}
