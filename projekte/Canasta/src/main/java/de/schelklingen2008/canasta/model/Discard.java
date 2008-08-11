package de.schelklingen2008.canasta.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Discard implements Serializable
{

    private List<Card> cards;

    public Discard()
    {
        super();

        cards = new ArrayList<Card>();
    }

    // public boolean isEmpty()
    // {
    // return cards.isEmpty();
    // }

    // public int size()
    // {
    // return cards.size();
    // }

    public void push(Card card)
    {
        cards.add(card);
    }

    public void clear()
    {
        cards.clear();
    }

    public int size()
    {
        return cards.size();
    }

    public Card peek()
    {
        if (cards.size() <= 0) return null;
        return cards.get(cards.size() - 1);
    }

    public Card pop()
    {
        if (cards.size() <= 0) return null;
        Card card = cards.get(cards.size() - 1);
        cards.remove(card);
        return card;
    }

    public Card[] popAll()
    {
        Card[] popped = (Card[]) cards.toArray();
        cards.clear();
        return popped;
    }
}
