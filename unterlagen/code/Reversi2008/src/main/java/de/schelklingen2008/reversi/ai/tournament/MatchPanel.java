package de.schelklingen2008.reversi.ai.tournament;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

public class MatchPanel extends JPanel
{

    private static final Color COL_BACKGROUND = new Color(0, 100, 0);
    private static final Color COL_RESULT     = Color.WHITE;
    private static final Color COL_BLANK      = Color.GRAY;
    private static final int   WIDTH          = 100;
    private static final int   HEIGHT         = 100;

    private List<Match>        matchList;

    public MatchPanel(List<Match> matches)
    {
        matchList = matches;
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

        int white = 0;
        int black = 0;
        for (Match match : matchList)
        {
            white += match.getPointsWhite();
            black += match.getPointsBlack();
        }
        Font boldFont = gfx.getFont().deriveFont(Font.BOLD);
        gfx.setFont(boldFont);
        gfx.drawString(white + " : " + black, 42, 50);
    }

    private boolean isBlank()
    {
        return matchList == null;
    }

    private void paintBackground(Graphics2D gfx)
    {
        gfx.setColor(isBlank() ? COL_BLANK : COL_BACKGROUND);
        gfx.fillRect(0, 0, WIDTH, HEIGHT);
        gfx.setColor(Color.BLACK);
        gfx.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
    }
}
