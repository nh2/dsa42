package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class AmorEvaluationFunction implements EvaluationFunction
{

    private int[][] spielfeldbewertung = { { 1600, 100, 800, 800, 800, 800, 100, 1600 },
                                           { 100, 50, 200, 200, 200, 200, 50, 100 }, { 800, 200, 200, 200, 200, 200, 200, 800 },
                                           { 800, 200, 200, 200, 200, 200, 200, 800 }, { 800, 200, 200, 200, 200, 200, 200, 800 },
                                           { 800, 200, 200, 200, 200, 200, 200, 800 }, { 100, 50, 200, 200, 200, 200, 50, 100 },
                                           { 1600, 100, 800, 800, 800, 800, 100, 1600 }, };

    public int evaluatePosition(GameModel gameModel, Player player)
    {
        return spielSteinBewertung(gameModel, player);
    }

    private void bewertungAnpassen(GameModel gameModel, Player player)
    {
        if (gameModel.getPlayer(1, 1) == player)
        {
            getSpielfeldbewertung()[1][2] = 800;
            getSpielfeldbewertung()[2][2] = 800;
            getSpielfeldbewertung()[2][1] = 800;
        }
        if (gameModel.getPlayer(8, 8) == player)
        {
            getSpielfeldbewertung()[7][8] = 800;
            getSpielfeldbewertung()[7][7] = 800;
            getSpielfeldbewertung()[8][7] = 800;
        }
        if (gameModel.getPlayer(8, 1) == player)
        {
            getSpielfeldbewertung()[7][1] = 800;
            getSpielfeldbewertung()[7][2] = 800;
            getSpielfeldbewertung()[8][2] = 800;
        }
        if (gameModel.getPlayer(1, 8) == player)
        {
            getSpielfeldbewertung()[1][7] = 800;
            getSpielfeldbewertung()[2][7] = 800;
            getSpielfeldbewertung()[2][8] = 800;
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
                    playerSteinBewertung = playerSteinBewertung + getSpielfeldbewertung()[i][j];
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
