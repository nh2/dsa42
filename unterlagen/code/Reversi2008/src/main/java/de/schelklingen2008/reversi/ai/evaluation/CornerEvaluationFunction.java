package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class CornerEvaluationFunction implements EvaluationFunction {

    public int evaluatePosition(final GameModel rules, final Player player) {
        if (rules.isFinished() && rules.isWinner(player)) return 10000;

        Player[][] board = rules.getBoard();
        int weight = 0;
        int r = GameModel.SIZE - 1;
        for (int y = 0; y < GameModel.SIZE; y++) {
            for (int x = 0; x < GameModel.SIZE; x++) {
                if (board[x][y] == player) {
                    if (x == 0 && y == 0)
                        weight += 20;
                    else if (x == 0 && y == r)
                        weight += 20;
                    else if (x == r && y == 0)
                        weight += 20;
                    else if (x == r && y == r)
                        weight += 20;
                    else if (x == 0)
                        weight += 5;
                    else if (x == r)
                        weight += 5;
                    else if (y == 0)
                        weight += 5;
                    else if (y == r)
                        weight += 5;
                    else
                        weight += 1;
                }
            }
        }
        return weight;
    }

    @Override
    public String toString() {
        return "Corner";
    }

}
