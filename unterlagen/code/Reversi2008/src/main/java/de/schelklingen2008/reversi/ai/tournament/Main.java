package de.schelklingen2008.reversi.ai.tournament;

import java.util.Collections;
import java.util.List;

import de.schelklingen2008.reversi.ai.evaluation.AmorEvaluationFunction;
import de.schelklingen2008.reversi.ai.strategy.AmorStrategy;
import de.schelklingen2008.reversi.ai.strategy.SimpleStrategy;

public class Main
{

    public static void main(String[] args)
    {
        Tournament t = new Tournament(10);
        t.addStrategy(new TournamentStrategy("Simple", new SimpleStrategy()));
        t.addStrategy(new TournamentStrategy("Amor", new AmorStrategy(new AmorEvaluationFunction(), 6)));
        // t.addStrategy(new TournamentStrategy("Horst", new HorstStrategy(6, 2)));
        // t.addStrategy(new TournamentStrategy("GreenGoblin", new ReversiStrategyNiklasLeo(new GreenGoblin(),
        // 4)));
        // t.addStrategy(new TournamentStrategy("Sophie", new SophieStrategy(new SophieEvaluation(), 4)));
        // t.addStrategy(new TournamentStrategy("Lars", new LarsStratMmFixedDepth(new LarsEvalDiff(), 4)));
        // t.addStrategy(new TournamentStrategy("DaDaLena", new ReversiStrategyDadaLena()));
        // t.addStrategy(new TournamentStrategy("Chaos", new ChaosStrategy(5)));
        // t.addStrategy(new TournamentStrategy("Manuel", new ManuelStrategy(new
        // ManuelEvaluationFunction())));
        //
        // t.addStrategy(new TournamentStrategy("Georg5", new AlphaBetaStrategy(new GeorgEvaluationFunction(),
        // 5,
        // 15,
        // true,
        // false)));
        // t.addStrategy(new TournamentStrategy("Georg1", new AlphaBetaStrategy(new GeorgEvaluationFunction(),
        // 1,
        // 1,
        // true,
        // false)));
        // t.addStrategy(new TournamentStrategy("Multi5", new AlphaBetaStrategyMultiThread(new
        // GeorgEvaluationFunction(),
        // 7,
        // 1,
        // 2,
        // true,
        // true)));

        t.prepare();
        long startzeit = System.currentTimeMillis();
        t.run();
        System.out.println("Vergangene Zeit: " + (System.currentTimeMillis() - startzeit) + " ms");

        int matchPerPlayer = 2 * t.getMatchCount() * (t.getStrategyCount() - 1);
        int matchCount = matchPerPlayer * t.getStrategyCount() / 2;
        System.out.println("Number of matches: " + matchCount);
        System.out.println("Number of matches for each player: " + matchPerPlayer);
        System.out.println("Maximum number of points for one player: " + matchPerPlayer * Match.POINTS_WIN);
        System.out.println();

        List<TournamentStrategy> strategies = t.getStrategies();
        Collections.sort(strategies);
        Collections.reverse(strategies);
        int sum = 0;
        for (TournamentStrategy strategy : strategies)
        {
            sum += strategy.getPoints();
        }
        for (TournamentStrategy strategy : strategies)
        {
            int pts = strategy.getPoints();
            String type = strategy.getType();
            System.out.println(strategy.getCreator() + " (" + type + "): " + pts + " " + (double) pts / sum);
        }
    }
}
