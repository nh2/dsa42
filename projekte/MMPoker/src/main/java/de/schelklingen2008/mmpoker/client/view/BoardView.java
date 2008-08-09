package de.schelklingen2008.mmpoker.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import de.schelklingen2008.mmpoker.client.controller.Controller;
import de.schelklingen2008.mmpoker.client.controller.GameChangeListener;
import de.schelklingen2008.mmpoker.client.model.GameContext;
import de.schelklingen2008.mmpoker.model.GameModel;
import de.schelklingen2008.mmpoker.model.Kartentyp;
import de.schelklingen2008.mmpoker.model.Kartenwert;
import de.schelklingen2008.mmpoker.model.Spielstadien;

/**
 * Displays the main game interface (the board).
 */
public class BoardView extends JPanel implements GameChangeListener
{

    private Controller controller;

    BufferedImage[][]  cardImages = new BufferedImage[Kartentyp.values().length][Kartenwert.values().length];

    /**
     * Constructs a view which will initialize itself and prepare to display the game board.
     */
    public BoardView(Controller controller)
    {
        for (Kartentyp typ : Kartentyp.values())
        {
            for (Kartenwert wert : Kartenwert.values())
            {
                loadImage(typ, wert);
            }
        }

        this.controller = controller;
        controller.addChangeListener(this);

    }

    private void loadImage(Kartentyp typ, Kartenwert wert)
    {
        String filename = "75/" + typ.getImageName() + "-" + wert.getImageName() + "-75.png";

        try
        {
            BufferedImage image = ImageIO.read(new File(filename));
            cardImages[typ.ordinal()][wert.ordinal()] = image;
        }
        catch (IOException e)
        {
            throw new RuntimeException("Kann Bild " + filename + " nicht laden.");
        }
    }

    public void gemeinschaftsKarten(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        if (getGameModel().getSpielstadium() == Spielstadien.FLOP)
        {
            gfx.drawImage(
                          cardImages[getGameModel().getSpielfeld()[1].getKartentyp().ordinal()][getGameModel().getSpielfeld()[1].getKartenwert()
                                                                                                                                .ordinal()],
                          225, 350, null);
            gfx.drawImage(
                          cardImages[getGameModel().getSpielfeld()[2].getKartentyp().ordinal()][getGameModel().getSpielfeld()[2].getKartenwert()
                                                                                                                                .ordinal()],
                          305, 350, null);
            gfx.drawImage(
                          cardImages[getGameModel().getSpielfeld()[3].getKartentyp().ordinal()][getGameModel().getSpielfeld()[3].getKartenwert()
                                                                                                                                .ordinal()],
                          385, 350, null);
        }
        else
        {

        }

        // gfx.drawImage(cardImages[karte.getKartentyp().ordinal()][karte.getKartenwert().ordinal()],
        // (spielstadium.ordinal() + 1) * 80 + 145, 350, null);
    }

    public void handKarten(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        g.setColor(Color.black);
        if (getGameModel().getAmZug().isNochDabei())
        {
            // zeichne
            gfx.drawImage(
                          cardImages[getGameModel().getAmZug().getHandblatt()[0].getKartentyp().ordinal()][getGameModel().getAmZug()
                                                                                                                         .getHandblatt()[0].getKartenwert()
                                                                                                                                           .ordinal()],
                          385, 350, null);
            gfx.drawImage(
                          cardImages[getGameModel().getAmZug().getHandblatt()[1].getKartentyp().ordinal()][getGameModel().getAmZug()
                                                                                                                         .getHandblatt()[1].getKartenwert()
                                                                                                                                           .ordinal()],
                          385, 350, null);
        }
        else
        {
            // kartenrücken zeichnen
        }

    }

    @Override
    public void paintComponent(Graphics g)
    {

        Graphics2D gfx = (Graphics2D) g;
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 800, 800);
        handKarten(g);
        gemeinschaftsKarten(g);
        g.setColor(Color.BLACK);

        String pot = "$" + getGameModel().getPot();
        gfx.drawString(pot, 200, 250);

    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(800, 800);
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
