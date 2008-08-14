package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class AlphabetaAmorEvaluationFunction implements EvaluationFunction
{


    private int[][] spielfeldbewertung = { { 50, -1, 8, 8, 8, 8, -1, 50 }, { -1, -5, 1, 1, 1, 1, -5, -1 },
                                           { 8, 1, 2, 2, 2, 2, 1, 8 }, { 8, 1, 2, 1, 1, 2, 1, 8 }, { 8, 1, 2, 1, 1, 2, 1, 8 },
                                           { 8, 1, 2, 2, 2, 2, 1, 8 }, { -1, -5, 1, 1, 1, 1, -5, -1 }, { 50, -1, 8, 8, 8, 8, -1, 50 }, };

    public int evaluatePosition(GameModel gameModel, Player player)
    {
        return spielSteinBewertung(gameModel, player);
    }

    private int spielSteinBewertung(GameModel gameModel, Player player)
    {

        int playerSteinBewertung = 0;
        Player[][] board = gameModel.getBoard();

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (board[i][j] == player)
                {
                    playerSteinBewertung = playerSteinBewertung + getSpielfeldbewertung()[i][j];
                }
                else
                {
                    if (board[i][j] == player.other())
                    {
                        playerSteinBewertung = playerSteinBewertung - getSpielfeldbewertung()[i][j];

                    }
                }
            }

        }
        return playerSteinBewertung;
    }


    public void setSpielfeldbewertung(int[][] spielfeldbewertung)
    {
        this.spielfeldbewertung = spielfeldbewertung;
    }

    public int[][] getSpielfeldbewertung()
    {
        return spielfeldbewertung;
    }
}
