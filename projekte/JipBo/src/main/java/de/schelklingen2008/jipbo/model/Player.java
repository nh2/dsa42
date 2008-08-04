package de.schelklingen2008.jipbo.model;

import java.util.List;

/**
 * Is a simple abstraction for a player entity.
 */
public class Player
{

    private List<Card> mStockPile;
    private Card[]     mDrawPile;
    private Card[]     mDiscardPile;
}