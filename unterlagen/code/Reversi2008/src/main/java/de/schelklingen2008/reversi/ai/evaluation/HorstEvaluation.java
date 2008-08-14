package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class HorstEvaluation implements EvaluationFunction
{

    int[][] values;

    public HorstEvaluation(GameModel game)
    {
        // values = new int[][] { { 10, 5, 5, 5, 5, 5, 5, 10 }, { 5, 1, 1, 1, 1, 1, 1, 5 }, { 5, 1, 1, 1, 1,
        // 1, 1, 5 },
        // { 5, 1, 1, 1, 1, 1, 1, 5 }, { 5, 1, 1, 1, 1, 1, 1, 5 }, { 5, 1, 1, 1, 1, 1, 1, 5 },
        // { 5, 1, 1, 1, 1, 1, 1, 5 }, { 10, 5, 5, 5, 5, 5, 5, 10 } };

        int xbreite = game.getBoard().length;
        int ybreite = game.getBoard()[0].length;

        values = new int[xbreite][ybreite];

        for (int i = 0; i < xbreite; i++)
        {
            for (int k = 0; k < ybreite; k++)
            {
                values[i][k]++;
                if (i == 0 || k == 0 || i == xbreite - 1 || k == ybreite - 1) values[i][k] += 5;
                if (i == 0 && k == 0 || // Oben links
                    k == 0
                    && i == xbreite - 1
                    || // Oben rechts
                    i == 0 - 1
                    && k == ybreite - 1
                    || // Unten links
                    i == xbreite - 1
                    && k == ybreite - 1 // Unten rechts
                ) values[i][k] += 10;
            }

        }
        values = new int[][] { { 100, 5, 5, 5, 5, 5, 5, 100 }, { 30, -1, -1, -1, -1, -1, -1, 30 },
                { 30, -1, 2, 2, 2, 2, -1, 30 }, { 30, -1, 2, 1, 1, 1, -1, 30 }, { 3, -1, 2, 1, 1, 2, -1, 30 },
                { 30, -1, 2, 2, 2, 2, -1, 30 }, { 30, -1, -1, -1, -1, -1, -1, 30 }, { 100, 5, 5, 5, 5, 5, 5, 100 } };
    }

    public int evaluatePosition(GameModel game, Player player)
    {
        int result = 0;
        // int result2 = 0;
        //
        // int ichSteine = 0, otherSteine = 0;
        // int ichRandSteine = 0, otherRandSteine = 0;
        // int ichEckenSteine = 0, otherEckenSteine = 0;

        int xbreite = game.getBoard().length;
        int ybreite = game.getBoard()[0].length;

        for (int i = 0; i < xbreite; i++)
        {
            for (int k = 0; k < ybreite; k++)
            {
                Player besetzt = game.getPlayer(i, k);
                if (besetzt == null) continue;
                if (besetzt == player)
                    result += values[i][k];
                else
                    result -= values[i][k];
            }
        }

        return result;

    }
}
