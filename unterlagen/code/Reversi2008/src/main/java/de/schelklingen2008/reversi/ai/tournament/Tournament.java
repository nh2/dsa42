package de.schelklingen2008.reversi.ai.tournament;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tournament
{

    public static final int          MATCH_COUNT_DEFAULT = 30;

    private int                      matchCount          = MATCH_COUNT_DEFAULT;

    private List<TournamentStrategy> strategies          = new ArrayList<TournamentStrategy>();

    private Map<String, List<Match>> matches             = new HashMap<String, List<Match>>();

    public Tournament()
    {
    }

    public Tournament(int matchCount)
    {
        this.matchCount = matchCount;
    }

    public Tournament(int matchCount, Collection<TournamentStrategy> manyStrategies)
    {
        this.matchCount = matchCount;
        addStrategies(manyStrategies);
    }

    public Tournament(Collection<TournamentStrategy> manyStrategies)
    {
        addStrategies(manyStrategies);
    }

    public void addStrategy(TournamentStrategy strategy)
    {
        strategies.add(strategy);
    }

    public void addStrategies(Collection<TournamentStrategy> manyStrategies)
    {
        strategies.addAll(manyStrategies);
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

                for (int i = 0; i < matchCount; i++)
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
                    long s = System.currentTimeMillis();
                    match.execute();
                    long e = System.currentTimeMillis();
                    white.addPoints(match.getPointsWhite());
                    black.addPoints(match.getPointsBlack());
                    System.out.println("Match played "
                                       + white.getCreator()
                                       + ":"
                                       + black.getCreator()
                                       + " "
                                       + match.getPointsWhite()
                                       + ":"
                                       + match.getPointsBlack()
                                       + " in "
                                       + (e - s)
                                       + " ms");
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

    public int getMatchCount()
    {
        return matchCount;
    }
}
