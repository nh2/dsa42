package de.schelklingen2008.mmpoker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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

        for (Iterator<Spieler> iterator = spielerliste.iterator(); iterator.hasNext();)
        {
            iterator.next().setWettsumme(5000);
        }// Geld verteilen ende

        Spieler spieler1 = new Spieler();
        getSpielerliste().add(0, spieler1);
        setAmZug(spieler1);
        Kartenstapel spielKarten = new Kartenstapel();

        // Karten aufs Spielfeld legen

        for (int i = 0; i < 4; i++)
        {

            getSpielfeld()[i] = spielKarten.zufallsKarte();
        }// Karten aufs Spielfeld legen ende

        // Blinds Setzen(ein zu kurz)
        for (int i = 0; i < spielerliste.size(); i++)
        {
            spielerliste.get(i).setWasfuernBlind(Blind.NICHTS);
        }
        spielerliste.get(spielerliste.size() - 1).setWasfuernBlind(Blind.DEALER);
        spielerliste.get(0).setWasfuernBlind(Blind.SMALLBLIND);
        if (spielerliste.size() > 1)
        {
            spielerliste.get(1).setWasfuernBlind(Blind.BIGBLIND);
        }

        // Blinds Setzen ende

    }

    public void RundeWiederholen()
    {
        // blinds verschieben, karten ausgeben, gewinner ermitteln
        // Blinds verschieben
        boolean b = true;
        int i = 0;
        while (b)
        {

            if (spielerliste.get(i).getWasfuernBlind() == Blind.DEALER)
            {
                spielerliste.get(i).setWasfuernBlind(Blind.NICHTS);
                spielerliste.get(istBeimLetzten(i + 1)).setWasfuernBlind(Blind.DEALER);
                spielerliste.get(istBeimLetzten(i + 2)).setWasfuernBlind(Blind.SMALLBLIND);
                spielerliste.get(istBeimLetzten(i + 3)).setWasfuernBlind(Blind.BIGBLIND);
                b = false;
            }
            else
            {
                i++;
            }
        }
        // Blinds verschieben ende

    }

    public String autoErgaenzen()
    { // Ergänzt fehlenden Betrag im Wettkästchen des Spielers zur aktuellen

        // Wettsumme.
        return "" + (getPot() - getAmZug().getWettsumme());

    }

    public void blaetterVergleichen()
    {
        Blatt aktBlatt;
        Blatt hoechstesBlatt;
        // Kartenwert blattWert = hoechstesBlatt.blattWert;
        for (Iterator<Spieler> iterator = spielerliste.iterator(); iterator.hasNext();)
        {

            aktBlatt = iterator.next().blattErmitteln();
            // if (aktBlatt >= hoechstesBlatt) {
            // hoechstesBlatt = aktBlatt;
            // }
            // else {

        }
    }

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

    public void betraise(String wettSummeText, Spieler client)
    {
        Integer wettSumme = new Integer(wettSummeText);
        if (wettSumme > pot)
        {
            pot = wettSumme;
        }

    }

    public void check(Spieler client)
    {
        // sendet dem Server die Aktion
    }

    public void fold(Spieler client)
    {
        client.setNochDabei(false);

    }

    public int istBeimLetzten(int i)
    {
        if (spielerliste.get(i).equals(spielerliste.get(spielerliste.size())))
        {
            i = 0;
            return 0;

        }
        else
        {
            return i;
        }

    }

    public void setAmZug(Spieler amZug)
    {
        this.amZug = amZug;
    }

    public Spieler getAmZug()
    {
        return amZug;
    }
}
