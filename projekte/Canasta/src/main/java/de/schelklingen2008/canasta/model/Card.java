package de.schelklingen2008.canasta.model;

public class Card
{

    private Suit suit;
    private Rank rank;

    public enum Suit
    {
        DIAMONDS, HEARTS, SPADES, CLUBS;
    }

    public enum Rank
    {
        TWO, THREE, 
        FOUR, FIVE, SIX, SEVEN, 
        EIGHT, NINE, TEN, JACK, QUEEN, KING, 
        ACE, 
        JOKER
    }
    
    
    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }
}
