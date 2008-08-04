package de.schelklingen2008.poker.model;

/**
 * Is a simple abstraction for a player entity.
 */
public class Player
{

    private String  name;
    private long    balance; // Kontostand
    // private int potNumber; // Zahl der Pots, die der Spieler noch bekommen kann
    private Card    card1, card2; // Karten des Spielers
    private long    ownBet;      // geleisteter Einsatz in der momentanen Phase
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
    }

}
