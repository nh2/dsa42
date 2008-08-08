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
        spielfeld = new Spielkarte[5];
        spielstadium = Spielstadien.FLOP;
        Rundenstart();

    }

    public void Rundenstart()
    { // Initiiert das Spiel.
        // Geld verteilen
        // for (Iterator<> iterator = spielerliste.iterator(); iterator.hasNext();)
        // {
        // iterator.next().setWettsumme(5000);
        // }
        // Geld verteilen ende
        Spieler spieler1 = new Spieler();
        getSpielerliste().add(0, spieler1);
        amZug = spieler1;
        Kartenstapel spielKarten = new Kartenstapel();

        // Karten aufs Spielfeld legen
        for (int i = 0; i < 4; i++)
        {
            getSpielfeld()[i] = spielKarten.zufallsKarte();
        }
        // Karten aufs Spielfeld legen ende
        // Blinds Setzen(ein zu kurz)
        for (int i = 0; i < spielerliste.size(); i++)
        {
            spielerliste.get(i).setWasfuernBlind(Blind.NICHTS);
        }
        spielerliste.get(spielerliste.size()).setWasfuernBlind(Blind.DEALER);
        spielerliste.get(0).setWasfuernBlind(Blind.SMALLBLIND);
        spielerliste.get(1).setWasfuernBlind(Blind.BIGBLIND);
        // Blinds Setzen ende

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

    public void betraise(int wettsumme, Spieler client)
    {
        System.out.println("Es funzt!!!");
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
