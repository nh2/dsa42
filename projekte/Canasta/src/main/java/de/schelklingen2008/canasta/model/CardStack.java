package de.schelklingen2008.canasta.model;

import java.util.Collection;

import de.schelklingen2008.canasta.model.Card.Suit;

public class CardStack
{

    private Collection<Card> cards;

    Suit getSuit()
    {
        return Suit.CLUBS;
    }
}
