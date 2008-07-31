package de.schelklingen2008.reversi.ai.strategy;

import junit.framework.TestCase;
import de.schelklingen2008.reversi.ai.evaluation.CornerEvaluationFunction;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class StrategyTest extends TestCase {

    public void testStrategy() throws Exception {
        ReversiStrategy minimax = new MinimaxStrategy(new CornerEvaluationFunction(), 5, false);
        ReversiStrategy alphabeta = new AlphaBetaStrategy(new CornerEvaluationFunction(), 5, false);

        play(minimax);
        System.out.println("#########");
        play(alphabeta);
    }

    private void play(final ReversiStrategy strategy) {
        GameModel model = new GameModel();
        computer(model, strategy, 2, 3);
        me(model, 2, 2);
        computer(model, strategy, 2, 1);
        me(model, 1, 1);
        computer(model, strategy, 3, 2);
        me(model, 3, 1);
        computer(model, strategy, 0, 0);
    }

    private void computer(final GameModel model, final ReversiStrategy strategy, final int x, final int y) {
        Piece move = strategy.move(model);
        assertEquals(x, move.getX());
        assertEquals(y, move.getY());
        model.placePiece(move.getX(), move.getY(), Player.WHITE);
        System.out.println("Computer: "
                           + move.getX()
                           + ","
                           + move.getY()
                           + " Considered "
                           + strategy.getCount()
                           + " moves.");
    }

    private void me(final GameModel model, final int x, final int y) {
        model.placePiece(x, y, Player.BLACK);
        System.out.println("Me      : " + x + "," + y);
    }

}
