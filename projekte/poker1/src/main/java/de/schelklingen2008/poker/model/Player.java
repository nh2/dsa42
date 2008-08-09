package de.schelklingen2008.poker.model;

import java.io.Serializable;

/**
 * Is a simple abstraction for a player entity.
 */
public class Player implements Serializable
{

    private String  name;
    private long    balance; // Kontostand
    // private int potNumber; // Zahl der Pots, die der Spieler noch bekommen kann
    private Card    card1, card2; // Karten des Spielers
    private long    ownBet;      // geleisteter Einsatz in der momentanen Phase
    private boolean isAllIn;
    private boolean stillIn;     // Ist der Spieler noch in der Runde
    private boolean lost;        // true, wenn der Spieler am Ende einer Runde kein Geld mehr hat, also

    // verloren hat

    public Player(String Name)
    {
        name = Name;
        balance = GameModel.START_BALANCE;
        // potNumber = 0;
        card1 = null;
        card2 = null;
        ownBet = 0;
        stillIn = true;
    }

    public boolean isAllIn()
    {
        return isAllIn;
    }

    public void setAllIn(boolean isAllIn)
    {
        this.isAllIn = isAllIn;
    }

    public Card getCard1()
    {
        return card1;
    }

    public void setCard1(Card card1)
    {
        this.card1 = card1;
    }

    public Card getCard2()
    {
        return card2;
    }

    public void setCard2(Card card2)
    {
        this.card2 = card2;
    }

    public String getName()
    {
        return name;
    }

    public long getBalance()
    {
        return balance;
    }

    public void setBalance(long newBalance)
    {
        balance = newBalance;
    }

    public long getOwnBet()
    {
        return ownBet;
    }

    public boolean isStillIn()
    {
        return stillIn;
    }

    public boolean hasLost()
    {
        return lost;
    }

    public void setOwnBet(long ownBet)
    {
        this.ownBet = ownBet;
    }

    public void setStillIn(boolean stillIn)
    {
        this.stillIn = stillIn;
    }

    public void setLost(boolean lost)
    {
        this.lost = lost;
    }
}
