package de.schelklingen2008.poker.model;

import java.util.List;

public class PatternChecker
{

    private static final int PAIR           = 2;
    private static final int FOUR_OF_A_KIND = 8;
    private static final int ANZAHL         = 7;
    public int               counter        = 0;
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

    public int numberOfPairs()
    {
        int pairCounter = 0;

        for (int i = 0; i < ANZAHL; i++)
        {
            counter = 1;
            for (int j = i + 1; j < ANZAHL; j++)
            {
                if (cards.get(i).getValueInt() == cards.get(j).getValueInt())
                {
                    counter++;
                    pairCounter++;

                }
            }

        }

        return pairCounter;

    }

    public boolean isFlush()
    {
        int[] flushCounter = new int[4];
        for (int i = 0; i < 4; i++)
        {
            // z�hle wie viele karten es mit der SuitNr. i gibt
            for (int j = 0; j < ANZAHL; j++)
            {
                if (cards.get(j).getSuitInt() == i) flushCounter[i]++;
            }
            if (flushCounter[i] >= 5)
            {
                System.out.println("Flush mit der Farbe" + i);
                return true;
            }

        }
        return false;
    }

    public int getHighestPatternValue()
    {
        if (isFourOfAKind()) return FOUR_OF_A_KIND;
        if (numberOfPairs() == 1) return PAIR;
        return 0;

    }

}
