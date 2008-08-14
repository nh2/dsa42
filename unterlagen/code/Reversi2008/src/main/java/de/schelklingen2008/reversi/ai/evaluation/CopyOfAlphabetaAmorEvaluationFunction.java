package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class CopyOfAlphabetaAmorEvaluationFunction implements EvaluationFunction
{

    private int[][] spielfeldbewertung = { { 5000, -100, 800, 800, 800, 800, -100, 5000 },
                                           { -100, -500, 100, 100, 100, 100, -500, -100 },
                                           { 800, 100, 200, 200, 200, 200, 100, 800 },
                                           { 800, 100, 200, 100, 100, 200, 100, 800 },
                                           { 800, 100, 200, 100, 100, 200, 100, 800 },
                                           { 800, 100, 200, 200, 200, 200, 100, 800 },
                                           { -100, -500, 100, 100, 100, 100, -500, -100 },
                                           { 5000, -100, 800, 800, 800, 800, -100, 5000 }, };



    public int evaluatePosition(GameModel gameModel, Player player)
    {
        return spielSteinBewertung(gameModel, player);
        // return mobiltyBewertung(gameModel, player);
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

    private int mobiltyBewertung(GameModel gameModel, Player player)
    {

        int anzahlMoeglicheZuege = gameModel.getLegalMoves(player).length;
        int anzahlMoeglicheZuegeAnderer = gameModel.getLegalMoves(player.other()).length;
        return anzahlMoeglicheZuege - anzahlMoeglicheZuegeAnderer;

    }

    public int anzahlSteineBewertung(GameModel gameModel, Player player)
    {
        return gameModel.countPieces(player) - gameModel.countPieces(player.other());

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
