package de.schelklingen2008.jipbo.model;

import java.util.List;

/**
 * Is a simple abstraction for a player entity.
 */
public class Player
{

    private String     mName;
    private List<Card> mStockPile;
    private Card[]     mDrawPile;
    private Card[]     mDiscardPile;

    public Player(String pName, List<Card> pStockPile, Card[] pDrawPile, Card[] pDiscardPile)
    {
        mName = pName;
        mStockPile = pStockPile;
        mDrawPile = pDrawPile;
        mDiscardPile = pDiscardPile;

    }

    public Card getLastStockCard()
    {
        return mStockPile.get(mStockPile.size());
    }

    public String getName()
    {
        return mName;
    }

    public List<Card> getStockPile()
    {
        return mStockPile;
    }

    public Card[] getDrawPile()
    {
        return mDrawPile;
    }

    public Card[] getDiscardPile()
    {
        return mDiscardPile;
    }
}