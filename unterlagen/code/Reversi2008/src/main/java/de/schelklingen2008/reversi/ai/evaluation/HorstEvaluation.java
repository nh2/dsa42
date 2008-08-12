package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class HorstEvaluation implements EvaluationFunction
{

    int ichSteine, otherSteine;
    int ichRandSteine, otherRandSteine;
    int ichEckenSteine, otherEckenSteine;

    public int evaluatePosition(GameModel game, Player player)
    {
        int result = 0;

        int xbreite = game.getBoard().length;
        int ybreite = game.getBoard()[0].length;

        for (int i = 0; i < xbreite; i++)
        {
            for (int k = 0; k < ybreite; k++)
            {
                Player besetzt = game.getPlayer(i, k);
                if (besetzt == null) continue;
                if (besetzt == player)
                {
                    ichSteine++;
                    if (i == 0 || k == 0 || i == xbreite - 1 || k == ybreite - 1) ichRandSteine++;
                    if (i == 0 && k == 0 || // Oben links
                        k == 0
                        && i == xbreite - 1
                        || // Oben rechts
                        i == 0 - 1
                        && k == ybreite - 1
                        || // Unten links
                        i == xbreite - 1
                        && k == ybreite - 1 // Unten rechts
                    ) ichEckenSteine++;
                }
                else
                {
                    otherSteine++;
                    if (i == 0 || k == 0 || i == xbreite - 1 || k == ybreite - 1) otherRandSteine++;
                    if (i == 0 && k == 0 || // Oben links
                        k == 0
                        && i == xbreite - 1
                        || // Oben rechts
                        i == 0 - 1
                        && k == ybreite - 1
                        || // Unten links
                        i == xbreite - 1
                        && k == ybreite - 1 // Unten rechts
                    ) otherEckenSteine++;
                }
            }
        }

        result += ichSteine;
        result += ichRandSteine * 3;
        result += ichEckenSteine * 8;

        result -= otherRandSteine * 3;
        result -= otherEckenSteine * 8;

        return result;
    }
}
