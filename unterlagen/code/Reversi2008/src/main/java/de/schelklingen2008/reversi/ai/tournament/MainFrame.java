package de.schelklingen2008.reversi.ai.tournament;

import javax.swing.JFrame;

import de.schelklingen2008.reversi.ai.strategy.SimpleStrategy;

public class MainFrame
{

    public static void main(String[] args)
    {
        Tournament t = new Tournament();
        t.addStrategy(new TournamentStrategy("Ben", new SimpleStrategy()));
        t.addStrategy(new TournamentStrategy("Georg", new SimpleStrategy()));
        t.addStrategy(new TournamentStrategy("Daniel", new SimpleStrategy()));
        t.addStrategy(new TournamentStrategy("Manuel", new SimpleStrategy()));
        t.addStrategy(new TournamentStrategy("Sarah", new SimpleStrategy()));
        t.prepare();

        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new TournamentPanel(t));
        frame.pack();
        frame.setVisible(true);

        t.run();
    }
}
