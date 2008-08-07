package de.schelklingen2008.billiards.client.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import de.schelklingen2008.billiards.client.controller.Controller;
import de.schelklingen2008.billiards.model.GameModel;

public class BallGauge extends JPanel
{

    private Controller controller = null;
    private double     value;

    public BallGauge(Controller controller)
    {
        this.controller = controller;
        setOpaque(false);
    }

    private BallGauge()
    {

    }

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D gfx = (Graphics2D) g;
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GameModel gameModel = controller.getGameContext().getGameModel();

        // TODO value
        value = 42;

        gfx.setColor(Color.BLACK);
        gfx.fillRect(0, (int) Math.round(getHeight() * (1 - value / 100d)), getWidth(), getHeight());
    }

}
