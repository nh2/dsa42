package de.schelklingen2008.risiko.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Country
{

    String        name;
    int           units;
    Player        occupier;
    List<Country> neighbours = new ArrayList<Country>();
    Color         c;

}
