package de.schelklingen2008.canasta.model;

public enum Rank
{
    THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE, TWO, JOKER;

    public boolean isWildcard()
    {
        if (this == JOKER || this == TWO) return true;

        return false;
    }

    public String getSmallString()
    {
        switch (this)
        {
            case THREE:
                return "3";
            case FOUR:
                return "4";
            case FIVE:
                return "5";
            case SIX:
                return "6";
            case SEVEN:
                return "7";
            case EIGHT:
                return "8";
            case NINE:
                return "9";
            case TEN:
                return "10";
            case JACK:
                return "J";
            case QUEEN:
                return "Q";
            case KING:
                return "K";
            case ACE:
                return "A";
            case TWO:
            case JOKER:
                return "W";

            default:
                throw new RuntimeException("Unknown error");
        }
    }
}