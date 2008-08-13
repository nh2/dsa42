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

    private ImageIcon[][] iconBuffer = new ImageIcon[4][13];
    private ImageIcon     iconBack   = null;

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

    public static String getFileName(Card card)
    {
        return card.getSuit() + "-" + card.getValue() + "-150.png";
    }

    public void fillImageArray()
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 13; j++)
            {
                iconBuffer[i][j] = new ImageIcon("src/main/resources/cards/" + getFileName(new Card(i, j)));
            }
        }
        iconBack = new ImageIcon("src/main/resources/cards/back-blue-150-1.png");
    }

    private void paintBoard()
    {
        removeAll();

        if (getGameModel() == null) return;

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

        GameModel model = getGameModel();

        playerPanel.add(Box.createHorizontalStrut(5));
        for (int i = 0; i < getGameModel().getPlayerList().size(); i++)
        {
            JPanel actPlayerPanel = new JPanel();
            actPlayerPanel.setLayout(new BoxLayout(actPlayerPanel, BoxLayout.PAGE_AXIS));
            actPlayerPanel.setOpaque(false);
            actPlayerPanel.add(createLabel(i + 1 + ": " + model.getPlayerList().get(i).getName()));
            actPlayerPanel.add(createLabel("Kontostand: " + model.getPlayerList().get(i).getBalance()));
            if (getGameModel().getPlayerList().get(i).isStillIn() == false)
            {
                actPlayerPanel.add(createLabel("Folded"));
            }
            if (getGameModel().getPlayerList().get(i).isStillIn() == true
                && model.getPlayerList().get(i).getBalance() == 0)
            {
                actPlayerPanel.add(createLabel("All-in"));
            }
            if (model.getPlayerList().get(i).hasLost() == true)
            {
                actPlayerPanel.add(createLabel("VERLOREN!"));
            }

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
            JLabel cardLabel = createLabel("");
            cardLabel.setIcon(iconBuffer[card.getSuitInt()][card.getValueInt()]);
            cardPanel.add(cardLabel);
            cardPanel.add(Box.createHorizontalStrut(5));
        }
        for (int i = cardList.size(); i < 5; i++)
        {
            JLabel cardLabel = createLabel("");
            cardLabel.setIcon(iconBack);
            cardPanel.add(cardLabel);
            cardPanel.add(Box.createHorizontalStrut(5));
        }

        potPanel.add(Box.createVerticalStrut(30));
        potPanel.add(createLabel("Es sind " + model.getPot() + " Euro im Pot"));
        potPanel.add(Box.createVerticalStrut(30));

        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(myCardPanel);
        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(myInfoPanel);
        myPanel.add(Box.createVerticalStrut(5));

        myCardPanel.add(Box.createVerticalStrut(5));
        myCardPanel.add(createLabel(getMyPlayer().getName() + ": Ihre Karten:"));
        myCardPanel.add(Box.createVerticalStrut(5));
        myCardPanel.add(twoCardsPanel);
        myCardPanel.add(Box.createVerticalStrut(5));

        JLabel myCard1 = createLabel("");
        myCard1.setIcon(iconBuffer[getMyPlayer().getCard1().getSuitInt()][getMyPlayer().getCard1().getValueInt()]);
        JLabel myCard2 = createLabel("");
        myCard2.setIcon(iconBuffer[getMyPlayer().getCard2().getSuitInt()][getMyPlayer().getCard2().getValueInt()]);

        twoCardsPanel.add(Box.createHorizontalStrut(5));
        twoCardsPanel.add(myCard1);
        twoCardsPanel.add(Box.createHorizontalStrut(5));
        twoCardsPanel.add(myCard2);
        twoCardsPanel.add(Box.createHorizontalStrut(5));

        myInfoPanel.add(Box.createHorizontalStrut(5));
        myInfoPanel.add(createLabel("Ihr Kontostand:"));
        myInfoPanel.add(Box.createHorizontalStrut(5));
        long myBal = getMyPlayer().getBalance();
        myInfoPanel.add(createLabel("" + myBal));
        myInfoPanel.add(Box.createHorizontalStrut(5));
        myInfoPanel.add(createLabel("Sie müssen " + model.getHighestBet() + " Euro setzen"));
        myInfoPanel.add(Box.createHorizontalStrut(5));
        myInfoPanel.add(myButtonPanel);
        myInfoPanel.add(Box.createHorizontalStrut(5));

        JButton callButton = new JButton("Call");
        JButton raiseButton = new JButton("Raise");
        JButton foldButton = new JButton("Fold");
        JButton checkButton = new JButton("Check");
        JButton reRaiseButton = new JButton("Re-Raise");

        ActionListener callListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                System.out.print(getActPlayer().getName());
                System.out.println(" hat gecallt.");
                controller.callButtonClicked();
            }
        };

        ActionListener raiseListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                String s = javax.swing.JOptionPane.showInputDialog("Bitte geben Sie Ihren Einsatz an:\n"
                                                                   + "(min.: "
                                                                   + String.valueOf(getGameModel().getMinBet())
                                                                   + " Euro, max.: "
                                                                   + String.valueOf(getGameModel().getMaxBet())
                                                                   + " Euro)");
                int betrag = Integer.parseInt(s);
                long longBetrag = betrag;
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
                String s = javax.swing.JOptionPane.showInputDialog("Die "
                                                                   + (getGameModel().getHighestBet() - getGameModel().getActPlayer()
                                                                                                                     .getOwnBet())
                                                                   + " Euro wurden bereits in den Pot gezahlt.\n"
                                                                   + " Wie viel wollen Sie noch extra drauflegen?\n"
                                                                   + "(min.: "
                                                                   + String.valueOf(getGameModel().getMinBet())
                                                                   + " Euro, max.: "
                                                                   + String.valueOf(getGameModel().getMaxBet())
                                                                   + " Euro)");
                int betrag = Integer.parseInt(s);
                long longBetrag = betrag;
                controller.reRaiseButtonClicked(longBetrag);

            }
        };

        callButton.addActionListener(callListener);
        foldButton.addActionListener(foldListener);
        raiseButton.addActionListener(raiseListener);
        checkButton.addActionListener(checkListener);
        reRaiseButton.addActionListener(reRaiseListener);

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
        myButtonPanel.add(checkButton);
        myButtonPanel.add(Box.createHorizontalStrut(5));
        myButtonPanel.add(callButton);
        myButtonPanel.add(Box.createHorizontalStrut(5));
        myButtonPanel.add(raiseButton);
        myButtonPanel.add(Box.createHorizontalStrut(5));
        myButtonPanel.add(reRaiseButton);
        myButtonPanel.add(Box.createHorizontalStrut(5));
        myButtonPanel.add(foldButton);
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
