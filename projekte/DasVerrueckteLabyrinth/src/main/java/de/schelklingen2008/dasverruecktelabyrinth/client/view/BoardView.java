package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import de.schelklingen2008.dasverruecktelabyrinth.client.Constants;
import de.schelklingen2008.dasverruecktelabyrinth.client.controller.Controller;
import de.schelklingen2008.dasverruecktelabyrinth.client.controller.GameChangeListener;
import de.schelklingen2008.dasverruecktelabyrinth.client.model.GameContext;
import de.schelklingen2008.dasverruecktelabyrinth.model.GameModel;
import de.schelklingen2008.dasverruecktelabyrinth.model.Player;
import de.schelklingen2008.dasverruecktelabyrinth.model.PlayerType;
import de.schelklingen2008.dasverruecktelabyrinth.model.Tile;

/**
 * Displays the main game interface (the board).
 */
public class BoardView extends JPanel implements GameChangeListener
{

    private Controller controller;

    /** position of cursor piece */
    private int        cx = 0;
    private int        cy = 0;

    // private int cy = 0;

    /**
     * Constructs a view which will initialize itself and prepare to display the game board.
     */
    public BoardView(Controller controller)
    {
        this.controller = controller;
        controller.addChangeListener(this);

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

    }

    private void pressed(MouseEvent e)
    {
        int tx = e.getX() / Constants.SPRITE_SIZE;
        int ty = e.getY() / Constants.SPRITE_SIZE;
        if (!isInBounds(tx, ty)) return;
        controller.boardClicked(tx, ty);
    }

    // private boolean isLegalMove(int tx, int ty)
    // {
    // return getGameModel().isLegalMove(tx, ty, getGameContext().getMyPlayer());
    // }

    private boolean isInBounds(int x, int y)
    {
        if (x < 0 || x >= GameModel.SIZE) return false;
        if (y < 0 || y >= GameModel.SIZE) return false;
        return true;
    }

    // public boolean isLegalMove(int x, int y)
    // {
    // return getGameModel().isLegalMove(tx, ty, getGameContext().getMyPlayer());
    // }

    @Override
    public Dimension getPreferredSize()
    {
        // TODO calculate correct dimensions for the board view
        return new Dimension(GameModel.SIZE * 80, GameModel.SIZE * 80);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        paintBackground(gfx);
        if (getGameModel() == null) return;
        paintBoard(gfx);
        paintTreasureCards(gfx);
        paintPlayer(gfx);
    }

    private void paintBackground(Graphics2D gfx)
    {
        gfx.setColor(Constants.COL_BOARD_BACKGROUND);
        gfx.fillRect(0, 0, getWidth(), getHeight());
    }

    private void paintBoard(Graphics2D gfx)
    {
        Tile[][] board = getGameModel().getBoard();
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                gfx.drawImage(Images.getTileImage(board[i][j]), i * 80, j * 80, null);
            }
        }

    }

    private void paintTreasureCards(Graphics2D gfx)
    {

        for (int i = 0; i < getGameModel().getBoard().length; i++)
        {
            for (int j = 0; j < getGameModel().getBoard().length; j++)
            {
                Tile[][] temp = getGameModel().getBoard();
                if (temp[i][j].getTC() != null)
                {
                    Tile t = temp[i][j];
                    int x = i * 80 + 20;
                    int y = j * 80 + 20;
                    gfx.drawImage(Images.getSmallTcImage(t.getTC()), x, y, null);
                }
            }
        }

    }

    public void paintPlayer(Graphics2D gfx)
    {
        getGameModel().getPlayers();
        for (Player player : getGameModel().getPlayers())
        {
            int x = player.getXKoordinate() * 80 + 40;
            int y = player.getYKoordinate() * 80 + 40;
            if (player.getPlayerType() == PlayerType.WHITE) gfx.setColor(Color.YELLOW);

            if (player.getPlayerType() == PlayerType.BLACK) gfx.setColor(Color.BLUE);
            if (player.getPlayerType() == PlayerType.GREEN) gfx.setColor(Color.GREEN);
            if (player.getPlayerType() == PlayerType.RED) gfx.setColor(Color.RED);
            gfx.fillOval(x, y, 15, 15);
            gfx.setColor(Color.BLACK);
            gfx.drawOval(x, y, 15, 15);

        }

    }

    public void gameChanged()
    {
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
}
