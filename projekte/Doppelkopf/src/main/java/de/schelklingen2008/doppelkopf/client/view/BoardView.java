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
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import de.schelklingen2008.doppelkopf.client.controller.Controller;
import de.schelklingen2008.doppelkopf.client.controller.GameChangeListener;
import de.schelklingen2008.doppelkopf.client.model.GameContext;
import de.schelklingen2008.doppelkopf.model.GameModel;
import de.schelklingen2008.doppelkopf.model.Karte;
import de.schelklingen2008.doppelkopf.model.Spieler;
import de.schelklingen2008.doppelkopf.model.SpielerListe;

/**
 * Displays the main game interface (the board).
 */
public class BoardView extends JPanel implements GameChangeListener // TODO Aspekt beim rezise neu berechnen
{

    private Controller        controller;

    private final Color       tischFarbe = Color.decode("#00008800");
    private Set<ZeichenKarte> karten     = new HashSet<ZeichenKarte>();
    private BufferedImage     rueckseite;

    SpielerListe              tempListe;
    Spieler                   ich;

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
            rueckseite = ImageIO.read(new File("src/main/resources/bilder/karten/50/back-blue-50-1.png"));
        }
        catch (IOException e1)
        {
            System.err.println("Bild nicht gefunden.");
        }

        tempListe.addAll(controller.getGameContext().getGameModel().getSpielerListe());
        ich = tempListe.getSpieler(controller.getGameContext().getMyName());
        // for (Farbe f : Farbe.values())
        // for (Bild b : Bild.values())
        // karten.add(new ZeichenKarte(new Karte(f, b)));
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

        // Bis zum aktuellen Spieler vorrücken (mir)
        while (tempListe.getAnDerReihe() != ich)
            tempListe.rotieren();

        // Karten zeichnen

        // Unten (eigene)
        {
            gfx.translate(120, 470);
            ZeichenKarte zk;
            int i = 0;
            for (Karte k : tempListe.getAnDerReihe().blatt.getKarten())
            {
                zk = new ZeichenKarte(k);
                zk.draw(gfx, i * 20, 0);
            }
            gfx.translate(-120, -420);
        }

        // Links
        gfx.translate(30, 385);
        gfx.rotate(-Math.PI / 2d);
        for (int i = 0; i < tempListe.Next().blatt.getKartenanzahl(); i++)
            gfx.drawImage(rueckseite, i * 20, 0, null);
        gfx.rotate(Math.PI / 2d);
        gfx.translate(-30, -435);

        // Oben
        gfx.translate(265, 30);
        for (int i = 0; i < tempListe.Next().blatt.getKartenanzahl(); i++)
            gfx.drawImage(rueckseite, i * 20, 0, null);
        gfx.translate(-265, -30);

        // Rechts
        gfx.translate(770, 165);
        gfx.rotate(Math.PI / 2d);
        for (int i = 0; i < tempListe.Next().blatt.getKartenanzahl(); i++)
            gfx.drawImage(rueckseite, i * 20, 0, null);
        gfx.rotate(-Math.PI / 2d);
        gfx.translate(-770, -165);

        // Karten in der Mitte
        gfx.translate(400, 250);
        gfx.fillRect(0, 0, 30, 70);
        gfx.rotate(Math.PI / 2d);
        gfx.fillRect(0, 0, 30, 70);
        gfx.translate(400, 250);
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
