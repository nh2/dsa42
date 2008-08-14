package de.schelklingen2008.reversi.ai.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.schelklingen2008.reversi.ai.evaluation.EvaluationFunction;
import de.schelklingen2008.reversi.ai.evaluation.ManuelEvaluationFunction;
import de.schelklingen2008.reversi.ai.tournament.Tournament;
import de.schelklingen2008.reversi.ai.tournament.TournamentStrategy;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class ManuelStrategy implements ReversiStrategy
{

    private EvaluationFunction evaluationFunction;

    public ManuelStrategy(EvaluationFunction evaluationFunction)
    {
        this.evaluationFunction = evaluationFunction;
    }

    public int getCount()
    {
        // TODO Return something useful
        return 42;
    }

    public int evaluateBestMove(GameModel gameModel, Player player, int depth, boolean otherPlayerCannotMove)
    {

        if (depth == 0)
        {
            int value = evaluationFunction.evaluatePosition(gameModel, player);
            if (otherPlayerCannotMove)
            {
                value += 10;
            }
            return value;
        }

        Set<Piece> legalMovesSet = gameModel.getLegalMovesSet(gameModel.getTurnHolder());

        int maxValue = Integer.MIN_VALUE;

        for (Piece move : legalMovesSet)
        {
            GameModel tmpGameModel = new GameModel(gameModel);
            tmpGameModel.placePiece(move.getX(), move.getY(), move.getPlayer());
            int value;

            if (tmpGameModel.getTurnHolder() == gameModel.getTurnHolder())
            {
                value = evaluateBestMove(tmpGameModel, player, depth - 1, true);
            }
            else
            {
                value = evaluateWorstMove(tmpGameModel, player, depth - 1, false);
            }

            if (value > maxValue)
            {
                maxValue = value;
            }
        }

        return maxValue;

    }

    public int evaluateWorstMove(GameModel gameModel, Player player, int depth, boolean otherPlayerCannotMove)
    {

        if (depth == 0)
        {
            int value = evaluationFunction.evaluatePosition(gameModel, player);
            if (otherPlayerCannotMove)
            {
                value -= 10;
            }
            return value;
        }

        Set<Piece> legalMovesSet = gameModel.getLegalMovesSet(gameModel.getTurnHolder());

        int minValue = Integer.MAX_VALUE;

        for (Piece move : legalMovesSet)
        {
            GameModel tmpGameModel = new GameModel(gameModel);
            tmpGameModel.placePiece(move.getX(), move.getY(), move.getPlayer());
            int value;

            if (tmpGameModel.getTurnHolder() == gameModel.getTurnHolder())
            {
                value = evaluateWorstMove(tmpGameModel, player, depth - 1, true);
            }
            else
            {
                value = evaluateBestMove(tmpGameModel, player, depth - 1, false);
            }

            if (value < minValue)
            {
                minValue = value;
            }
        }

        return minValue;

    }

    public Piece getBestMove(GameModel gameModel, Player player, int depth)
    {

        Set<Piece> legalMovesSet = gameModel.getLegalMovesSet(gameModel.getTurnHolder());
        List<Piece> bestMoves = new ArrayList<Piece>();

        int maxValue = Integer.MIN_VALUE;

        for (Piece move : legalMovesSet)
        {
            GameModel tmpGameModel = new GameModel(gameModel);
            tmpGameModel.placePiece(move.getX(), move.getY(), move.getPlayer());
            int value;

            if (tmpGameModel.getTurnHolder() == gameModel.getTurnHolder())
            {
                value = evaluateBestMove(tmpGameModel, player, depth - 1, true);
            }
            else
            {
                value = evaluateWorstMove(tmpGameModel, player, depth - 1, false);
            }

            if (value > maxValue)
            {
                maxValue = value;
                bestMoves.clear();
                bestMoves.add(move);
            }
            else if (value == maxValue)
            {
                bestMoves.add(move);
            }
        }

        return bestMoves.get((int) (Math.random() * bestMoves.size()));

    }

    public Piece move(GameModel gameModel)
    {

        return getBestMove(gameModel, gameModel.getTurnHolder(), 3);

    }

    public static void main(String[] args)
    {
        Tournament tournament = new Tournament();
        tournament.addStrategy(new TournamentStrategy("N.N.", new SimpleStrategy()));
        tournament.addStrategy(new TournamentStrategy("Manuel", new ManuelStrategy(new ManuelEvaluationFunction())));

        tournament.prepare();
        tournament.run();
    }
}
