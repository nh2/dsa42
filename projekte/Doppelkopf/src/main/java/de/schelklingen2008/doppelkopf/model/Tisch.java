package de.schelklingen2008.doppelkopf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Tisch implements Serializable
{

    private List<Karte>  mitte;
    private Spieler      anDerReihe;
    private int          stichAnzahl;
    private SpielerListe spieler;

    public Tisch()
    {
        mitte = new ArrayList<Karte>(4);

        spieler = new SpielerListe();
        for (int i = 1; i <= 4; i++)
            spieler.add(new Spieler("Spieler " + i));
        anDerReihe = spieler.getAnDerReihe();
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
            anDerReihe = spieler.next();
        }
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

    public int getStichAnzahl()
    {
        return stichAnzahl;
    }

    public SpielerListe getSpieler()
    {
        return spieler;
    }
}
