package de.schelklingen2008.poker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    public static final long START_BALANCE = 10000;

    private List<Player>     playerList    = new ArrayList<Player>();
    private List<Pot>        potList       = new ArrayList<Pot>();
    private long             highestBet;
    private List<Card>       cardList      = new ArrayList<Card>();
    private int              phase;
    private int              actPlayerIndex;
    private int              dealerIndex;
    private long             smallBlind;

    public boolean isFinished()
    {
        return false;
    }

    public void runde()
    {
        potList.add(new Pot(playerList));
    }
}