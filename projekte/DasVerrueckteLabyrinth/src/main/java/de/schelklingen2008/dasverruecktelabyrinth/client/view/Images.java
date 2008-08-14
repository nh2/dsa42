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
            curveOne = ImageIO.read(new File("src/main/resources/TilesBilder/curveOne.png"));
            curveTwo = ImageIO.read(new File("src/main/resources/TilesBilder/curveTwo.png"));
            curveThree = ImageIO.read(new File("src/main/resources/TilesBilder/curveThree.png"));
            curveFour = ImageIO.read(new File("src/main/resources/TilesBilder/curveFour.png"));
            horizontal = ImageIO.read(new File("src/main/resources/TilesBilder/horizontal.png"));
            vertikal = ImageIO.read(new File("src/main/resources/TilesBilder/vertikal.png"));
            cross = ImageIO.read(new File("src/main/resources/TilesBilder/Cross.png"));

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

            eule80 = ImageIO.read(new File("src/main/resources/Bilder/80/eule.png"));
            krone80 = ImageIO.read(new File("src/main/resources/Bilder/80/krone.png"));
            flaschengeist80 = ImageIO.read(new File("src/main/resources/Bilder/80/flaschengeist.png"));
            ring80 = ImageIO.read(new File("src/main/resources/Bilder/80/ring.png"));
            spinne80 = ImageIO.read(new File("src/main/resources/Bilder/80/spinne.png"));
            fee80 = ImageIO.read(new File("src/main/resources/Bilder/80/fee.png"));
            karte80 = ImageIO.read(new File("src/main/resources/Bilder/80/karte.png"));
            drache80 = ImageIO.read(new File("src/main/resources/Bilder/80/Drache.png"));
            bibel80 = ImageIO.read(new File("src/main/resources/Bilder/80/bibel.png"));
            eidechse80 = ImageIO.read(new File("src/main/resources/Bilder/80/eidechse.png"));
            geldbeutel80 = ImageIO.read(new File("src/main/resources/Bilder/80/geldbeutel.png"));
            fledermaus80 = ImageIO.read(new File("src/main/resources/Bilder/80/fledermaus.png"));
            troll80 = ImageIO.read(new File("src/main/resources/Bilder/80/troll.png"));
            scarabaeus80 = ImageIO.read(new File("src/main/resources/Bilder/80/scarabaeus.png"));
            maus80 = ImageIO.read(new File("src/main/resources/Bilder/80/maus.png"));
            motte80 = ImageIO.read(new File("src/main/resources/Bilder/80/motte.png"));
            smaragd80 = ImageIO.read(new File("src/main/resources/Bilder/80/smaragd.png"));
            totenkopf80 = ImageIO.read(new File("src/main/resources/Bilder/80/totenkopf.png"));
            helm80 = ImageIO.read(new File("src/main/resources/Bilder/80/helm.png"));
            leuchter80 = ImageIO.read(new File("src/main/resources/Bilder/80/leuchter.png"));
            schmuckkasten80 = ImageIO.read(new File("src/main/resources/Bilder/80/schmuckkasten.png"));
            schluessel80 = ImageIO.read(new File("src/main/resources/Bilder/80/schluessel.png"));
            schwert80 = ImageIO.read(new File("src/main/resources/Bilder/80/schwert.png"));
            gespenst80 = ImageIO.read(new File("src/main/resources/Bilder/80/gespenst.png"));
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
        if (tc == TreasureCard.SCHL‹SSEL) return schluessel;
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
        if (tc == TreasureCard.SCHL‹SSEL) return schluessel80;
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
