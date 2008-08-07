package de.schelklingen2008.mmpoker.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel {

    private Spielkarte[]  spielfeld;
    private Spieler       amZug;
    private int           pot;
    private List<Spieler> spielerliste = new ArrayList<Spieler>();
    private Spielstadien  spielstadium;

    public GameModel() {
        pot = 500;
        Spieler spieler1 = new Spieler();
        getSpielerliste().add(0, spieler1);
        amZug = spieler1;
        spielstadium = Spielstadien.FLOPD;

    }

    public void Rundenstart() { // Initiiert das Spiel.
        // Geld verteilen
        for (Iterator<Spieler> iterator = spielerliste.iterator(); iterator.hasNext();) {
            iterator.next().setWettsumme(5000);
        }
        // Geld verteilen ende

        // Blinds Setzen(ein zu kurz)
    }

    public void RundeWiederholen() {
        // blinds verschieben, karten ausgeben, gewinner ermitteln

    }

    public String autoErgaenzen() { // Ergänzt fehlenden Betrag im Wettkästchen des Spielers zur aktuellen

        // Wettsumme.
        return "" + (getPot() - amZug.getWettsumme());

    }

    // public void blaetterVergleichen()
    // {
    // Blatt aktBlatt;
    // Blatt hoechstesBlatt;
    // hoechstesBlatt.blattWert
    // for (Iterator<Spieler> iterator = spielerliste.iterator(); iterator.hasNext();)
    // {
    //           
    //           
    // aktBlatt = iterator.next().blattErmitteln();
    // if (aktBlatt >= hoechstesBlatt)
    // {
    // hoechstesBlatt = aktBlatt;
    // }
    // else
    // {
    //
    // }
    // }
    // }

    public Spielkarte[] getSpielfeld() {
        return spielfeld;
    }

    public void setPot(int wettsumme) {
        pot = wettsumme;
    }

    public int getPot() {
        return pot;
    }

    public Spielkarte getKarte() {
        return null;
    }

    public void setSpielerliste(List<Spieler> spielerliste) {
        this.spielerliste = spielerliste;
    }

    public List<Spieler> getSpielerliste() {
        return spielerliste;
    }

    public void setSpielstadium(Spielstadien spielstadium) {
        this.spielstadium = spielstadium;
    }

    public Spielstadien getSpielstadium() {
        return spielstadium;
    }

}
