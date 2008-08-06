package de.schelklingen2008.jipbo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    private Card[]     mBuildPile;
    private List<Card> mStockcards;
    private Player[]   mPlayers;

    public GameModel()
    {
        // test initialisation
        mBuildPile = new Card[] { new Card(2), new Card(4), new Card(6), new Card(8), new Card(-1) };
        List<Card> mStockPile = new ArrayList<Card>();
        mStockPile.add(new Card(12));
        mStockPile.add(new Card(0));
        mPlayers = new Player[] { new Player("Elitsa", mStockPile, new Card[] { new Card(1), new Card(0), new Card(12),
                new Card(10), new Card(0) }, new Card[] { new Card(2), new Card(2), new Card(2), new Card(2) }) };
    }

    public Player[] getPlayers()
    {
        return mPlayers;
    }

    public Card[] getBuildPile()
    {
        return mBuildPile;
    }

    public List<Card> getStockcards()
    {
        return mStockcards;
    }

}