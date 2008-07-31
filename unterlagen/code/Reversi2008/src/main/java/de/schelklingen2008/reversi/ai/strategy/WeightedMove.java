/**
 *
 */
package de.schelklingen2008.reversi.ai.strategy;

import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

class WeightedMove extends Piece implements Comparable<WeightedMove>
{

    private final int alpha;

    public WeightedMove(final int x, final int y, final Player player, final int alpha)
    {
        super(x, y, player);
        this.alpha = alpha;
    }

    public int getAlpha()
    {
        return alpha;
    }

    public int compareTo(final WeightedMove other)
    {
        return other.alpha - alpha;
    }

}