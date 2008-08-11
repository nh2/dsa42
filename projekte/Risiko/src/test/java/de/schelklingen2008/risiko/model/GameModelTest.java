package de.schelklingen2008.risiko.model;

import junit.framework.TestCase;

public class GameModelTest extends TestCase
{

    public void testInit()
    {
        GameModel model = new GameModel(new String[] { "leo", "lena" });
        model.placeUnit(model.getCountry(0));
        model.placeUnit(model.getCountry(0));
        model.placeUnit(model.getCountry(0));
        model.placeUnit(model.getCountry(0));
        model.placeUnit(model.getCountry(1));
        model.placeUnit(model.getCountry(1));
        model.placeUnit(model.getCountry(0));
        model.placeUnit(model.getCountry(1));
        model.placeUnit(model.getCountry(0));
        model.placeUnit(model.getCountry(1));
        model.placeUnit(model.getCountry(0));
        model.placeUnit(model.getCountry(1));
        model.placeUnit(model.getCountry(0));
        model.placeUnit(model.getCountry(1));

        System.out.println("Island: "
                           + model.getCountry(0).getOccupier().getPlayerName()
                           + " "
                           + model.getCountry(0).getUnits());
        System.out.println("Irland: "
                           + model.getCountry(1).getOccupier().getPlayerName()
                           + " "
                           + model.getCountry(1).getUnits());
        model.attack(model.getCountry(0), model.getCountry(1));
        System.out.println("Island: "
                           + model.getCountry(0).getOccupier().getPlayerName()
                           + " "
                           + model.getCountry(0).getUnits());
        System.out.println("Irland: "
                           + model.getCountry(1).getOccupier().getPlayerName()
                           + " "
                           + model.getCountry(1).getUnits());

        model.attack(model.getCountry(0), model.getCountry(1));
        System.out.println("Island: "
                           + model.getCountry(0).getOccupier().getPlayerName()
                           + " "
                           + model.getCountry(0).getUnits());
        System.out.println("Irland: "
                           + model.getCountry(1).getOccupier().getPlayerName()
                           + " "
                           + model.getCountry(1).getUnits());

        model.attack(model.getCountry(0), model.getCountry(1));
        System.out.println("Island: "
                           + model.getCountry(0).getOccupier().getPlayerName()
                           + " "
                           + model.getCountry(0).getUnits());
        System.out.println("Irland: "
                           + model.getCountry(1).getOccupier().getPlayerName()
                           + " "
                           + model.getCountry(1).getUnits());
    }
}
