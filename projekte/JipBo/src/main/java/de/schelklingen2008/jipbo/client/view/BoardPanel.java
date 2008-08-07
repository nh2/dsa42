package de.schelklingen2008.jipbo.client.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import de.schelklingen2008.jipbo.client.controller.Controller;
import de.schelklingen2008.jipbo.model.Card;

public class BoardPanel extends JPanel
{

    private Card[]      mCards;
    private boolean     mIsDrawPile;
    private CardPanel[] mCardPanel = new CardPanel[5];
    private Controller  controller;

    public BoardPanel(Controller pController, Card[] pCards, boolean pIsDrawPile, Color pBGC)
    {
        controller = pController;
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
            if (pController != null && mCards[i].getNumber() != -1)
            {
                mCardPanel[i].addMouseListener(new MouseAdapter()
                {

                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        CardPanel cardPanel = (CardPanel) e.getComponent();
                        cardPanel.setBorder();
                        System.out.println(cardPanel.getValue());
                        if (isDrawPile())
                        {
                            System.out.println("Hand");
                        }
                        else if (isPublicCards())
                        {
                            System.out.println("Ablegestapel");
                        }
                        else if (isMyBoardPanel())
                        {
                            System.out.println("Meine Karten");
                        }
                        if (!isPublicCards())
                        {
                            controller.setSelectedCard(isDrawPile(), cardPanel.getValue());
                        }
                    }
                });
            }
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

    public boolean isDrawPile()
    {
        return mIsDrawPile;
    }

    public boolean isMyBoardPanel()
    {
        return !isDrawPile() && !isPublicCards() ? true : false;
    }

    public boolean isPublicCards()
    {
        return mCards[4].getNumber() == -1 ? true : false;
    }

}
