package de.schelklingen2008.billiards.model;

public class Player
{

    private int score = 0;

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
