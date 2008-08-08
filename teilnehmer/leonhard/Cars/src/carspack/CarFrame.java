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

    BufferedImage auto;

    public CarFrame()
    {

        setVisible(true);
        pack();
        try
        {
            auto = ImageIO.read(new File("./src/carspack/car.bmp"));
        }
        catch (IOException e)
        {
            throw new RuntimeException("Kann Bild nicht laden.");
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args)
    {

        CarFrame f = new CarFrame();
        f.repaint();

    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(1000, 1000);
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 1000);
        for (int i = -200; i < 1200; i += 20)
        {
            g.drawImage(auto, i, 500, 100, 100, null);

            // auto = ImageIO.read("./src/carspack/auto.bmp");

        }
        repaint();

    }

}
