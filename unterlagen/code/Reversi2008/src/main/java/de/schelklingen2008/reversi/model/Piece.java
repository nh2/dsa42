package de.schelklingen2008.reversi.model;

/**
 * Is a simple data object for a piece corresponding to a location and a player.
 */
public class Piece
{

    private int    x;
    private int    y;
    private Player player;

    public Piece(Piece piece)
    {
        x = piece.x;
        y = piece.y;
        player = piece.player;
    }

    public Piece(int x, int y, Player player)
    {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Player getPlayer()
    {
        return player;
    }

    @Override
    public String toString()
    {
        return "[x=" + x + ", y=" + y + ", p=" + player + "]";
    }

}
