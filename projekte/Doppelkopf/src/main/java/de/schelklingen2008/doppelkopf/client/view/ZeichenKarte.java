package de.schelklingen2008.doppelkopf.client.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import de.schelklingen2008.doppelkopf.model.Farbe;
import de.schelklingen2008.doppelkopf.model.Karte;

public class ZeichenKarte
{

    Karte         karte;
    BufferedImage image;

    public ZeichenKarte(Karte karte)
    {
        this.karte = karte;
        String farbe;
        String bild;
        switch (karte.farbe)
        {
            case Farbe.Kreuz:

        }
        image = ImageIO.read(new File("src/main/resources/bilder/karten/75/back-blue-50-1.png"));
    }

    public void draw(Graphics g, int x, int y)
    {

    }
}
