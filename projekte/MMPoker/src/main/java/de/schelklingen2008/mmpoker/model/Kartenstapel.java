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
        int zufall = random.nextInt(49);
        Spielkarte zufallk = kartenstapel.get(zufall);
        kartenstapel.remove(zufall);
        return zufallk;

    }

    public Kartenstapel()
    {

        int i = 0;
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
