package de.schelklingen2008.jipbo.model;

import java.io.Serializable;
import java.util.List;

import de.schelklingen2008.jipbo.client.Constants;

/**
 * Is a simple abstraction for a player entity.
 */
public class Player implements Serializable
{

    private String     mName;
    private List<Card> mStockPile;
    private Card[]     mDrawPile;
    private Card[]     mDiscardPile;

    public Player(String pName, List<Card> pStockPile, Card[] pDiscardPile)
    {
        mName = pName;
        mStockPile = pStockPile;
        mDrawPile = new Card[] { new Card(-2), new Card(-2), new Card(-2), new Card(-2), new Card(-2) };

        if (pDiscardPile != null)
        {
            mDiscardPile = pDiscardPile;
        }
        else
        {
            mDiscardPile = new Card[] { new Card(-2), new Card(-2), new Card(-2), new Card(-2) };
        }

    }

    public Card getLastStockPile()
    {
        return mStockPile.get(mStockPile.size() - 1);
    }

    public Card[] getBoardCards()
    {
        Card[] rCards = new Card[5];
        for (int i = 0; i < rCards.length - 1; i++)
        {
            rCards[i] = getDiscardPile()[i];
        }
        rCards[4] = getLastStockPile();
        return rCards;
    }

    public String getName()
    {
        return mName;
    }

    public List<Card> getStockPile()
    {
        return mStockPile;
    }

    public void removeLastCardFromStockPile()
    {
        mStockPile.remove(mStockPile.size() - 1);
    }

    public Card[] getDrawPile()
    {
        return mDrawPile;
    }

    public void setDrawPile(Card[] drawPile)
    {
        mDrawPile = drawPile;
    }

    public void removeDrawPileCard(int pPlace)
    {
        mDrawPile[pPlace].setNumber(Constants.EMPTY);
    }

    public Card[] getDiscardPile()
    {
        return mDiscardPile;
    }

    public void removeDiscardPileCard(int pPlace)
    {
        mDiscardPile[pPlace].setNumber(-2);// -2 <=> no card
    }

}