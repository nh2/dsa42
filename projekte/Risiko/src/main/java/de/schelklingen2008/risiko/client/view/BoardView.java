package de.schelklingen2008.risiko.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        try
        {
            map = ImageIO.read(new File("./src/main/resources/europa_karte_de.png"));
        }
        catch (IOException e)
        {
            throw new RuntimeException("Kann Bild nicht laden.");
        }
        ;

        addMouseListener(new MouseAdapter()
        {

            @Override
            public void mousePressed(MouseEvent e)
            {
                pressed(e);
            }
        });
    }

    private void pressed(MouseEvent e)
    {

        // getGameModel().setAllCountriesUnselected();
        // getGameModel().getCountrybyColor(c).setSelected(true);

        Color c = new Color(map.getRGB(e.getX(), e.getY()));

        if (getGameModel().isLegalMoveSet(getGameContext().getMyPlayer(), getGameModel().getCountryByColor(c)))
        {
            controller.placeUnit(getGameModel().getCountryByColor(c));
        }

        // else if(getGameModel().isLegalMoveAttack(getGameContext().getMyPlayer(), , null))

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

    public void gameChanged()
    {
        // System.out.println(getGameModel().getCountry(0).isSelected());

        repaint();
    }

    private GameContext getGameContext()
    {
        return controller.getGameContext();
    }

    private GameModel getGameModel()
    {
        return getGameContext().getGameModel();
    }

    private void paintBackground(Graphics2D gfx)
    {
        gfx.drawImage(map, 1, 1, 1200, 1000, null);
    }

    private void paintBoard(Graphics2D gfx)
    {
        if (getGameModel() == null) return;
        paintCountrys(gfx);
        paintUnits(gfx);
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
            gfx.setColor(Color.BLACK);
        }

    }

    private void paintUnits(Graphics2D gfx)
    {
        gfx.setColor(Color.BLACK);
        for (int i = 0; i < getGameModel().getCountryArray().length; i++)
        {
            int lUnits = getGameModel().getCountry(i).getUnits();
            gfx.drawString("" + lUnits + " " + getGameModel().getCountry(i).getOccupier().getPlayerName(),
                           getGameModel().getCountry(i).getPositionNameX(), getGameModel().getCountry(i)
                                                                                          .getPositionNameY() + 10);
        }
    }

}
