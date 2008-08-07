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
import de.schelklingen2008.mmpoker.model.Spielkarte;
import de.schelklingen2008.mmpoker.model.Spielstadien;

/**
 * Displays the main game interface (the board).
 */
public class BoardView extends JPanel implements GameChangeListener {

    private Controller controller;

    BufferedImage[][]  cardImages = new BufferedImage[Kartentyp.values().length][Kartenwert.values().length];

    /**
     * Constructs a view which will initialize itself and prepare to display the game board.
     */
    public BoardView(Controller controller) {
        for (Kartentyp typ : Kartentyp.values()) {
            for (Kartenwert wert : Kartenwert.values()) {
                loadImage(typ, wert);
            }
        }

        this.controller = controller;
        controller.addChangeListener(this);
        //
        // addMouseMotionListener(new MouseMotionAdapter()
        // {
        // @Override
        // public void mouseMoved(MouseEvent e)
        // {
        // moved(e);
        // }
        // });
        //
        // addMouseListener(new MouseAdapter()
        // {
        // @Override
        // public void mousePressed(MouseEvent e)
        // {
        // pressed(e);
        // }
        // });
    }

    // private void moved(MouseEvent e)
    // {
    // // TODO respond to player´s mouse movements
    // }

    // private void pressed(MouseEvent e)
    // {
    // // TODO respond to player´s mouse clicks
    // }

    private void loadImage(Kartentyp typ, Kartenwert wert) {
        String filename = "75/" + typ.getImageName() + "-" + wert.getImageName() + "-75.png";

        try {
            BufferedImage image = ImageIO.read(new File(filename));
            cardImages[typ.ordinal()][wert.ordinal()] = image;
        }
        catch (IOException e) {
            throw new RuntimeException("Kann Bild " + filename + " nicht laden.");
        }
    }

    public void gemeinschaftsKarten(Graphics g, Spielkarte karte, Spielstadien spielstadium) {
        Graphics2D gfx = (Graphics2D) g;
        gfx.drawImage(cardImages[karte.getKartentyp().ordinal()][karte.getKartenwert().ordinal()],
                      (spielstadium.ordinal() + 1) * 80 + 145, 350, null);
    }

    public void handKarten(Graphics g, Spielkarte karte) {
        Graphics2D gfx = (Graphics2D) g;
        gfx.drawImage(cardImages[karte.getKartentyp().ordinal()][karte.getKartenwert().ordinal()], 420, 700, null);
        gfx.drawImage(cardImages[karte.getKartentyp().ordinal()][karte.getKartenwert().ordinal()], 350, 700, null);

    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D gfx = (Graphics2D) g;
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 800, 800);
        // gemeinschaftsKarten(g, getGameModel().getSpielfeld()[1], Spielstadien.FLOPE);
        // gemeinschaftsKarten(g, getGameModel().getSpielfeld()[2], Spielstadien.FLOPZ);
        // gemeinschaftsKarten(g, getGameModel().getSpielfeld()[3], Spielstadien.FLOPD);
        // gemeinschaftsKarten(g, getGameModel().getSpielfeld()[4], Spielstadien.TURN);
        // gemeinschaftsKarten(g, getGameModel().getSpielfeld()[5], Spielstadien.RIVER);
        // handKarten(g, getGameModel().getSpielfeld()[1]);
        g.setColor(Color.BLACK);
        String pot = "$" + getGameModel().getPot();
        gfx.drawString(pot, 200, 250);
        String spielername = getGameModel().getSpielerliste().get(0).getName();
        g.setColor(Color.BLACK);
        gfx.drawString(spielername, 200, 200);

        // gfx.drawImage(cardImages[Kartentyp.HERZ.ordinal()][Kartenwert.BUBE.ordinal()], 225, 350, null);
        // gfx.drawImage(cardImages[Kartentyp.HERZ.ordinal()][Kartenwert.BUBE.ordinal()], 305, 350, null);
        // Flop
        // gfx.drawImage(cardImages[Kartentyp.HERZ.ordinal()][Kartenwert.BUBE.ordinal()], 385, 350, null);
        // Flop
        // gfx.drawImage(cardImages[Kartentyp.HERZ.ordinal()][Kartenwert.BUBE.ordinal()], 465, 350, null);
        // Flop
        // gfx.drawImage(cardImages[Kartentyp.HERZ.ordinal()][Kartenwert.BUBE.ordinal()], 545, 350, null);
        // Turn
        // gfx.drawImage(cardImages[Kartentyp.HERZ.ordinal()][Kartenwert.BUBE.ordinal()], 385, 680, null);
        // River
        // gfx.drawImage(cardImages[Kartentyp.HERZ.ordinal()][Kartenwert.BUBE.ordinal()], 465, 680, null);
        // gfx.drawImage(cardImages[Kartentyp.HERZ.ordinal()][Kartenwert.BUBE.ordinal()], 20, 350, null);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    }

    public void gameChanged() {
        repaint();
    }

    private GameModel getGameModel() {
        return getGameContext().getGameModel();
    }

    private GameContext getGameContext() {
        return controller.getGameContext();
    }
}
