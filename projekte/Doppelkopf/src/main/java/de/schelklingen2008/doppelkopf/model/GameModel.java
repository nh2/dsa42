package de.schelklingen2008.doppelkopf.model;

import java.util.List;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    SpielerListe    spieler;     // TODO static entfernen
    public Tisch    tisch;
    List<Integer>[] punkte;
    // Spieler mischen; // TODO Nicht mehr benötigt: SpielerListe merkt, wer dran ist
    boolean         rundeBeendet;

    public GameModel()
    {
        spieler = new SpielerListe();
        // Vier Spieler hinzufügen
        for (int i = 1; i <= 4; i++)
            spieler.add(new Spieler("Spieler " + i));

        // Spiel starten
        neuesSpiel();
    }

    private void neuesSpiel()
    {
        // Tisch erzeugen
        tisch = new Tisch(this, spieler.getAnDerReihe());

        // Karten geben
        tisch.gibKarten();
    }

    public SpielerListe getSpielerListe()
    {
        return spieler;
    }

    public boolean isFinished()
    {
        return false;
    }
}