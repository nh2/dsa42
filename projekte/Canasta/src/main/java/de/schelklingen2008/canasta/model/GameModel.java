package de.schelklingen2008.canasta.model;

import java.io.Serializable;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel implements Serializable
{

    private int      turnHolder;
    private Player[] players;
    private Talon    talon;
    private Discard  discard;

    /**
     * TODO export constant to Constants.java
     */
    public final int initialCardNumber = 15;

    public GameModel(String[] playerNames)
    {
        // only 2 or 4 players allowed for now
        if (playerNames.length != 2 && playerNames.length != 4)
        {
            throw new IllegalArgumentException("Only 2 or 4 players allowed for now!");
        }

        /**
         * TODO Feature: any player order allowed
         */
        // create the player objects
        players = new Player[playerNames.length];
        int i = 0;
        for (String playerName : playerNames)
        {
            players[i] = new Player(playerName);
            i++;
        }

        // create a full 110 card talon
        talon = Talon.getInstance();
        talon.shuffle();

        // create discard pile
        discard = new Discard();

        // draw 15 cards for every player
        for (Player player : players)
        {
            for (int j = 0; j < initialCardNumber; j++)
            {
                player.getHand().add(talon.pop());
            }
        }

        // discard one card
        discard.push(talon.pop());

        // create outlays

        for (Player player : players)
        {
            Outlay outlay = player.getOutlay();
            for (int j = 0; j < 10; j++)
            {
                CardStack cardStack = new CardStack();
                for (int k = 0; k < 10; k++)
                {
                    cardStack.add(new Card(Rank.QUEEN, Suit.HEARTS));
                }
                outlay.add(cardStack);
            }
        }
    }

    public void drawCard(Player player)
    {
        Card card = talon.pop();

        player.getHand().add(card);
    }

    public void meldCard()
    {
        // TODO player makes outlay

        goOut();
    }

    public void discardCard(Player player)
    {
        // TODO player discards card
        // if (players[getPlayerIndex(player.getName())].hasCanasta() &&
        // players[getPlayerIndex(player.getName())].getHand().size() == 0)
        // {
        // goOut();
        // }
        endTurn();
    }

    public void goOut()
    {
        // TODO round is finished, deal new cards, evaluate score, etc
    }

    public void endTurn()
    {
        turnHolder++;
        turnHolder %= players.length;
    }

    public int getTurnHolder()
    {
        return turnHolder;
    }

    public Player[] getPlayers()
    {
        return players;
    }

    public Talon getTalon()
    {
        return talon;
    }

    public Discard getDiscard()
    {
        return discard;
    }

    public boolean isFinished()
    {
        return false;
    }

    public Player getPlayer(String string)
    {
        for (int i = 0; i < players.length; i++)
        {
            if (players[i].getName().equals(string))
            {
                return players[i];
            }
        }
        throw new RuntimeException(new IllegalArgumentException("Playername " + string + " not found in game model"));
    }

    public int getPlayerIndex(String string)
    {
        for (int i = 0; i < players.length; i++)
        {
            if (players[i].getName().equals(string))
            {
                return i;
            }
        }
        throw new RuntimeException(new IllegalArgumentException("Playername " + string + " not found in game model"));
    }
}