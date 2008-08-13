package de.schelklingen2008.billiards.model;

public class TurnHolderChangeEvent
{

    private final Player oldTurnHolder, newTurnHolder;

    public TurnHolderChangeEvent(Player oldTurnHolder, Player newTurnHolder)
    {
        this.oldTurnHolder = oldTurnHolder;
        this.newTurnHolder = newTurnHolder;
    }

    public Player getOldTurnHolder()
    {
        return oldTurnHolder;
    }

    public Player getNewTurnHolder()
    {
        return newTurnHolder;
    }

}
