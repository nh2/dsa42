package de.schelklingen2008.reversi.ai.strategy;

import java.util.ArrayList;
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
        int best = Integer.MIN_VALUE;
        Piece bestMove = null;

        int value = 0;

        List<Piece> legalMoves = new ArrayList<Piece>(gameModel.getLegalMovesSet(gameModel.getTurnHolder()));
        int[] values= new int[legalMoves.size()];
        List<SecondRunner> threads = new ArrayList<SecondRunner>(legalMoves.size());

        for(int i = 0; i < legalMoves.size(); i++)
            threads.add(new SecondRunner(gameModel, legalMoves.get(i), values, i));

        for (int i = 0; i < legalMoves.size(); i++)
        {


            FirstRunner t1 = null, t2 = null;
            Piece bestPiece;
            // GameModel clone;
            // clone = new GameModel(gameModel);
            // Piece toPlace = legalMoves.get(i);
            // clone.placePiece(toPlace);
            // value = min(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, clone, true);

            t1 = new FirstRunner(gameModel, legalMoves.get(i));
            t1.start();
            if (i + 1 < legalMoves.size())
            {
                t2 = new FirstRunner(gameModel, legalMoves.get(i + 1));
                t2.start();

            }
            try
            {
                t1.join();
            }
            catch (InterruptedException e)
            {
            }
            if (i + 1 < legalMoves.size()) try
            {
                t2.join();

            }
            catch (InterruptedException e)
            {
            }

            int value1=0, value2=0;

            value1 = t1.getResult();
            if (i + 1 < legalMoves.size()) value2 = t2.getResult();

            if (i + 1 < legalMoves.size())
            {
                if (value1 >= value2)
                {
                    value = value1;
                    bestPiece = t1.piece;
                }
                else
                {
                    value = value2;
                    bestPiece = t2.piece;
                }
            }
            else
            {
                value = value1;
                bestPiece = t1.piece;
            }

            if (value >= best)
            {

                best = value;
                bestMove = bestPiece;
            }
            if (i + 1 < legalMoves.size()) i++;

        }

        return bestMove;

    }

    class FirstRunner extends Thread
    {

        GameModel   game;
        Piece       piece;
        private int result = 0;

        public FirstRunner(GameModel game, Piece piece)
        {
            this.game = game;
            this.piece = piece;
        }

        @Override
        public void run()
        {
            GameModel clone;
            clone = new GameModel(game);
            clone.placePiece(piece);
            result = min(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, clone);
        }

        public int getResult()
        {
            return result;
        }
    };

    class SecondRunner extends Thread
    {

        GameModel   game;
        Piece       piece;
        private int result = 0;

        public SecondRunner(GameModel game, Piece piece, int[] values, int pos)
        {
            this.game = game;
            this.piece = piece;
        }

        @Override
        public void run()
        {
            GameModel clone;
            clone = new GameModel(game);
            clone.placePiece(piece);
            result = min(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, clone);
        }

        public int getResult()
        {
            return result;
        }
    };

    private int max(int depth, int alpha, int beta, GameModel gameModel)
    {
        if (depth == 0)
        {
            return evalFunction.evaluatePosition(gameModel, player);

        }

        for (Piece piece : gameModel.getLegalMovesSet(gameModel.getTurnHolder())){
            GameModel clone = new GameModel(gameModel);
            clone.placePiece(piece);
        }

        for (Piece piece : gameModel.getLegalMovesSet(gameModel.getTurnHolder()))
        {
            GameModel clone = new GameModel(gameModel);
            clone.placePiece(piece);

            alpha = Math.max(alpha, min(depth - 1, alpha, beta, clone));

            if (alpha >= beta)
            {
                return beta;
            }

        }
        return alpha;

    }

    private int min(int depth, int alpha, int beta, GameModel gameModel)
    {
        if (depth == 0)
        {
            return evalFunction.evaluatePosition(gameModel, player);


        }

        for (Piece piece : gameModel.getLegalMovesSet(gameModel.getTurnHolder()))
        {
            GameModel clone = new GameModel(gameModel);
            clone.placePiece(piece);

            beta = Math.min(beta, max(depth - 1, alpha, beta, clone));

            if (beta <= alpha)
            {
                return alpha;
            }

        }
        return beta;

    }




}
