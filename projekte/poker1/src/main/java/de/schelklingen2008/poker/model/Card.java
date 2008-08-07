package de.schelklingen2008.poker.model;

import java.io.Serializable;

public class Card implements Serializable
{

    private int suit;
    private int number;

    public Card(int s, int n)
    {

        suit = s;
        number = n;
    }

    public int getValueInt()
    {
        return number;
    }

    public int getSuitInt()
    {
        return suit;
    }

    public String getValue()
    {
        if (number >= 0 && number <= 8) return "" + (number + 2);
        switch (number)
        {
            case 9:
                return "j";
            case 10:
                return "q";
            case 11:
                return "k";
            case 12:
                return "a";
        }
        return null;
    }

    public String getSuit()
    {
        switch (suit)
        {
            case 0:
                return "clubs";
            case 1:
                return "spades";
            case 2:
                return "hearts";
            case 3:
                return "diamonds";
        }
        return null;
    }

    public String getName()
    {
        return getSuit() + " " + getValue();
    }

}
