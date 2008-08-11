package de.schelklingen2008.reversi.ai.tournament;

import java.util.List;

import de.schelklingen2008.reversi.ai.evaluation.CornerEvaluationFunction;
import de.schelklingen2008.reversi.ai.strategy.AlphaBetaStrategy;
import de.schelklingen2008.reversi.ai.strategy.MinimaxStrategy;
import de.schelklingen2008.reversi.ai.strategy.SimpleStrategy;

public class Main
{

    public static void main(String[] args)
    {
        Tournament t = new Tournament();
        t.addStrategy(new TournamentStrategy("Ben", new SimpleStrategy()));
        t.addStrategy(new TournamentStrategy("Georg", new AlphaBetaStrategy(new CornerEvaluationFunction(), 1, true)));
        t.addStrategy(new TournamentStrategy("Daniel", new AlphaBetaStrategy(new CornerEvaluationFunction(), 3, true)));
        t.addStrategy(new TournamentStrategy("Manuel", new AlphaBetaStrategy(new CornerEvaluationFunction(), 5, true)));
        t.addStrategy(new TournamentStrategy("Sarah", new MinimaxStrategy(new CornerEvaluationFunction(), 1, true)));

        t.run();
        List<TournamentStrategy> strategies = t.getStrategies();
        for (TournamentStrategy strategy : strategies)
        {
            System.out.println(strategy.getCreator()
                               + " ("
                               + strategy.getStrategy().getClass().getName()
                               + "): "
                               + strategy.getPoints());
        }
    }
}
