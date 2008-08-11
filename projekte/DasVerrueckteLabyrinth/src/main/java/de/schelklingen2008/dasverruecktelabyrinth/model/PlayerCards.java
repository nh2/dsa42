package de.schelklingen2008.dasverruecktelabyrinth.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerCards implements Serializable
{

    private List<TreasureCard> hidden; // Stapel der verdeckten Bildkarten
    private List<TreasureCard> open;  // Stapel der gesammelten/aufgedeckten

    // Bildkarten

    public PlayerCards()
    {

        hidden = new ArrayList<TreasureCard>(); // Stapel der verdeckten Bildkarten
        open = new ArrayList<TreasureCard>();

        hidden = null;
        open = null;

    }

    public List<TreasureCard> getHiddenCards()
    {
        return hidden;
    }

    public List<TreasureCard> getOpenCards()
    {
        return open;
    }

}
