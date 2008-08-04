package de.schelklingen2008.dasverruecktelabyrinth.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerCards
{

    private List<TreasureCard> hidden; // Stapel der verdeckten Bildkarten
    private List<TreasureCard> open;  // Stapel der gesammelten/aufgedeckten

    // Bildkarten

    public PlayerCards()
    {

        hidden = new ArrayList<TreasureCard>(); // Stapel der verdeckten Bildkarten
        open = new ArrayList<TreasureCard>();

    }
}
