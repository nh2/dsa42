package de.schelklingen2008.jipbo.client.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import javax.swing.JPanel;

import de.schelklingen2008.jipbo.client.controller.Controller;
import de.schelklingen2008.jipbo.model.Card;
import de.schelklingen2008.util.LoggerFactory;

public class BoardPanel extends JPanel
{

    private static final Logger sLogger    = LoggerFactory.create();

    private Card[]              mCards;
    private boolean             mIsDrawPile;
    private CardPanel[]         mCardPanel = new CardPanel[5];
    private Controller          mController;
    private BoardPanel          kBoardPanel;

    public BoardPanel(Controller pController, Card[] pCards, boolean pIsDrawPile, Color pBGC)
    {
        mController = pController;
        setBackground(pBGC);

        mCards = pCards;
        mIsDrawPile = pIsDrawPile;
        kBoardPanel = null;
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
            if (mController != null && mCards[i].getNumber() != -1)
            {
                mCardPanel[i].addMouseListener(new MouseAdapter()
                {

                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        CardPanel cardPanel = (CardPanel) e.getComponent();

                        if (kBoardPanel != null)
                        {
                            CardPanel[] kCardPanel = kBoardPanel.getCardPanel();
                            for (int j = 0; j < kCardPanel.length; j++)
                            {
                                kCardPanel[j].setBorder(false);
                            }
                            kBoardPanel.repaint();
                        }

                        for (int j = 0; j < mCardPanel.length; j++)
                        {
                            mCardPanel[j].setBorder(false);
                        }
                        if (!isPublicCards())
                        {
                            mController.setOwnSelectedCard(isDrawPile(), cardPanel.getValue());

                            cardPanel.setBorder(mController.getSelectedOwnCardIndex() == cardPanel.getValue());

                        }
                        else
                        {
                            if (mController.getSelectedOwnCardIndex() != -2
                                && (mController.getSelectedOwnCardIndex() == 0 || mController.getSelectedOwnCardIndex() - 1 == cardPanel.getValue()))
                            {
                                mController.setPublicSelectedCard(cardPanel.getValue());
                                cardPanel.setBorder(mController.getSelectedPublicCardIndex() == cardPanel.getValue());
                            }
                        }
                        repaint();

                        sLogger.fine("send own card "
                                     + mController.getSelectedOwnCardIndex()
                                     + " and public card "
                                     + mController.getSelectedPublicCardIndex()
                                     + " to controller");
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

    public CardPanel[] getCardPanel()
    {
        return mCardPanel;
    }

    public void setKBoardPanel(BoardPanel pBoardPanel)
    {
        kBoardPanel = pBoardPanel;
    }

}
