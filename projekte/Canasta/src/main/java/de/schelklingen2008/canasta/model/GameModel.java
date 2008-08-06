package de.schelklingen2008.canasta.model;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    private int      turnHolder;
    private Player[] players;
    private Talon    talon;
    private Discard  discard;

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
        discard.add(talon.pop());
    }

    public void drawCard()
    {
        // TODO player draws from the talon or takes the discard pile

    }

    public void meldCard()
    {
        // TODO player makes outlay

        goOut();
    }

    public void discardCard()
    {
        // TODO player discards card
        if (players[turnHolder].hasCanasta() && players[turnHolder].getHand().size() == 0)
        {
            goOut();
        }
        endTurn();
    }

    public void goOut()
    {
        // TODO round is finished, deal new cards, evaluate score, etc
    }

    public void endTurn()
    {
        // TODO next players turn
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

}