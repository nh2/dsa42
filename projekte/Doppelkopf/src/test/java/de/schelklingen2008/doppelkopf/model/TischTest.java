package de.schelklingen2008.doppelkopf.model;

import junit.framework.TestCase;

public class TischTest extends TestCase
{

    public void testGibKarten()
    {
        Tisch tisch = new Tisch(new Spieler("Test"));
        tisch.gibKarten();

    }

}
