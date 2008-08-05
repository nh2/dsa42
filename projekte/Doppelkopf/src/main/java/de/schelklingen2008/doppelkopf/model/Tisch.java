package de.schelklingen2008.doppelkopf.model;

import java.util.Collections;
import java.util.Stack;

public class Tisch
{

    Stack<Karte> mitte;
    Spieler      anDerReihe;
    int          stichAnzahl;

    public Tisch(Spieler fängtAn)
    {
    	anDerReihe = fängtAn;
    }
    
    public void gibKarten()
    {
        Stack<Karte> stapel = erzeugeStapel();		// Ausgabekartenstapel erzeugen
        mischeStapel(stapel);						// Stapel mischen
        for (Karte k : stapel)
            System.out.println(k.toString());
        // Karten verteilen
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
}
