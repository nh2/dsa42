package de.schelklingen2008.reversi.client;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import de.schelklingen2008.reversi.model.Player;

/**
 * Keeps all constant values that are of global interest for the client.
 */
public final class Constants
{

    /** must correspond to the name of the message properties file */
    public static final String BUNDLE_NAME = "Reversi";

    /** names of message properties */
    public static final String MSG_BACK_TO_LOBBY = "m.back_to_lobby";
    public static final String MSG_TITLE = "m.title";
    public static final String MSG_WINNER = "m.winner";
    public static final String MSG_DRAW = "m.draw";

    /** is the pixel dimension of the pieces */
    public static final int SPRITE_SIZE = 64;

    /** piece colors and insets */
    public static final Color COL_PIECE_BORDER = Color.BLACK;
    public static final int INSETS_PIECE = 3;
    public static final Map<Player, Color> COL_PIECE = new HashMap<Player, Color>();
    static
    {
        COL_PIECE.put(Player.WHITE, new Color(150, 40, 40));
        COL_PIECE.put(Player.BLACK, new Color(40, 150, 40));
    }

    /** board colors */
    public static final Color COL_BOARD_BACKGROUND = new Color(220, 220, 220);
    public static final Color COL_BOARD_GRID = Color.BLACK;

}
