package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.schelklingen2008.dasverruecktelabyrinth.client.controller.Controller;
import de.schelklingen2008.dasverruecktelabyrinth.client.controller.GameChangeListener;
import de.schelklingen2008.dasverruecktelabyrinth.client.model.GameContext;
import de.schelklingen2008.dasverruecktelabyrinth.model.GameModel;
import de.schelklingen2008.dasverruecktelabyrinth.model.PlayerCards;
import de.schelklingen2008.dasverruecktelabyrinth.model.PlayerType;
import de.schelklingen2008.dasverruecktelabyrinth.model.TreasureCard;

public class PlayerPanel extends JPanel implements GameChangeListener
{

    private Controller controller;

    BufferedImage      curveOne, curveTwo, curveThree, curveFour;
    BufferedImage      cross, horizontal, vertikal;
    BufferedImage      eule, krone, flaschengeist, ring, motte, spinne;
    BufferedImage      fee, karte, drache, bibel, eidechse, geldbeutel, fledermaus;
    BufferedImage      troll, scarabaeus, maus, smaragd, totenkopf, helm, leuchter;
    BufferedImage      schmuckkasten, schluessel, schwert, gespenst;

    public PlayerPanel(Controller controller)

    {
        controller = controller;
        setLayout(new BorderLayout());

        JPanel drehButtons = new JPanel();

        JLabel linsert = new JLabel(new ImageIcon(getInsert()));

        JLabel obersteCard = new JLabel(new ImageIcon(getObersteCard(hiddenCards())));

        drehButtons.setLayout(new BoxLayout(drehButtons, BoxLayout.PAGE_AXIS));
        drehButtons.add(new Button("rechts drehen"));
        drehButtons.add(new Button("links drehen"));

        try
        {
            curveOne = ImageIO.read(new File("src/main/resources/TilesBilder/curveOne.png"));
            curveTwo = ImageIO.read(new File("src/main/resources/TilesBilder/curveTwo.png"));
            curveThree = ImageIO.read(new File("src/main/resources/TilesBilder/curveThree.png"));
            curveFour = ImageIO.read(new File("src/main/resources/TilesBilder/curveFour.png"));
            horizontal = ImageIO.read(new File("src/main/resources/TilesBilder/horizontal.png"));
            vertikal = ImageIO.read(new File("src/main/resources/TilesBilder/vertikal.png"));
            cross = ImageIO.read(new File("src/main/resources/TilesBilder/Cross.png"));
            eule = ImageIO.read(new File("src/main/resources/Bilder/80/eule.png"));
            krone = ImageIO.read(new File("src/main/resources/Bilder/80/krone.png"));
            flaschengeist = ImageIO.read(new File("src/main/resources/80/Bilder/flaschengeist.png"));
            ring = ImageIO.read(new File("src/main/resources/Bilder/80/ring.png"));
            spinne = ImageIO.read(new File("src/main/resources/Bilder/80/spinne.png"));
            fee = ImageIO.read(new File("src/main/resources/Bilder/80/fee.png"));
            karte = ImageIO.read(new File("src/main/resources/Bilder/80/karte.png"));
            drache = ImageIO.read(new File("src/main/resources/Bilder/80/Drache.png"));
            bibel = ImageIO.read(new File("src/main/resources/Bilder/80/bibel.png"));
            eidechse = ImageIO.read(new File("src/main/resources/Bilder/80/eidechse.png"));
            geldbeutel = ImageIO.read(new File("src/main/resources/Bilder/80/geldbeutel.png"));
            fledermaus = ImageIO.read(new File("src/main/resources/Bilder/80/fledermaus.png"));
            troll = ImageIO.read(new File("src/main/resources/Bilder/80/troll.png"));
            scarabaeus = ImageIO.read(new File("src/main/resources/Bilder/80/scarabaeus.png"));
            maus = ImageIO.read(new File("src/main/resources/Bilder/80/maus.png"));
            smaragd = ImageIO.read(new File("src/main/resources/Bilder/80/smaragd.png"));
            totenkopf = ImageIO.read(new File("src/main/resources/Bilder/80/totenkopf.png"));
            helm = ImageIO.read(new File("src/main/resources/Bilder/80/helm.png"));
            leuchter = ImageIO.read(new File("src/main/resources/Bilder/80/leuchter.png"));
            schmuckkasten = ImageIO.read(new File("src/main/resources/Bilder/80/schmuckkasten.png"));
            schluessel = ImageIO.read(new File("src/main/resources/Bilder/80/schluessel.png"));
            schwert = ImageIO.read(new File("src/main/resources/Bilder/80/schwert.png"));
            gespenst = ImageIO.read(new File("src/main/resources/Bilder/80/gespenst.png"));

        }
        catch (IOException e)
        {
            throw new RuntimeException("Kann Bild nicht laden.", e);

        }

    }

    private List openCards()
    {
        Map<PlayerType, PlayerCards> MapWtf = getGameModel().getPlayerCardsMap();

        PlayerCards openCards = MapWtf.get(PlayerType.WHITE);
        List<TreasureCard> open = openCards.getOpenCards();
        return open;

    }

    private BufferedImage getObersteCard(TreasureCard tC)
    {
        BufferedImage temp = null;
        if (tC == TreasureCard.EULE) temp = eule;

        if (tC == TreasureCard.KRONE) temp = krone;

        if (tC == TreasureCard.FLASCHENGEIST) temp = flaschengeist;

        if (tC == TreasureCard.RING) temp = ring;
        if (tC == TreasureCard.MOTTE) temp = motte;
        if (tC == TreasureCard.SPINNE) temp = spinne;
        if (tC == TreasureCard.FEE) temp = fee;
        if (tC == TreasureCard.KARTE) temp = karte;
        if (tC == TreasureCard.DRACHE) temp = drache;
        if (tC == TreasureCard.BIBEL) temp = bibel;
        if (tC == TreasureCard.EIDECHSE) temp = eidechse;
        if (tC == TreasureCard.GELDBEUTEL) temp = geldbeutel;
        if (tC == TreasureCard.FLEDERMAUS) temp = fledermaus;
        if (tC == TreasureCard.TROLL) temp = troll;
        if (tC == TreasureCard.SCARABAEUS) temp = scarabaeus;
        if (tC == TreasureCard.MAUS) temp = maus;
        if (tC == TreasureCard.SMARAGD) temp = smaragd;
        if (tC == TreasureCard.TOTENKOPF) temp = totenkopf;
        if (tC == TreasureCard.HELM) temp = helm;
        if (tC == TreasureCard.LEUCHTER) temp = leuchter;
        if (tC == TreasureCard.SCHMUCKKASTEN) temp = schmuckkasten;
        if (tC == TreasureCard.SCHLÜSSEL) temp = schluessel;
        if (tC == TreasureCard.SCHWERT) temp = schwert;
        if (tC == TreasureCard.GESPENST) temp = gespenst;
        return temp;
    }

    private TreasureCard hiddenCards()
    {
        Map<PlayerType, PlayerCards> MapWtf = getGameModel().getPlayerCardsMap();
        PlayerCards hiddenCards = MapWtf.get(PlayerType.WHITE);
        List<TreasureCard> hidden = hiddenCards.getHiddenCards();
        TreasureCard searchThisCard = hidden.get(0);
        return searchThisCard;
    }

    private BufferedImage getInsert()
    {
        BufferedImage temp = null;
        if (getGameModel().getInsertTile().isCross() == true) temp = cross;
        if (getGameModel().getInsertTile().isCurve1() == true) temp = curveOne;
        if (getGameModel().getInsertTile().isCurve2() == true) temp = curveTwo;
        if (getGameModel().getInsertTile().isCurve3() == true) temp = curveThree;
        if (getGameModel().getInsertTile().isCurve4() == true) temp = curveFour;
        if (getGameModel().getInsertTile().isHorizontal() == true) temp = horizontal;
        if (getGameModel().getInsertTile().isVertikal() == true) temp = vertikal;

        return temp;
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
        repaint();

    }
}
