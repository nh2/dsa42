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

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (player == null ? 0 : player.hashCode());
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Piece other = (Piece) obj;
        if (player == null)
        {
            if (other.player != null) return false;
        }
        else if (!player.equals(other.player)) return false;
        if (x != other.x) return false;
        if (y != other.y) return false;
        return true;
    }

}
