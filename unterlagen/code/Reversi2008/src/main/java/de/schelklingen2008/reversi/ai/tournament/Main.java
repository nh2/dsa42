package de.schelklingen2008.reversi.ai.tournament;

import java.util.Collections;
import java.util.List;

import de.schelklingen2008.reversi.ai.strategy.SimpleStrategy;

public class Main
{

    public static void main(String[] args)
    {
        Tournament t = new Tournament();

        t.addStrategy(new TournamentStrategy("Ben", new SimpleStrategy()));
        t.addStrategy(new TournamentStrategy("Georg", new SimpleStrategy()));
        t.addStrategy(new TournamentStrategy("Jo", new SimpleStrategy()));

        t.prepare();
        long startzeit = System.currentTimeMillis();
        t.run();
        System.out.println("Vergangene Zeit: " + (System.currentTimeMillis() - startzeit) + " ms");

        int matchPerPlayer = Tournament.MATCH_COUNT * (t.getStrategyCount() - 1);
        int matchCount = matchPerPlayer * t.getStrategyCount() / 2;
        System.out.println("Number of matches: " + matchCount);
        System.out.println("Number of matches for each player: " + matchPerPlayer);
        System.out.println("Maximum number of points for one player: " + matchPerPlayer * Match.POINTS_WIN);
        System.out.println();

        List<TournamentStrategy> strategies = t.getStrategies();
        Collections.sort(strategies);
        Collections.reverse(strategies);
        for (TournamentStrategy strategy : strategies)
        {
            System.out.println(strategy.getCreator() + " (" + strategy.getType() + "): " + strategy.getPoints());
        }
    }
}
