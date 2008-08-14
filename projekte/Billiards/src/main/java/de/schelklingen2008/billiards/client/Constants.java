package de.schelklingen2008.billiards.client;

/**
 * Keeps all constant values that are of global interest for the client.
 */
public final class Constants
{

    /** must correspond to the name of the message properties file */
    public static final String BUNDLE_NAME = "Billiards";

    public static final String MSG_PLACE_WHITE_BALL =
        "A foul was committed. Please place the white ball within the red area.";
    public static final String MSG_PLAYER_WON = "Player %s has won the game.";
    public static final String MSG_BLACK_BALL_POCKETED_IN_BREAK =
        "The black ball has been pocketed during the break. The game will be restarted.";

    /** names of message properties */
    public static final String MSG_BACK_TO_LOBBY = "m.back_to_lobby";
    public static final String MSG_TITLE = "m.title";
    public static final String MSG_WINNER = "m.winner";
    public static final String MSG_DRAW = "m.draw";
}
