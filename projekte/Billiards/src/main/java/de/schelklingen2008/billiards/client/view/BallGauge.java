package de.schelklingen2008.billiards.client.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class BallGauge extends JPanel
{

    /**
     * 
     */
    private static final long serialVersionUID = -7217000556441927509L;

    private double value = 0d;

    public BallGauge()
    {
        setOpaque(false);
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D gfx = (Graphics2D) g;
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gfx.setColor(Color.BLACK);
        gfx.fillRect(0, (int) Math.round(getHeight() * (1 - value / 100d)), getWidth(), getHeight());
    }

}
