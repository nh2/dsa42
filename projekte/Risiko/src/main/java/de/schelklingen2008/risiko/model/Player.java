package de.schelklingen2008.risiko.model;

/**
 * Is a simple abstraction for a player entity.
 */
public enum Player
{
    WHITE, BLACK;

    public static Player valueOf(int playerIndex)
    {
        if (playerIndex == 0) return WHITE;
        if (playerIndex == 1) return BLACK;
        return null;
    }
}
