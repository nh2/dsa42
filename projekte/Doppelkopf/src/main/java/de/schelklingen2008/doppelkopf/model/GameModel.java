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

        boolean zugGueltig = true;

        if (spieler != spielerliste.getAnDerReihe())
        {
            zugGueltig = false;
            logger.log(Level.INFO, "Spieler nicht an der Reihe. "
                                   + spielerliste.getAnDerReihe()
                                   + " ist an der Reihe. ");
        }

        if (mitte.size() == 4)
        {
            zugGueltig = false;
            logger.log(Level.INFO, "Mitte ist voll. ");
        }

        if (tisch.getMitte().size() != 0) // Der erste darf immer alles spielen
        {
            Karte grundkarte = tisch.getMitte().get(0);
            if (grundkarte.isTrumpf())
            { // Die Grundkarte ist Trumpf
                // Er spielt nicht trumpf, aber er hat Trumpf
                if (!karte.isTrumpf() && spieler.getBlatt().hatTrumpf()) zugGueltig = false;
            }
            else if (spieler.getBlatt().hatFarbe(grundkarte.farbe))
            {
                if (karte.farbe != grundkarte.farbe || karte.isTrumpf()) zugGueltig = false;
            }
        }

        if (zugGueltig)
        {
            // Zug ausführen
            logger.log(Level.INFO, "Gültiger Zug. ");
            blatt.remove(karte);
            mitte.add(karte);
            tisch.getMittenspieler().add(spieler);

            getSpieler().next();

            if (mitte.size() == 4)
            {
                int position = mitte.indexOf(Karte.gibtHoechsteAusStapel(mitte));
                Spieler stichsieger = tisch.getMittenspieler().get(position);
                stichsieger.getGewinnstapel().addAll(mitte);
                tisch.stichGespielt();
                spielerliste.setAnDerReihe(stichsieger);
            }

            if (tisch.getStichAnzahl() == 12)
            {
                int repunkte = tisch.getRePunkte();
                int contrapunkte = tisch.getContraPunkte();
                int rundenpunkte = 0;

                if (repunkte > contrapunkte)
                {
                    rundenpunkte++;
                    int diff = repunkte - contrapunkte;
                    rundenpunkte += diff / 30;
                }
                else if (contrapunkte > repunkte)
                {
                    rundenpunkte -= 2;
                    int diff = contrapunkte - repunkte;
                    rundenpunkte -= diff / 30;
                }

                if (tisch.getTeamRe().size() == 1) // Solo
                    for (Spieler p : tisch.getTeamRe())
                        p.rundenpunkte.add(rundenpunkte * 3);
                else
                    // kein Solo
                    for (Spieler p : tisch.getTeamRe())
                        p.rundenpunkte.add(rundenpunkte);

                for (Spieler p : tisch.getTeamContra())
                    p.rundenpunkte.add(-rundenpunkte);

                neuesSpiel();
            }

            return;
        }
        logger.log(Level.INFO, "Ungültiger Zug. ");
    }
}