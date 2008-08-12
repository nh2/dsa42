package de.schelklingen2008.reversi.ai.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class SimpleStrategy implements ReversiStrategy
{

    public Piece move(final GameModel gameModel)
    {
        Player me = gameModel.getTurnHolder();

        List<Piece> legalMoves = new ArrayList<Piece>();
        for (int x = 0; x < GameModel.SIZE; x++)
            for (int y = 0; y < GameModel.SIZE; y++)
                if (gameModel.isLegalMove(x, y, me)) legalMoves.add(new Piece(x, y, me));

        return legalMoves.get(new Random().nextInt(legalMoves.size()));
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
