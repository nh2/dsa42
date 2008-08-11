package de.schelklingen2008.reversi.ai.tournament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.schelklingen2008.reversi.model.Player;

public class Tournament
{

    private static final int         POINTS_DRAW            = 1;
    private static final int         POINTS_WIN             = 2;
    private static final int         MATCH_COUNT            = 1;
    private static final long        TIME_LIMIT_PER_MOVE_MS = 1000;

    private List<TournamentStrategy> strategies             = new ArrayList<TournamentStrategy>();

    private Map<String, Match>       matches                = new HashMap<String, Match>();

    public void addStrategy(TournamentStrategy strategy)
    {
        strategies.add(strategy);
    }

    public void run()
    {
        List<TournamentStrategy> whitePlayers = new ArrayList<TournamentStrategy>(strategies);
        List<TournamentStrategy> blackPlayers = new ArrayList<TournamentStrategy>(strategies);

        for (TournamentStrategy white : whitePlayers)
        {
            System.out.print(white.getCreator());
            for (TournamentStrategy black : blackPlayers)
            {
                System.out.print(";");
                if (white.equals(black)) continue;

                int whitePointsBefore = white.getPoints();
                int blackPointsBefore = black.getPoints();
                for (int i = 0; i < MATCH_COUNT; i++)
                {
                    Match match = new Match(white.getStrategy(), black.getStrategy());
                    matches.put(matchKey(white, black), match);
                    match.execute();

                    Player winner = match.getWinner();
                    if (null == winner)
                    {
                        white.addPoints(POINTS_DRAW);
                        black.addPoints(POINTS_DRAW);
                    }
                    if (Player.WHITE == winner) white.addPoints(POINTS_WIN);
                    if (Player.BLACK == winner) black.addPoints(POINTS_WIN);
                }
                int whitePointsDiff = white.getPoints() - whitePointsBefore;
                int blackPointsDiff = black.getPoints() - blackPointsBefore;
                System.out.print(whitePointsDiff + ":" + blackPointsDiff);
            }
            System.out.println();
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

    public Match getMatch(TournamentStrategy s1, TournamentStrategy s2)
    {
        return null;
    }
}
