package de.schelklingen2008.doppelkopf.client.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.schelklingen2008.doppelkopf.model.Karte;

public class ZeichenKarte
{

    Karte         karte;
    BufferedImage image;

    public ZeichenKarte(Karte karte)
    {
        this.karte = karte;
        StringBuffer filename = new StringBuffer("src/main/resources/bilder/karten/75/");
        switch (karte.farbe)
        {
            case Kreuz:		filename.append("cross"); break;
            case Pik:		filename.append("spades"); break;
            case Herz:		filename.append("hearts"); break;
            case Karo:		filename.append("diamonds"); break;
        }
        filename.append("-");
        switch (karte.bild)
        {
            case As:		filename.append("??"); break;
            case Zehn:		filename.append("??"); break;
            case Koenig:	filename.append("??"); break;
            case Dame:		filename.append("??"); break;
            case Bube:		filename.append("??"); break;
            case Neun:		filename.append("??"); break;
        }
        filename.append("-50-??.png");
        try {
			image = ImageIO.read(new File(filename.toString()));
		} catch (IOException e) {
			System.err.println("Fehler beim Laden der Datei " + filename.toString());
		}
    }

    public void draw(Graphics g, int x, int y)
    {
    	g.drawImage(image, x, y, null);
    }
}
