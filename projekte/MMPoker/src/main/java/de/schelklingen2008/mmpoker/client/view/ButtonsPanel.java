package de.schelklingen2008.mmpoker.client.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.schelklingen2008.mmpoker.client.controller.Controller;

public class ButtonsPanel extends JPanel implements ActionListener {

    private Controller controller;

    public ButtonsPanel(Controller controller) {
        controller = controller;

        JButton betButton = new JButton("bet");
        JButton raiseButton = new JButton("raise");
        JButton foldButton = new JButton("fold");
        JButton checkButton = new JButton("check");
        JTextField betField = new JTextField(controller.getGameContext().getGameModel().autoErgaenzen());
        betField.setMaximumSize(new Dimension(100, 25));
        betField.setPreferredSize(new Dimension(100, 25));
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        add(Box.createHorizontalGlue());
        add(betButton);
        add(Box.createHorizontalStrut(5));
        add(raiseButton);
        add(Box.createHorizontalStrut(5));
        add(foldButton);
        add(Box.createHorizontalStrut(5));
        add(checkButton);
        add(Box.createHorizontalStrut(5));
        add(betField);
        add(Box.createHorizontalStrut(5));

    }

    public void actionPerformed(ActionEvent e) {

    }
}
