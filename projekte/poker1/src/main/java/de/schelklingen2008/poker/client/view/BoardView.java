package de.schelklingen2008.poker.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

    private GameModel     model;

    /**
     * Constructs a view which will initialize itself and prepare to display the game board.
     */
    public BoardView(Controller controller)
    {
        this.controller = controller;
        controller.addChangeListener(this);
        model = getGameModel();
        fillImageArray();
        paintBoard();
    }

    @Override
    public Dimension getPreferredSize()
    {
        // TODO calculate correct dimensions for the board view
        return new Dimension(1000, 700);
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

    }

    private void bg(JPanel panel) // Hintergrund einstellen
    {
        panel.setBackground(new Color(0x44FF44));
    }

    private void paintBoard()
    {
        removeAll();

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

        add(Box.createHorizontalStrut(5));
        add(playerPanel);
        add(Box.createHorizontalStrut(5));
        add(middlePanel);
        add(Box.createHorizontalStrut(5));
        add(myPanel);
        add(Box.createHorizontalStrut(5));

        playerPanel.add(Box.createVerticalStrut(5));
        for (int i = 0; i < model.getPlayerList().size(); i++)
        {
            JPanel actPlayerPanel = new JPanel();
            actPlayerPanel.setLayout(new BoxLayout(actPlayerPanel, BoxLayout.PAGE_AXIS));
            actPlayerPanel.add(new JLabel(i + 1 + ": " + model.getPlayerList().get(i).getName()));
            actPlayerPanel.add(new JLabel("Kontostand: " + model.getPlayerList().get(i).getBalance()));
            if (model.getPlayerList().get(i).isStillIn() == false)
            {
                actPlayerPanel.add(new JLabel("Folded"));
            }
            if (model.getPlayerList().get(i).isStillIn() == true && model.getPlayerList().get(i).getBalance() == 0)
            {
                actPlayerPanel.add(new JLabel("All-in"));
            }
            if (model.getPlayerList().get(i).hasLost() == true)
            {
                actPlayerPanel.add(new JLabel("VERLOREN!"));
            }

            playerPanel.add(actPlayerPanel);
            playerPanel.add(Box.createVerticalStrut(5));
        }

        middlePanel.add(Box.createVerticalStrut(5));
        middlePanel.add(cardPanel);
        middlePanel.add(Box.createVerticalStrut(5));
        middlePanel.add(potPanel);
        middlePanel.add(Box.createVerticalStrut(5));

        cardPanel.add(Box.createVerticalStrut(5));
        for (Iterator iterator = model.getCardList().iterator(); iterator.hasNext();)
        {
            Card card = (Card) iterator.next();
            JLabel cardLabel = new JLabel("");
            cardLabel.setIcon(iconBuffer[card.getSuitInt()][card.getValueInt()]);
            cardPanel.add(cardLabel);
            cardPanel.add(Box.createVerticalStrut(5));
        }

        potPanel.add(Box.createVerticalStrut(30));
        potPanel.add(new JLabel("Es sind " + model.getPot() + " Euro im Pot"));
        potPanel.add(Box.createVerticalStrut(30));

        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(myCardPanel);
        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(myInfoPanel);
        myPanel.add(Box.createVerticalStrut(5));

        myCardPanel.add(Box.createVerticalStrut(5));
        myCardPanel.add(new JLabel(getMyPlayer().getName() + ": Ihre Karten:"));
        myCardPanel.add(Box.createVerticalStrut(5));
        myCardPanel.add(twoCardsPanel);
        myCardPanel.add(Box.createVerticalStrut(5));

        JLabel myCard1 = new JLabel("");
        myCard1.setIcon(iconBuffer[getMyPlayer().getCard1().getSuitInt()][getMyPlayer().getCard1().getValueInt()]);
        JLabel myCard2 = new JLabel("");
        myCard2.setIcon(iconBuffer[getMyPlayer().getCard2().getSuitInt()][getMyPlayer().getCard2().getValueInt()]);

        twoCardsPanel.add(Box.createVerticalStrut(5));
        twoCardsPanel.add(myCard1);
        twoCardsPanel.add(Box.createVerticalStrut(5));
        twoCardsPanel.add(myCard2);
        twoCardsPanel.add(Box.createVerticalStrut(5));

        myInfoPanel.add(Box.createHorizontalStrut(5));
        myInfoPanel.add(new JLabel("Ihr Kontostand:"));
        myInfoPanel.add(Box.createHorizontalStrut(5));
        long myBal = getMyPlayer().getBalance();
        myInfoPanel.add(new JLabel("" + myBal));
        myInfoPanel.add(Box.createHorizontalStrut(5));
        myInfoPanel.add(new JLabel("Sie müssen " + model.getHighestBet() + " Euro setzen"));
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
                                                                   + String.valueOf(model.getMinBet())
                                                                   + " Euro, max.: "
                                                                   + String.valueOf(model.getMaxBet())
                                                                   + " Euro)");
                int betrag = Integer.parseInt(s);
                long longBetrag = betrag;
                System.out.print(getActPlayer().getName());
                System.out.println(" hat geraist.");
                controller.raiseButtonClicked(longBetrag);
            }
        };

        ActionListener foldListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                System.out.print(getActPlayer().getName());
                System.out.println(" hat gefoldet.");
                controller.foldButtonClicked();

            }
        };
        ActionListener checkListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                System.out.print(getActPlayer().getName());
                System.out.println("hat gecheckt.");
                controller.checkButtonClicked();

            }
        };

        ActionListener reRaiseListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                String s = javax.swing.JOptionPane.showInputDialog("Die "
                                                                   + (model.getHighestBet() - model.getActPlayer()
                                                                                                   .getOwnBet())
                                                                   + " Euro wurden bereits in den Pot gezahlt.\n"
                                                                   + " Wie viel wollen Sie noch extra drauflegen?\n"
                                                                   + "(min.: "
                                                                   + String.valueOf(model.getMinBet())
                                                                   + " Euro, max.: "
                                                                   + String.valueOf(model.getMaxBet())
                                                                   + " Euro)");
                int betrag = Integer.parseInt(s);
                long longBetrag = betrag;
                System.out.print(getActPlayer().getName());
                System.out.println("hat geReRaised.");
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

        myButtonPanel.add(Box.createVerticalStrut(5));
        myButtonPanel.add(checkButton);
        myButtonPanel.add(Box.createVerticalStrut(5));
        myButtonPanel.add(callButton);
        myButtonPanel.add(Box.createVerticalStrut(5));
        myButtonPanel.add(raiseButton);
        myButtonPanel.add(Box.createVerticalStrut(5));
        myButtonPanel.add(reRaiseButton);
        myButtonPanel.add(Box.createVerticalStrut(5));
        myButtonPanel.add(foldButton);
        myButtonPanel.add(Box.createVerticalStrut(5));

        setBackground(new Color(0x44FF44));

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
