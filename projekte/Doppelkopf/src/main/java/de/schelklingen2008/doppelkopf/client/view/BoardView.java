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
public class BoardView extends JPanel implements GameChangeListener
{

    private Controller        controller;

    private final Color       tischFarbe   = Color.decode("#00008800");
    private Set<ZeichenKarte> karten       = new HashSet<ZeichenKarte>();
    private BufferedImage     rueckseite;

    private final int         mx           = 400;
    private final int         my           = 300;

    Mittenplatz[]             mittenKarten = { new Mittenplatz(mx - 37, my - 10), new Mittenplatz(mx - 65, my - 40),
            new Mittenplatz(mx - 37, my - 97), new Mittenplatz(mx - 10, my - 60), };

    GameModel                 spiel;
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

        // if (spiel.getTisch() == null) return; // Wenn die Spieler noch nicht vom Server geladen
        // if (spiel.getTisch().getStichAnzahl() == 0) return;
        // sind

        // for (Farbe f : Farbe.values())
        // for (Bild b : Bild.values())
        // karten.add(new ZeichenKarte(new Karte(f, b)));

        // int result = JOptionPane.showConfirmDialog(this, "Hallo Wellt");
        // if (result == JOptionPane.YES_OPTION) System.out.println("Jo");
    }

    private void moved(MouseEvent e)
    {
    }

    private void pressed(MouseEvent e)
    {
        int startx = 140;
        int starty = 463;
        int kartenhoehe = 107;
        int alleKartenBreite = (ich.getBlatt().getKartenanzahl() - 1) * 40 + 75;

        int clickx = e.getX();
        int clicky = e.getY();

        // Nur fortfahren, wenn im Bereich der Karten
        if (clicky < starty || clicky > starty + kartenhoehe) return;
        if (clickx < startx || clickx > startx + alleKartenBreite) return;

        // Klickpunkt relativ zum Anfang der Karten setzen
        clickx -= startx;
        clicky -= starty;

        int karteVonLinks = clickx / 40;
        // Von der letzten Karte ist mehr sicht- und klickbar
        if (karteVonLinks >= ich.getBlatt().getKartenanzahl()) karteVonLinks = ich.getBlatt().getKartenanzahl() - 1;
        Karte klickKarte = ich.getBlatt().getKartenSortiert().get(karteVonLinks);

        boolean gueltig = true;
        controller.karteClicked(klickKarte);

        // String gueltigMeldung = "Zug ist ";
        // if (!gueltig) gueltigMeldung += "nicht ";
        // gueltigMeldung += "gültig.";
        // JOptionPane.showMessageDialog(this, "Karte "
        // + klickKarte
        // + ": "
        // + karteVonLinks
        // + ": ( "
        // + clickx
        // + " | "
        // + clicky
        // + " ) "
        // + gueltigMeldung
        // + " Karte ist trumpf: "
        // + klickKarte.isTrumpf());
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        paintBackground(gfx);
        if (getGameModel() == null) return;
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
            if (inhalt == null) return;
            g.drawImage(inhalt.image, x, y, null);
        }
    }

    private void paintBoard(Graphics2D gfx)
    {
        if (spiel == null) return;
        if (spiel.getTisch() == null) return; // Wenn die Spieler noch nicht vom
        if (spiel.getSpieler().size() == 0) return; // Server geladen

        // Spielernamen zeichnen
        // Bis zum aktuellen Spieler vorrÃ¼cken (mir)
        while (tempListe.getAnDerReihe() != ich)
            tempListe.rotieren();

        gfx.setColor(Color.white);
        int namenspositionen[][] = { { 135, 451 }, { 30, 103 }, { 265, 18 }, { 700, 103 } };

        for (int i = 0; i < 4; i++)
        {
            Spieler zeichenSpieler = tempListe.getAnDerReihe();
            String name = "";
            if (zeichenSpieler == spiel.getSpieler().getAnDerReihe()) name += "* ";
            name += zeichenSpieler.getName();
            int gesamtpunkte = 0;
            for (int p : zeichenSpieler.rundenpunkte)
                gesamtpunkte += p;
            name += gesamtpunkte;
            gfx.drawString(name, namenspositionen[i][0], namenspositionen[i][1]);
            tempListe.rotieren();
        }

        // Bis zum aktuellen Spieler vorruecken (mir)
        while (tempListe.getAnDerReihe() != ich)
            tempListe.rotieren();

        // Karten zeichnen

        // Unten (eigene)
        {
            gfx.translate(140, 463);
            ZeichenKarte zk;
            int i = 0;
            for (Karte k : tempListe.getAnDerReihe().getBlatt().getKartenSortiert())
            {
                zk = new ZeichenKarte(k);
                zk.draw(gfx, i * 40, 0);
                i++;
            }
            tempListe.rotieren();
            gfx.translate(-140, -463);
        }

        // Links
        gfx.translate(30, 385);
        gfx.rotate(-Math.PI / 2d);
        for (int i = 0; i < tempListe.getAnDerReihe().getBlatt().getKartenanzahl(); i++)
            gfx.drawImage(rueckseite, i * 20, 0, null);
        tempListe.rotieren();
        gfx.rotate(Math.PI / 2d);
        gfx.translate(-30, -385);

        // Oben
        gfx.translate(265, 30);
        for (int i = 0; i < tempListe.getAnDerReihe().getBlatt().getKartenanzahl(); i++)
            gfx.drawImage(rueckseite, i * 20, 0, null);
        tempListe.rotieren();
        gfx.translate(-265, -30);

        // Rechts
        gfx.translate(770, 115);
        gfx.rotate(Math.PI / 2d);
        for (int i = 0; i < tempListe.getAnDerReihe().getBlatt().getKartenanzahl(); i++)
            gfx.drawImage(rueckseite, i * 20, 0, null);
        tempListe.rotieren();
        gfx.rotate(-Math.PI / 2d);
        gfx.translate(-770, -115);

        // Karten in der Mitte
        for (Mittenplatz mp : mittenKarten)
            mp.draw(gfx);

    }

    public void gameChanged()
    {
        spiel = getGameContext().getGameModel();

        tempListe.clear();
        tempListe.addAll(spiel.getSpieler());
        ich = tempListe.getSpieler(getGameContext().getMyName());

        List<Karte> l = new ArrayList<Karte>(tempListe.getAnDerReihe().getBlatt().getKarten());
        List<Karte> mitte = spiel.getTisch().getMitte();

        {
            int i = 0;
            for (; i < mitte.size(); i++)
                mittenKarten[i].inhalt = new ZeichenKarte(mitte.get(i));
            for (; i < mittenKarten.length; i++)
                mittenKarten[i].inhalt = null;
            mitte.clear();
        }
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
