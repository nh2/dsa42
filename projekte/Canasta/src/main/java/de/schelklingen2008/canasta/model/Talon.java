package de.schelklingen2008.canasta.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Talon
{

    private List<Card> cards;

    public Talon()
    {
        super();
        cards = new ArrayList<Card>();
    }

    public static Talon getInstance()
    {
        Talon talon = new Talon();

        Card[] cards = new Card[110];

        int j = 0;
        for (int i = 0; i < 2; i++)
        {
            for (Suit suit : Suit.values())
            {
                for (Rank rank : Rank.values())
                {
                    if (rank != Rank.JOKER)
                    {
                        cards[j] = new Card(rank, suit);
                        j++;
                    }
                }
            }
            for (int k = 0; k < 3; k++)
            {
                cards[j] = new Card(Rank.JOKER, null);
                j++;
            }
        }

        talon.cards.addAll(Arrays.asList(cards));

        return talon;
    }

    // Top card has index 0
    public Card pop()
    {
        if (cards.size() <= 0) return null;

        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }

    public Card peek()
    {
        if (cards.size() <= 0) return null;

        Card card = cards.get(0);
        return card;
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

}
