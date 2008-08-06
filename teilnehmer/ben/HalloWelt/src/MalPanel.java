import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MalPanel extends JPanel
{

    private BufferedImage karte;

    public MalPanel()
    {
        try
        {
            karte = ImageIO.read(new File("Karten/clubs-8-150.png"));
        }
        catch (IOException e)
        {
            throw new RuntimeException("Kann Bild nicht laden.");
        }

        MouseMotionListener mml = new MouseMotionAdapter()
        {

            @Override
            public void mouseMoved(MouseEvent e)
            {
                getGraphics().drawImage(karte, e.getX(), e.getY(), null);
            }
        };

        addMouseMotionListener(mml);
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
        g.fillOval(100, 100, 10, 10);

        gfx.translate(50, 10);
        drawCards(gfx);
        gfx.translate(-50, -10);

        gfx.translate(10, 450);
        gfx.rotate(-Math.PI / 2);
        drawCards(gfx);
        gfx.rotate(Math.PI / 2);
        gfx.translate(-10, -450);
    }

    private void drawCards(Graphics2D gfx)
    {
        for (int i = 0; i < 11; i++)
        {
            gfx.drawImage(karte, i * 20, 0, null);
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new MalPanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
