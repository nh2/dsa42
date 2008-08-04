package de.schelklingen2008.poker.model;

import java.util.ArrayList;
import java.util.List;

public class Pot
{

    private List<Integer> possibleWinners = new ArrayList<Integer>();

    public void addPlayer(int playerIndex)

    {
        possibleWinners.add(playerIndex);
    }

}
