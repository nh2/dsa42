package de.schelklingen2008.jipbo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel implements Serializable
{

    private Card[]     mBuildPile;
    private List<Card> mStockCards;
    private Player[]   mPlayers;

    public GameModel()
    {
        // test initialisation
        mBuildPile = new Card[] { new Card(2), new Card(4), new Card(6), new Card(8), new Card(-1) };
        List<Card> mStockPile = new ArrayList<Card>();
        mStockPile.add(new Card(12));
        mStockPile.add(new Card(0));
        mStockPile.add(new Card(12));
        mPlayers = new Player[] {
                new Player("Ich", mStockPile, new Card[] { new Card(1), new Card(0), new Card(12), new Card(10),
                        new Card(0) }, new Card[] { new Card(5), new Card(2), new Card(0), new Card(10) }),
                new Player("Fabio", mStockPile, new Card[] { new Card(3), new Card(2), new Card(11), new Card(0),
                        new Card(0) }, new Card[] { new Card(2), new Card(0), new Card(12), new Card(2) }),
                new Player("Peter", mStockPile, new Card[] { new Card(1), new Card(0), new Card(12), new Card(10),
                        new Card(0) }, new Card[] { new Card(4), new Card(7), new Card(0), new Card(2) }),
                new Player("Bulgarien", mStockPile, new Card[] { new Card(1), new Card(0), new Card(12), new Card(10),
                        new Card(0) }, new Card[] { new Card(1), new Card(3), new Card(11), new Card(6) }) };
    }

    public Player[] getPlayers()
    {
        return mPlayers;
    }

    public Player getPlayerIndexOf(int pN)
    {
        return mPlayers[pN];
    }

    public int getPlayerSize()
    {
        return mPlayers.length;
    }

    public String getPlayerNameIndexOf(int pN)
    {
        return mPlayers[pN].getName();
    }

    public Card[] getBuildPile()
    {
        return mBuildPile;
    }

    public List<Card> getStockcards()
    {
        return mStockCards;
    }

}