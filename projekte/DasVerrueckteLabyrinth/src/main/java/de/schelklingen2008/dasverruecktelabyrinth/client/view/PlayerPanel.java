package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

import de.schelklingen2008.dasverruecktelabyrinth.client.controller.Controller;
import de.schelklingen2008.dasverruecktelabyrinth.client.model.GameContext;
import de.schelklingen2008.dasverruecktelabyrinth.model.GameModel;

public class PlayerPanel extends JPanel
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

        drehButtons.setLayout(new BoxLayout(drehButtons, BoxLayout.PAGE_AXIS));
        drehButtons.add(new Button("rechts drehen"));
        drehButtons.add(new Button("links drehen"));

        JPanel insertTile = new JPanel();
        insertTile.setLayout(new BorderLayout());
        BufferedImage insert = getInsert();
        

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
}
