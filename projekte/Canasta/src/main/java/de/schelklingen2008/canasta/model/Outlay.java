package de.schelklingen2008.canasta.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Outlay implements Iterable<CardStack>
{

    private List<CardStack> cardStacks;

    public Outlay()
    {
        super();
        cardStacks = new ArrayList<CardStack>();
    }

    public boolean add(CardStack e)
    {
        return cardStacks.add(e);
    }

    public CardStack get(int index)
    {
        return cardStacks.get(index);
    }

    public boolean isEmpty()
    {
        return cardStacks.isEmpty();
    }

    public Iterator<CardStack> iterator()
    {
        return cardStacks.iterator();
    }

    public int size()
    {
        return cardStacks.size();
    }

}
