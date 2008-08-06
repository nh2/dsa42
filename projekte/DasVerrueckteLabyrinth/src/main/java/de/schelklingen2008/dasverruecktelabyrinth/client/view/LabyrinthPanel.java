package de.schelklingen2008.dasverruecktelabyrinth.client.view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LabyrinthPanel extends JPanel
{

    public LabyrinthPanel(){
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
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        
        JPanel buttonsOst = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        
        JPanel buttonsNord = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        
        JPanel buttonsWest = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        
        
        setLayout(new BorderLayout());
        add()
    }
}
