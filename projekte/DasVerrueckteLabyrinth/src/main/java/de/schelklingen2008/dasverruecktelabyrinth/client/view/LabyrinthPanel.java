package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LabyrinthPanel extends JPanel
{

    public LabyrinthPanel()
    {
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
        buttonsSued.setLayout(new BoxLayout(buttonsSued, BoxLayout.PAGE_AXIS));
        add(pushButton1);
        add(pushButton2);
        add(pushButton3);

        JPanel buttonsOst = new JPanel();
        buttonsOst.setLayout(new BoxLayout(buttonsOst, BoxLayout.PAGE_AXIS));
        add(pushButton4);
        add(pushButton5);
        add(pushButton6);

        JPanel buttonsNord = new JPanel();
        buttonsNord.setLayout(new BoxLayout(buttonsNord, BoxLayout.PAGE_AXIS));
        add(pushButton7);
        add(pushButton8);
        add(pushButton9);

        JPanel buttonsWest = new JPanel();
        buttonsWest.setLayout(new BoxLayout(buttonsWest, BoxLayout.PAGE_AXIS));
        add(pushButton10);
        add(pushButton11);
        add(pushButton12);

        setLayout(new BorderLayout());
        add(buttonsSued, BorderLayout.SOUTH);
        add(buttonsNord, BorderLayout.NORTH);
        add(buttonsWest, BorderLayout.WEST);
        add(buttonsOst, BorderLayout.EAST);
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new LabyrinthPanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
