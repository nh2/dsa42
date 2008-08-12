package de.schelklingen2008.reversi.ai.strategy;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;

public interface ReversiStrategyFaMa
{

    /** Requests a move from a strategy. */
    Piece move(GameModel gameModel);

    /** How many positions has the strategy considered before making the move? */
    int getCount();
}
