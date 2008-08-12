package de.schelklingen2008.reversi.ai.evaluation;

import junit.framework.TestCase;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

public class IsSafeTest extends TestCase
{

    public void testIsSafe()
    {
        int size = GameModel.SIZE;
        Player[][] board = new Player[size][size];
        boolean[][] safePieces = new boolean[size][size];

        Player w = Player.WHITE;
        Player b = Player.BLACK;

        LarsEvalDiff eval = new LarsEvalDiff();

        boolean[][] result;

        board[0] = new Player[] { null, null, null, null, null, null, null, null };
        board[1] = new Player[] { null, null, null, null, null, null, null, null };
        board[2] = new Player[] { null, null, null, null, null, null, null, null };
        board[3] = new Player[] { null, null, null, null, null, null, null, null };
        board[4] = new Player[] { null, null, null, null, null, null, null, null };
        board[5] = new Player[] { null, null, null, null, null, null, null, null };
        board[6] = new Player[] { null, null, null, null, null, null, null, null };
        board[7] = new Player[] { null, null, null, null, null, null, null, null };

        safePieces[0] = new boolean[] { false, false, false, false, false, false, false, false };
        safePieces[1] = new boolean[] { false, false, false, false, false, false, false, false };
        safePieces[2] = new boolean[] { false, false, false, false, false, false, false, false };
        safePieces[3] = new boolean[] { false, false, false, false, false, false, false, false };
        safePieces[4] = new boolean[] { false, false, false, false, false, false, false, false };
        safePieces[5] = new boolean[] { false, false, false, false, false, false, false, false };
        safePieces[6] = new boolean[] { false, false, false, false, false, false, false, false };
        safePieces[7] = new boolean[] { false, false, false, false, false, false, false, false };

        result = eval.getSafePieces(board, w);

        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                assertEquals(safePieces[x][y], result[x][y]);
            }
        }

        board[0] = new Player[] { w, w, null, null, null, null, null, w };
        board[1] = new Player[] { w, null, null, null, null, null, null, null };
        board[2] = new Player[] { null, null, null, null, null, null, null, null };
        board[3] = new Player[] { null, null, null, null, null, null, null, null };
        board[4] = new Player[] { null, null, null, null, null, null, null, null };
        board[5] = new Player[] { null, null, null, null, null, null, null, null };
        board[6] = new Player[] { null, null, null, null, null, null, null, w };
        board[7] = new Player[] { null, null, null, null, null, null, null, w };

        safePieces[0] = new boolean[] { true, true, false, false, false, false, false, true };
        safePieces[1] = new boolean[] { false, false, false, false, false, false, false, false };
        safePieces[2] = new boolean[] { false, false, false, false, false, false, false, false };
        safePieces[3] = new boolean[] { false, false, false, false, false, false, false, false };
        safePieces[4] = new boolean[] { false, false, false, false, false, false, false, false };
        safePieces[5] = new boolean[] { false, false, false, false, false, false, false, false };
        safePieces[6] = new boolean[] { false, false, false, false, false, false, false, true };
        safePieces[7] = new boolean[] { false, false, false, false, false, false, false, true };

        result = eval.getSafePieces(board, w);

        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                assertEquals("Failure on " + x + " " + y, safePieces[x][y], result[x][y]);
            }
        }

        // Object o1 = new String("Bla");
        // Object o2 = new String("Bla");
        // assertEquals(o1, o2);
    }
}
