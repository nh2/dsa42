package de.schelklingen2008.mmpoker.model;

import junit.framework.TestCase;

public class GameModelTest extends TestCase
{

    public void testRaise() throws Exception
    {
        GameModel model = new GameModel(new String[] { "Peter", "Paul" });

        Spieler spieler1 = model.getSpielerliste().get(0);
        int geld1 = spieler1.getGeld();
        Spieler spieler2 = model.getSpielerliste().get(1);
        int geld2 = spieler2.getGeld();

        assertEquals(spieler1, model.getAmZug());

        model.raise("1", spieler1);
        assertEquals(1, model.getPot());
        assertEquals(spieler2, model.getAmZug());
        assertEquals(geld1 - 1, spieler1.getGeld());

        model.raise("50", spieler2);
        assertEquals(spieler2.getLetzteAktion(), LetzteAktion.RAISE);
        assertEquals(spieler1, model.getAmZug());
        assertEquals(spieler1.getWasfuernBlind(), Blind.SMALLBLIND);
        assertEquals(spieler2.getWasfuernBlind(), Blind.BIGBLIND);
        System.out.println(model.getStandWettsumme());

        model.call("49", spieler1);
        assertEquals(spieler1.getLetzteAktion(), LetzteAktion.CALL);

    }
}
