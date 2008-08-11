package de.schelklingen2008.doppelkopf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Tisch implements Serializable
{

    private List<Karte>   mitte;
    private List<Spieler> mittenSpieler;
    private Spieler       anDerReihe;
    private int           stichAnzahl;
    private SpielerListe  spielerliste;
    private Set<Spieler>  re, contra;

    public Tisch(SpielerListe spielerliste)
    {
        mitte = new ArrayList<Karte>(4);
        mittenSpieler = new ArrayList<Spieler>(4);

        re = new HashSet<Spieler>();
        contra = new HashSet<Spieler>();

        this.spielerliste = spielerliste;
        // for (int i = 1; i <= 4; i++)
        // spielerliste.add(new Spieler("Spieler " + i));
        anDerReihe = spielerliste.getAnDerReihe();
    }

    public void gibKarten()
    {
        Stack<Karte> stapel = erzeugeStapel(); // Ausgabekartenstapel erzeugen
        mischeStapel(stapel); // Stapel mischen
        for (Karte k : stapel)
            System.out.println(k.toString());

        // Karten verteilen

        while (!stapel.isEmpty())
        {
            anDerReihe.getBlatt().add(stapel.pop());
            anDerReihe = spielerliste.next();
        }

        teileTeamsZu();
    }

    private void teileTeamsZu()
    {
        for (Spieler p : spielerliste)
            if (p.getBlatt().getKarte(Farbe.Kreuz, Bild.Dame) != null)
                re.add(p);
            else
                contra.add(p);

    }

    private Stack<Karte> erzeugeStapel()
    {
        Stack<Karte> stapel = new Stack<Karte>();
        for (Farbe f : Farbe.values())
            for (Bild b : Bild.values())
                for (int i = 0; i < 2; i++)
                    stapel.push(new Karte(f, b));

        return stapel;
    }

    private void mischeStapel(Stack<Karte> stapel)
    {
        Collections.shuffle(stapel);
    }

    public List<Karte> getMitte()
    {
        return mitte;
    }

    public List<Spieler> getMittenspieler()
    {
        return mittenSpieler;
    }

    public int getStichAnzahl()
    {
        return stichAnzahl;
    }

    public void stichGespielt()
    {
        mitte.clear();
        mittenSpieler.clear();
        stichAnzahl++;
    }

    public int getRePunkte()
    {
        int punkte = 0;
        for (Spieler p : re)
            for (Karte k : p.getGewinnstapel())
                punkte += k.bild.getWertigkeit();
        return punkte;
    }

    public int getContraPunkte()
    {
        int punkte = 0;
        for (Spieler p : contra)
            for (Karte k : p.getGewinnstapel())
                punkte += k.bild.getWertigkeit();
        return punkte;
    }

    public Set<Spieler> getTeamRe()
    {
        return re;
    }

    public Set<Spieler> getTeamContra()
    {
        return contra;
    }
}
