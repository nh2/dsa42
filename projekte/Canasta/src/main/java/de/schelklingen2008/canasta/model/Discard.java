package de.schelklingen2008.canasta.model;

import java.util.ArrayList;

/**
 * TODO DA WHOLE CLASS
 * 
 * @author Akademie
 */
public class Discard extends ArrayList<Card>
{

    // Top Card has index 'size'

    public Discard()
    {
        super();
    }

    public Card peek()
    {
        /**
         * TODO bla
         */
        return new Card(Rank.JACK, Suit.SPADES);
    }
}
