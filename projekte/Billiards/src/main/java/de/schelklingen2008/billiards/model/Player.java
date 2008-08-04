package de.schelklingen2008.billiards.model;

public class Player
{

    public int getId()
    {
        return id;
    }

    private int score = 0;
    private int id;

    private Player()
    {

    }

    @Override
    public int hashCode()
    {
        return id;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Player other = (Player) obj;
        if (id != other.id) return false;
        return true;
    }

    public Player(int id)
    {
        this.id = id;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public void resetScore()
    {
        score = 0;
    }

}
