package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class AmorEvaluationFunction implements EvaluationFunction
{

    //           private int[][] spielfeldbewertung = { { 9999, 5, 500, 200, 200, 500,   5, 9999 },
    //                                                {   5,    1,  50, 150, 150,  50,   1, 5 },
    //                                                {  500,  50, 250, 100, 100, 250,  50, 500 },
    //                                                {  200, 150, 100,  50,  50, 100, 150, 200 },
    //                                                {  200, 150, 100,  50,  50, 100, 150, 200 },
    //                                                {  500,  50, 250, 100, 100, 200,  50, 500 },
    //                                                {  5,    1,   50, 100, 100,  50,   1, 5 },
    //                                                {  9999, 5,  500, 200, 200, 500,   5, 9999 }, };
    //

    private int[][] spielfeldbewertung = { { 5000, -100, 800, 800, 800, 800, -100, 5000 },
                                           { -100, -500, 100, 100, 100, 100, -500, -100 }, { 800, 100, 200, 200, 200, 200, 100, 800 },
                                           { 800, 100, 200, 200, 200, 200, 100, 800 }, { 800, 100, 200, 200, 200, 200, 100, 800 },
                                           { 800, 100, 200, 200, 200, 200, 100, 800 }, { -100, -500, 100, 100, 100, 100, -500, -100 },
                                           { 5000, -100, 800, 800, 800, 800, -100, 5000 }, };


    public int evaluatePosition(GameModel gameModel, Player player)
    {
        return spielSteinBewertung(gameModel, player);
    }



    private int spielSteinBewertung(GameModel gameModel, Player player)
    {

        Player[][] board = gameModel.getBoard();
        int playerSteinBewertung = 0;

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
