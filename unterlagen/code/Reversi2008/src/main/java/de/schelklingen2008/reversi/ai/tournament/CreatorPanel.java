package de.schelklingen2008.reversi.ai.tournament;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class CreatorPanel extends JPanel
{

    private static final Color COL_BACKGROUND = new Color(200, 200, 200);
    private static final Color COL_RESULT     = Color.WHITE;
    private static final Color COL_BLANK      = Color.GRAY;
    public static final int    WIDTH          = MatchPanel.WIDTH;
    public static final int    HEIGHT         = MatchPanel.HEIGHT;

    private String             creator;

    public CreatorPanel(String creator)
    {
        this.creator = creator;
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
        if (!isBlank()) paintResult(gfx);
    }

    private void paintResult(Graphics2D gfx)
    {
        gfx.setColor(COL_RESULT);

        Font boldFont = gfx.getFont().deriveFont(Font.BOLD);
        gfx.setFont(boldFont);
        Rectangle2D bounds = boldFont.getStringBounds(creator, gfx.getFontRenderContext());
        LineMetrics lm = boldFont.getLineMetrics(creator, gfx.getFontRenderContext());
        gfx.drawString(creator, (int) (WIDTH - bounds.getWidth()) / 2, HEIGHT / 2 - lm.getStrikethroughOffset());
    }

    private boolean isBlank()
    {
        return creator == null;
    }

    private void paintBackground(Graphics2D gfx)
    {
        gfx.setColor(isBlank() ? COL_BLANK : COL_BACKGROUND);
        gfx.fillRect(0, 0, WIDTH, HEIGHT);
        gfx.setColor(Color.BLACK);
        gfx.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
    }
}
