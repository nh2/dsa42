/**
 * 
 */
package de.schelklingen2008.mmpoker.model;

public enum Spielstadien
{
    NICHTS, FLOP, TURN, RIVER;

    public Spielstadien next()
    {
        if (this == NICHTS) return FLOP;
        if (this == FLOP) return TURN;
        if (this == TURN) return RIVER;
        if (this == RIVER)
            return null;
        else
            return null;

    }

}
