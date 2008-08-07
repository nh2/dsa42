package de.schelklingen2008.mmpoker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kartenstapel extends GameModel implements Serializable
{

    List<Spielkarte> kartenstapel = new ArrayList<Spielkarte>();
    private Random   random;

    public Spielkarte zufallsKarte()
    {
        random = new Random();

        return kartenstapel.get(random.nextInt(55));

    }

    public void kartenSetzen()
    {
        for (Kartenwert kartenwert : Kartenwert.values())
        {
            for (Kartentyp kartentyp : Kartentyp.values())
            {
                new Spielkarte(kartentyp, kartenwert);
            }
        }

    }
}
