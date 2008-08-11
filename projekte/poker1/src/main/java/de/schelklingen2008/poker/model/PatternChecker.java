package de.schelklingen2008.poker.model;

import java.util.List;

public class PatternChecker
{

    private static final int PAIR           = 2;
    private static final int FOUR_OF_A_KIND = 8;
    private static final int ANZAHL         = 7;

    private List<Card>       cards;

    public PatternChecker(List<Card> cards)
    {
        this.cards = cards;
    }

    public static List<Card> sort(List<Card> cardList)
    {
        // List<Card> newList = new ArrayList();
        for (int i = 0; i < ANZAHL - 1; i++)
        {
            for (int j = 0; j < ANZAHL - 1; j++)
            {
                if (cardList.get(j).greaterThan(cardList.get(j + 1)))
                {
                    Card card = new Card(0, 0);
                    card = cardList.get(j);
                    cardList.set(j, cardList.get(j + 1));
                    cardList.set(j + 1, card);

                }
            }
        }
        return cardList;
    }

    public boolean isFourOfAKind()
    {
        return false;

    }

    public boolean isPair()
    {
        int pairCounter = 0;
        for (int i = 0; i < ANZAHL; i++)
        {
            for (int j = i + 1; j < ANZAHL; j++)
            {
                if (cards.get(i).getValueInt() == cards.get(j).getValueInt())
                {
                    pairCounter++;

                }
            }
        }
        if (pairCounter > 0)
            return true;
        else
            return false;
    }

    public int getHighestPatternValue()
    {
        if (isFourOfAKind()) return FOUR_OF_A_KIND;
        if (isPair()) return PAIR;
        return 0;

    }

}
