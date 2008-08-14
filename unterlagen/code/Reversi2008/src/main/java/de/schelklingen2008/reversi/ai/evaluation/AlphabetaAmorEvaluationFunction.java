package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class AlphabetaAmorEvaluationFunction implements EvaluationFunction
{

    private int[][] spielfeldbewertung = { { 5000, -100, 800, 800, 800, 800, -100, 5000 },
                                           { -100, -500, 100, 100, 100, 100, -500, -100 },
                                           { 800, 100, 200, 200, 200, 200, 100, 800 },
                                           { 800, 100, 200, 200, 200, 200, 100, 800 },
                                           { 800, 100, 200, 200, 200, 200, 100, 800 },
                                           { 800, 100, 200, 200, 200, 200, 100, 800 },
                                           { -100, -500, 100, 100, 100, 100, -500, -100 },
                                           { 5000, -100, 800, 800, 800, 800, -100, 5000 }, };

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

    public int mobiltyBewertung(GameModel gameModel, Player player)
    {
        int anzahlMoeglicheZuege = gameModel.getLegalMovesSet(player).size();
        int anzahlMoeglicheZuegeAnderer = gameModel.getLegalMovesSet(player.other()).size();
        return anzahlMoeglicheZuege * 1000 - anzahlMoeglicheZuegeAnderer * 1000;

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
