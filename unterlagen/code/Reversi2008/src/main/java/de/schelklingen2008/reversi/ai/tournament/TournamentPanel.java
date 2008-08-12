package de.schelklingen2008.reversi.ai.tournament;

import java.awt.Dimension;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TournamentPanel extends JPanel
{

    private Tournament model;

    public TournamentPanel(Tournament model)
    {
        this.model = model;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        add(titlePanel);

        List<TournamentStrategy> strategies = model.getStrategies();
        for (TournamentStrategy s1 : strategies)
        {
            JPanel linePanel = new JPanel();
            linePanel.setLayout(new BoxLayout(linePanel, BoxLayout.LINE_AXIS));
            add(linePanel);
            JLabel creatorLabel = new JLabel(s1.getCreator());
            creatorLabel.setPreferredSize(new Dimension(100, 100));
            // titlePanel.add(creatorLabel);
            linePanel.add(creatorLabel);
            linePanel.add(Box.createHorizontalGlue());

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
