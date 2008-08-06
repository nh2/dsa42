package de.schelklingen2008.dasverruecktelabyrinth.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestPanel extends JPanel
{

    private BufferedImage krone;

    public TestPanel()
    {

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JPanel Board = new JPanel();
        Board.setLayout(new BoxLayout(Board, BoxLayout.PAGE_AXIS));

        try
        {
            krone = ImageIO.read(new File("src/main/resources/Bilder/Koenigskrone.jpg"));
        }
        catch (IOException e)
        {
            throw new RuntimeException("Kann Bild nicht laden.");
        }

    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(500, 500);
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillOval(50, 50, 10, 10);

        for (int i = 1; i < 11; i++)
        {
            gfx.drawImage(krone, i * 20, i * 20, null);
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new TestPanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
