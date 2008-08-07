package de.schelklingen2008.poker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pot implements Serializable
{

    private List<Integer> possibleWinners = new ArrayList<Integer>();
    private long          potContent;

    public Pot(List<Integer> possibleWinnersIndexList)
    {
        for (Iterator iterator = possibleWinnersIndexList.iterator(); iterator.hasNext();)
        {
            Integer index = (Integer) iterator.next();
            possibleWinners.add(index);
        }
    }

    public long getPotContent()
    {
        return potContent;
    }

    public void setPotContent(long potContent)
    {
        this.potContent = potContent;
    }

    public List<Integer> getPossibleWinners()
    {
        return possibleWinners;
    }

}
