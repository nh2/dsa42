package de.schelklingen2008.reversi.ai.tournament;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MatchPanel extends JPanel
{

    private static final Color COL_BACKGROUND = Color.GREEN;
    private static final Color COL_RESULT     = Color.BLACK;
    private static final int   WIDTH          = 100;
    private static final int   HEIGHT         = 100;

    private Match              model;

    public MatchPanel(Match model)
    {
        this.model = model;
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        paintBackground(gfx);
        paintResult(gfx);
    }

    private void paintResult(Graphics2D gfx)
    {
        gfx.setColor(COL_RESULT);
        gfx.drawString("Hallo", 0, 0);
    }

    private void paintBackground(Graphics2D gfx)
    {
        gfx.setColor(COL_BACKGROUND);
        gfx.fillRect(0, 0, WIDTH, HEIGHT);
    }
}
