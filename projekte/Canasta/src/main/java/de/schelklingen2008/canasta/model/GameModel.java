package de.schelklingen2008.canasta.model;

/**
 * Maintains the rules and the state of the game.
 */
public class GameModel
{

    private Player   turnHolder;
    private Player[] players;
    private Talon    talon;
    private Discard  discard;

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
        if (turnHolder.hasCanasta() && turnHolder.getHand().size() == 0)
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

    public boolean isFinished()
    {
        return false;
    }

}