package de.schelklingen2008.jipbo.client.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import javax.swing.JPanel;

import de.schelklingen2008.jipbo.client.Constants;
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
            if (mController != null && (mCards[i].getNumber() != -1 || !mIsDrawPile))
            {
                mCardPanel[i].addMouseListener(new MouseAdapter()
                {

                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (!mController.getGameContext().isMyTurn()) return;

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
                        int cardPlace = 0;
                        for (int j = 0; j < mCardPanel.length; j++)
                        {
                            mCardPanel[j].setBorder(false);
                            if (mCardPanel[j].equals(cardPanel))
                            {
                                cardPlace = j;
                            }
                        }

                        if (!isPublicCards())
                        {
                            mController.setOwnSelectedCard(cardPlace, isDrawPile(), cardPanel.getValue());

                            int newPlace = mController.getSelectedOwnCardPlace();
                            if (newPlace >= 0 && mController.getGameContext().isMyTurn())
                            {
                                boolean newIsHand = mController.isSelectedOwnCardInHand();
                                boolean drawMyPanel = newIsHand && mIsDrawPile || !newIsHand && !mIsDrawPile;
                                BoardPanel newPanel = drawMyPanel ? BoardPanel.this : kBoardPanel;
                                newPanel.getCardPanel()[newPlace].setBorder(true);
                                newPanel.repaint();
                            }
                        }
                        else
                        {
                            mController.setPublicSelectedCard(cardPlace, cardPanel.getValue());
                        }

                        repaint();

                        sLogger.fine("send own card " + mController.getSelectedOwnCardIndex() + " to controller");
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
        return !isDrawPile() && !isPublicCards();
    }

    public boolean isPublicCards()
    {
        return mCards[4].getNumber() == Constants.COVER;
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
