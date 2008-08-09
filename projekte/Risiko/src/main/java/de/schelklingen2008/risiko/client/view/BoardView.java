package de.schelklingen2008.risiko.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import de.schelklingen2008.risiko.client.controller.Controller;
import de.schelklingen2008.risiko.client.controller.GameChangeListener;
import de.schelklingen2008.risiko.client.model.GameContext;
import de.schelklingen2008.risiko.model.GameModel;
import de.schelklingen2008.util.LoggerFactory;

/**
 * Displays the main game interface (the board).
 */
public class BoardView extends JPanel implements GameChangeListener
{

    private Controller    controller;
    private BufferedImage map;
    private final Logger  logger = LoggerFactory.create();

    /**
     * Constructs a view which will initialize itself and prepare to display the game board.
     */
    public BoardView(Controller controller)
    {
        this.controller = controller;
        controller.addChangeListener(this);
        logger.info("fjd11111");
        try
        {
            map = ImageIO.read(new File("./src/main/resources/europa_karte_de.png"));
        }
        catch (IOException e)
        {
            throw new RuntimeException("Kann Bild nicht laden.");
        }

        addMouseMotionListener(new MouseMotionAdapter()
        {

            @Override
            public void mouseMoved(MouseEvent e)
            {
                moved(e);
            }
        });

        addMouseListener(new MouseAdapter()
        {

            @Override
            public void mousePressed(MouseEvent e)
            {
                pressed(e);
            }
        });
    }

    private void moved(MouseEvent e)
    {
        // TODO respond to player´s mouse movements
    }

    private void pressed(MouseEvent e)
    {
        logger.info("fjd11111");
        getGameModel().setAllCountriesUnselected();
        Color c = new Color(map.getRGB(e.getX(), e.getY()));
        getGameModel().getCountrybyColor(c).setSelected(true);
        /*
         * if (getGameContext().getMyPlayer().getUnitsToSet() != 0) { logger.info("fjd"); if
         * (getGameModel().getCountrybyColor(c).getOccupier().equals(getGameContext().getMyPlayer())) {
         * controller.placeUnit(getGameModel().getCountrybyColor(c));
         * getGameContext().getMyPlayer().setUnits(getGameContext().getMyPlayer().getUnitsToSet() - 1); } }
         * else { controller.setMine(getGameModel().getCountrybyColor(c),
         * getGameContext().getMyPlayer().getPlayerIndex());
         * getGameContext().getMyPlayer().setUnits(getGameContext().getMyPlayer().getUnitsToSet() + 1); }
         */

        repaint();
    }

    @Override
    public Dimension getPreferredSize()
    {
        // TODO calculate correct dimensions for the board view
        return new Dimension(1200, 1000);

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        paintBackground(gfx);
        paintBoard(gfx);

    }

    private void paintBackground(Graphics2D gfx)
    {
        gfx.drawImage(map, 1, 1, 1200, 1000, null);
    }

    private void paintBoard(Graphics2D gfx)
    {
        paintCountrys(gfx);
        paintUnits(gfx);
    }

    public void gameChanged()
    {
        // System.out.println(getGameModel().getCountry(0).isSelected());

        repaint();
    }

    private GameModel getGameModel()
    {
        return getGameContext().getGameModel();
    }

    private GameContext getGameContext()
    {
        return controller.getGameContext();
    }

    private void paintCountrys(Graphics2D gfx)
    {

        for (int j = 0; j < getGameModel().getCountryArray().length; j++)
        {
            if (getGameModel().getCountry(j).isSelected())
            {
                gfx.setColor(Color.WHITE);
            }
            else
            {
                gfx.setColor(Color.BLACK);
            }
            gfx.drawString(getGameModel().getCountry(j).getName(), getGameModel().getCountry(j).getPositionNameX(),
                           getGameModel().getCountry(j).getPositionNameY());
            // gfx.drawString("Großbritanien", 210, 538);
        }

    }

    private void paintUnits(Graphics2D gfx)
    {

        for (int i = 0; i < getGameModel().getCountryArray().length; i++)
        {
            int lUnits = getGameModel().getCountry(i).getUnits();
            gfx.drawString("" + lUnits, getGameModel().getCountry(i).getPositionNameX(),
                           getGameModel().getCountry(i).getPositionNameY() + 10);
        }
    }

}
