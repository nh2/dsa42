package de.schelklingen2008.doppelkopf.model;

import junit.framework.TestCase;

public class GameModelTest extends TestCase
{

    public void testGameModel()
    {
        GameModel g = new GameModel(new String[] { "niklas", "dick", "doof", "sarah" });
        Karte k1, k2, k3, k4;

        k1 = new Karte(Farbe.Herz, Bild.As);
        k2 = new Karte(Farbe.Pik, Bild.Neun);
        k3 = new Karte(Farbe.Herz, Bild.Koenig);
        k4 = new Karte(Farbe.Kreuz, Bild.Koenig);

        Spieler s1 = g.getSpieler().getSpieler("niklas");
        s1.getBlatt().add(k1);
        Spieler s2 = g.getSpieler().getSpieler("dick");
        s2.getBlatt().add(k2);
        Spieler s3 = g.getSpieler().getSpieler("doof");
        s3.getBlatt().add(k3);
        Spieler s4 = g.getSpieler().getSpieler("sarah");
        s4.getBlatt().add(k4);

        g.karteAusspielen(s1, k1);
        g.karteAusspielen(s2, k2);
        g.karteAusspielen(s3, k3);
        g.karteAusspielen(s4, k4);

        assertEquals(4, s1.getGewinnstapel().size());
        assertEquals(g.getSpieler().getAnDerReihe(), g.getSpieler().getSpieler("niklas"));

        // k1 = new Karte(Farbe.Kreuz, Bild.As);
        // k2 = new Karte(Farbe.Kreuz, Bild.Dame);
        // k3 = new Karte(Farbe.Karo, Bild.Neun);
        // k4 = new Karte(Farbe.Herz, Bild.Dame);
        //
        // s1.getBlatt().add(k1);
        // s2.getBlatt().add(k2);
        // s3.getBlatt().add(k3);
        // s4.getBlatt().add(k4);
        //
        // g.karteAusspielen(s4, k1);
        // g.karteAusspielen(s1, k2);
        // g.karteAusspielen(s2, k3);
        // g.karteAusspielen(s3, k4);
        //
        // assertEquals(4, s2.getGewinnstapel().size());
        // assertEquals(g.getSpieler().getAnDerReihe(), g.getSpieler().getSpieler("2"));
    }
}
