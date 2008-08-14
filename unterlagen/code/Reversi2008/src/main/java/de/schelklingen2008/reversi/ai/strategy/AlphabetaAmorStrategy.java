package de.schelklingen2008.reversi.ai.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class AlphabetaAmorStrategy implements ReversiStrategy
{

    private final EvaluationFunction evalFunction;
    private final int                depth;
    private int                      count;
    Player                           player;


    public AlphabetaAmorStrategy(EvaluationFunction evalFunction, int depth)
    {
        this.depth = depth;
        this.evalFunction = evalFunction;
    }

    public int getCount()
    {
        return count;
    }

    public Piece move(GameModel gameModel)
    {

        player = gameModel.getTurnHolder();
        Piece bestMove = null;
        List<Piece> legalMoves = new ArrayList<Piece>(gameModel.getLegalMovesSet(gameModel.getTurnHolder()));
        Integer[] values = new Integer[legalMoves.size()];
        List<SecondRunner> threads = new ArrayList<SecondRunner>(legalMoves.size());

        for (int i = 0; i < legalMoves.size(); i++)
        {
            threads.add(new SecondRunner(gameModel, legalMoves.get(i), values, i));
            threads.get(i).start();
        }

        try
        {
            for (SecondRunner sr : threads)
                sr.join();
        }
        catch (InterruptedException e)
        {
        }

        int bester = Integer.MIN_VALUE;
        int pos = 0;
        for (int i = 0; i < values.length; i++)
            if (values[i] > bester)
            {
                bester = values[i];
                pos = i;
            }

        bestMove = legalMoves.get(pos);

        return bestMove;
    }

    class SecondRunner extends Thread
    {

        GameModel         game;
        Piece             piece;
        private Integer[] values;
        private int       pos;

        public SecondRunner(GameModel game, Piece piece, Integer[] values, int pos)
        {
            this.game = game;
            this.piece = piece;
            this.values = values;
            this.pos = pos;
        }

        @Override
        public void run()
        {
            GameModel clone;
            clone = new GameModel(game);
            clone.placePiece(piece);
            values[pos] = min(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, clone);
        }
    };

    private int max(int depth, int alpha, int beta, GameModel gameModel)
    {

        List<pieceInt> liste = new weightListe(false);

        if (depth == 0 || gameModel.isFinished())
        {
            return evalFunction.evaluatePosition(gameModel, player);
        }

        for (Piece piece : gameModel.getLegalMovesSet(gameModel.getTurnHolder()))
        {
            GameModel clone = new GameModel(gameModel);
            clone.placePiece(piece);
            liste.add(new pieceInt(false, piece, evalFunction.evaluatePosition(clone, player)));
        }
        Collections.sort(liste);

        for (int i = 0; i < liste.size(); i++)
        {

            GameModel clone = new GameModel(gameModel);
            clone.placePiece(liste.get(i).piece);

            alpha = Math.max(alpha, min(depth - 1, alpha, beta, clone));

            if (alpha >= beta)
            {
                return beta;
            }

        }

        return alpha;

    }

    public class weightListe extends ArrayList<pieceInt>
    {

        private boolean min;

        public weightListe(boolean min)
        {
            this.min = min;
        }
    }

    public class pieceInt implements Comparable<pieceInt>
    {

        public int     wert;
        public Piece   piece;
        public boolean min;

        public pieceInt(boolean min, Piece piece, int wert)
        {
            this.wert = wert;
            this.piece = piece;
            this.min = min;

        }

        public int compareTo(pieceInt o)
        {
            if (min)
            {
                return wert - o.wert;
            }
            return o.wert - wert;
        }

    }

    private int min(int depth, int alpha, int beta, GameModel gameModel)

    {
        List<pieceInt> liste = new weightListe(true);

        if (depth == 0 || gameModel.isFinished())
        {
            return evalFunction.evaluatePosition(gameModel, player);
        }

        for (Piece piece : gameModel.getLegalMovesSet(gameModel.getTurnHolder()))
        {
            GameModel clone = new GameModel(gameModel);
            clone.placePiece(piece);
            liste.add(new pieceInt(true, piece, evalFunction.evaluatePosition(clone, player)));

        }
        Collections.sort(liste);

        for (int i = 0; i < liste.size(); i++)
        {

            GameModel clone = new GameModel(gameModel);
            clone.placePiece(liste.get(i).piece);

            beta = Math.min(beta, max(depth - 1, alpha, beta, clone));

            if (beta <= alpha)
            {
                return alpha;
            }

        }

        return beta;

    }



}
