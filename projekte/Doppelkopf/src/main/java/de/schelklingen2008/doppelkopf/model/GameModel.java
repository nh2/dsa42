package de.schelklingen2008.doppelkopf.model;

import java.util.List;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    SpielerListe    spieler;
    Tisch           tisch;
    List<Integer>[] punkte;
    Spieler         mischen;		// TODO Nicht mehr benötigt: SpielerListe merkt, wer dran ist
    boolean         rundeBeendet;

    public GameModel()
    {
    	// Vier Spieler hinzufügen
    	
     	// Spiel starten
    	neuesSpiel();
    }
    
    private void neuesSpiel()
    {
       	// Tisch erzeugen
    	
    	// Karten geben
    }

    public boolean isFinished()
    {
        return false;
    }
}