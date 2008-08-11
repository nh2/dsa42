package de.schelklingen2008.canasta.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.schelklingen2008.canasta.client.Constants;

public class CardStack extends ArrayList<Card> implements Serializable
{

    private List<Card> cards;

    public CardStack()
    {
        super();
        cards = new ArrayList<Card>();
    }

    public Rank getRank()
    {

        return GameModel.getRank((Card[]) cards.toArray());
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
        int jokerCount = 0;

        for (Card card : cards)
        {
            if (card.getRank() == Rank.TWO || card.getRank() == Rank.JOKER)
            {
                jokerCount++;
            }
        }

        return jokerCount;
    }

    public boolean isCanasta()
    {
        return size() >= Constants.GAME_CANASTA_MIN_CARDS;
    }

    @Override
    public Object[] toArray()
    {
        return cards.toArray();
    }

}
