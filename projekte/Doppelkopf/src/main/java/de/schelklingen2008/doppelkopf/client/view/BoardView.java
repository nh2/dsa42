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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    private final int         mx         = 400;
    private final int         my         = 300;

    Mittenplatz               mpUnten    = new Mittenplatz(mx - 37, my - 10);
    Mittenplatz               mpLinks    = new Mittenplatz(mx - 65, my - 40);
    Mittenplatz               mpOben     = new Mittenplatz(mx - 37, my - 97);
    Mittenplatz               mpRechts   = new Mittenplatz(mx - 10, my - 60);

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

        tempListe = new SpielerListe();
        // tempListe.addAll(controller.getGameContext().getGameModel().getSpielerListe());
        GameModel spiel = new GameModel();
        tempListe.addAll(spiel.getTisch().getSpieler());
        // ich = tempListe.getSpieler(controller.getGameContext().getMyName());
        ich = tempListe.getSpieler("Spieler 1");

        List<Karte> l = new ArrayList<Karte>(tempListe.getAnDerReihe().getBlatt().getKarten());
        List<Karte> mitte = spiel.getTisch().getMitte();
        mitte.add(l.get(0));
        mitte.add(l.get(1));
        mitte.add(l.get(2));
        mitte.add(l.get(3));
        tempListe.getAnDerReihe().getBlatt().getKarten().remove(l.get(0));
        mpUnten.inhalt = new ZeichenKarte(mitte.get(3));
        mpLinks.inhalt = new ZeichenKarte(mitte.get(2));
        mpOben.inhalt = new ZeichenKarte(mitte.get(1));
        mpRechts.inhalt = new ZeichenKarte(mitte.get(0));
        mitte.clear();

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
        gfx.setColor(Color.decode("#00007700"));
        gfx.drawRect(130, 458, 535, 117);// Unterer Spielerbereich
        gfx.drawRect(25, 110, 80, 280); // Linker Spielerbereich
        gfx.drawRect(260, 25, 280, 80); // Oberer Spielerbereich
        gfx.drawRect(695, 110, 80, 280);// Rechter Spielerbereich
        gfx.setColor(Color.black);

        // Spielernamen zeichnen
        // Bis zum aktuellen Spieler vorrücken (mir)
        while (tempListe.getAnDerReihe() != ich)
            tempListe.rotieren();

        gfx.setColor(Color.white);
        gfx.drawString(tempListe.getAnDerReihe().getName(), 135, 451);
        gfx.drawString(tempListe.next().getName(), 30, 103);
        gfx.drawString(tempListe.next().getName(), 265, 18);
        gfx.drawString(tempListe.next().getName(), 700, 103);
    }

    private class Mittenplatz
    {

        private int         x;
        private int         y;
        public ZeichenKarte inhalt;

        public Mittenplatz(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public void draw(Graphics g)
        {
            g.drawImage(inhalt.image, x, y, null);
        }
    }

    private void paintBoard(Graphics2D gfx)
    {
        // Bis zum aktuellen Spieler vorruecken (mir)
        while (tempListe.getAnDerReihe() != ich)
            tempListe.rotieren();

        // Karten zeichnen

        // Unten (eigene)
        {
            gfx.translate(140, 463);
            ZeichenKarte zk;
            int i = 0;
            for (Karte k : tempListe.getAnDerReihe().getBlatt().getKarten())
            {
                zk = new ZeichenKarte(k);
                zk.draw(gfx, i * 40, 0);
                i++;
            }
            gfx.translate(-140, -463);
        }

        // Links
        gfx.translate(30, 385);
        gfx.rotate(-Math.PI / 2d);
        for (int i = 0; i < tempListe.next().getBlatt().getKartenanzahl(); i++)
            gfx.drawImage(rueckseite, i * 20, 0, null);
        gfx.rotate(Math.PI / 2d);
        gfx.translate(-30, -385);

        // Oben
        gfx.translate(265, 30);
        for (int i = 0; i < tempListe.next().getBlatt().getKartenanzahl(); i++)
            gfx.drawImage(rueckseite, i * 20, 0, null);
        gfx.translate(-265, -30);

        // Rechts
        gfx.translate(770, 115);
        gfx.rotate(Math.PI / 2d);
        for (int i = 0; i < tempListe.next().getBlatt().getKartenanzahl(); i++)
            gfx.drawImage(rueckseite, i * 20, 0, null);
        gfx.rotate(-Math.PI / 2d);
        gfx.translate(-770, -115);

        // Karten in der Mitte

        mpUnten.draw(gfx);
        mpLinks.draw(gfx);
        mpOben.draw(gfx);
        mpRechts.draw(gfx);

        // gfx.translate(400, 250);
        // gfx.fillRect(0, 0, 30, 70);
        // gfx.rotate(Math.PI / 2d);
        // gfx.fillRect(0, 0, 30, 70);
        // gfx.translate(400, 250);
        // gfx.fillOval(395, 295, 10, 10);
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
