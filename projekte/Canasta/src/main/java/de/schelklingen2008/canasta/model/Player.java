package de.schelklingen2008.canasta.model;

import java.io.Serializable;

import de.schelklingen2008.canasta.client.Constants;

/**
 * Is a simple abstraction for a player entity. To be merged with PlayerState
 */
public class Player implements Serializable
{

    private final String name;
    private int          totalScore;
    private Hand         hand;
    private Outlay       outlay;

    public Player(String name)
    {
        super();

        this.name = name;

        hand = new Hand();
        outlay = new Outlay();
    }

    public String getName()
    {
        return name;
    }

    public int getTotalScore()
    {
        return totalScore;
    }

    public void setTotalScore(int totalScore)
    {
        this.totalScore = totalScore;
    }

    public int getCurrentScore()
    {
        int result = 0;

        for (CardStack cardStack : getOutlay())
        {
            // Canastas
            if (cardStack.isCanasta())
            {
                if (cardStack.getRank() == Rank.JOKER)
                {
                    result += Constants.SCORE_CANASTA_JOKER;
                }
                else if (cardStack.getJokerCount() > 0)
                {
                    result += Constants.SCORE_CANASTA_MIXED;
                }
                else
                {
                    result += Constants.SCORE_CANASTA_NATURAL;
                }
            }

            // Cards
            for (Card card : cardStack)
            {
                result += GameModel.getCardValue(card);
            }
        }

        // Hand
        for (Card card : getHand())
        {
            result -= GameModel.getCardValue(card);
        }

        return result;
    }

    public Hand getHand()
    {
        return hand;
    }

    public Outlay getOutlay()
    {
        return outlay;
    }

    public boolean hasCanasta()
    {
        for (CardStack stack : getOutlay())
        {
            if (stack.isCanasta())
            {
                return true;
            }
        }
        return false;
    }
}
