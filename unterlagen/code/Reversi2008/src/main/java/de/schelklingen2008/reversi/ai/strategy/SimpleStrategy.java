package de.schelklingen2008.reversi.ai.strategy;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class SimpleStrategy implements ReversiStrategy
{

    public Piece move(final GameModel gameModel)
    {
        Player me = gameModel.getTurnHolder();
        for (int x = 0; x < GameModel.SIZE; x++)
            for (int y = 0; y < GameModel.SIZE; y++)
                if (gameModel.isLegalMove(x, y, me)) return new Piece(x, y, me);

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
