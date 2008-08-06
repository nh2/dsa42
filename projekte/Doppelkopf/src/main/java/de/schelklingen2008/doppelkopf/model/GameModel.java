package de.schelklingen2008.doppelkopf.model;

import java.io.Serializable;
import java.util.List;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel implements Serializable
{

    private Tisch           tisch;
    private List<Integer>[] punkte;
    private boolean         rundeBeendet;

    public GameModel()
    {

        // Spiel starten
        neuesSpiel();
    }

    private void neuesSpiel()
    {
        tisch = new Tisch();
        tisch.gibKarten();
    }

    public boolean isFinished()
    {
        return false;
    }

    public Tisch getTisch()
    {
        return tisch;
    }

    public boolean isRundeBeendet()
    {
        return rundeBeendet;
    }
}