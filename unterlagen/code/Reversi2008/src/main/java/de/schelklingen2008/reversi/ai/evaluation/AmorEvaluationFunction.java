package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class AmorEvaluationFunction implements EvaluationFunction
{

    private int[][] spielfeldbewertung = { { 5000, 100, 2000, 2000, 2000, 2000, 100, 5000 },
                                           { 100, 50, 200, 200, 200, 200, 50, 100 },
                                           { 2000, 200, 200, 200, 200, 200, 200, 2000 },
                                           { 2000, 200, 200, 200, 200, 200, 200, 2000 },
                                           { 2000, 200, 200, 200, 200, 200, 200, 2000 },
                                           { 2000, 200, 200, 200, 200, 200, 200, 2000 },
                                           { 100, 50, 200, 200, 200, 200, 50, 100 },
                                           { 5000, 100, 2000, 2000, 2000, 2000, 100, 5000 }, };

    public int evaluatePosition(GameModel gameModel, Player player)
    {
        return spielSteinBewertung(gameModel, player);
    }

    private void bewertungAnpassen(GameModel gameModel, Player player)
    {
        if (gameModel.getPlayer(0, 0) == player)
        {
            getSpielfeldbewertung()[0][1] = 400;
            getSpielfeldbewertung()[1][1] = 400;
            getSpielfeldbewertung()[1][0] = 400;
        }
        if (gameModel.getPlayer(7, 7) == player)
        {
            getSpielfeldbewertung()[6][7] = 400;
            getSpielfeldbewertung()[6][6] = 400;
            getSpielfeldbewertung()[7][6] = 400;
        }
        if (gameModel.getPlayer(7, 0) == player)
        {
            getSpielfeldbewertung()[6][0] = 400;
            getSpielfeldbewertung()[6][1] = 400;
            getSpielfeldbewertung()[7][1] = 400;
        }
        if (gameModel.getPlayer(0, 7) == player)
        {
            getSpielfeldbewertung()[0][6] = 400;
            getSpielfeldbewertung()[1][6] = 400;
            getSpielfeldbewertung()[1][7] = 400;
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
                    playerSteinBewertung = playerSteinBewertung + getSpielfeldbewertung()[i][j]*2;
                }
                else
                {
                    if (gameModel.getBoard()[i][j] == player.other())
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
