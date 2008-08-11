package de.schelklingen2008.canasta.model;

import java.io.Serializable;

import com.threerings.io.Streamable;

public class Card implements Serializable, Comparable<Card>, Streamable
{

    private final Suit suit;
    private final Rank rank;

    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public Suit getSuit()
    {
        return suit;
    }

    public Rank getRank()
    {
        return rank;
    }

    @Override
    public String toString()
    {
        if (rank == Rank.JOKER)
        {
            return "JOKER";
        }
        else
        {

            return rank.toString() + " of " + suit.toString();
        }
    }

    public int compareTo(Card o)
    {
        return getRank().compareTo(o.getRank());
    }
}
