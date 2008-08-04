package de.schelklingen2008.doppelkopf.model;

import java.util.List;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    List<Spieler>   spieler;
    Tisch           tisch;
    List<Integer>[] punkte;
    Spieler         mischen;
    boolean         rundeBeendet;

    private void neuesSpiel()
    {

    }

    public boolean isFinished()
    {
        return false;
    }
}