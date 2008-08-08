package de.schelklingen2008.canasta.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Hand implements Iterable<Card>, Serializable
{

    private List<Card> cards;

    public Hand()
    {
        super();

        cards = new LinkedList<Card>();
    }

    public boolean add(Card e)
    {
        boolean result = cards.add(e);
        sort();
        return result;
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

    private void sort()
    {
        Collections.sort(cards);
    }

}
