package de.schelklingen2008.doppelkopf.model;

import junit.framework.TestCase;

public class GameModelTest extends TestCase
{

    public void testGameModel()
    {
        GameModel g = new GameModel();

        System.out.println(g.getSpieler().getAnDerReihe().getBlatt().toString());
    }

}
