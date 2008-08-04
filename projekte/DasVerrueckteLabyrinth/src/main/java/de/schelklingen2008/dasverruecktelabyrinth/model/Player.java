package de.schelklingen2008.dasverruecktelabyrinth.model;

/**
 * Is a simple abstraction for a player entity.
 */
public enum Player
{
    WHITE, BLACK, RED, GREEN;

    public static Player valueOf(int playerIndex)
    {
        if (playerIndex == 0) return WHITE;
        if (playerIndex == 1) return BLACK;
        if (playerIndex == 2) return RED;
        if (playerIndex == 3) return GREEN;
        return null;
    }
}
