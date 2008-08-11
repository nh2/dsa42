package de.schelklingen2008.canasta.client;

/**
 * Keeps all constant values that are of global interest for the client.
 */
public final class Constants
{

    /** must correspond to the name of the message properties file */
    public static final String BUNDLE_NAME             = "Canasta";

    /** names of message properties */
    public static final String MSG_BACK_TO_LOBBY       = "m.back_to_lobby";
    public static final String MSG_TITLE               = "m.title";
    public static final String MSG_WINNER              = "m.winner";
    public static final String MSG_DRAW                = "m.draw";

    /** properties of the game view */
    public static final int    HAND_BORDER             = 90;
    public static final int    BOARD_WIDTH             = 800;
    public static final int    BOARD_HEIGHT            = 800;
    public static final int    SHARED_CARDS_SPACE      = 135;

    public static final int    COLOR_HANDSPACE         = 0x003300;
    public static final int    COLOR_OUTLAY            = 0x336600;

    /** rule properties */
    public static final int    GAME_INITIAL_CARD_COUNT = 15;
    public static final int    GAME_CANASTA_MIN_CARDS  = 7;
    public static final int    GAME_WIN_SCORE          = 5000;

    public static final int[]  GAME_MIN_OUTLAY         = { 15, 50, 90, 120 };
    public static final int[]  GAME_SCORE_LEVEL        = { 0, 1500, 3000, 5000 };

}
