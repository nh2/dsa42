package de.schelklingen2008.mmpoker.model;

import junit.framework.TestCase;

public class GameModelTest extends TestCase
{

    public void testRaise() throws Exception
    {
        GameModel model = new GameModel(new String[] { "Maja", "Moritz" });

        Spieler spieler1 = model.getSpielerliste().get(0);
        int geld1 = spieler1.getGeld();
        Spieler spieler2 = model.getSpielerliste().get(1);
        int geld2 = spieler2.getGeld();

        assertEquals(spieler1, model.getAmZug());

        // model.raise("1", spieler1);
        // assertEquals(1, model.getPot());
        // assertEquals(spieler2, model.getAmZug());
        // assertEquals(geld1 - 1, spieler1.getGeld());
        //
        // model.raise("50", spieler2);
        // assertEquals(spieler2.getLetzteAktion(), LetzteAktion.RAISE);
        // assertEquals(spieler1, model.getAmZug());
        // assertEquals(spieler1.getWasfuernBlind(), Blind.SMALLBLIND);
        // assertEquals(spieler2.getWasfuernBlind(), Blind.BIGBLIND);
        //
        // model.call("49", spieler1);
        // assertEquals(spieler1.getLetzteAktion(), LetzteAktion.CALL);
        //
        // model.check(spieler2);
        // assertEquals(spieler2.getLetzteAktion(), LetzteAktion.CHECKED);
        //
        // model.check(spieler1);
        // assertEquals(true, model.rundeBeendet());

        System.out.println(spieler1.getName() + "    Spieler1");
        System.out.println(spieler2.getName() + "     Spieler2");
        System.out.println(model.getPot() + "       Der Pot");
        System.out.println(model.getAmZug().getName() + "    Der Spieler, der am Zug ist");
        System.out.println(spieler1.getWettsumme() + "    die wettsumme des spielers1");
        System.out.println(spieler2.getWettsumme() + "    die wettsumme des spielers2");
        System.out.println(spieler1.getLetzteAktion().toString() + "   Die letze Aktion von " + spieler1.getName());
        System.out.println(spieler2.getLetzteAktion().toString() + "   Die letze Aktion von " + spieler2.getName());
        System.out.println("");
        System.out.println("");
        model.raise("40", spieler1);
        System.out.println(spieler1.getName() + "    Spieler1");
        System.out.println(spieler2.getName() + "     Spieler2");
        System.out.println(model.getPot() + "       Der Pot");
        System.out.println(model.getAmZug().getName() + "    Der Spieler, der am Zug ist");
        System.out.println(spieler1.getWettsumme() + "    die wettsumme des spielers1");
        System.out.println(spieler2.getWettsumme() + "    die wettsumme des spielers2");
        System.out.println(spieler1.getLetzteAktion().toString() + "   Die letze Aktion von " + spieler1.getName());
        System.out.println(spieler2.getLetzteAktion().toString() + "   Die letze Aktion von " + spieler2.getName());
        System.out.println("");
        model.raise("60", spieler2);
        System.out.println(spieler1.getName() + "    Spieler1");
        System.out.println(spieler2.getName() + "     Spieler2");
        System.out.println(model.getPot() + "       Der Pot");
        System.out.println(model.getAmZug().getName() + "    Der Spieler, der am Zug ist");
        System.out.println(spieler1.getWettsumme() + "    die wettsumme des spielers1");
        System.out.println(spieler2.getWettsumme() + "    die wettsumme des spielers2");
        System.out.println(spieler1.getLetzteAktion().toString() + "   Die letze Aktion von " + spieler1.getName());
        System.out.println(spieler2.getLetzteAktion().toString() + "   Die letze Aktion von " + spieler2.getName());
        System.out.println("");
        model.call("20", spieler1);
        System.out.println(spieler1.getName() + "    Spieler1");
        System.out.println(spieler2.getName() + "     Spieler2");
        System.out.println(model.getPot() + "       Der Pot");
        System.out.println(model.getSpielstadium() + "       Stadium");
        System.out.println(model.getAmZug().getName() + "    Der Spieler, der am Zug ist");
        System.out.println(spieler1.getWettsumme() + "    die wettsumme des spielers1");
        System.out.println(spieler2.getWettsumme() + "    die wettsumme des spielers2");
        System.out.println(spieler1.getLetzteAktion().toString() + "   Die letze Aktion von " + spieler1.getName());
        System.out.println(spieler2.getLetzteAktion().toString() + "   Die letze Aktion von " + spieler2.getName());

    }
}
