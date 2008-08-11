package de.schelklingen2008.canasta.model;

public enum Rank
{
    THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE, TWO, JOKER;

    public boolean isWildcard()
    {
        if (this == JOKER || this == TWO) return true;

        return false;
    }
}