package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import de.schelklingen2008.dasverruecktelabyrinth.model.Tile;

public class TilePanel extends JPanel
{

    private Tile tile;

    public TilePanel(Tile tile)
    {
        this.tile = tile;
        setPreferredSize(new Dimension(80, 80));
        setMaximumSize(new Dimension(80, 80));
        setMinimumSize(new Dimension(80, 80));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        gfx.drawImage(Images.getTileImage(tile), 0, 0, null);
        gfx.drawImage(Images.getSmallTcImage(tile.getTC()), 25, 25, null);
    }

    public void setTile(Tile tile)
    {
        this.tile = tile;
        repaint();
    }
}
