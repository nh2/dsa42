package de.schelklingen2008.jipbo.client.view;

import java.awt.Color;

import javax.swing.JPanel;

import de.schelklingen2008.jipbo.model.Card;

public class BoardPanel extends JPanel
{

    private Card[]      mCards;
    private boolean     mIsDrawPile;
    private CardPanel[] mCardPanel = new CardPanel[5];

    public BoardPanel(Card[] pCards, boolean pIsDrawPile, Color pBGC)
    {
        // setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(pBGC);

        mCards = pCards;
        mIsDrawPile = pIsDrawPile;
        for (int i = 0; i < mCards.length; i++)
        {

            if (i == 4 || mIsDrawPile)
            {
                mCardPanel[i] = new CardPanel(mCards[i].getNumber(), true, (mCards[i].getNumber() != -1 ? true : false));
            }
            else
            {
                mCardPanel[i] = new CardPanel(mCards[i].getNumber(), false, false);
            }
            add(mCardPanel[i]);
        }
    }

    public void setValue(Card[] pCards)
    {
        mCards = pCards;
        for (int i = 0; i < mCardPanel.length; i++)
        {
            mCardPanel[i].setValue(mCards[i].getNumber());
        }
    }
}
