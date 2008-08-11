package de.schelklingen2008.dasverruecktelabyrinth.client;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import de.schelklingen2008.dasverruecktelabyrinth.model.PlayerType;

/**
 * Keeps all constant values that are of global interest for the client.
 */
public final class Constants
{

    /** must correspond to the name of the message properties file */
    public static final String                 BUNDLE_NAME          = "DasVerrueckteLabyrinth";

    /** names of message properties */
    public static final String                 MSG_BACK_TO_LOBBY    = "m.back_to_lobby";
    public static final String                 MSG_TITLE            = "m.title";
    public static final String                 MSG_WINNER           = "m.winner";
    public static final String                 MSG_DRAW             = "m.draw";

    /** is the pixel dimension of the pieces */
    public static final int                    SPRITE_SIZE          = 80;
    public static final Color                  COL_BOARD_BACKGROUND = new Color(0, 0, 0);

    /** piece colors and insets */
    public static final Map<PlayerType, Color> COL_PIECE            = new HashMap<PlayerType, Color>();
    static
    {
        COL_PIECE.put(PlayerType.WHITE, Color.YELLOW);
        COL_PIECE.put(PlayerType.BLACK, Color.BLUE);
        COL_PIECE.put(PlayerType.RED, Color.RED);
        COL_PIECE.put(PlayerType.GREEN, Color.GREEN);

    }
}
