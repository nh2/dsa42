package de.schelklingen2008.reversi.ai.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.ai.evaluation.HorstEvaluation;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class HorstStrategy implements ReversiStrategy
{

    private int        anzahlBesteVerfolgen = 3;

    EvaluationFunction evalfunc;
    private int        searchDepth          = 3;

    private Player     max;

    public HorstStrategy(int depth, int width)
    {
        searchDepth = depth;
        anzahlBesteVerfolgen = width;
    }

    public HorstStrategy(int depth, int width, EvaluationFunction evalfunc)
    {
        this(depth, width);
        this.evalfunc = evalfunc;
    }

    public int getCount()
    {
        return 0;
    }

    public Piece move(GameModel gameModel)
    {
        if (evalfunc == null) evalfunc = new HorstEvaluation(gameModel);
        return minimax(gameModel);
    }

    private List<GameModel> cloneList  = new ArrayList<GameModel>(10000);
    private List<GameModel> cloneList2 = new ArrayList<GameModel>(10000);
    int                     clones     = 0;
    int                     clones2    = 0;

    private Piece minimax(GameModel game)
    {
        max = game.getTurnHolder();
        int best = Integer.MIN_VALUE;
        Piece bestMove = null;

        Set<Piece> legalMoves = game.getLegalMovesSet(max);

        List<Integer> valuesList = new ArrayList<Integer>(10);
        List<Piece> intelligentMovesList = new ArrayList<Piece>(10);
        // Jeden Zug bewerten
        {
            GameModel clone;
            for (Piece move : legalMoves)
            {
                clone = new GameModel(game);
                clone.placePiece(move.getX(), move.getY(), game.getTurnHolder());
                valuesList.add(evalfunc.evaluatePosition(clone, max));
                intelligentMovesList.add(move);
            }
        }
        // Die schlechten Züge aussortieren

        while (valuesList.size() > anzahlBesteVerfolgen)
        {
            int kleinster = 0;
            int kleinsterPos = 0;
            if (game.getTurnHolder() == max)
            {
                kleinster = Integer.MAX_VALUE;
                for (int i = 0; i < valuesList.size(); i++)
                    if (valuesList.get(i) < kleinster)
                    {
                        kleinster = valuesList.get(i);
                        kleinsterPos = i;
                    }
            }

            else
            {
                kleinster = Integer.MIN_VALUE;
                for (int i = 0; i < valuesList.size(); i++)
                    if (valuesList.get(i) > kleinster)
                    {
                        kleinster = valuesList.get(i);
                        kleinsterPos = i;
                    }
            }

            valuesList.remove(kleinsterPos);

            intelligentMovesList.remove(kleinsterPos);
        }

        // Rechnen
        for (Piece move : intelligentMovesList)
        {
            GameModel clone = new GameModel(game);
            // cloneList.add(new GameModel(game));
            // GameModel clone = cloneList.get(clones);
            // clones++;
            clone.placePiece(move.getX(), move.getY(), max);
            int value = minimaxval(clone, searchDepth);
            // clones--;
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
        if (depth == 0) return evalfunc.evaluatePosition(game, max);
        int best = 0;
        if (game.getTurnHolder() == max)
            best = Integer.MIN_VALUE;
        else
            best = Integer.MAX_VALUE;

        Set<Piece> legalMoves = game.getLegalMovesSet(game.getTurnHolder());
        List<Integer> valuesList = new ArrayList<Integer>(10);
        List<Piece> intelligentMovesList = new ArrayList<Piece>(10);
        // Jeden Zug bewerten

        {
            GameModel clone;
            for (Piece move : legalMoves)
            {
                clone = new GameModel(game);
                clone.placePiece(move.getX(), move.getY(), game.getTurnHolder());
                valuesList.add(evalfunc.evaluatePosition(clone, max));
                intelligentMovesList.add(move);
            }
        }

        // Die schlechten Züge aussortieren

        while (valuesList.size() > anzahlBesteVerfolgen)
        {
            int kleinster = 0;
            int kleinsterPos = 0;
            if (game.getTurnHolder() == max)
            {
                kleinster = Integer.MAX_VALUE;
                for (int i = 0; i < valuesList.size(); i++)
                    if (valuesList.get(i) < kleinster)
                    {
                        kleinster = valuesList.get(i);
                        kleinsterPos = i;
                    }
            }

            else
            {
                kleinster = Integer.MIN_VALUE;
                for (int i = 0; i < valuesList.size(); i++)
                    if (valuesList.get(i) > kleinster)
                    {
                        kleinster = valuesList.get(i);
                        kleinsterPos = i;
                    }
            }

            valuesList.remove(kleinsterPos);

            intelligentMovesList.remove(kleinsterPos);
        }

        // Rechnen
        for (Piece move : intelligentMovesList)
        {
            GameModel clone = new GameModel(game);
            // cloneList2.add(new GameModel(game));
            // GameModel clone = cloneList2.get(clones2);
            // clones2++;
            clone.placePiece(move.getX(), move.getY(), game.getTurnHolder());
            int val = minimaxval(clone, depth - 1);
            // clones2--;
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

    // private List<Piece> getLegalMoves(GameModel game, Player turnHolder)
    // {
    // List<Piece> legalMoves = new ArrayList<Piece>();
    // boolean[][] legalMoves2 = game.getLegalMoves(turnHolder);
    // for (int i = 0; i < legalMoves2.length; i++)
    // {
    // for (int k = 0; k < legalMoves2[i].length; k++)
    // {
    // if (legalMoves2[i][k]) legalMoves.add(new Piece(i, k, game.getTurnHolder()));
    // }
    // }
    // return legalMoves;
    // }
    @Override
    public String toString()
    {
        return "Horst";
    }
}
