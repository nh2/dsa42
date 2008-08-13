package de.schelklingen2008.reversi.ai.tournament;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TournamentPanel extends JPanel
{

    public TournamentPanel(Tournament model)
    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        add(titlePanel);
        titlePanel.add(new CreatorPanel(null));

        List<TournamentStrategy> strategies = model.getStrategies();
        for (TournamentStrategy s1 : strategies)
        {
            JPanel linePanel = new JPanel();
            linePanel.setLayout(new BoxLayout(linePanel, BoxLayout.LINE_AXIS));
            add(linePanel);

            linePanel.add(new CreatorPanel(s1.getCreator()));
            titlePanel.add(new CreatorPanel(s1.getCreator()));

            for (TournamentStrategy s2 : strategies)
            {
                List<Match> matches = model.getMatches(s1, s2);
                System.out.println(matches);
                MatchPanel matchPanel = new MatchPanel(matches);
                linePanel.add(matchPanel);
            }
        }
    }
}
