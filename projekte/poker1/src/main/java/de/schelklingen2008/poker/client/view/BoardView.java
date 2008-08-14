package de.schelklingen2008.poker.client.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.schelklingen2008.poker.client.Constants;
import de.schelklingen2008.poker.client.controller.Controller;
import de.schelklingen2008.poker.client.controller.GameChangeListener;
import de.schelklingen2008.poker.client.model.GameContext;
import de.schelklingen2008.poker.model.Card;
import de.schelklingen2008.poker.model.GameModel;
import de.schelklingen2008.poker.model.Player;

/**
 * Displays the main game interface (the board).
 */
public class BoardView extends JPanel implements GameChangeListener
{

    private Controller    controller;

    private ImageIcon[][] iconBuffer      = new ImageIcon[4][13];
    private ImageIcon[][] smallIconBuffer = new ImageIcon[4][13];
    private ImageIcon     iconBack        = null;
    private ImageIcon     smallIconBack   = null;

    /**
     * Constructs a view which will initialize itself and prepare to display the game board.
     */
    public BoardView(Controller controller)
    {
        this.controller = controller;
        controller.addChangeListener(this);
        fillImageArray();
        paintBoard();
    }

    public static String getFileName(Card card, int size)
    {
        return card.getSuit() + "-" + card.getValue() + "-" + size + ".png";
    }

    public void fillImageArray()
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 13; j++)
            {
                iconBuffer[i][j] = new ImageIcon("src/main/resources/cards/" + getFileName(new Card(i, j), 150));
                smallIconBuffer[i][j] = new ImageIcon("src/main/resources/cards/" + getFileName(new Card(i, j), 40));
            }
        }
        iconBack = new ImageIcon("src/main/resources/cards/back-blue-150-1.png");
        smallIconBack = new ImageIcon("src/main/resources/cards/back-blue-40-1.png");
    }

    private void paintBoard()
    {
        removeAll();

        GameModel model = getGameModel();

        if (model == null) return;

        JPanel playerPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel cardPanel = new JPanel();
        JPanel potPanel = new JPanel();
        JPanel myPanel = new JPanel();
        JPanel myCardPanel = new JPanel();
        JPanel myInfoPanel = new JPanel();
        JPanel myButtonPanel = new JPanel();
        JPanel twoCardsPanel = new JPanel();

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.LINE_AXIS));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.LINE_AXIS));
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.LINE_AXIS));
        potPanel.setLayout(new BoxLayout(potPanel, BoxLayout.PAGE_AXIS));
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.LINE_AXIS));
        myCardPanel.setLayout(new BoxLayout(myCardPanel, BoxLayout.PAGE_AXIS));
        myInfoPanel.setLayout(new BoxLayout(myInfoPanel, BoxLayout.PAGE_AXIS));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.LINE_AXIS));
        twoCardsPanel.setLayout(new BoxLayout(twoCardsPanel, BoxLayout.LINE_AXIS));
        myButtonPanel.setLayout(new BoxLayout(myButtonPanel, BoxLayout.LINE_AXIS));

        setOpaque(false);
        playerPanel.setOpaque(false);
        middlePanel.setOpaque(false);
        cardPanel.setOpaque(false);
        potPanel.setOpaque(false);
        myPanel.setOpaque(false);
        myCardPanel.setOpaque(false);
        myInfoPanel.setOpaque(false);
        middlePanel.setOpaque(false);
        twoCardsPanel.setOpaque(false);
        myButtonPanel.setOpaque(false);

        add(Box.createVerticalStrut(10));
        add(playerPanel);
        add(Box.createVerticalStrut(10));
        add(middlePanel);
        add(Box.createVerticalStrut(10));
        add(myPanel);
        add(Box.createVerticalStrut(10));

        playerPanel.add(Box.createHorizontalStrut(5));
        for (int i = 0; i < getGameModel().getPlayerList().size(); i++)
        {
            JPanel actPlayerPanel = new JPanel();
            actPlayerPanel.setLayout(new BoxLayout(actPlayerPanel, BoxLayout.PAGE_AXIS));
            actPlayerPanel.setOpaque(false);
            actPlayerPanel.add(createLabel(i + 1 + ": " + model.getPlayerList().get(i).getName()));
            actPlayerPanel.add(createLabel(controller.getMessage(Constants.MSG_BALANCE)
                                           + ": "
                                           + model.getPlayerList().get(i).getBalance()));
            if (getGameModel().getActPlayerIndex() == i)
            {
                actPlayerPanel.add(createLabel(controller.getMessage(Constants.MSG_TURNHOLDER)));
            }
            else
            {
                actPlayerPanel.add(createLabel(""));
            }
            if (!getGameModel().getPlayerList().get(i).isStillIn())
            {
                actPlayerPanel.add(createLabel(controller.getMessage(Constants.MSG_FOLDED)));
            }
            else
            {
                actPlayerPanel.add(createLabel(""));
            }
            if (getGameModel().getPlayerList().get(i).isStillIn() && model.getPlayerList().get(i).getBalance() == 0)
            {
                actPlayerPanel.add(createLabel(controller.getMessage(Constants.MSG_ALLIN)));
            }
            else
            {
                actPlayerPanel.add(createLabel(""));
            }
            if (model.getPlayerList().get(i).hasLost())
            {
                actPlayerPanel.add(createLabel(controller.getMessage(Constants.MSG_LOST)));
            }
            else
            {
                actPlayerPanel.add(createLabel(""));
            }

            JPanel actPlayerCardPanel = new JPanel();
            actPlayerCardPanel.setLayout(new BoxLayout(actPlayerCardPanel, BoxLayout.LINE_AXIS));
            actPlayerCardPanel.setOpaque(false);
            actPlayerCardPanel.add(Box.createVerticalStrut(5));
            if (getGameModel().getPhase() == 4)
            {
                Card card1 = getGameModel().getPlayerList().get(i).getCard1();
                Card card2 = getGameModel().getPlayerList().get(i).getCard2();
                actPlayerCardPanel.add(createLabel(smallIconBuffer[card1.getSuitInt()][card1.getValueInt()]));
                actPlayerCardPanel.add(Box.createHorizontalStrut(5));
                actPlayerCardPanel.add(createLabel(smallIconBuffer[card2.getSuitInt()][card2.getValueInt()]));
            }
            else
            {
                actPlayerCardPanel.add(createLabel(smallIconBack));
                actPlayerCardPanel.add(Box.createHorizontalStrut(5));
                actPlayerCardPanel.add(createLabel(smallIconBack));
            }
            actPlayerCardPanel.add(Box.createHorizontalStrut(5));
            actPlayerPanel.add(actPlayerCardPanel);
            playerPanel.add(actPlayerPanel);
            playerPanel.add(Box.createHorizontalStrut(5));

        }

        middlePanel.add(Box.createHorizontalStrut(5));
        middlePanel.add(cardPanel);
        middlePanel.add(Box.createHorizontalStrut(5));
        middlePanel.add(potPanel);
        middlePanel.add(Box.createHorizontalStrut(5));

        cardPanel.add(Box.createHorizontalStrut(5));
        List<Card> cardList = model.getCardList();
        for (Card card : cardList)
        {
            cardPanel.add(createLabel(iconBuffer[card.getSuitInt()][card.getValueInt()]));
            cardPanel.add(Box.createHorizontalStrut(5));
        }
        for (int i = cardList.size(); i < 5; i++)
        {
            cardPanel.add(createLabel(iconBack));
            cardPanel.add(Box.createHorizontalStrut(5));
        }

        potPanel.add(Box.createVerticalStrut(30));
        if (model.getPhase() != 4)
            potPanel.add(createLabel(model.getPot() + " " + controller.getMessage(Constants.MSG_EUROS_IN_POT)));
        else
        {
            for (Player player : model.getWinnerList())
            {
                potPanel.add(createLabel(player.getName()
                                         + " "
                                         + controller.getMessage(Constants.MSG_WON)
                                         + " "
                                         + model.getWinnerValue()
                                         + " "
                                         + controller.getMessage(Constants.MSG_EUROS)));
            }
        }

        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(myCardPanel);
        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(myInfoPanel);
        myPanel.add(Box.createVerticalStrut(5));

        myCardPanel.add(Box.createVerticalStrut(5));
        myCardPanel.add(createLabel(getMyPlayer().getName() + ": " + controller.getMessage(Constants.MSG_YOUR_CARDS)));
        myCardPanel.add(Box.createVerticalStrut(5));
        myCardPanel.add(twoCardsPanel);
        myCardPanel.add(Box.createVerticalStrut(5));

        twoCardsPanel.add(Box.createHorizontalStrut(5));
        twoCardsPanel.add(createLabel(iconBuffer[getMyPlayer().getCard1().getSuitInt()][getMyPlayer().getCard1()
                                                                                                     .getValueInt()]));
        twoCardsPanel.add(Box.createHorizontalStrut(5));
        twoCardsPanel.add(createLabel(iconBuffer[getMyPlayer().getCard2().getSuitInt()][getMyPlayer().getCard2()
                                                                                                     .getValueInt()]));
        twoCardsPanel.add(Box.createHorizontalStrut(5));

        myInfoPanel.add(Box.createHorizontalStrut(5));
        myInfoPanel.add(createLabel(controller.getMessage(Constants.MSG_YOUR_BALANCE)));
        myInfoPanel.add(Box.createHorizontalStrut(5));
        long myBal = getMyPlayer().getBalance();
        myInfoPanel.add(createLabel(String.valueOf(myBal)));
        myInfoPanel.add(Box.createHorizontalStrut(5));
        myInfoPanel.add(createLabel(model.getHighestBet()
                                    - getMyPlayer().getOwnBet()
                                    + " "
                                    + controller.getMessage(Constants.MSG_EUROS_MUST_BE_SET)));
        myInfoPanel.add(Box.createHorizontalStrut(5));
        myInfoPanel.add(myButtonPanel);
        myInfoPanel.add(Box.createHorizontalStrut(5));

        JButton callButton = new JButton(controller.getMessage(Constants.MSG_CALL));
        JButton raiseButton = new JButton(controller.getMessage(Constants.MSG_RAISE));
        JButton foldButton = new JButton(controller.getMessage(Constants.MSG_FOLD));
        JButton checkButton = new JButton(controller.getMessage(Constants.MSG_CHECK));
        JButton reRaiseButton = new JButton(controller.getMessage(Constants.MSG_RERAISE));
        JButton okayButton = new JButton(controller.getMessage(Constants.MSG_OKAY));

        ActionListener callListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                controller.callButtonClicked();
            }
        };

        ActionListener raiseListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                GameModel model = getGameModel();
                String s = javax.swing.JOptionPane.showInputDialog(controller.getMessage(Constants.MSG_HOW_MUCH_RAISE)
                                                                   + controller.getMessage(Constants.MSG_MIN)
                                                                   + String.valueOf(model.getMinBet())
                                                                   + controller.getMessage(Constants.MSG_MAX)
                                                                   + String.valueOf(model.getMaxBet())
                                                                   + controller.getMessage(Constants.MSG_EUROS));
                int betrag = Integer.parseInt(s);
                long longBetrag = betrag;
                if (betrag < model.getMinBet() || betrag > model.getMaxBet()) return;
                controller.raiseButtonClicked(longBetrag);
            }
        };

        ActionListener foldListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                controller.foldButtonClicked();

            }
        };
        ActionListener checkListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                controller.checkButtonClicked();

            }
        };

        ActionListener reRaiseListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                GameModel model = getGameModel();
                String s = javax.swing.JOptionPane.showInputDialog(model.getHighestBet()
                                                                   - model.getActPlayer().getOwnBet()
                                                                   + controller.getMessage(Constants.MSG_HOW_MUCH_RERAISE)
                                                                   + controller.getMessage(Constants.MSG_MIN)
                                                                   + String.valueOf(model.getMinBet())
                                                                   + controller.getMessage(Constants.MSG_MAX)
                                                                   + String.valueOf(model.getMaxBet())
                                                                   + controller.getMessage(Constants.MSG_EUROS));
                int betrag = Integer.parseInt(s);
                if (betrag < model.getMinBet() || betrag > model.getMaxBet()) return;
                long longBetrag = betrag;
                controller.reRaiseButtonClicked(longBetrag);
            }
        };

        ActionListener okayListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                controller.okayClicked();
            }

        };

        callButton.addActionListener(callListener);
        foldButton.addActionListener(foldListener);
        raiseButton.addActionListener(raiseListener);
        checkButton.addActionListener(checkListener);
        reRaiseButton.addActionListener(reRaiseListener);
        okayButton.addActionListener(okayListener);

        callButton.setEnabled(false);
        foldButton.setEnabled(false);
        raiseButton.setEnabled(false);
        checkButton.setEnabled(false);
        reRaiseButton.setEnabled(false);

        if (model.mustCallOrReRaise(getMyIndex()) == true)
        {
            callButton.setEnabled(true);
            reRaiseButton.setEnabled(true);
            foldButton.setEnabled(true);
        }

        if (model.mustCheckOrRaise(getMyIndex()) == true)
        {
            raiseButton.setEnabled(true);
            checkButton.setEnabled(true);
            foldButton.setEnabled(true);
        }

        myButtonPanel.add(Box.createHorizontalStrut(5));

        if (model.getPhase() != 4)
        {
            myButtonPanel.add(checkButton);
            myButtonPanel.add(Box.createHorizontalStrut(5));
            myButtonPanel.add(callButton);
            myButtonPanel.add(Box.createHorizontalStrut(5));
            myButtonPanel.add(raiseButton);
            myButtonPanel.add(Box.createHorizontalStrut(5));
            myButtonPanel.add(reRaiseButton);
            myButtonPanel.add(Box.createHorizontalStrut(5));
            myButtonPanel.add(foldButton);
        }
        else
        {
            myButtonPanel.add(okayButton);
        }
        myButtonPanel.add(Box.createHorizontalStrut(5));
    }

    private JLabel createLabel(String text)
    {
        JLabel label = new JLabel(text);
        label.setOpaque(false);
        label.setForeground(Color.WHITE);
        label.setBackground(Constants.BACK_GREEN);
        return label;
    }

    private JLabel createLabel(ImageIcon icon)
    {
        JLabel label = new JLabel(icon);
        label.setOpaque(false);
        label.setForeground(Color.WHITE);
        label.setBackground(Constants.BACK_GREEN);
        return label;
    }

    private int getMyIndex()
    {
        return getGameContext().getMyIndex();
    }

    private int getActIndex()
    {
        return getGameModel().getActPlayerIndex();
    }

    private Player getActPlayer()
    {
        return getGameModel().getPlayerList().get(getGameModel().getActPlayerIndex());
    }

    private Player getMyPlayer()
    {
        return getGameModel().getPlayerList().get(getGameContext().getMyIndex());
    }

    private GameContext getGameContext()
    {
        return controller.getGameContext();
    }

    public void gameChanged()
    {
        paintBoard();
    }

    private GameModel getGameModel()
    {
        return getGameContext().getGameModel();
    }
}
