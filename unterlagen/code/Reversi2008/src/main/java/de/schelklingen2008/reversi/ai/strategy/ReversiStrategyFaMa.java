package de.schelklingen2008.reversi.ai.strategy;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;

public class ReversiStrategyFaMa implements ReversiStrategy
{

    public Piece move(GameModel gameModel)
    {
        int best = Integer.MIN_VALUE;
        Piece bestMove = null;
        for (int i = 0; i < GameModel.SIZE; i++)
        {
            for (int j = 0; j < GameModel.SIZE; j++)
            {
                if (gameModel.getLegalMoves(gameModel.getTurnHolder())[i][j])
                {
                    GameModel clone = new GameModel(gameModel); //erzeugt ein Klon des aktuellen Spielstands
                    int value = mm
                }
            }
        }   
    }

    public int getCount()
    {
        return 5;
    }

}
