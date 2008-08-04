package de.schelklingen2008.canasta.model;

public class Card
{

    private Suit suit;
    private Rank rank;

    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }
}
