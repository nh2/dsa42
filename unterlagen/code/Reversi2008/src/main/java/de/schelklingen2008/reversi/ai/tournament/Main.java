package de.schelklingen2008.reversi.ai.tournament;

import java.util.Collections;
import java.util.List;

<<<<<<< .mine
import de.schelklingen2008.reversi.ai.strategy.HorstStrategy;
=======
import de.schelklingen2008.reversi.ai.strategy.AmorStrategy;
>>>>>>> .r785
import de.schelklingen2008.reversi.ai.strategy.SimpleStrategy;

public class Main
{

    public static void main(String[] args)
    {
        Tournament t = new Tournament();
<<<<<<< .mine
        t.addStrategy(new TournamentStrategy("Niklas und Sarah", new HorstStrategy(11)));
        t.addStrategy(new TournamentStrategy("Ben und Georg", new SimpleStrategy()));
        // t.addStrategy(new TournamentStrategy("Jo", new SimpleStrategy()));
=======
        t.addStrategy(new TournamentStrategy("Ben", new AmorStrategy()));
        t.addStrategy(new TournamentStrategy("Georg", new SimpleStrategy()));
        t.addStrategy(new TournamentStrategy("Jo", new SimpleStrategy()));
>>>>>>> .r785

        t.prepare();
        t.run();

        int matchPerPlayer = 2 * Tournament.MATCH_COUNT * (t.getStrategyCount() - 1);
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
