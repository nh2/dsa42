package de.schelklingen2008.doppelkopf.client.view;

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

import de.schelklingen2008.doppelkopf.client.controller.Controller;
import de.schelklingen2008.doppelkopf.client.controller.GameChangeListener;
import de.schelklingen2008.doppelkopf.client.model.GameContext;
import de.schelklingen2008.doppelkopf.model.GameModel;
import de.schelklingen2008.doppelkopf.model.SpielerListe;

/**
 * Displays the main game interface (the board).
 */
public class BoardView extends JPanel implements GameChangeListener // TODO Aspekt beim rezise neu berechnen
{

    private Controller    controller;

    private final Color   tischFarbe = Color.decode("#00008800");
    private BufferedImage rückseite;

    /**
     * Constructs a view which will initialize itself and prepare to display the game board.
     */
    public BoardView(Controller controller)
    {
        this.controller = controller;
        controller.addChangeListener(this);

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

        try
        {
            rückseite = ImageIO.read(new File("src/main/resources/bilder/karten/50/back-blue-50-1.png"));
        }
        catch (IOException e1)
        {
            System.err.println("Bild nicht gefunden.");
        }
    }

    private void moved(MouseEvent e)
    {
        // TODO respond to player�s mouse movements
    }

    private void pressed(MouseEvent e)
    {
        // TODO respond to player�s mouse clicks
    }

    @Override
    public Dimension getPreferredSize()
    {
        // TODO calculate correct dimensions for the board view
        return new Dimension(800, 600);
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
        gfx.setBackground(tischFarbe);
        gfx.clearRect(0, 0, getWidth(), getHeight());

        // Spielerbereiche zeichnen
        gfx.drawRect(250, 30, 300, 70); // Oberer Spielerbereich
        gfx.drawRect(30, 100, 70, 300); // Linker Spielerbereich
        gfx.drawRect(700, 100, 70, 300);// Rechter Spielerbereich
        gfx.drawRect(120, 420, 560, 150);// Unterer Spielerbereich

        SpielerListe spieler = controller.getGameContext().getGameModel().getSpielerListe(); // TODO vom
        // Server holen

        // Karten zeichnen
        // Oben
        gfx.translate(265, 30);
        for (int i = 0; i < spieler.get(2).blatt.getKartenanzahl(); i++)
            gfx.drawImage(rückseite, i * 20, 0, null);
        gfx.translate(-265, -30);

        // Links
        gfx.translate(30, 385);
        gfx.rotate(-Math.PI / 2d);
        for (int i = 0; i < spieler.get(1).blatt.getKartenanzahl(); i++)
            gfx.drawImage(rückseite, i * 20, 0, null);
        gfx.rotate(Math.PI / 2d);
        gfx.translate(-30, -435);

        // Rechts
        gfx.translate(770, 165);
        gfx.rotate(Math.PI / 2d);
        for (int i = 0; i < spieler.get(3).blatt.getKartenanzahl(); i++)
            gfx.drawImage(rückseite, i * 20, 0, null);
        gfx.rotate(-Math.PI / 2d);
        gfx.translate(-770, -165);

        // Unten
        gfx.translate(120, 470);
        for (int i = 0; i < spieler.get(0).blatt.getKartenanzahl(); i++)
            gfx.drawImage(rückseite, i * 20, 0, null);
        gfx.translate(-120, -420);
    }

    private void paintBoard(Graphics2D gfx)
    {
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
