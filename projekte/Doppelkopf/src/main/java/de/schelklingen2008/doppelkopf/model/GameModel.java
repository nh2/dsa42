package de.schelklingen2008.doppelkopf.model;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.schelklingen2008.util.LoggerFactory;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel implements Serializable
{

    private static final Logger logger       = LoggerFactory.create();

    private Tisch               tisch;
    private List<Integer>[]     punkte;
    private SpielerListe        spielerliste = new SpielerListe();
    private boolean             rundeBeendet;

    public GameModel()
    {
    }

    public GameModel(String[] spielerNamen)
    {
        if (spielerNamen.length != 4) throw new RuntimeException("Falsche Spieleranzahl für Doppelkopf");

        // Spieler hinzufügen
        for (String spielername : spielerNamen)
            spielerliste.add(new Spieler(spielername));

        logger.log(Level.INFO, spielerliste.toString());

        // Spiel starten
        neuesSpiel();
    }

    private void neuesSpiel()
    {
        tisch = new Tisch(spielerliste);
        tisch.gibKarten();
    }

    public boolean isFinished()
    {
        return false;
    }

    public SpielerListe getSpieler()
    {
        return spielerliste;
    }

    public Tisch getTisch()
    {
        return tisch;
    }

    public boolean isRundeBeendet()
    {
        return rundeBeendet;
    }

    public void karteAusspielen(Spieler spieler, Karte karte)
    {
        logger.log(Level.INFO, spieler.toString() + " will Karte spielen: " + karte.toString() + " ");
        // Spieler spieler = spielerliste.getSpieler(spielerName);
        Blatt blatt = spieler.getBlatt();
        List<Karte> mitte = tisch.getMitte();
        // TODO berechnen, ob der Zug gültig ist
        boolean zugGueltig = true;

        if (spieler != spielerliste.getAnDerReihe())
        {
            zugGueltig = false;
            logger.log(Level.INFO, "Spieler nicht an der Reihe. ");
        }

        if (mitte.size() == 4)
        {
            zugGueltig = false;
            logger.log(Level.INFO, "Mitte ist voll. ");
        }

        if (zugGueltig)
        {
            // Zug ausführen
            logger.log(Level.INFO, "Gültiger Zug. ");
            blatt.remove(karte);
            mitte.add(karte);
            getSpieler().next();

            // TODO Auf vollen Stapel überprüfen, wenn nötig, Karten einstreichen

            return;
        }
        logger.log(Level.INFO, "Ungültiger Zug. ");
    }
}