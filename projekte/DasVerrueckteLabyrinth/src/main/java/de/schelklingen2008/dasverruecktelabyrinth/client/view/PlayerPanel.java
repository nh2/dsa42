package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.schelklingen2008.dasverruecktelabyrinth.client.controller.Controller;
import de.schelklingen2008.dasverruecktelabyrinth.client.controller.GameChangeListener;
import de.schelklingen2008.dasverruecktelabyrinth.client.model.GameContext;
import de.schelklingen2008.dasverruecktelabyrinth.model.GameModel;
import de.schelklingen2008.dasverruecktelabyrinth.model.PlayerCards;
import de.schelklingen2008.dasverruecktelabyrinth.model.PlayerType;
import de.schelklingen2008.dasverruecktelabyrinth.model.Tile;
import de.schelklingen2008.dasverruecktelabyrinth.model.TreasureCard;
import de.schelklingen2008.util.LoggerFactory;

public class PlayerPanel extends JPanel implements GameChangeListener
{

    private static final Logger sLogger = LoggerFactory.create();

    private Controller          controller;

    private TilePanel           insertTilePanel;
    private JLabel              searchThisCardLabel;

    public PlayerPanel(Controller controller)

    {
        setPreferredSize(new Dimension(600, 130));

        this.controller = controller;
        controller.addChangeListener(this);

        setBackground(Color.white);

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JPanel drehButtons = new JPanel();
        drehButtons.setLayout(new BoxLayout(drehButtons, BoxLayout.PAGE_AXIS));

        JButton right = new JButton("rechts");
        JButton left = new JButton("links");

        right.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                PlayerPanel.this.controller.rechtsDrehen();
            }
        });
        left.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                PlayerPanel.this.controller.linksDrehen();
            }
        });

        drehButtons.add(right);
        drehButtons.add(left);

        insertTilePanel = new TilePanel(new Tile(true, true, true, true, TreasureCard.BIBEL));
        insertTilePanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.PAGE_AXIS));

        JPanel searchThisCardPanel = new JPanel();
        searchThisCardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        searchThisCardPanel.setBackground(Color.white);
        searchThisCardLabel = new JLabel(new ImageIcon(Images.getBigTcImage(TreasureCard.EULE)));
        searchThisCardPanel.add(searchThisCardLabel);

        add(drehButtons);
        add(insertTilePanel);
        add(searchThisCardPanel);
    }

    public TreasureCard getHiddenCard()
    {
        Map<PlayerType, PlayerCards> pcMap = getGameModel().getPlayerCardsMap();
        PlayerCards hiddenCards = pcMap.get(getGameContext().getMyPlayerType());
        List<TreasureCard> hidden = hiddenCards.getHiddenCards();
        TreasureCard searchThisCard = hidden.get(0);
        return searchThisCard;
    }

    private GameModel getGameModel()
    {
        return getGameContext().getGameModel();
    }

    private GameContext getGameContext()
    {
        return controller.getGameContext();
    }

    public void gameChanged()
    {
        insertTilePanel.setTile(getGameModel().getInsertTile());
        searchThisCardLabel.setIcon(new ImageIcon(Images.getBigTcImage(getHiddenCard())));

        repaint();
    }
}
