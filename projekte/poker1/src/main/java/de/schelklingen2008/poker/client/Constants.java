package de.schelklingen2008.poker.client;

import java.awt.Color;

/**
 * Keeps all constant values that are of global interest for the client.
 */
public final class Constants
{

    /** must correspond to the name of the message properties file */
    public static final String BUNDLE_NAME           = "Poker";

    /** names of message properties */
    public static final String MSG_BACK_TO_LOBBY     = "m.back_to_lobby";
    public static final String MSG_TITLE             = "m.title";
    public static final String MSG_WINNER            = "m.winner";
    public static final String MSG_DRAW              = "m.draw";

    public static final long   START_BALANCE         = 10000;
    public static final long   SMALL_BLIND           = 200;

    public static final Color  BACK_GREEN            = new Color(0x115511);

    public static final String MSG_BALANCE           = "m.balance";
    public static final String MSG_RAISE             = "m.raise";
    public static final String MSG_FOLDED            = "m.folded";
    public static final String MSG_ALLIN             = "m.allin";
    public static final String MSG_LOST              = "m.lost";
    public static final String MSG_EUROS_IN_POT      = "m.eurosinpot";
    public static final String MSG_YOUR_CARDS        = "m.yourcards";
    public static final String MSG_YOUR_BALANCE      = "m.yourbalance";
    public static final String MSG_EUROS_MUST_BE_SET = "m.eurosmustbeset";
    public static final String MSG_CALL              = "m.call";
    public static final String MSG_FOLD              = "m.fold";
    public static final String MSG_CHECK             = "m.check";
    public static final String MSG_RERAISE           = "m.reraise";

    public static final String MSG_TURNHOLDER        = "m.turnholder";

    public static final String MSG_HOW_MUCH_RAISE    = "m.howmuchraise";
    public static final String MSG_HOW_MUCH_RERAISE  = "m.howmuchreraise";

    public static final String MSG_MIN               = "m.min";

    public static final String MSG_MAX               = "m.max";

    public static final String MSG_EUROS             = "m.euros";

    public static final String MSG_OKAY              = "m.okay";

    public static final String MSG_WON               = "m.won";

}
