package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class InsertPanel extends JPanel
{

    private BufferedImage curveOne, curveTwo, curveThree, curveFour;
    private BufferedImage cross, horizontal, vertikal;
    private BufferedImage eule, krone, flaschengeist, ring, motte, spinne;
    private BufferedImage fee, karte, drache, bibel, eidechse, geldbeutel, fledermaus;
    private BufferedImage troll, scarabaeus, maus, smaragd, totenkopf, helm, leuchter;
    private BufferedImage schmuckkasten, schluessel, schwert, gespenst;

    public InsertPanel(Graphics g)
    {
        super();

        try
        {
            eule = ImageIO.read(new File("src/main/resources/Bilder/80/eule.png"));
            krone = ImageIO.read(new File("src/main/resources/Bilder/80/krone.png"));
            flaschengeist = ImageIO.read(new File("src/main/resources/Bilder/80/flaschengeist.png"));
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
            curveOne = ImageIO.read(new File("src/main/resources/TilesBilder/curveOne.png"));
            curveTwo = ImageIO.read(new File("src/main/resources/TilesBilder/curveTwo.png"));
            curveThree = ImageIO.read(new File("src/main/resources/TilesBilder/curveThree.png"));
            curveFour = ImageIO.read(new File("src/main/resources/TilesBilder/curveFour.png"));
            horizontal = ImageIO.read(new File("src/main/resources/TilesBilder/horizontal.png"));
            vertikal = ImageIO.read(new File("src/main/resources/TilesBilder/vertikal.png"));
            cross = ImageIO.read(new File("src/main/resources/TilesBilder/Cross.png"));
        }
        catch (IOException e)
        {
            throw new RuntimeException("Kann Bild nicht laden.", e);
        }

        g.drawImage();
    }
}
