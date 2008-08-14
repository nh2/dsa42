package de.schelklingen2008.reversi.ai.evaluation;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class EvaluationFunctionDadaLena implements EvaluationFunction
{

    public int evaluatePosition(GameModel gameModel, Player player, Piece move)
    {
        int eval;
        eval = gameModel.countPieces(player);
        if (isEdge(move))
        {
            // eval = eval + 1;
        }
        else
        {
            if (isNeighbourOfCorner(move))
            {
                eval = eval - 1;
            }
            else
            {
                if (isCorner(move))
                {
                    // eval = eval + 1;// TODO
                }
            }
        }
        return eval;
    }

    private boolean isNeighbourOfCorner(Piece move)
    {
        if (move.getX() == 1)
        {
            if (move.getY() == 2 || move.getY() == 7 || move.getY() == 8)
            {
                return true;
            }
            else
                return false;
        }
        else
        {
            if (move.getX() == 2)
            {
                if (move.getY() == 1 || move.getY() == 7 || move.getY() == 8)
                {
                    return true;
                }
                else
                    return false;
            }
            else
            {
                if (move.getX() == 7)
                {
                    if (move.getY() == 1 || move.getY() == 2 || move.getY() == 8)
                    {
                        return true;
                    }
                    else
                        return false;
                }
                else
                {
                    if (move.getX() == 8)
                    {
                        if (move.getY() == 2 || move.getY() == 7 || move.getY() == 1)
                        {
                            return true;
                        }
                        else
                            return false;
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }
    }

    private boolean isCorner(Piece move)
    {
        if (move.getX() == 1 || move.getX() == 8)
        {
            if (move.getY() == 1 || move.getY() == 8)
            {
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    private boolean isEdge(Piece move)
    {
        if (move.getX() == 1 || move.getX() == 8 || move.getY() == 1 || move.getY() == 8)
        {
            return true;
        }
        else
            return false;
    }
}
