package de.schelklingen2008.poker.model;

import java.util.ArrayList;
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

    // public int numberOfPairs()
    // {
    // int pairCounter = 0;
    //
    // for (int i = 0; i < ANZAHL; i++)
    // {
    // counter = 1;
    // for (int j = i + 1; j < ANZAHL; j++)
    // {
    // if (cards.get(i).getValueInt() == cards.get(j).getValueInt())
    // {
    // counter++;
    // pairCounter++;
    //
    // }
    // }
    //
    // }
    //
    // return pairCounter;
    //
    // }

    public Pattern mehrlinge()
    {
        int[] mehrlingsCounter = new int[13];
        List<Pattern> mehrlingsList = new ArrayList();
        Pattern pattern = new Pattern(0, 0, 0);
        int pairCounter = 0;
        int tripleCounter = 0;

        for (int i = 0; i < 13; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                if (cards.get(j).getValueInt() == i) mehrlingsCounter[i]++;
            }
            if (mehrlingsCounter[i] > 1)
            {
                System.out.println("Mehrling der Art " + i + " Anzahl: " + mehrlingsCounter[i]);
                if (mehrlingsCounter[i] == 2) pairCounter++;
            }
        }
        System.out.println(pairCounter);

        return pattern;
    }

    public int getCount(int i)
    {
        int mehrlingsCounter = 0;
        for (int j = 0; j < 7; j++)
        {
            if (cards.get(j).getValueInt() == i) mehrlingsCounter++;
        }
        // if (mehrlingsCounter > 1)
        // {
        // System.out.println("Mehrling der Art " + i + " Anzahl: " + mehrlingsCounter);
        //
        // }

        return mehrlingsCounter;

    }

    public boolean isPair() // es muss zusätzlich getestet werden, ob isFullHouse auch true ist
    {
        boolean pairFound = false;
        for (int i = 0; i < 13; i++)
        {
            if (getCount(i) == 2)
            {
                if (pairFound)
                    return false;
                else
                    pairFound = true;
            }
        }
        return pairFound;

    }

    public boolean isThreeOfAKind() // es muss zusätzlich getestet werden, ob isFullHouse auch true ist
    {
        boolean toakFound = false; // toak: three of a kind
        for (int i = 0; i < 13; i++)
        {
            if (getCount(i) == 3)
            {
                if (toakFound)
                    return false;
                else
                    toakFound = true;
            }
        }
        return toakFound;

    }

    public boolean isFourOfAKind()
    {
        boolean foakFound = false; // foak: four of a kind
        for (int i = 0; i < 13; i++)
        {
            if (getCount(i) == 4)
            {
                if (foakFound)
                    return false;
                else
                    foakFound = true;
            }
        }
        return foakFound;

    }

    public boolean isStraight()
    {
        if (straight(0, 5)) return true;
        if (straight(1, 5)) return true;
        if (straight(2, 5)) return true;
        return false;
    }

    public boolean straight(int pos, int length)
    {
        if (length == 1)
            return true;
        else
        {
            if (straight(pos + 1, length - 1) == true
                && cards.get(pos + 1).getValueInt() == cards.get(pos).getValueInt() + 1)
                return true;
            else
                return false;
        }

    }

    public boolean isFlush()
    {
        int[] flushCounter = new int[4];
        for (int i = 0; i < 4; i++)
        {
            // zähle wie viele karten es mit der SuitNr. i gibt
            for (int j = 0; j < ANZAHL; j++)
            {
                if (cards.get(j).getSuitInt() == i) flushCounter[i]++;
            }
            if (flushCounter[i] >= 5)
            {
                // System.out.println("Flush mit der Farbe" + i);
                return true;
            }

        }
        return false;
    }

    public boolean isFullHouse()
    {
        if (isPair() && isThreeOfAKind()) return true;
        return false;
    }

    public boolean isStraightFlush()
    {
        if (isStraight() && isFlush()) return true;
        return false;
    }

    public int getHighestPatternValue()
    {
        if (isFourOfAKind()) return FOUR_OF_A_KIND;

        return 0;

    }

}
