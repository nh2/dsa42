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

public class MalPanel extends JPanel {

    private BufferedImage karte;

    public MalPanel() {
        try {
            karte = ImageIO.read(new File("Karten/clubs-8-150.png"));
        }
        catch (IOException e) {
            throw new RuntimeException("Kann Bild nicht laden.");
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D gfx = (Graphics2D) g;
        g.setColor(Color.GREEN);
        // g.fillOval(50, 50, 10, 10);

        for (int i = 1; i < 11; i++) {
            gfx.drawImage(karte, i * 20, i * 20, 37, 53, null);
        }
    }

    public static void main(String[] args) {
        // JFrame frame = new JFrame();
        // frame.getContentPane().add(new MalPanel());
        // frame.pack();
        // frame.setVisible(true);
    }

}
