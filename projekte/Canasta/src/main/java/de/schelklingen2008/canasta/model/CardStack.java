package de.schelklingen2008.canasta.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardStack extends ArrayList<Card>
{

    private List<Card> cards;

    public CardStack()
    {
        super();
        cards = new ArrayList<Card>();
    }

    public Rank getRank()
    {
        // TODO STUB
        return null;
    }

    @Override
    public boolean add(Card e)
    {
        return cards.add(e);
    }

    @Override
    public void clear()
    {
        cards.clear();
    }

    @Override
    public Card get(int index)
    {
        return cards.get(index);
    }

    @Override
    public Iterator<Card> iterator()
    {
        return cards.iterator();
    }

    @Override
    public Card remove(int index)
    {
        return cards.remove(index);
    }

    @Override
    public int size()
    {
        return cards.size();
    }

    public int getJokerCount()
    {
        // TODO getJokerCount() STUB
        return 0;
    }

    @Override
    public Object[] toArray()
    {
        return cards.toArray();
    }

}
