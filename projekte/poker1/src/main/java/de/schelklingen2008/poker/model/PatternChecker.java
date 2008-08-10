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
