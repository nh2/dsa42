package de.schelklingen2008.poker.shared;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import de.schelklingen2008.poker.model.Card;
import de.schelklingen2008.poker.model.PatternChecker;

public class PatternTest extends TestCase
{

    public List<Card> fill(List<Card> cards)
    {

        cards.add(new Card(0, 9));
        cards.add(new Card(1, 1));
        cards.add(new Card(2, 9));
        cards.add(new Card(3, 7));
        cards.add(new Card(2, 3));
        cards.add(new Card(0, 7));
        cards.add(new Card(1, 10));

        return cards;

    }

    public List<Card> fill2(List<Card> cards)
    {

        cards.add(new Card(1, 9));
        cards.add(new Card(3, 9));
        cards.add(new Card(0, 9));
        cards.add(new Card(2, 9));
        cards.add(new Card(2, 3));
        cards.add(new Card(0, 7));
        cards.add(new Card(1, 10));

        return cards;

    }

    public List<Card> fill3(List<Card> cards)
    {

        cards.add(new Card(0, 9));
        cards.add(new Card(1, 1));
        cards.add(new Card(2, 9));
        cards.add(new Card(3, 7));
        cards.add(new Card(2, 3));
        cards.add(new Card(0, 7));
        cards.add(new Card(1, 10));

        return cards;

    }

    public List<Card> fill4(List<Card> cards)
    {

        cards.add(new Card(0, 9));
        cards.add(new Card(1, 1));
        cards.add(new Card(2, 9));
        cards.add(new Card(3, 7));
        cards.add(new Card(2, 3));
        cards.add(new Card(0, 7));
        cards.add(new Card(1, 10));

        return cards;

    }

    public void output(List<Card> list)
    {
        PatternChecker.sort(list);
        System.out.println();
        for (Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            Card card = (Card) iterator.next();
            System.out.println(card.getValue() + " " + card.getSuit());

        }
        PatternChecker checker = new PatternChecker(list);
        assertTrue(checker.isPair());

    }

    public void testHaupt()
    {
        List<Card> cardList = new ArrayList<Card>();
        cardList = fill(cardList);

    }
}
