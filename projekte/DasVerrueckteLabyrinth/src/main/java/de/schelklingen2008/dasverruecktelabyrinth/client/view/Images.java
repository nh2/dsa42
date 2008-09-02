package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.schelklingen2008.dasverruecktelabyrinth.model.Tile;
import de.schelklingen2008.dasverruecktelabyrinth.model.TreasureCard;

public class Images
{

    public final static BufferedImage curveOne, curveTwo, curveThree, curveFour;
    public final static BufferedImage cross, horizontal, vertikal;
    public final static BufferedImage eule, krone, flaschengeist, ring, motte, spinne;
    public final static BufferedImage fee, karte, drache, bibel, eidechse, geldbeutel, fledermaus;
    public final static BufferedImage troll, scarabaeus, maus, smaragd, totenkopf, helm, leuchter;
    public final static BufferedImage schmuckkasten, schluessel, schwert, gespenst;

    public final static BufferedImage eule80, krone80, flaschengeist80, ring80, motte80, spinne80;
    public final static BufferedImage fee80, karte80, drache80, bibel80, eidechse80, geldbeutel80, fledermaus80;
    public final static BufferedImage troll80, scarabaeus80, maus80, smaragd80, totenkopf80, helm80, leuchter80;
    public final static BufferedImage schmuckkasten80, schluessel80, schwert80, gespenst80;

    static
    {
        try
        {

            curveOne = ImageIO.read(new File("src/main/resources/TilesBilderKrakeligAberSchoen/curveOne.png"));
            curveTwo = ImageIO.read(new File("src/main/resources/TilesBilderKrakeligAberSchoen/curveTwo.png"));
            curveThree = ImageIO.read(new File("src/main/resources/TilesBilderKrakeligAberSchoen/curveThree.png"));
            curveFour = ImageIO.read(new File("src/main/resources/TilesBilderKrakeligAberSchoen/curveFour.png"));
            horizontal = ImageIO.read(new File("src/main/resources/TilesBilderKrakeligAberSchoen/horizontal.png"));
            vertikal = ImageIO.read(new File("src/main/resources/TilesBilderKrakeligAberSchoen/vertikal.png"));
            cross = ImageIO.read(new File("src/main/resources/TilesBilderKrakeligAberSchoen/Cross.png"));

            // curveOne = ImageIO.read(new File("src/main/resources/TilesBilder/curveOne.png"));
            // curveTwo = ImageIO.read(new File("src/main/resources/TilesBilder/curveTwo.png"));
            // curveThree = ImageIO.read(new File("src/main/resources/TilesBilder/curveThree.png"));
            // curveFour = ImageIO.read(new File("src/main/resources/TilesBilder/curveFour.png"));
            // horizontal = ImageIO.read(new File("src/main/resources/TilesBilder/horizontal.png"));
            // vertikal = ImageIO.read(new File("src/main/resources/TilesBilder/vertikal.png"));
            // cross = ImageIO.read(new File("src/main/resources/TilesBilder/Cross.png"));

            // curveOne = ImageIO.read(new File("src/main/resources/TilesBilder/Curve1.tif"));
            // curveTwo = ImageIO.read(new File("src/main/resources/TilesBilder/Curve2.tif"));
            // curveThree = ImageIO.read(new File("src/main/resources/TilesBilder/Curve3.tif"));
            // curveFour = ImageIO.read(new File("src/main/resources/TilesBilder/Curve4.tif"));
            // horizontal = ImageIO.read(new File("src/main/resources/TilesBilder/Horizontal.tif"));
            // vertikal = ImageIO.read(new File("src/main/resources/TilesBilder/Vertikal.tif"));
            // cross = ImageIO.read(new File("src/main/resources/TilesBilder/Crossing.tif"));

            eule = ImageIO.read(new File("src/main/resources/Bilder/eule.png"));
            krone = ImageIO.read(new File("src/main/resources/Bilder/krone.png"));
            flaschengeist = ImageIO.read(new File("src/main/resources/Bilder/flaschengeist.png"));
            ring = ImageIO.read(new File("src/main/resources/Bilder/ring.png"));
            spinne = ImageIO.read(new File("src/main/resources/Bilder/spinne.png"));
            fee = ImageIO.read(new File("src/main/resources/Bilder/fee.png"));
            karte = ImageIO.read(new File("src/main/resources/Bilder/karte.png"));
            drache = ImageIO.read(new File("src/main/resources/Bilder/Drache.png"));
            bibel = ImageIO.read(new File("src/main/resources/Bilder/bibel.png"));
            eidechse = ImageIO.read(new File("src/main/resources/Bilder/eidechse.png"));
            geldbeutel = ImageIO.read(new File("src/main/resources/Bilder/geldbeutel.png"));
            fledermaus = ImageIO.read(new File("src/main/resources/Bilder/fledermaus.png"));
            troll = ImageIO.read(new File("src/main/resources/Bilder/troll.png"));
            scarabaeus = ImageIO.read(new File("src/main/resources/Bilder/scarabaeus.png"));
            maus = ImageIO.read(new File("src/main/resources/Bilder/maus.png"));
            motte = ImageIO.read(new File("src/main/resources/Bilder/motte.png"));
            smaragd = ImageIO.read(new File("src/main/resources/Bilder/smaragd.png"));
            totenkopf = ImageIO.read(new File("src/main/resources/Bilder/totenkopf.png"));
            helm = ImageIO.read(new File("src/main/resources/Bilder/helm.png"));
            leuchter = ImageIO.read(new File("src/main/resources/Bilder/leuchter.png"));
            schmuckkasten = ImageIO.read(new File("src/main/resources/Bilder/schmuckkasten.png"));
            schluessel = ImageIO.read(new File("src/main/resources/Bilder/schluessel.png"));
            schwert = ImageIO.read(new File("src/main/resources/Bilder/schwert.png"));
            gespenst = ImageIO.read(new File("src/main/resources/Bilder/gespenst.png"));

            eule80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/eule80.png"));
            krone80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/krone80.png"));
            flaschengeist80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/flaschengeist80.png"));
            ring80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/ring80.png"));
            spinne80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/spinne80.png"));
            fee80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/fee80.png"));
            karte80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/karte80.png"));
            drache80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/drache80.png"));
            bibel80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/bibel80.png"));
            eidechse80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/eidechse80.png"));
            geldbeutel80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/geldbeutel80.png"));
            fledermaus80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/fledermaus80.png"));
            troll80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/troll80.png"));
            scarabaeus80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/scarabaeus80.png"));
            maus80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/maus80.png"));
            motte80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/motte80.png"));
            smaragd80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/smaragd80.png"));
            totenkopf80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/totenkopf80.png"));
            helm80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/helm80.png"));
            leuchter80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/leuchter80.png"));
            schmuckkasten80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/schmuckkasten80.png"));
            schluessel80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/schluessel80.png"));
            schwert80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/schwert80.png"));
            gespenst80 = ImageIO.read(new File("src/main/resources/BilderGross/tcBIG/gespenst80.png"));
        }
        catch (IOException e)
        {
            throw new RuntimeException("Kann Bild nicht laden.", e);
        }
    }

    private Images()
    {
        // TODO Auto-generated constructor stub
    }

    public static BufferedImage getSmallTcImage(TreasureCard tc)
    {
        if (tc == TreasureCard.EULE) return eule;
        if (tc == TreasureCard.KRONE) return krone;
        if (tc == TreasureCard.FLASCHENGEIST) return flaschengeist;
        if (tc == TreasureCard.RING) return ring;
        if (tc == TreasureCard.MOTTE) return motte;
        if (tc == TreasureCard.SPINNE) return spinne;
        if (tc == TreasureCard.FEE) return fee;
        if (tc == TreasureCard.KARTE) return karte;
        if (tc == TreasureCard.DRACHE) return drache;
        if (tc == TreasureCard.BIBEL) return bibel;
        if (tc == TreasureCard.EIDECHSE) return eidechse;
        if (tc == TreasureCard.GELDBEUTEL) return geldbeutel;
        if (tc == TreasureCard.FLEDERMAUS) return fledermaus;
        if (tc == TreasureCard.TROLL) return troll;
        if (tc == TreasureCard.SCARABAEUS) return scarabaeus;
        if (tc == TreasureCard.MAUS) return maus;
        if (tc == TreasureCard.MOTTE) return motte;
        if (tc == TreasureCard.SMARAGD) return smaragd;
        if (tc == TreasureCard.TOTENKOPF) return totenkopf;
        if (tc == TreasureCard.HELM) return helm;
        if (tc == TreasureCard.LEUCHTER) return leuchter;
        if (tc == TreasureCard.SCHMUCKKASTEN) return schmuckkasten;
        if (tc == TreasureCard.SCHLUESSEL) return schluessel;
        if (tc == TreasureCard.SCHWERT) return schwert;
        if (tc == TreasureCard.GESPENST) return gespenst;
        throw new IllegalStateException("Unbekanntes kleines Bild " + tc);
    }

    public static BufferedImage getBigTcImage(TreasureCard tc)
    {
        if (tc == TreasureCard.EULE) return eule80;
        if (tc == TreasureCard.KRONE) return krone80;
        if (tc == TreasureCard.FLASCHENGEIST) return flaschengeist80;
        if (tc == TreasureCard.RING) return ring80;
        if (tc == TreasureCard.MOTTE) return motte80;
        if (tc == TreasureCard.SPINNE) return spinne80;
        if (tc == TreasureCard.FEE) return fee80;
        if (tc == TreasureCard.KARTE) return karte80;
        if (tc == TreasureCard.DRACHE) return drache80;
        if (tc == TreasureCard.BIBEL) return bibel80;
        if (tc == TreasureCard.EIDECHSE) return eidechse80;
        if (tc == TreasureCard.GELDBEUTEL) return geldbeutel80;
        if (tc == TreasureCard.FLEDERMAUS) return fledermaus80;
        if (tc == TreasureCard.TROLL) return troll80;
        if (tc == TreasureCard.SCARABAEUS) return scarabaeus80;
        if (tc == TreasureCard.MAUS) return maus80;
        if (tc == TreasureCard.MOTTE) return motte80;
        if (tc == TreasureCard.SMARAGD) return smaragd80;
        if (tc == TreasureCard.TOTENKOPF) return totenkopf80;
        if (tc == TreasureCard.HELM) return helm80;
        if (tc == TreasureCard.LEUCHTER) return leuchter80;
        if (tc == TreasureCard.SCHMUCKKASTEN) return schmuckkasten80;
        if (tc == TreasureCard.SCHLUESSEL) return schluessel80;
        if (tc == TreasureCard.SCHWERT) return schwert80;
        if (tc == TreasureCard.GESPENST) return gespenst80;
        throw new IllegalStateException("Unbekanntes groﬂes Bild " + tc);
    }

    public static final BufferedImage getTileImage(Tile tile)
    {
        if (tile.isCross() == true) return Images.cross;
        if (tile.isHorizontal() == true) return Images.horizontal;
        if (tile.isVertical() == true) return Images.vertikal;
        if (tile.isCurve1() == true) return Images.curveOne;
        if (tile.isCurve2() == true) return Images.curveTwo;
        if (tile.isCurve3() == true) return Images.curveThree;
        if (tile.isCurve4() == true) return Images.curveFour;
        throw new IllegalStateException("Unbekanntes Tile" + tile);
    }
}
