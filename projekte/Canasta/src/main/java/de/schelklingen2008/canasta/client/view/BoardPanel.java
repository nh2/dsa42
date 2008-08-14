package de.schelklingen2008.canasta.client.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.schelklingen2008.canasta.client.Constants;
import de.schelklingen2008.canasta.client.controller.Controller;

public class BoardPanel extends JPanel
{

    public BoardPanel(Controller controller)
    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(new Color(Constants.COLOR_OUTLAY));

        JButton outlayButton = new JButton("Outlay");

        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.PAGE_AXIS));
        bp.setBackground(new Color(Constants.COLOR_OUTLAY));
        bp.setBorder(BorderFactory.createEmptyBorder(0, 350, 0, 0));
        bp.add(outlayButton);

        add(new BoardView(controller));
        add(bp);

    }
}
