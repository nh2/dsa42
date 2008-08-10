package de.schelklingen2008.poker.shared;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import de.schelklingen2008.poker.model.Card;
import de.schelklingen2008.poker.model.PatternChecker;

public class PatternTest extends TestCase
{

    public void test()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card(0, 9));
        cards.add(new Card(1, 1));
        cards.add(new Card(2, 9));
        cards.add(new Card(3, 7));
        cards.add(new Card(2, 3));
        cards.add(new Card(0, 7));
        cards.add(new Card(1, 10));

        PatternChecker checker = new PatternChecker(cards);
        assertTrue(checker.isPair());
    }
}
