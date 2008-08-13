package de.schelklingen2008.reversi.client.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import de.schelklingen2008.reversi.client.Constants;
import de.schelklingen2008.reversi.client.controller.Controller;
import de.schelklingen2008.reversi.client.controller.GameChangeListener;
import de.schelklingen2008.reversi.client.model.GameContext;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Player;

/**
 * Displays the main game interface (the board).
 */
public class BoardView extends JPanel implements GameChangeListener
{

    private static final AlphaComposite TRANSPARENCY_50_PERCENT = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                                                                                             0.5f);

    private Controller                  controller;

    /** position of cursor piece */
    private int                         cx                      = 0;
    private int                         cy                      = 0;

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
        int tx = e.getX() / Constants.SPRITE_SIZE;
        int ty = e.getY() / Constants.SPRITE_SIZE;
        if (!isInBounds(tx, ty)) return;
        if (tx == cx && ty == cy) return;

        cx = tx;
        cy = ty;
        repaint();
    }

    private boolean isLegalMove(int tx, int ty)
    {
        return getGameModel().isLegalMove(tx, ty, getGameContext().getMyPlayer());
    }

    private void pressed(MouseEvent e)
    {
        int tx = e.getX() / Constants.SPRITE_SIZE;
        int ty = e.getY() / Constants.SPRITE_SIZE;
        if (!isInBounds(tx, ty)) return;
        if (isLegalMove(tx, ty)) controller.boardClicked(tx, ty);
    }

    private boolean isInBounds(int x, int y)
    {
        if (x < 0 || x >= GameModel.SIZE) return false;
        if (y < 0 || y >= GameModel.SIZE) return false;
        return true;
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(GameModel.SIZE * Constants.SPRITE_SIZE + 1, GameModel.SIZE * Constants.SPRITE_SIZE + 1);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        paintBackground(gfx);
        paintBoard(gfx);
        paintPieces(gfx);
        paintPossibleMoves(gfx);
        paintCursor(gfx);
    }

    private void paintPossibleMoves(Graphics2D gfx)
    {
        if (getGameContext().getMyPlayer() != getGameModel().getTurnHolder()) return;

        boolean[][] legalMoves = getGameModel().getLegalMoves(getGameModel().getTurnHolder());
        for (int y = 0; y < GameModel.SIZE; y++)
        {
            for (int x = 0; x < GameModel.SIZE; x++)
            {
                if (legalMoves[x][y])
                {
                    Color myColor = Color.LIGHT_GRAY;
                    paintPiece(gfx, x, y, myColor, true);
                }
            }
        }

    }

    private void paintBackground(Graphics2D gfx)
    {
        gfx.setColor(Constants.COL_BOARD_BACKGROUND);
        gfx.fillRect(0, 0, getWidth(), getHeight());
    }

    private void paintBoard(Graphics2D gfx)
    {
        gfx.setColor(Constants.COL_BOARD_GRID);
        for (int y = 0; y <= GameModel.SIZE; y++)
        {
            int ypos = y * Constants.SPRITE_SIZE;
            gfx.drawLine(0, ypos, Constants.SPRITE_SIZE * GameModel.SIZE, ypos);
        }
        for (int x = 0; x <= GameModel.SIZE; x++)
        {
            int xpos = x * Constants.SPRITE_SIZE;
            gfx.drawLine(xpos, 0, xpos, Constants.SPRITE_SIZE * GameModel.SIZE);
        }
    }

    private void paintPieces(Graphics2D gfx)
    {
        for (int y = 0; y < GameModel.SIZE; y++)
        {
            for (int x = 0; x < GameModel.SIZE; x++)
            {
                if (!getGameModel().isOccupied(x, y)) continue;
                Player player = getGameModel().getPlayer(x, y);
                Color color = Constants.COL_PIECE.get(player);
                paintPiece(gfx, x, y, color, false);
            }
        }
    }

    private void paintCursor(Graphics2D gfx)
    {
        if (!getGameContext().isMyTurn()) return;
        if (!isLegalMove(cx, cy)) return;

        Color myColor = Constants.COL_PIECE.get(getGameContext().getMyPlayer());
        paintPiece(gfx, cx, cy, myColor, true);
    }

    public void paintPiece(Graphics2D gfx, int x, int y, Color color, boolean transparent)
    {
        Composite original = gfx.getComposite();
        if (transparent) gfx.setComposite(TRANSPARENCY_50_PERCENT);

        int px = x * Constants.SPRITE_SIZE + Constants.INSETS_PIECE;
        int py = y * Constants.SPRITE_SIZE + Constants.INSETS_PIECE;
        int pwid = Constants.SPRITE_SIZE - 2 * Constants.INSETS_PIECE;
        int phei = Constants.SPRITE_SIZE - 2 * Constants.INSETS_PIECE;

        gfx.setColor(color);
        gfx.fillOval(px, py, pwid + 1, phei + 1);
        gfx.setColor(Constants.COL_PIECE_BORDER);
        gfx.drawOval(px, py, pwid, phei);

        if (transparent) gfx.setComposite(original);
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
