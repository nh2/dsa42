package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class AmorEvaluationFunction implements EvaluationFunction
{

    private int[][] spielfeldbewertung = { { 50000, 100, 1000, 1000, 1000, 1000, 100, 50000 },
                                           { 100, 50, 200, 200, 200, 200, 50, 100 },
                                           { 1000, 200, 200, 200, 200, 200, 200, 1000 },
                                           { 1000, 200, 200, 200, 200, 200, 200, 1000 },
                                           { 1000, 200, 200, 200, 200, 200, 200, 1000 },
                                           { 1000, 200, 200, 200, 200, 200, 200, 1000 },
                                           { 100, 50, 200, 200, 200, 200, 50, 100 },
                                           { 50000, 100, 1000, 1000, 1000, 1000, 100, 50000 }, };

    public int evaluatePosition(GameModel gameModel, Player player)
    {
        return spielSteinBewertung(gameModel, player);
    }

    private void bewertungAnpassen(GameModel gameModel, Player player)
    {
        if (gameModel.getPlayer(0, 0) == player)
        {
            getSpielfeldbewertung()[0][1] = 1000;
            getSpielfeldbewertung()[1][1] = 1000;
            getSpielfeldbewertung()[1][0] = 1000;
        }
        if (gameModel.getPlayer(7, 7) == player)
        {
            getSpielfeldbewertung()[6][7] = 1000;
            getSpielfeldbewertung()[6][6] = 1000;
            getSpielfeldbewertung()[7][6] = 1000;
        }
        if (gameModel.getPlayer(7, 0) == player)
        {
            getSpielfeldbewertung()[6][0] = 1000;
            getSpielfeldbewertung()[6][1] = 1000;
            getSpielfeldbewertung()[7][1] = 1000;
        }
        if (gameModel.getPlayer(0, 7) == player)
        {
            getSpielfeldbewertung()[0][6] = 1000;
            getSpielfeldbewertung()[1][6] = 1000;
            getSpielfeldbewertung()[1][7] = 1000;
        }

    }

    private int spielSteinBewertung(GameModel gameModel, Player player)
    {

        int playerSteinBewertung = 0;

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (gameModel.getBoard()[i][j] == player)
                {
                    playerSteinBewertung = playerSteinBewertung +getSpielfeldbewertung()[i][j];
                }
                else
                {
                    if (gameModel.getBoard()[i][j] == player.other())
                    {
                        playerSteinBewertung = playerSteinBewertung -getSpielfeldbewertung()[i][j];

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
