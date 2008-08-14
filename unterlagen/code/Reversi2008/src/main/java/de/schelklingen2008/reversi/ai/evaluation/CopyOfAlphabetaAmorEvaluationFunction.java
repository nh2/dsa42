package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class CopyOfAlphabetaAmorEvaluationFunction implements EvaluationFunction
{

    private int[][] spielfeldbewertung = { { 50000, 100, 800, 800, 800, 800, 100, 50000 },
                                           { 100, 50, 100, 100, 100, 100, 50, 100 },
                                           { 800, 100, 200, 200, 200, 200, 100, 800 },
                                           { 800, 100, 200, 200, 200, 200, 100, 800 },
                                           { 800, 100, 200, 200, 200, 200, 100, 800 },
                                           { 800, 100, 200, 200, 200, 200, 100, 800 },
                                           { 100, 50, 100, 100, 100, 100, 50, 100 },
                                           { 50000, 100, 800, 800, 800, 800, 100, 50000 }, };

    public int evaluatePosition(GameModel gameModel, Player player)
    {
        return spielSteinBewertung(gameModel, player);
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
