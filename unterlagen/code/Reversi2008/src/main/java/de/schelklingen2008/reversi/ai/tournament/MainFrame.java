package de.schelklingen2008.reversi.ai.tournament;

import javax.swing.JFrame;

import de.schelklingen2008.reversi.ai.evaluation.GeorgEvaluationFunction;
import de.schelklingen2008.reversi.ai.strategy.AlphaBetaStrategy;

public class MainFrame
{

    public static void main(String[] args)
    {
        Tournament t = new Tournament(500);
        t.addStrategy(new TournamentStrategy("Georg_5_15", new AlphaBetaStrategy(new GeorgEvaluationFunction(),
                                                                                 5,
                                                                                 15,
                                                                                 true,
                                                                                 false)));
        t.addStrategy(new TournamentStrategy("Georg_5_0", new AlphaBetaStrategy(new GeorgEvaluationFunction(),
                                                                                5,
                                                                                0,
                                                                                true,
                                                                                false)));
        t.prepare();

        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final TournamentPanel tPanel = new TournamentPanel(t);
        frame.getContentPane().add(tPanel);
        frame.pack();
        frame.setVisible(true);

        t.run();
    }
}
