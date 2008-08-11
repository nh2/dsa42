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
    private Kartenstapel  kartenStapel = new Kartenstapel();
    private int           standWettsumme;
    private Spieler       letzterRaiser;

    public GameModel(String[] names)
    {
        for (int i = 0; i < names.length; i++)
        {
            spielerliste.add(new Spieler(names[i]));
        }
        spielfeld = new Spielkarte[5];
        spielstadium = Spielstadien.FLOP;
        rundenstart();

    }

    public void rundenstart()
    { // Initiiert das Spiel.
        // Geld verteilen

        for (Iterator<Spieler> iterator = spielerliste.iterator(); iterator.hasNext();)
        {
            Spieler spieler = iterator.next();
            spieler.setGeld(5000);
        }// Geld verteilen ende

        setAmZug(spielerliste.get(0));
        Kartenstapel spielKarten = new Kartenstapel();

        // Karten aufs Spielfeld legen

        kartenStapel.neuerStapel();
        for (int j = 0; j < spielfeld.length; j++)
        {
            spielfeld[j] = kartenStapel.zufallsKarte();
        }

        for (Iterator<Spieler> iterator = spielerliste.iterator(); iterator.hasNext();)
        {
            Spielkarte[] zufallsHandBlatt = new Spielkarte[2];
            zufallsHandBlatt[0] = kartenStapel.zufallsKarte();
            zufallsHandBlatt[1] = kartenStapel.zufallsKarte();
            iterator.next().setHandblatt(zufallsHandBlatt);
        }
        // Karten aufs Spielfeld legen ende

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

    public void neueRunde()
    {

        // Blinds verschieben
        boolean hatBlindsVerschoben = false;
        int i = 0;
        while (hatBlindsVerschoben)
        {
            if (spielerliste.get(i).getWasfuernBlind() == Blind.DEALER)
            {
                spielerliste.get(i).setWasfuernBlind(Blind.NICHTS);
                i++;
                spielerliste.get(kleinerAlsSpielerliste(i)).setWasfuernBlind(Blind.DEALER);
                i++;
                spielerliste.get(kleinerAlsSpielerliste(i)).setWasfuernBlind(Blind.SMALLBLIND);
                i++;
                spielerliste.get(kleinerAlsSpielerliste(i)).setWasfuernBlind(Blind.BIGBLIND);
                hatBlindsVerschoben = true;
            }
            i++;

        }
        // Blinds verschieben ende
        // Karten ausgeben
        kartenStapel.neuerStapel();
        for (int j = 0; j < spielfeld.length; j++)
        {
            spielfeld[j] = kartenStapel.zufallsKarte();
        }
        for (Iterator<Spieler> iterator = spielerliste.iterator(); iterator.hasNext();)
        {
            Spielkarte[] zufallsHandBlatt = new Spielkarte[2];
            zufallsHandBlatt[0] = kartenStapel.zufallsKarte();
            zufallsHandBlatt[1] = kartenStapel.zufallsKarte();
            iterator.next().setHandblatt(zufallsHandBlatt);
        }
        // Karten ausgeben ende

        for (int j = 0; j < spielfeld.length; j++)
        {
            spielfeld[j] = kartenStapel.zufallsKarte();
        }

        // if ( ) {
        // Gewinner ermitteln
        // }
        // else {
        //
        // }

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

            aktBlatt = iterator.next().blattErmitteln(getSpielfeld());
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

    public boolean rundeBeendet()
    {
        for (Spieler spieler : spielerliste)
        {
            if (spieler.getLetzteAktion() == LetzteAktion.CALL || spieler.getLetzteAktion() == LetzteAktion.FOLD)
            {

            }
            else
            {
                return false;
            }
        }
        return true;
    }

    public void call(String wettSummeText, Spieler client)
    {
        Integer wettSumme = new Integer(wettSummeText);
        if (wettSumme + client.getWettsumme() == getStandWettsumme())
        {
            advanceTurnHolder(client);
            client.setGeld(client.getGeld() - wettSumme);
            client.setWettsumme(getStandWettsumme());
            client.setLetzteAktion(LetzteAktion.CALL);
        }
        rundeBeendet();
    }

    public void raise(String wettSummeText, Spieler spieler)
    {
        Integer wettSumme = new Integer(wettSummeText);
        int neueSumme = wettSumme + spieler.getWettsumme();
        if (neueSumme <= getStandWettsumme()) return;
        if (getLetzterRaiser() != null && getLetzterRaiser().equals(spieler)) return;

        setStandWettsumme(neueSumme);
        setPot(getPot() + wettSumme);
        setLetzterRaiser(spieler);
        spieler.setGeld(spieler.getGeld() - wettSumme);
        spieler.setWettsumme(getStandWettsumme());
        spieler.setLetzteAktion(LetzteAktion.RAISE);

        advanceTurnHolder(spieler);
    }

    private void advanceTurnHolder(Spieler s)
    {
        int nextIndex = (getSpielerNummer(s) + 1) % 2;
        setAmZug(spielerliste.get(nextIndex));
    }

    public void check(Spieler client)
    {
        if (client.getWettsumme() == getStandWettsumme())
        {
            advanceTurnHolder(client);
            client.setLetzteAktion(LetzteAktion.CHECKED);
        }
    }

    public void fold(Spieler client)
    {
        advanceTurnHolder(client);

        client.setLetzteAktion(LetzteAktion.FOLD);

    }

    public void setAmZug(Spieler amZug)
    {
        this.amZug = amZug;
    }

    public Spieler getAmZug()
    {
        return amZug;

    }

    public int kleinerAlsSpielerliste(int i)
    {
        if (i == spielerliste.size() - 1)
        {

            return 0;
        }
        else
        {
            return i;
        }
    }

    public Spieler getSpieler(String name)
    {
        if (name == null) return null;
        for (Spieler spieler : spielerliste)
        {
            if (name.equals(spieler.getName())) return spieler;
        }
        return null;
    }

    public int getSpielerNummer(Spieler spieler)
    {
        return spielerliste.indexOf(spieler);
    }

    public void setStandWettsumme(int standWettsumme)
    {
        this.standWettsumme = standWettsumme;
    }

    public int getStandWettsumme()
    {
        return standWettsumme;
    }

    public void setLetzterRaiser(Spieler letzterWetter)
    {
        letzterRaiser = letzterWetter;
    }

    public Spieler getLetzterRaiser()
    {
        return letzterRaiser;
    }
}
