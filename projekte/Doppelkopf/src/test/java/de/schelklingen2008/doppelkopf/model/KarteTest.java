package de.schelklingen2008.doppelkopf.model;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class KarteTest extends TestCase
{

    public void testCompareTo()
    {
        List<Karte> k1 = new ArrayList<Karte>();
        List<Karte> k2 = new ArrayList<Karte>();

        k1.add(new Karte(Farbe.Herz, Bild.Zehn));
        k2.add(new Karte(Farbe.Pik, Bild.Koenig));

        k1.add(new Karte(Farbe.Pik, Bild.As));
        k2.add(new Karte(Farbe.Pik, Bild.Koenig));

        for (int i = 0; i < k1.size(); i++)
        {
            assertEquals(true, k1.get(i).compareTo(k2.get(i)) > 0);
            // assertEquals(-1, k2.get(i).compareTo(k1.get(i)));
        }
    }

    public void testSticht()
    {
        List<Karte> k1 = new ArrayList<Karte>();
        List<Karte> k2 = new ArrayList<Karte>();

        k1.add(new Karte(Farbe.Herz, Bild.Zehn));
        k2.add(new Karte(Farbe.Pik, Bild.Koenig));

        k1.add(new Karte(Farbe.Pik, Bild.As));
        k2.add(new Karte(Farbe.Pik, Bild.Koenig));

        for (int i = 0; i < k1.size(); i++)
        {
            assertTrue(k1.get(i).sticht(k2.get(i)));
        }
    }

}
