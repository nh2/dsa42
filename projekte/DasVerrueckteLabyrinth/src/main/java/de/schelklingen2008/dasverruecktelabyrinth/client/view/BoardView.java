package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import de.schelklingen2008.dasverruecktelabyrinth.client.Constants;
import de.schelklingen2008.dasverruecktelabyrinth.client.controller.Controller;
import de.schelklingen2008.dasverruecktelabyrinth.client.controller.GameChangeListener;
import de.schelklingen2008.dasverruecktelabyrinth.client.model.GameContext;
import de.schelklingen2008.dasverruecktelabyrinth.model.GameModel;
import de.schelklingen2008.dasverruecktelabyrinth.model.Player;
import de.schelklingen2008.dasverruecktelabyrinth.model.PlayerType;
import de.schelklingen2008.dasverruecktelabyrinth.model.Tile;
import de.schelklingen2008.dasverruecktelabyrinth.model.TreasureCard;

/**
 * Displays the main game interface (the board).
 */
public class BoardView extends JPanel implements GameChangeListener
{

    private Controller controller;

    /** position of cursor piece */
    private int        cx = 0;
    private int        cy = 0;

    // Images

    BufferedImage      curveOne, curveTwo, curveThree, curveFour;
    BufferedImage      cross, horizontal, vertikal;
    BufferedImage      eule, krone, flaschengeist, ring, motte, spinne;
    BufferedImage      fee, karte, drache, bibel, eidechse, geldbeutel, fledermaus;
    BufferedImage      troll, scarabaeus, maus, smaragd, totenkopf, helm, leuchter;
    BufferedImage      schmuckkasten, schluessel, schwert, gespenst;

    // private int cy = 0;

    /**
     * Constructs a view which will initialize itself and prepare to display the game board.
     */
    public BoardView(Controller controller)
    {
        this.controller = controller;
        controller.addChangeListener(this);

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
            smaragd = ImageIO.read(new File("src/main/resources/Bilder/smaragd.png"));
            totenkopf = ImageIO.read(new File("src/main/resources/Bilder/totenkopf.png"));
            helm = ImageIO.read(new File("src/main/resources/Bilder/helm.png"));
            leuchter = ImageIO.read(new File("src/main/resources/Bilder/leuchter.png"));
            schmuckkasten = ImageIO.read(new File("src/main/resources/Bilder/schmuckkasten.png"));
            schluessel = ImageIO.read(new File("src/main/resources/Bilder/schluessel.png"));
            schwert = ImageIO.read(new File("src/main/resources/Bilder/schwert.png"));
            gespenst = ImageIO.read(new File("src/main/resources/Bilder/gespenst.png"));

        }
        catch (IOException e)
        {
            throw new RuntimeException("Kann Bild nicht laden.", e);
        }

        addMouseMotionListener(new MouseMotionAdapter()
        {

            @Override
            public void mouseMoved(MouseEvent e)
            {
                moved(e);
            }
        });

        addMouseListener(new MouseAdapter()
        {

            @Override
            public void mousePressed(MouseEvent e)
            {
                pressed(e);
            }
        });

    }

    private void moved(MouseEvent e)
    {

    }

    private void pressed(MouseEvent e)
    {
        // int tx = e.getX() / Constants.SPRITE_SIZE;
        // int ty = e.getY() / Constants.SPRITE_SIZE;
        // if (!isInBounds(tx, ty)) return;
        // if (isLegalMove(tx, ty)) controller.boardClicked(tx, ty);
    }

    // private boolean isLegalMove(int tx, int ty)
    // {
    // return getGameModel().isLegalMove(tx, ty, getGameContext().getMyPlayer());
    // }

    private boolean isInBounds(int x, int y)
    {
        if (x < 0 || x >= GameModel.SIZE) return false;
        if (y < 0 || y >= GameModel.SIZE) return false;
        return true;
    }

    // public boolean isLegalMove(int x, int y)
    // {
    // return getGameModel().isLegalMove(tx, ty, getGameContext().getMyPlayer());
    // }

    @Override
    public Dimension getPreferredSize()
    {
        // TODO calculate correct dimensions for the board view
        return new Dimension(getGameModel().getBoard().length * 80, getGameModel().getBoard().length * 80);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        // TODO do proper painting of game state
        paintBackground(gfx);
        paintBoard(gfx);
        paintTreasureCards(gfx);
        paintPlayer(gfx);
    }

    private void paintBackground(Graphics2D gfx)
    {
        gfx.setColor(Constants.COL_BOARD_BACKGROUND);
        gfx.fillRect(0, 0, getWidth(), getHeight());
    }

    private void paintBoard(Graphics2D gfx)
    {
        for (int i = 0; i < getGameModel().getBoard().length; i++)
        {
            for (int j = 0; j < getGameModel().getBoard().length; j++)
            {
                Tile[][] temp = getGameModel().getBoard();
                if (temp[i][j].isCross() == true) gfx.drawImage(cross, i * 80, j * 80, null);
                if (temp[i][j].isHorizontal() == true) gfx.drawImage(horizontal, i * 80, j * 80, null);
                if (temp[i][j].isVertikal() == true) gfx.drawImage(vertikal, i * 80, j * 80, null);
                if (temp[i][j].isCurve1() == true) gfx.drawImage(curveOne, i * 80, j * 80, null);
                if (temp[i][j].isCurve2() == true) gfx.drawImage(curveTwo, i * 80, j * 80, null);
                if (temp[i][j].isCurve3() == true) gfx.drawImage(curveThree, i * 80, j * 80, null);
                if (temp[i][j].isCurve4() == true) gfx.drawImage(curveFour, i * 80, j * 80, null);
            }
        }

    }

    private void paintTreasureCards(Graphics2D gfx)
    {

        for (int i = 0; i < getGameModel().getBoard().length; i++)
        {
            for (int j = 0; j < getGameModel().getBoard().length; j++)
            {
                Tile[][] temp = getGameModel().getBoard();
                if (temp[i][j].getTC() != null)
                {
                    Tile t = temp[i][j];
                    int x = i * 80 + 20;
                    int y = j * 80 + 20;
                    if (t.getTC() == TreasureCard.EULE) gfx.drawImage(eule, x, y, null);

                    if (t.getTC() == TreasureCard.KRONE) gfx.drawImage(krone, x, y, null);

                    if (t.getTC() == TreasureCard.FLASCHENGEIST) gfx.drawImage(flaschengeist, x, y, null);

                    if (t.getTC() == TreasureCard.RING) gfx.drawImage(ring, x, y, null);
                    if (t.getTC() == TreasureCard.MOTTE) gfx.drawImage(motte, x, y, null);
                    if (t.getTC() == TreasureCard.SPINNE) gfx.drawImage(spinne, x, y, null);
                    if (t.getTC() == TreasureCard.FEE) gfx.drawImage(fee, x, y, null);
                    if (t.getTC() == TreasureCard.KARTE) gfx.drawImage(karte, x, y, null);
                    if (t.getTC() == TreasureCard.DRACHE) gfx.drawImage(drache, x, y, null);
                    if (t.getTC() == TreasureCard.BIBEL) gfx.drawImage(bibel, x, y, null);
                    if (t.getTC() == TreasureCard.EIDECHSE) gfx.drawImage(eidechse, x, y, null);
                    if (t.getTC() == TreasureCard.GELDBEUTEL) gfx.drawImage(geldbeutel, x, y, null);
                    if (t.getTC() == TreasureCard.FLEDERMAUS) gfx.drawImage(fledermaus, x, y, null);
                    if (t.getTC() == TreasureCard.TROLL) gfx.drawImage(troll, x, y, null);
                    if (t.getTC() == TreasureCard.SCARABAEUS) gfx.drawImage(scarabaeus, x, y, null);
                    if (t.getTC() == TreasureCard.MAUS) gfx.drawImage(maus, x, y, null);
                    if (t.getTC() == TreasureCard.SMARAGD) gfx.drawImage(smaragd, x, y, null);
                    if (t.getTC() == TreasureCard.TOTENKOPF) gfx.drawImage(totenkopf, x, y, null);
                    if (t.getTC() == TreasureCard.HELM) gfx.drawImage(helm, x, y, null);
                    if (t.getTC() == TreasureCard.LEUCHTER) gfx.drawImage(leuchter, x, y, null);
                    if (t.getTC() == TreasureCard.SCHMUCKKASTEN) gfx.drawImage(schmuckkasten, x, y, null);
                    if (t.getTC() == TreasureCard.SCHLÜSSEL) gfx.drawImage(schluessel, x, y, null);
                    if (t.getTC() == TreasureCard.SCHWERT) gfx.drawImage(schwert, x, y, null);
                    if (t.getTC() == TreasureCard.GESPENST) gfx.drawImage(gespenst, x, y, null);
                }
            }
        }

    }

    public void paintPlayer(Graphics2D gfx)
    {
        getGameModel().getPlayers();
        for (Player player : getGameModel().getPlayers())
        {
            int x = player.getXKoordinate() * 80 + 40;
            int y = player.getYKoordinate() * 80 + 40;
            if (player.getPlayerType() == PlayerType.WHITE) gfx.setColor(Color.YELLOW);

            if (player.getPlayerType() == PlayerType.BLACK) gfx.setColor(Color.BLUE);
            if (player.getPlayerType() == PlayerType.GREEN) gfx.setColor(Color.GREEN);
            if (player.getPlayerType() == PlayerType.RED) gfx.setColor(Color.RED);
            gfx.fillOval(x, y, 15, 15);
            gfx.setColor(Color.BLACK);
            gfx.drawOval(x, y, 15, 15);

        }

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
