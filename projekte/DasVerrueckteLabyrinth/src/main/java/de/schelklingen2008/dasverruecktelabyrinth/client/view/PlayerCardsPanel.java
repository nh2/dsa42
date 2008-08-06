package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import de.schelklingen2008.dasverruecktelabyrinth.client.controller.Controller;
import de.schelklingen2008.dasverruecktelabyrinth.client.model.GameContext;
import de.schelklingen2008.dasverruecktelabyrinth.model.GameModel;
import de.schelklingen2008.dasverruecktelabyrinth.model.PlayerCards;
import de.schelklingen2008.dasverruecktelabyrinth.model.PlayerType;
import de.schelklingen2008.dasverruecktelabyrinth.model.TreasureCard;

public class PlayerCardsPanel extends JPanel
{

    private Controller controller;

    public PlayerCardsPanel(Controller controller)
    {
        this.controller = controller;

        Map<PlayerType, PlayerCards> MapWtf = getGameModel().getPlayerCardsMap();

        PlayerCards karten = MapWtf.get(PlayerType.WHITE);
        List<TreasureCard> open = karten.getOpenCards();
        List<TreasureCard> hidden = karten.getHiddenCards();

        // TODO auf listen zugreifen

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
