package de.schelklingen2008.dasverruecktelabyrinth.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    private static final int    SIZE         = 7;
    private static final Player PLAYER_START = Player.WHITE;

    private Tile[][]            board;
    private Player              turnHolder;

    Map<Player, PlayerCards>    players      = new HashMap<Player, PlayerCards>();

    public boolean isFinished()
    {
        return false;
    }
}