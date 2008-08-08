package carspack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class CarFrame extends JFrame
{

    BufferedImage fahrrad, autoAlt, autoNeu, Schlitten, LKW;
    private int   pos = 0;

    public CarFrame()
    {

        setVisible(true);
        pack();

        try
        {
            fahrrad = ImageIO.read(new File("./src/carspack/car.bmp"));
            autoAlt = ImageIO.read(new File("./src/carspack/AutoAlt.bmp"));
            autoNeu = ImageIO.read(new File("./src/carspack/AutoNeu.bmp"));

        }
        catch (IOException e)
        {
            throw new RuntimeException("Kann Bild nicht laden.");
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(1000, 1000);
    }

    public void setPos(int x)
    {
        pos = x;
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 1000);

        for (int i = -100; i < 1100; i++)
        {
            g.drawImage(fahrrad, i, 500, 100, 100, null);

            try
            {
                Thread.sleep(1L);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                throw new RuntimeException("kann nicht warten", e);
            }
            repaint();
        }

    }
}
