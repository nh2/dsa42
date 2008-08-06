package de.schelklingen2008.jipbo.client;

import java.awt.Color;

/**
 * Keeps all constant values that are of global interest for the client.
 */
public final class Constants
{

    /** must correspond to the name of the message properties file */
    public static final String BUNDLE_NAME          = "JipBo";

    /** names of message properties */
    public static final String MSG_BACK_TO_LOBBY    = "m.back_to_lobby";
    public static final String MSG_TITLE            = "m.title";
    public static final String MSG_WINNER           = "m.winner";
    public static final String MSG_DRAW             = "m.draw";

    /** game's duration - number of cards in stockpile */
    public static final int    DURATION             = 10;

    /** board colors */
    public static final Color  COL_BOARD_BACKGROUND = Color.decode("#00009900");
    public static final Color  COL_BOARD_GRID       = Color.BLACK;
}
