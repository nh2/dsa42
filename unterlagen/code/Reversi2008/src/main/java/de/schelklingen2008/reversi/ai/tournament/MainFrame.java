package de.schelklingen2008.reversi.ai.tournament;

import javax.swing.JFrame;

import de.schelklingen2008.reversi.ai.strategy.SimpleStrategy;

public class MainFrame
{

    public static void main(String[] args)
    {
        Tournament t = new Tournament(500);
        t.addStrategy(new TournamentStrategy("Ben", new SimpleStrategy()));
        t.addStrategy(new TournamentStrategy("Georg", new SimpleStrategy()));
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
