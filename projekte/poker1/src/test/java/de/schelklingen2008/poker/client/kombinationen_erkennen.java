package de.schelklingen2008.poker.client;

import java.util.Iterator;

import de.schelklingen2008.poker.model.Card;
import de.schelklingen2008.poker.model.GameModel;

public class kombinationen_erkennen
{

    public static void kartenAusgeben(GameModel model)
    {
        for (Iterator iterator = model.getCardList().iterator(); iterator.hasNext();)
        {
            Card card = (Card) iterator.next();
            System.out.println(card.getSuit());
            System.out.println(card.getValue());

        }
    }

    public static void main(String[] args)
    {
        GameModel model = new GameModel();
        kartenAusgeben(model);
    }
}
