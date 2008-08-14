package de.schelklingen2008.reversi.ai.tournament;

import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import de.schelklingen2008.reversi.ai.evaluation.AlphabetaAmorEvaluationFunction;
import de.schelklingen2008.reversi.ai.evaluation.GeorgEvaluationFunction;
import de.schelklingen2008.reversi.ai.evaluation.GreenGoblin;
import de.schelklingen2008.reversi.ai.evaluation.LarsEvalDiff;
import de.schelklingen2008.reversi.ai.evaluation.ManuelEvaluationFunction;
import de.schelklingen2008.reversi.ai.evaluation.SophieEvaluation;
import de.schelklingen2008.reversi.ai.strategy.AlphaBetaStrategy;
import de.schelklingen2008.reversi.ai.strategy.AlphabetaAmorStrategy;
import de.schelklingen2008.reversi.ai.strategy.ChaosStrategy;
import de.schelklingen2008.reversi.ai.strategy.HorstStrategy;
import de.schelklingen2008.reversi.ai.strategy.LarsStratMmFixedDepth;
import de.schelklingen2008.reversi.ai.strategy.ManuelStrategy;
import de.schelklingen2008.reversi.ai.strategy.ReversiStrategyDadaLena;
import de.schelklingen2008.reversi.ai.strategy.ReversiStrategyNiklasLeo;
import de.schelklingen2008.reversi.ai.strategy.SophieStrategy;

public class MainFrame
{

    public static void main(String[] args)
    {
        Tournament t = new Tournament(4);
        t.addStrategy(new TournamentStrategy("GeBen1", new AlphaBetaStrategy(new GeorgEvaluationFunction(),
                                                                             1,
                                                                             1,
                                                                             true,
                                                                             false)));
        t.addStrategy(new TournamentStrategy("Manuel", new ManuelStrategy(new ManuelEvaluationFunction())));
        t.addStrategy(new TournamentStrategy("Amor",
                                             new AlphabetaAmorStrategy(new AlphabetaAmorEvaluationFunction(), 6)));
        t.addStrategy(new TournamentStrategy("Horst", new HorstStrategy(6, 2)));
        t.addStrategy(new TournamentStrategy("GreenGoblin", new ReversiStrategyNiklasLeo(new GreenGoblin(), 4)));
        t.addStrategy(new TournamentStrategy("Sophie", new SophieStrategy(new SophieEvaluation(), 4)));
        t.addStrategy(new TournamentStrategy("Lars", new LarsStratMmFixedDepth(new LarsEvalDiff(), 4)));
        t.addStrategy(new TournamentStrategy("DaDaLena", new ReversiStrategyDadaLena()));
        t.addStrategy(new TournamentStrategy("Chaos", new ChaosStrategy(5)));

        t.addStrategy(new TournamentStrategy("GeBen5", new AlphaBetaStrategy(new GeorgEvaluationFunction(),
                                                                             5,
                                                                             15,
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

        List<TournamentStrategy> strategies = t.getStrategies();
        Collections.sort(strategies);
        Collections.reverse(strategies);
        for (TournamentStrategy strategy : strategies)
        {
            int pts = strategy.getPoints();
            String type = strategy.getType();
            System.out.println(strategy.getCreator() + " (" + type + "): " + pts);
        }

    }
}
