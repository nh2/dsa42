package de.schelklingen2008.poker.client.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.schelklingen2008.poker.client.controller.Controller;
import de.schelklingen2008.poker.client.controller.GameChangeListener;
import de.schelklingen2008.poker.client.model.GameContext;
import de.schelklingen2008.poker.model.Card;
import de.schelklingen2008.poker.model.GameModel;

/**
 * Displays the main game interface (the board).
 */
public class BoardView extends JPanel implements GameChangeListener
{

    private Controller        controller;

    private BufferedImage[][] imageBuffer = new BufferedImage[4][13];

    private GameModel         model;

    /**
     * Constructs a view which will initialize itself and prepare to display the game board.
     */
    public BoardView(Controller controller)
    {
        this.controller = controller;
        controller.addChangeListener(this);
        model = controller.getGameContext().getGameModel();
    }

    @Override
    public Dimension getPreferredSize()
    {
        // TODO calculate correct dimensions for the board view
        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        // TODO do proper painting of game state
        paintBackground(gfx);
        paintBoard(gfx);
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
                try
                {
                    imageBuffer[i][j] = ImageIO.read(new File(getFileName(new Card(i, j))));
                }
                catch (IOException e)
                {
                    throw new RuntimeException("Kann Bild nicht laden.");
                }
            }
        }

    }

    private void paintBackground(Graphics2D gfx)
    {
    }

    private void paintBoard(Graphics2D gfx)
    {
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
            String output = i + 1 + ": " + model.getPlayerList().get(i).getName() + "\n";
            output += model.getPlayerList().get(i).getBalance();
            if (model.getPlayerList().get(i).isStillIn() == false)
            {
                output += "Folded\n";
            }
            if (model.getPlayerList().get(i).isStillIn() == true && model.getPlayerList().get(i).getBalance() == 0)
            {
                output += "All-in\n";
            }

            playerPanel.add(new JLabel(output));
            playerPanel.add(Box.createVerticalStrut(5));
        }

        // playerPanel.add(new JLabel("Hallo, Label 1"));
        middlePanel.add(Box.createVerticalStrut(5));
        middlePanel.add(cardPanel);
        middlePanel.add(Box.createVerticalStrut(5));
        middlePanel.add(potPanel);
        middlePanel.add(Box.createVerticalStrut(5));

        cardPanel.add(Box.createVerticalStrut(5));
        for (Iterator iterator = model.getCardList().iterator(); iterator.hasNext();)
        {
            Card card = (Card) iterator.next();
            BufferedImage cardImage = imageBuffer[card.getSuitInt()][card.getValueInt()];
            // JLabel cardLabel = new JLabel("");
            // cardLabel.setIcon()

        }
        potPanel.add(Box.createVerticalStrut(5));
        potPanel.add(new JLabel("pot"));
        potPanel.add(Box.createVerticalStrut(5));

        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(myCardPanel);
        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(myInfoPanel);
        myPanel.add(Box.createVerticalStrut(5));

        myCardPanel.add(Box.createVerticalStrut(5));
        myCardPanel.add(new JLabel("Ich: Ihre Karten:"));
        myCardPanel.add(Box.createVerticalStrut(5));
        myCardPanel.add(twoCardsPanel);
        myCardPanel.add(Box.createVerticalStrut(5));

        twoCardsPanel.add(Box.createVerticalStrut(5));
        twoCardsPanel.add(new JLabel("karte1"));
        twoCardsPanel.add(Box.createVerticalStrut(5));
        twoCardsPanel.add(new JLabel("karte2"));
        twoCardsPanel.add(Box.createVerticalStrut(5));

        myInfoPanel.add(Box.createHorizontalStrut(5));
        myInfoPanel.add(new JLabel("Ihr Kontostand:"));
        myInfoPanel.add(Box.createHorizontalStrut(5));
        myInfoPanel.add(new JLabel("137Euro"));
        myInfoPanel.add(Box.createHorizontalStrut(5));
        myInfoPanel.add(new JLabel("Sie müssen 200 Euro setzen"));
        myInfoPanel.add(Box.createHorizontalStrut(5));
        myInfoPanel.add(myButtonPanel);
        myInfoPanel.add(Box.createHorizontalStrut(5));

        JButton callButton = new JButton("Call");
        JButton raiseButton = new JButton("Raise");
        JButton foldButton = new JButton("Fold");
        JButton checkButton = new JButton("Check");

        ActionListener callListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Gecallt");
            }

        };

        ActionListener raiseListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Geraist");
            }

        };

        ActionListener foldListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Gefoldet");
            }

        };
        ActionListener checkListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Gecheckt");
            }

        };

        callButton.addActionListener(callListener);
        foldButton.addActionListener(foldListener);
        raiseButton.addActionListener(raiseListener);
        checkButton.addActionListener(checkListener);

        myButtonPanel.add(callButton);
        myButtonPanel.add(foldButton);
        myButtonPanel.add(raiseButton);
        myButtonPanel.add(checkButton);

    }

    public void gameChanged()
    {
        repaint();
    }

    private GameModel getGameModel()
    {
        return getGameContext().getGameModel();
    }

    private GameContext getGameContext()
    {
        return controller.getGameContext();
    }

}
