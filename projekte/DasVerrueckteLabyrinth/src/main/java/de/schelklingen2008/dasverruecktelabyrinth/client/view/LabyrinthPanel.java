package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.schelklingen2008.dasverruecktelabyrinth.client.controller.Controller;
import de.schelklingen2008.dasverruecktelabyrinth.client.model.GameContext;

public class LabyrinthPanel extends JPanel
{

    public LabyrinthPanel(Controller controller)
    {
        setLayout(new BorderLayout());

        JButton pushButton1 = new JButton("hier");
        JButton pushButton2 = new JButton("hier");
        JButton pushButton3 = new JButton("hier");
        JButton pushButton4 = new JButton("hier");
        JButton pushButton5 = new JButton("hier");
        JButton pushButton6 = new JButton("hier");
        JButton pushButton7 = new JButton("hier");
        JButton pushButton8 = new JButton("hier");
        JButton pushButton9 = new JButton("hier");
        JButton pushButton10 = new JButton("hier");
        JButton pushButton11 = new JButton("hier");
        JButton pushButton12 = new JButton("hier");

        JPanel buttonsSued = new JPanel();
        buttonsSued.setLayout(new BoxLayout(buttonsSued, BoxLayout.LINE_AXIS));
        buttonsSued.add(Box.createHorizontalStrut(150));
        buttonsSued.add(pushButton1);
        buttonsSued.add(Box.createHorizontalStrut(103));
        buttonsSued.add(pushButton2);
        buttonsSued.add(Box.createHorizontalStrut(104));
        buttonsSued.add(pushButton3);

        JPanel buttonsOst = new JPanel();
        buttonsOst.setLayout(new BoxLayout(buttonsOst, BoxLayout.PAGE_AXIS));
        buttonsOst.add(Box.createVerticalStrut(104));
        buttonsOst.add(pushButton4);
        buttonsOst.add(Box.createVerticalStrut(130));
        buttonsOst.add(pushButton5);
        buttonsOst.add(Box.createVerticalStrut(140));
        buttonsOst.add(pushButton6);

        JPanel buttonsNord = new JPanel();
        buttonsNord.setLayout(new BoxLayout(buttonsNord, BoxLayout.LINE_AXIS));
        buttonsNord.add(Box.createHorizontalStrut(150));
        buttonsNord.add(pushButton7);
        buttonsNord.add(Box.createHorizontalStrut(103));
        buttonsNord.add(pushButton8);
        buttonsNord.add(Box.createHorizontalStrut(104));
        buttonsNord.add(pushButton9);

        JPanel buttonsWest = new JPanel();
        buttonsWest.setLayout(new BoxLayout(buttonsWest, BoxLayout.PAGE_AXIS));
        buttonsWest.add(Box.createVerticalStrut(104));
        buttonsWest.add(pushButton10);
        buttonsWest.add(Box.createVerticalStrut(130));
        buttonsWest.add(pushButton11);
        buttonsWest.add(Box.createVerticalStrut(140));
        buttonsWest.add(pushButton12);

        add(buttonsSued, BorderLayout.SOUTH);
        add(buttonsNord, BorderLayout.NORTH);
        add(buttonsWest, BorderLayout.WEST);
        add(buttonsOst, BorderLayout.EAST);

        BoardView b = new BoardView(controller);
        add(b, BorderLayout.CENTER);
    }

    @Override
    public void setPreferredSize(Dimension preferredSize)
    {
        // TODO Auto-generated method stub
        return new Dimension(680, 640);
    }

    public static void main(String[] args)
    {
        Controller controller = new Controller();
        GameContext ctx = controller.getGameContext();
        ctx.setPlayers(new String[] { "dick", "doof" });
        ctx.setMyName("dick");

        JFrame frame = new JFrame();
        frame.getContentPane().add(new LabyrinthPanel(controller));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
