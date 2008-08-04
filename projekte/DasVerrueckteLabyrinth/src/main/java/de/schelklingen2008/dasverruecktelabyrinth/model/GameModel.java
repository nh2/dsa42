package de.schelklingen2008.dasverruecktelabyrinth.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    private static final int        SIZE         = 7;
    private static final PlayerType PLAYER_START = PlayerType.WHITE;

    private Tile[][]                board;                                                // Spielbrett
    private PlayerType              turnHolder;                                           // Wer ist dran
    private Tile                    insert;                                               // einschiebbare
                                                                                           // Spielfeldkarte

    Map<PlayerType, Player>         player       = new HashMap<PlayerType, Player>();
    Map<PlayerType, PlayerCards>    playerTypes  = new HashMap<PlayerType, PlayerCards>();

    public boolean isFinished()
    {
        return false;
    }
}