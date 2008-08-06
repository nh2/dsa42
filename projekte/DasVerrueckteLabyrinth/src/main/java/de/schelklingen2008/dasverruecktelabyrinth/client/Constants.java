package de.schelklingen2008.dasverruecktelabyrinth.client;

import java.awt.Color;

/**
 * Keeps all constant values that are of global interest for the client.
 */
public final class Constants
{

    /** must correspond to the name of the message properties file */
    public static final String BUNDLE_NAME          = "DasVerrueckteLabyrinth";

    /** names of message properties */
    public static final String MSG_BACK_TO_LOBBY    = "m.back_to_lobby";
    public static final String MSG_TITLE            = "m.title";
    public static final String MSG_WINNER           = "m.winner";
    public static final String MSG_DRAW             = "m.draw";

    /** is the pixel dimension of the pieces */
    public static final int    SPRITE_SIZE          = 64;
    public static final Color  COL_BOARD_BACKGROUND = new Color(150, 30, 20);
}
