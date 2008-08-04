package de.schelklingen2008.canasta.model;

public class Card
{

    private Suit suit; // Jokers will save NULL
    private Rank rank;

    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public Suit getSuit()
    {
        return suit;
    }

    public Rank getRank()
    {
        return rank;
    }
}
