package de.schelklingen2008.reversi.ai.strategy;

import java.util.ArrayList;
import java.util.List;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.ai.evaluation.HorstEvaluation;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class HorstStrategy implements ReversiStrategy
{

    EvaluationFunction evalfunc    = new HorstEvaluation();
    private int        searchDepth = 3;

    private Player     max;

    public HorstStrategy(int depth)
    {
        searchDepth = depth;
    }

    public int getCount()
    {
        return 0;
    }

    public Piece move(GameModel gameModel)
    {
        return minimax(gameModel);
    }

    private Piece minimax(GameModel game)
    {
        max = game.getTurnHolder();
        int best = Integer.MIN_VALUE;
        Piece bestMove = null;
        List<Piece> legalMoves = getLegalMoves(game, max);

        for (Piece move : legalMoves)
        {
            GameModel clone = new GameModel(game);
            clone.placePiece(move.getX(), move.getY(), max);
            int value = minimaxval(clone, searchDepth);
            if (value > best)
            {
                best = value;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private int minimaxval(GameModel game, int depth)
    {
        if (depth == 0) return evalfunc.evaluatePosition(game, game.getTurnHolder());
        int best = 0;
        if (game.getTurnHolder() == max)
            best = Integer.MIN_VALUE;
        else
            best = Integer.MAX_VALUE;

        List<Piece> legalMoves = getLegalMoves(game, max);

        for (Piece move : legalMoves)
        {
            GameModel clone = new GameModel(game);
            clone.placePiece(move.getX(), move.getY(), game.getTurnHolder());
            int val = minimaxval(clone, searchDepth);
            if (game.getTurnHolder() == max)
            {
                if (val > best) best = val;
            }
            else
            {
                if (val < best) best = val;
            }
        }

        return best;
    }

    private List<Piece> getLegalMoves(GameModel game, Player turnHolder)
    {
        List<Piece> legalMoves = new ArrayList<Piece>();
        for (int i = 0; i < game.getLegalMoves(turnHolder).length; i++)
        {
            for (int k = 0; k < game.getLegalMoves(turnHolder)[i].length; k++)
            {
                if (game.getLegalMoves(turnHolder)[i][k]) legalMoves.add(new Piece(i, k, game.getTurnHolder()));
            }
        }
        return legalMoves;
    }
}
