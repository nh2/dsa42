package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.schelklingen2008.dasverruecktelabyrinth.client.controller.Controller;
import de.schelklingen2008.dasverruecktelabyrinth.client.model.GameContext;

public class LabyPlusPlayerPanel extends JPanel
{

    public LabyPlusPlayerPanel(Controller controller)
    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        LabyrinthPanel lP = new LabyrinthPanel(controller);
        lP.setBorder(BorderFactory.createLineBorder(Color.black));
        PlayerPanel pP = new PlayerPanel(controller);
        pP.setBorder(BorderFactory.createLineBorder(Color.black));

        add(lP);
        add(pP);

    }

    public static void main(String[] args)
    {
        Controller controller = new Controller();
        GameContext ctx = controller.getGameContext();
        ctx.setPlayers(new String[] { "dick", "doof" });
        ctx.setMyName("dick");

        JFrame frame = new JFrame();
        frame.getContentPane().add(new LabyPlusPlayerPanel(controller));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}