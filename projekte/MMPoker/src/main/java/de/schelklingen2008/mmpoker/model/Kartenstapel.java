package de.schelklingen2008.mmpoker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kartenstapel implements Serializable
{

    List<Spielkarte> kartenstapel = new ArrayList<Spielkarte>();
    private Random   random;

    public Spielkarte zufallsKarte()
    {
        random = new Random();

        return kartenstapel.get(random.nextInt(52));

    }

    public Kartenstapel()
    {
        ArrayList<Spielkarte> kartenstapel = new ArrayList<Spielkarte>();
        int i = 1;
        for (Kartenwert kartenwert : Kartenwert.values())
        {
            for (Kartentyp kartentyp : Kartentyp.values())
            {
                kartenstapel.add(i, new Spielkarte(kartentyp, kartenwert));
                i++;
            }
        }

    }
}
