package de.schelklingen2008.jipbo.client.view;

import java.awt.Color;

import javax.swing.JPanel;

public class BoardPanel extends JPanel
{

    private int[]       mN;
    private boolean     mIsDrawPile;
    private CardPanel[] mCards = new CardPanel[5];

    public BoardPanel(int[] pN, boolean pIsDrawPile, Color pBGC)
    {
        // setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(pBGC);
        mN = pN;
        mIsDrawPile = pIsDrawPile;
        for (int i = 0; i < mN.length; i++)
        {

            if (i == 4 || mIsDrawPile)
            {
                mCards[i] = new CardPanel(mN[i], true, (mN[i] != -1 ? true : false));
            }
            else
            {
                mCards[i] = new CardPanel(mN[i], false, false);
            }
            add(mCards[i]);
        }
    }

    public void setValue(int[] pN)
    {
        mN = pN;
        for (int i = 0; i < mCards.length; i++)
        {
            mCards[i].setValue(mN[i]);
        }
    }
}
