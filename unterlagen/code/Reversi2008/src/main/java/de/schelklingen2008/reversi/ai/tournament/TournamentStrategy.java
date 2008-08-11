package de.schelklingen2008.reversi.ai.tournament;

import de.schelklingen2008.reversi.ai.strategy.ReversiStrategy;

public class TournamentStrategy
{

    private ReversiStrategy strategy;
    private String          creator;
    private int             points;

    public TournamentStrategy(String creator, ReversiStrategy strategy)
    {
        this.creator = creator;
        this.strategy = strategy;
    }

    public ReversiStrategy getStrategy()
    {
        return strategy;
    }

    public String getCreator()
    {
        return creator;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (creator == null ? 0 : creator.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        TournamentStrategy other = (TournamentStrategy) obj;
        if (creator == null)
        {
            if (other.creator != null) return false;
        }
        else if (!creator.equals(other.creator)) return false;
        return true;
    }

    public void addPoints(int pointsToAdd)
    {
        points += pointsToAdd;
    }

    public int getPoints()
    {
        return points;
    }

    @Override
    public String toString()
    {
        return getCreator() + " (" + getStrategy().getClass().getName() + "): " + getPoints();
    }
}
