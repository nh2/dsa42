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
    private int   pos = 0;

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
        g.drawImage(auto, pos, 500, 100, 100, null);

    }

}
