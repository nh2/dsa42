package de.schelklingen2008.risiko.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Country
{

    private String        name;
    private int           units;
    private Player        occupier;
    private List<Country> neighbours = new ArrayList<Country>();
    private Color         c;

}
