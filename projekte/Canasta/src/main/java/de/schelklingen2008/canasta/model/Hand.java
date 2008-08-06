package de.schelklingen2008.canasta.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Hand implements Iterable<Card>
{

    private List<Card> cards;

    public Hand()
    {
        super();

        cards = new LinkedList<Card>();

        add(new Card(Rank.QUEEN, Suit.DIAMONDS));
        add(new Card(Rank.KING, Suit.HEARTS));
        add(new Card(Rank.EIGHT, Suit.SPADES));

        add(new Card(Rank.ACE, Suit.CLUBS));
        add(new Card(Rank.ACE, Suit.CLUBS));
        add(new Card(Rank.ACE, Suit.CLUBS));
        add(new Card(Rank.ACE, Suit.CLUBS));
        add(new Card(Rank.ACE, Suit.CLUBS));
        add(new Card(Rank.ACE, Suit.CLUBS));
        add(new Card(Rank.ACE, Suit.CLUBS));
        add(new Card(Rank.ACE, Suit.CLUBS));
        add(new Card(Rank.KING, Suit.HEARTS));
        add(new Card(Rank.EIGHT, Suit.SPADES));
        add(new Card(Rank.KING, Suit.HEARTS));
        add(new Card(Rank.EIGHT, Suit.SPADES));
        add(new Card(Rank.KING, Suit.HEARTS));
        add(new Card(Rank.EIGHT, Suit.SPADES));
        add(new Card(Rank.KING, Suit.HEARTS));
        add(new Card(Rank.EIGHT, Suit.SPADES));
        add(new Card(Rank.KING, Suit.HEARTS));
        add(new Card(Rank.EIGHT, Suit.SPADES));

    }

    public boolean add(Card e)
    {
        return cards.add(e);
    }

    public Card get(int index)
    {
        return cards.get(index);
    }

    public Card remove(int index)
    {
        return cards.remove(index);
    }

    public int size()
    {
        return cards.size();
    }

    @Override
    public String toString()
    {
        return cards.toString();
    }

    public Iterator<Card> iterator()
    {
        return cards.iterator();
    }
}
