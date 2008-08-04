package de.schelklingen2008.mmpoker.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel {

    private Spielkarte[] spielfeld;
    private Spieler      amZug;
    private int          wettsumme;
    List<Spieler>        spielerliste = new ArrayList<Spieler>();

    public void Rundenstart() { // Initiiert das Spiel.
        for (Iterator<Spieler> iterator = spielerliste.iterator(); iterator.hasNext();) { // Geld verteilen
            iterator.next().setWettsumme(5000);
        } // Geld verteilen ende

        // Blinds Setzen(ein zu kurz)
    }

    public void RundeWiederholen() {
        // blinds verschieben, karten ausgeben, geweinner ermitteln
    }

    public int autoErgaenzen() { // Ergänzt fehlenden Betrag im Wettkästchen des Spielers zur aktuellen
        // Wettsumme.
        return wettsumme - amZug.getWettsumme();

    }

    public void blattErmitteln() { // Kartenwert, Blatt, Höchste Karte

    }
}
