package de.schelklingen2008.reversi.ai.strategy;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class SimpleStrategy implements ReversiStrategy
{

    public SimpleStrategy()
    {
    }

    public Piece move(final GameModel gameModel)
    {
        Player player = gameModel.getTurnHolder();

        boolean[][] legalMoves = gameModel.getLegalMoves(player);
        for (int i = 0; i < legalMoves.length; i++)
            for (int j = 0; j < legalMoves[i].length; j++)
                if (legalMoves[i][j]) return new Piece(i, j, player);

        return null;
    }

    @Override
    public String toString()
    {
        return "Simple";
    }

    public int getCount()
    {
        return 1;
    }

}
