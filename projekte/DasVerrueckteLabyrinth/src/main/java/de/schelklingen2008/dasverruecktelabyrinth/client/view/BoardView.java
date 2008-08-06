package de.schelklingen2008.dasverruecktelabyrinth.client.view;

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
import de.schelklingen2008.dasverruecktelabyrinth.model.Tile;

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
            eule = ImageIO.read(new File("src/main/resources/TilesBilder/eule.png"));
            krone = ImageIO.read(new File("src/main/resources/TilesBilder/krone.png"));
            flaschengeist = ImageIO.read(new File("src/main/resources/TilesBilder/flaschengeist.png"));
            ring = ImageIO.read(new File("src/main/resources/TilesBilder/ring.png"));
            motte = ImageIO.read(new File("src/main/resources/TilesBilder/motte.png"));
            spinne = ImageIO.read(new File("src/main/resources/TilesBilder/spinne.png"));
            fee = ImageIO.read(new File("src/main/resources/TilesBilder/fee.png"));
            karte = ImageIO.read(new File("src/main/resources/TilesBilder/karte.png"));
            drache = ImageIO.read(new File("src/main/resources/TilesBilder/Drache.png"));
            bibel = ImageIO.read(new File("src/main/resources/TilesBilder/bibel.png"));
            eidechse = ImageIO.read(new File("src/main/resources/TilesBilder/eidechse.png"));
            geldbeutel = ImageIO.read(new File("src/main/resources/TilesBilder/geldbeutel.png"));
            fledermaus = ImageIO.read(new File("src/main/resources/TilesBilder/fledermaus.png"));
            troll = ImageIO.read(new File("src/main/resources/TilesBilder/troll.png"));
            scarabaeus = ImageIO.read(new File("src/main/resources/TilesBilder/scarabaeus.png"));
            maus = ImageIO.read(new File("src/main/resources/TilesBilder/maus.png"));
            smaragd = ImageIO.read(new File("src/main/resources/TilesBilder/smaragd.png"));
            totenkopf = ImageIO.read(new File("src/main/resources/TilesBilder/totenkopf.png"));
            helm = ImageIO.read(new File("src/main/resources/TilesBilder/helm.png"));
            leuchter = ImageIO.read(new File("src/main/resources/TilesBilder/leuchter.png"));
            schmuckkasten = ImageIO.read(new File("src/main/resources/TilesBilder/schmuckkasten.png"));
            schluessel = ImageIO.read(new File("src/main/resources/TilesBilder/schluessel.png"));
            schwert = ImageIO.read(new File("src/main/resources/TilesBilder/schwert.png"));
            gespenst = ImageIO.read(new File("src/main/resources/TilesBilder/gespenst.png"));

        }
        catch (IOException e)
        {
            throw new RuntimeException("Kann Bild nicht laden.");
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
        int tx = e.getX() / Constants.SPRITE_SIZE;
        int ty = e.getY() / Constants.SPRITE_SIZE;
        if (!isInBounds(tx, ty)) return;
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

    @Override
    public Dimension getPreferredSize()
    {
        // TODO calculate correct dimensions for the board view
        return new Dimension(0, 0);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        // TODO do proper painting of game state
        paintBackground(gfx);
        paintBoard(gfx);
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
                if (temp[i][j].isCross() == true)
                {
                    gfx.drawImage(cross, i * 80, j * 80, null);
                }
                if (temp[i][j].isHorizontal() == true)
                {
                    gfx.drawImage(horizontal, i * 80, j * 80, null);
                }
                if (temp[i][j].isVertikal() == true)
                {
                    gfx.drawImage(vertikal, i * 80, j * 80, null);
                }
                if (temp[i][j].isCurve1() == true)
                {
                    gfx.drawImage(curveOne, i * 80, j * 80, null);
                }
                if (temp[i][j].isCurve2() == true)
                {
                    gfx.drawImage(curveTwo, i * 80, j * 80, null);
                }
                if (temp[i][j].isCurve3() == true)
                {
                    gfx.drawImage(curveThree, i * 80, j * 80, null);
                }
                if (temp[i][j].isCurve4() == true)
                {
                    gfx.drawImage(curveFour, i * 80, j * 80, null);
                }
            }
        }

    }

    private void paintTreasureCards()
    {

        for (int i = 0; i < getGameModel().getBoard().length; i++)
            ;

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
