package de.schelklingen2008.jipbo.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class CardPanel extends JPanel
{

    private int     mN;
    private boolean mIsBig;
    private boolean mRotate;
    private boolean mBorder;

    public CardPanel(int pN, boolean pIsBig, boolean pRotate)
    {
        mN = pN;
        mIsBig = pIsBig;
        mRotate = pRotate;
        mBorder = false;
    }

    public void setValue(int pN)
    {
        mN = pN;
        repaint();
    }

    public int getValue()
    {
        return mN;
    }

    public boolean isMIsBig()
    {
        return mIsBig;
    }

    public boolean isMRotate()
    {
        return mRotate;
    }

    public void setBorder(boolean pEnable)
    {
        mBorder = pEnable;
        if (mBorder)
        {
            setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        else
        {
            setBorder(BorderFactory.createEmptyBorder());
        }
    }

    @Override
    public Dimension getPreferredSize()
    {
        if (mIsBig)
        {
            return new Dimension(63 + 24, 130);
        }
        else
        {
            return new Dimension(50 + 12, 130);
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;

        if (mN != -2)
        {
            if (mRotate) gfx.rotate(-Math.PI / 24);

            if (mIsBig)
            {
                gfx.drawImage(drawCard(mN), 6, 15, 63, 100, null);
            }
            else
            {
                gfx.drawImage(drawCard(mN), 6, 25, 50, 80, null);
            }

            if (mRotate) gfx.rotate(Math.PI / 24);
        }
        else
        {
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

    }

    private BufferedImage drawCard(int pN)
    {
        if (pN < -1 && pN > 12) throw new IllegalArgumentException("The number must be between 0 and 12. (BoardView))");
        BufferedImage card;
        try
        {
            String imgName;

            if (pN == -1)
            {
                imgName = "cover";
            }
            else if (pN == 0)
            {
                imgName = "joker";
            }
            else
            {
                imgName = "jipbo_big_" + pN;
            }
            card = ImageIO.read(new File("src/main/resources/cards/" + imgName + ".png"));
        }
        catch (IOException e)
        {
            throw new RuntimeException("Image " + pN + " not found.");
        }

        return card;
    }
}
