package de.schelklingen2008.doppelkopf.model;

import junit.framework.TestCase;

public class GameModelTest extends TestCase
{

    public void testGameModel()
    {
        GameModel g = new GameModel(new String[] { "1", "2", "3", "4" });
        Karte k1, k2, k3, k4;

        k1 = new Karte(Farbe.Kreuz, Bild.As);
        k2 = new Karte(Farbe.Kreuz, Bild.Koenig);
        k3 = new Karte(Farbe.Karo, Bild.Neun);
        k4 = new Karte(Farbe.Herz, Bild.Zehn);

        Spieler s1 = g.getSpieler().getSpieler("1");
        s1.getBlatt().add(k1);
        Spieler s2 = g.getSpieler().getSpieler("2");
        s2.getBlatt().add(k2);
        Spieler s3 = g.getSpieler().getSpieler("3");
        s3.getBlatt().add(k3);
        Spieler s4 = g.getSpieler().getSpieler("4");
        s4.getBlatt().add(k4);

        g.karteAusspielen(s1, k1);
        g.karteAusspielen(s2, k2);
        g.karteAusspielen(s3, k3);
        g.karteAusspielen(s4, k4);

        assertEquals(4, s4.getGewinnstapel().size());
        assertEquals(g.getSpieler().getAnDerReihe(), g.getSpieler().getSpieler("4"));
    }
}
