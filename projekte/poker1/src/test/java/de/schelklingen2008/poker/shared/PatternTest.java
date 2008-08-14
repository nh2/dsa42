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
        cards.add(new Card(3, 8));
        cards.add(new Card(2, 5));
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

        cards.add(new Card(3, 9));
        cards.add(new Card(1, 1));
        cards.add(new Card(3, 9));
        cards.add(new Card(3, 7));
        cards.add(new Card(3, 3));
        cards.add(new Card(1, 7));
        cards.add(new Card(3, 9));

        return cards;

    }

    public List<Card> straightList(List<Card> cards)
    {

        cards.add(new Card(0, 0));
        cards.add(new Card(1, 0));
        cards.add(new Card(2, 3));
        cards.add(new Card(3, 4));
        cards.add(new Card(2, 5));
        cards.add(new Card(0, 6));
        cards.add(new Card(1, 7));

        return cards;

    }

    public List<Card> fill5(List<Card> cards)
    {

        cards.add(new Card(3, 9));
        cards.add(new Card(1, 2));
        cards.add(new Card(3, 9));
        cards.add(new Card(3, 3));
        cards.add(new Card(3, 4));
        cards.add(new Card(1, 2));
        cards.add(new Card(3, 9));

        return cards;

    }

    public List<Card> fill6(List<Card> cards)
    {

        cards.add(new Card(3, 1));
        cards.add(new Card(1, 2));
        cards.add(new Card(3, 3));
        cards.add(new Card(3, 4));
        cards.add(new Card(3, 4));
        cards.add(new Card(1, 5));
        cards.add(new Card(3, 6));

        return cards;

    }

    // public void output(List<Card> list, PatternChecker checker)
    // {
    // list = PatternChecker.sort(list);
    // System.out.println();
    // for (Iterator iterator = list.iterator(); iterator.hasNext();)
    // {
    // Card card = (Card) iterator.next();
    // System.out.println(card.getValue() + " " + card.getSuit());
    //
    // }
    // // PatternChecker checker = new PatternChecker(list);
    // System.out.println(checker.numberOfPairs());
    // System.out.println(checker.counter);
    // assertEquals(1, checker.numberOfPairs());
    //
    // }

    public void ueberpruefung(PatternChecker checker)
    {

        System.out.println("Paar: " + checker.isPair());
        System.out.println("Drilling: " + checker.isThreeOfAKind());
        System.out.println("Straﬂe: " + checker.isStraight());
        System.out.println("Flush: " + checker.isFlush());
        System.out.println("Full House: " + checker.isFullHouse());
        System.out.println("Vierling: " + checker.isFourOfAKind());
        System.out.println("Straight Flush: " + checker.isStraightFlush());
        System.out.println("Royal Flush: " + checker.isRoyalFlush());
        // System.out.println(checker.getHighestPatternValue());
    }

    public void test2(List<Card> cardList, PatternChecker checker)
    {

        cardList.clear();
        fill5(cardList);
        cardList = PatternChecker.sort(cardList);
        ueberpruefung(checker);
    }

    public void test3(List<Card> cardList, PatternChecker checker)
    {

        cardList.clear();
        fill6(cardList);
        cardList = PatternChecker.sort(cardList);
        for (Iterator iterator = cardList.iterator(); iterator.hasNext();)
        {
            Card card = (Card) iterator.next();
            System.out.println(card.getName());
        }
        ueberpruefung(checker);
    }

    public void testHaupt()
    {
        List<Card> cardList = new ArrayList<Card>();
        PatternChecker checker = new PatternChecker(cardList);
        // cardList = fill(cardList);
        // checker.mehrlinge();
        // cardList.clear();
        // cardList = fill3(cardList);
        // checker.mehrlinge();
        // System.out.println(checker.isPair());
        // cardList = fill3(cardList);
        // assertTrue(checker.isFlush());
        test2(cardList, checker);
        test3(cardList, checker);

        List<Card> cards = new ArrayList<Card>();
        cards.addAll(cardList);
        System.out.println(cards.size());

    }
}
