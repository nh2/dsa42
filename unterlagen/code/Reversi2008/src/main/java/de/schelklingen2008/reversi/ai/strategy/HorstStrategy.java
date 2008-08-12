package de.schelklingen2008.reversi.ai.strategy;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;

public class HorstStrategy implements ReversiStrategy
{

    public int getCount()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public Piece move(GameModel gameModel)
    {
        return minimax(gameModel);
    }

    private Piece minimax(GameModel game)
    {
        return null;
    }

    int minimaxval(GameModel game, int depth)
    {
        return -1;
    }
}
