package de.schelklingen2008.canasta.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Talon extends ArrayList<Card>
{

    public Card pop()
    {
        Card card = get(0);
        this.remove(0);
        return card;
    }

    public void shuffle()
    {
        Collections.shuffle(this);
    }

    public Talon()
    {
        super();
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

        talon.addAll(Arrays.asList(cards));

        return talon;
    }
}
