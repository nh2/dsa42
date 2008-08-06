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

    public GameModel(String[] playerNames)
    {
        players = new Player[playerNames.length];

        int i = 0;
        for (String playerName : playerNames)
        {
            players[i] = new Player(playerName);
            i++;
        }

        talon = Talon.getInstance();
        discard = new Discard();
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