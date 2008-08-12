package de.schelklingen2008.reversi.ai.tournament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tournament
{

    private static final int         POINTS_DRAW            = 1;
    private static final int         POINTS_WIN             = 2;
    public static final int          MATCH_COUNT            = 1;
    private static final long        TIME_LIMIT_PER_MOVE_MS = 1000;

    private List<TournamentStrategy> strategies             = new ArrayList<TournamentStrategy>();

    private Map<String, List<Match>> matches                = new HashMap<String, List<Match>>();

    public void addStrategy(TournamentStrategy strategy)
    {
        strategies.add(strategy);
    }

    public void prepare()
    {
        List<TournamentStrategy> whitePlayers = new ArrayList<TournamentStrategy>(strategies);
        List<TournamentStrategy> blackPlayers = new ArrayList<TournamentStrategy>(strategies);

        for (TournamentStrategy white : whitePlayers)
        {
            for (TournamentStrategy black : blackPlayers)
            {
                if (white.equals(black)) continue;

                ArrayList<Match> matchList = new ArrayList<Match>();
                matches.put(matchKey(white, black), matchList);

                for (int i = 0; i < MATCH_COUNT; i++)
                {
                    Match match = new Match(white.getStrategy(), black.getStrategy());
                    matchList.add(match);
                }
            }
        }
    }

    public void run()
    {
        List<TournamentStrategy> whitePlayers = new ArrayList<TournamentStrategy>(strategies);
        List<TournamentStrategy> blackPlayers = new ArrayList<TournamentStrategy>(strategies);

        for (TournamentStrategy white : whitePlayers)
        {
            for (TournamentStrategy black : blackPlayers)
            {
                List<Match> matchList = matches.get(matchKey(white, black));
                if (matchList == null) continue;
                for (Match match : matchList)
                {
                    match.execute();
                    white.addPoints(match.getPointsWhite());
                    black.addPoints(match.getPointsBlack());
                }
            }
        }
    }

    private String matchKey(TournamentStrategy s1, TournamentStrategy s2)
    {
        return s1.getCreator() + "/" + s2.getCreator();
    }

    public List<TournamentStrategy> getStrategies()
    {
        return new ArrayList<TournamentStrategy>(strategies);
    }

    public List<Match> getMatches(TournamentStrategy s1, TournamentStrategy s2)
    {
        return matches.get(matchKey(s1, s2));
    }

    public int getStrategyCount()
    {
        return strategies.size();
    }
}
