package de.schelklingen2008.mmpoker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel implements Serializable
{

    private Spielkarte[]  spielfeld;
    private Spieler       amZug;
    private int           pot;
    private List<Spieler> spielerliste = new ArrayList<Spieler>();
    private Spielstadien  spielstadium;

    public GameModel()
    {
        pot = 0;

        spielstadium = Spielstadien.FLOP;
        Rundenstart();

    }

    public void Rundenstart()
    { // Initiiert das Spiel.
        // Geld verteilen
        // for (Iterator<Spieler> iterator = spielerliste.iterator(); iterator.hasNext();)
        // {
        // iterator.next().setWettsumme(5000);
        // }
        // Geld verteilen ende
        Spieler spieler1 = new Spieler();
        getSpielerliste().add(0, spieler1);
        amZug = spieler1;
        Kartenstapel spielKarten = new Kartenstapel();
        // Karten aufs Spielfeld legen
        for (int i = 1; i < 5; i++)
        {
            getSpielfeld()[i] = spielKarten.zufallsKarte();
        }
        // Karten aufs Spielfeld legen ende
        // Blinds Setzen(ein zu kurz)
    }

    public void RundeWiederholen()
    {
        // blinds verschieben, karten ausgeben, gewinner ermitteln

    }

    public String autoErgaenzen()
    { // Ergänzt fehlenden Betrag im Wettkästchen des Spielers zur aktuellen

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

    public Spielkarte[] getSpielfeld()
    {
        return spielfeld;
    }

    public void setPot(int wettsumme)
    {
        pot = wettsumme;
    }

    public int getPot()
    {
        return pot;
    }

    public Spielkarte getKarte()
    {
        return null;
    }

    public void setSpielerliste(List<Spieler> spielerliste)
    {
        this.spielerliste = spielerliste;
    }

    public List<Spieler> getSpielerliste()
    {
        return spielerliste;
    }

    public void setSpielstadium(Spielstadien spielstadium)
    {
        this.spielstadium = spielstadium;
    }

    public Spielstadien getSpielstadium()
    {
        return spielstadium;
    }

    public void betraise(int wettsumme, int spielernummer)
    {
        // if (wettsumme > spielerliste.get(spielernummer).getGeld())
        // {
        //
        // }
        // else
        // {
        // if (condition)
        // {
        //
        // }
        // else
        // {
        //
        // }
        // }
        // sendet dem Server die Summe/Aktion
    }

    public void check()
    {
        // sendet dem Server die Aktion
    }

    public void fold()
    {
        // sendet dem Server die Aktion
    }

}
